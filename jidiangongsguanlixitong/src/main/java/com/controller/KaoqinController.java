
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 考勤
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/kaoqin")
public class KaoqinController {
    private static final Logger logger = LoggerFactory.getLogger(KaoqinController.class);

    private static final String TABLE_NAME = "kaoqin";

    @Autowired
    private KaoqinService kaoqinService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private KehuService kehuService;//客户
    @Autowired
    private LingjianService lingjianService;//机电零件
    @Autowired
    private LingjianOrderService lingjianOrderService;//机电零件订单
    @Autowired
    private QingjiaService qingjiaService;//请假
    @Autowired
    private ShebeiService shebeiService;//机电设备
    @Autowired
    private ShebeiOrderService shebeiOrderService;//机电设备订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = kaoqinService.queryPage(params);

        //字典表数据转换
        List<KaoqinView> list =(List<KaoqinView>)page.getList();
        for(KaoqinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        KaoqinEntity kaoqin = kaoqinService.selectById(id);
        if(kaoqin !=null){
            //entity转view
            KaoqinView view = new KaoqinView();
            BeanUtils.copyProperties( kaoqin , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(kaoqin.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody KaoqinEntity kaoqin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,kaoqin:{}",this.getClass().getName(),kaoqin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            kaoqin.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<KaoqinEntity> queryWrapper = new EntityWrapper<KaoqinEntity>()
            .eq("yonghu_id", kaoqin.getYonghuId())
            .eq("kaoqin_types", kaoqin.getKaoqinTypes())
            .eq("kaoqin_time", new SimpleDateFormat("yyyy-MM-dd").format(kaoqin.getKaoqinTime()))
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KaoqinEntity kaoqinEntity = kaoqinService.selectOne(queryWrapper);
        if(kaoqinEntity==null){
            kaoqin.setInsertTime(new Date());
            kaoqin.setCreateTime(new Date());
            kaoqinService.insert(kaoqin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody KaoqinEntity kaoqin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,kaoqin:{}",this.getClass().getName(),kaoqin.toString());
        KaoqinEntity oldKaoqinEntity = kaoqinService.selectById(kaoqin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            kaoqin.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(kaoqin.getKaoqinContent()) || "null".equals(kaoqin.getKaoqinContent())){
                kaoqin.setKaoqinContent(null);
        }

            kaoqinService.updateById(kaoqin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<KaoqinEntity> oldKaoqinList =kaoqinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        kaoqinService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<KaoqinEntity> kaoqinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            KaoqinEntity kaoqinEntity = new KaoqinEntity();
//                            kaoqinEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            kaoqinEntity.setKaoqinTypes(Integer.valueOf(data.get(0)));   //考勤结果 要改的
//                            kaoqinEntity.setKaoqinContent("");//详情和图片
//                            kaoqinEntity.setKaoqinTime(sdf.parse(data.get(0)));          //考勤日期 要改的
//                            kaoqinEntity.setInsertTime(date);//时间
//                            kaoqinEntity.setCreateTime(date);//时间
                            kaoqinList.add(kaoqinEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        kaoqinService.insertBatch(kaoqinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = kaoqinService.queryPage(params);

        //字典表数据转换
        List<KaoqinView> list =(List<KaoqinView>)page.getList();
        for(KaoqinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        KaoqinEntity kaoqin = kaoqinService.selectById(id);
            if(kaoqin !=null){


                //entity转view
                KaoqinView view = new KaoqinView();
                BeanUtils.copyProperties( kaoqin , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(kaoqin.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody KaoqinEntity kaoqin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,kaoqin:{}",this.getClass().getName(),kaoqin.toString());
        Wrapper<KaoqinEntity> queryWrapper = new EntityWrapper<KaoqinEntity>()
            .eq("yonghu_id", kaoqin.getYonghuId())
            .eq("kaoqin_types", kaoqin.getKaoqinTypes())
//            .notIn("kaoqin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KaoqinEntity kaoqinEntity = kaoqinService.selectOne(queryWrapper);
        if(kaoqinEntity==null){
            kaoqin.setInsertTime(new Date());
            kaoqin.setCreateTime(new Date());
        kaoqinService.insert(kaoqin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

