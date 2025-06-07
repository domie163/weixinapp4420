
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
 * 机电零件
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lingjian")
public class LingjianController {
    private static final Logger logger = LoggerFactory.getLogger(LingjianController.class);

    private static final String TABLE_NAME = "lingjian";

    @Autowired
    private LingjianService lingjianService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private KaoqinService kaoqinService;//考勤
    @Autowired
    private KehuService kehuService;//客户
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
        params.put("lingjianDeleteStart",1);params.put("lingjianDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = lingjianService.queryPage(params);

        //字典表数据转换
        List<LingjianView> list =(List<LingjianView>)page.getList();
        for(LingjianView c:list){
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
        LingjianEntity lingjian = lingjianService.selectById(id);
        if(lingjian !=null){
            //entity转view
            LingjianView view = new LingjianView();
            BeanUtils.copyProperties( lingjian , view );//把实体数据重构到view中
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
    public R save(@RequestBody LingjianEntity lingjian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lingjian:{}",this.getClass().getName(),lingjian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<LingjianEntity> queryWrapper = new EntityWrapper<LingjianEntity>()
            .eq("lingjian_name", lingjian.getLingjianName())
            .eq("lingjian_types", lingjian.getLingjianTypes())
            .eq("lingjian_kucun_number", lingjian.getLingjianKucunNumber())
            .eq("lingjian_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LingjianEntity lingjianEntity = lingjianService.selectOne(queryWrapper);
        if(lingjianEntity==null){
            lingjian.setLingjianClicknum(1);
            lingjian.setLingjianDelete(1);
            lingjian.setInsertTime(new Date());
            lingjian.setCreateTime(new Date());
            lingjianService.insert(lingjian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LingjianEntity lingjian, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lingjian:{}",this.getClass().getName(),lingjian.toString());
        LingjianEntity oldLingjianEntity = lingjianService.selectById(lingjian.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(lingjian.getLingjianPhoto()) || "null".equals(lingjian.getLingjianPhoto())){
                lingjian.setLingjianPhoto(null);
        }
        if("".equals(lingjian.getLingjianContent()) || "null".equals(lingjian.getLingjianContent())){
                lingjian.setLingjianContent(null);
        }

            lingjianService.updateById(lingjian);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LingjianEntity> oldLingjianList =lingjianService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<LingjianEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LingjianEntity lingjianEntity = new LingjianEntity();
            lingjianEntity.setId(id);
            lingjianEntity.setLingjianDelete(2);
            list.add(lingjianEntity);
        }
        if(list != null && list.size() >0){
            lingjianService.updateBatchById(list);
        }

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
            List<LingjianEntity> lingjianList = new ArrayList<>();//上传的东西
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
                            LingjianEntity lingjianEntity = new LingjianEntity();
//                            lingjianEntity.setLingjianName(data.get(0));                    //机电零件名称 要改的
//                            lingjianEntity.setLingjianUuidNumber(data.get(0));                    //机电零件编号 要改的
//                            lingjianEntity.setLingjianPhoto("");//详情和图片
//                            lingjianEntity.setLingjianTypes(Integer.valueOf(data.get(0)));   //机电零件类型 要改的
//                            lingjianEntity.setLingjianKucunNumber(Integer.valueOf(data.get(0)));   //机电零件库存 要改的
//                            lingjianEntity.setLingjianNewMoney(data.get(0));                    //现价/积分 要改的
//                            lingjianEntity.setLingjianClicknum(Integer.valueOf(data.get(0)));   //机电零件热度 要改的
//                            lingjianEntity.setLingjianContent("");//详情和图片
//                            lingjianEntity.setLingjianDelete(1);//逻辑删除字段
//                            lingjianEntity.setInsertTime(date);//时间
//                            lingjianEntity.setCreateTime(date);//时间
                            lingjianList.add(lingjianEntity);


                            //把要查询是否重复的字段放入map中
                                //机电零件编号
                                if(seachFields.containsKey("lingjianUuidNumber")){
                                    List<String> lingjianUuidNumber = seachFields.get("lingjianUuidNumber");
                                    lingjianUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> lingjianUuidNumber = new ArrayList<>();
                                    lingjianUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("lingjianUuidNumber",lingjianUuidNumber);
                                }
                        }

                        //查询是否重复
                         //机电零件编号
                        List<LingjianEntity> lingjianEntities_lingjianUuidNumber = lingjianService.selectList(new EntityWrapper<LingjianEntity>().in("lingjian_uuid_number", seachFields.get("lingjianUuidNumber")).eq("lingjian_delete", 1));
                        if(lingjianEntities_lingjianUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LingjianEntity s:lingjianEntities_lingjianUuidNumber){
                                repeatFields.add(s.getLingjianUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [机电零件编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        lingjianService.insertBatch(lingjianList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<LingjianView> returnLingjianViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("lingjianYesnoTypes",2);
        PageUtils pageUtils = lingjianOrderService.queryPage(params1);
        List<LingjianOrderView> orderViewsList =(List<LingjianOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(LingjianOrderView orderView:orderViewsList){
            Integer lingjianTypes = orderView.getLingjianTypes();
            if(typeMap.containsKey(lingjianTypes)){
                typeMap.put(lingjianTypes,typeMap.get(lingjianTypes)+1);
            }else{
                typeMap.put(lingjianTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("lingjianTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("lingjianYesnoTypes",2);
            PageUtils pageUtils1 = lingjianService.queryPage(params2);
            List<LingjianView> lingjianViewList =(List<LingjianView>)pageUtils1.getList();
            returnLingjianViewList.addAll(lingjianViewList);
            if(returnLingjianViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("lingjianYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = lingjianService.queryPage(params);
        if(returnLingjianViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnLingjianViewList.size();//要添加的数量
            List<LingjianView> lingjianViewList =(List<LingjianView>)page.getList();
            for(LingjianView lingjianView:lingjianViewList){
                Boolean addFlag = true;
                for(LingjianView returnLingjianView:returnLingjianViewList){
                    if(returnLingjianView.getId().intValue() ==lingjianView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnLingjianViewList.add(lingjianView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnLingjianViewList = returnLingjianViewList.subList(0, limit);
        }

        for(LingjianView c:returnLingjianViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnLingjianViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = lingjianService.queryPage(params);

        //字典表数据转换
        List<LingjianView> list =(List<LingjianView>)page.getList();
        for(LingjianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LingjianEntity lingjian = lingjianService.selectById(id);
            if(lingjian !=null){

                //点击数量加1
                lingjian.setLingjianClicknum(lingjian.getLingjianClicknum()+1);
                lingjianService.updateById(lingjian);

                //entity转view
                LingjianView view = new LingjianView();
                BeanUtils.copyProperties( lingjian , view );//把实体数据重构到view中

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
    public R add(@RequestBody LingjianEntity lingjian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,lingjian:{}",this.getClass().getName(),lingjian.toString());
        Wrapper<LingjianEntity> queryWrapper = new EntityWrapper<LingjianEntity>()
            .eq("lingjian_name", lingjian.getLingjianName())
            .eq("lingjian_uuid_number", lingjian.getLingjianUuidNumber())
            .eq("lingjian_types", lingjian.getLingjianTypes())
            .eq("lingjian_kucun_number", lingjian.getLingjianKucunNumber())
            .eq("lingjian_clicknum", lingjian.getLingjianClicknum())
            .eq("lingjian_delete", lingjian.getLingjianDelete())
//            .notIn("lingjian_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LingjianEntity lingjianEntity = lingjianService.selectOne(queryWrapper);
        if(lingjianEntity==null){
            lingjian.setLingjianClicknum(1);
            lingjian.setLingjianDelete(1);
            lingjian.setInsertTime(new Date());
            lingjian.setCreateTime(new Date());
        lingjianService.insert(lingjian);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

