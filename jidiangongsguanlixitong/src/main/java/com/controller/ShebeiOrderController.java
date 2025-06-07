
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
 * 机电设备订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shebeiOrder")
public class ShebeiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ShebeiOrderController.class);

    private static final String TABLE_NAME = "shebeiOrder";

    @Autowired
    private ShebeiOrderService shebeiOrderService;


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
    private LingjianService lingjianService;//机电零件
    @Autowired
    private LingjianOrderService lingjianOrderService;//机电零件订单
    @Autowired
    private QingjiaService qingjiaService;//请假
    @Autowired
    private ShebeiService shebeiService;//机电设备
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
        PageUtils page = shebeiOrderService.queryPage(params);

        //字典表数据转换
        List<ShebeiOrderView> list =(List<ShebeiOrderView>)page.getList();
        for(ShebeiOrderView c:list){
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
        ShebeiOrderEntity shebeiOrder = shebeiOrderService.selectById(id);
        if(shebeiOrder !=null){
            //entity转view
            ShebeiOrderView view = new ShebeiOrderView();
            BeanUtils.copyProperties( shebeiOrder , view );//把实体数据重构到view中
            //级联表 机电设备
            //级联表
            ShebeiEntity shebei = shebeiService.selectById(shebeiOrder.getShebeiId());
            if(shebei != null){
            BeanUtils.copyProperties( shebei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setShebeiId(shebei.getId());
            }
            //级联表 客户
            //级联表
            KehuEntity kehu = kehuService.selectById(shebeiOrder.getKehuId());
            if(kehu != null){
            BeanUtils.copyProperties( kehu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setKehuId(kehu.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shebeiOrder.getYonghuId());
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
    public R save(@RequestBody ShebeiOrderEntity shebeiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shebeiOrder:{}",this.getClass().getName(),shebeiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            shebeiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        shebeiOrder.setCreateTime(new Date());
        shebeiOrder.setInsertTime(new Date());
        shebeiOrderService.insert(shebeiOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShebeiOrderEntity shebeiOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,shebeiOrder:{}",this.getClass().getName(),shebeiOrder.toString());
        ShebeiOrderEntity oldShebeiOrderEntity = shebeiOrderService.selectById(shebeiOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            shebeiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            shebeiOrderService.updateById(shebeiOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ShebeiOrderEntity> oldShebeiOrderList =shebeiOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        shebeiOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<ShebeiOrderEntity> shebeiOrderList = new ArrayList<>();//上传的东西
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
                            ShebeiOrderEntity shebeiOrderEntity = new ShebeiOrderEntity();
//                            shebeiOrderEntity.setShebeiOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            shebeiOrderEntity.setShebeiId(Integer.valueOf(data.get(0)));   //机电设备 要改的
//                            shebeiOrderEntity.setKehuId(Integer.valueOf(data.get(0)));   //客户 要改的
//                            shebeiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            shebeiOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            shebeiOrderEntity.setShebeiOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            shebeiOrderEntity.setShebeiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            shebeiOrderEntity.setInsertTime(date);//时间
//                            shebeiOrderEntity.setCreateTime(date);//时间
                            shebeiOrderList.add(shebeiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("shebeiOrderUuidNumber")){
                                    List<String> shebeiOrderUuidNumber = seachFields.get("shebeiOrderUuidNumber");
                                    shebeiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shebeiOrderUuidNumber = new ArrayList<>();
                                    shebeiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shebeiOrderUuidNumber",shebeiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<ShebeiOrderEntity> shebeiOrderEntities_shebeiOrderUuidNumber = shebeiOrderService.selectList(new EntityWrapper<ShebeiOrderEntity>().in("shebei_order_uuid_number", seachFields.get("shebeiOrderUuidNumber")));
                        if(shebeiOrderEntities_shebeiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShebeiOrderEntity s:shebeiOrderEntities_shebeiOrderUuidNumber){
                                repeatFields.add(s.getShebeiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shebeiOrderService.insertBatch(shebeiOrderList);
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
        PageUtils page = shebeiOrderService.queryPage(params);

        //字典表数据转换
        List<ShebeiOrderView> list =(List<ShebeiOrderView>)page.getList();
        for(ShebeiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShebeiOrderEntity shebeiOrder = shebeiOrderService.selectById(id);
            if(shebeiOrder !=null){


                //entity转view
                ShebeiOrderView view = new ShebeiOrderView();
                BeanUtils.copyProperties( shebeiOrder , view );//把实体数据重构到view中

                //级联表
                    ShebeiEntity shebei = shebeiService.selectById(shebeiOrder.getShebeiId());
                if(shebei != null){
                    BeanUtils.copyProperties( shebei , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShebeiId(shebei.getId());
                }
                //级联表
                    KehuEntity kehu = kehuService.selectById(shebeiOrder.getKehuId());
                if(kehu != null){
                    BeanUtils.copyProperties( kehu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setKehuId(kehu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(shebeiOrder.getYonghuId());
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
    public R add(@RequestBody ShebeiOrderEntity shebeiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,shebeiOrder:{}",this.getClass().getName(),shebeiOrder.toString());
            ShebeiEntity shebeiEntity = shebeiService.selectById(shebeiOrder.getShebeiId());
            if(shebeiEntity == null){
                return R.error(511,"查不到该机电设备");
            }
            // Double shebeiNewMoney = shebeiEntity.getShebeiNewMoney();

            if(false){
            }
            else if(shebeiEntity.getShebeiNewMoney() == null){
                return R.error(511,"现价/积分不能为空");
            }
            else if((shebeiEntity.getShebeiKucunNumber() -shebeiOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
//            if(yonghuEntity.getNewMoney() == null)
//                return R.error(511,"用户金额不能为空");
//            double balance = yonghuEntity.getNewMoney() - shebeiEntity.getShebeiNewMoney()*shebeiOrder.getBuyNumber();//余额
//            if(balance<0)
//                return R.error(511,"余额不够支付");
            shebeiOrder.setShebeiOrderTypes(101); //设置订单状态为已支付
            shebeiOrder.setShebeiOrderTruePrice(0.0); //设置实付价格
            shebeiOrder.setYonghuId(userId); //设置订单支付人id
            shebeiOrder.setShebeiOrderUuidNumber(String.valueOf(new Date().getTime()));
            shebeiOrder.setInsertTime(new Date());
            shebeiOrder.setCreateTime(new Date());
                shebeiEntity.setShebeiKucunNumber( shebeiEntity.getShebeiKucunNumber() -shebeiOrder.getBuyNumber());
                shebeiService.updateById(shebeiEntity);
                shebeiOrderService.insert(shebeiOrder);//新增订单


            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String shebeiOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");

            Integer kehuId = Integer.valueOf(String.valueOf(params.get("kehuId")));//客户
        KehuEntity kehuEntity = kehuService.selectById(kehuId);

        String data = String.valueOf(params.get("shebeis"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> shebeis = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<ShebeiOrderEntity> shebeiOrderList = new ArrayList<>();
        //商品表
        List<ShebeiEntity> shebeiList = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : shebeis) {
           //取值
            Integer shebeiId = Integer.valueOf(String.valueOf(map.get("shebeiId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            ShebeiEntity shebeiEntity = shebeiService.selectById(shebeiId);//购买的商品
            String id = String.valueOf(map.get("id"));

            //判断商品的库存是否足够
            if(shebeiEntity.getShebeiKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(shebeiEntity.getShebeiName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                shebeiEntity.setShebeiKucunNumber(shebeiEntity.getShebeiKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            ShebeiOrderEntity shebeiOrderEntity = new ShebeiOrderEntity<>();

            //赋值订单信息
            shebeiOrderEntity.setShebeiOrderUuidNumber(shebeiOrderUuidNumber);//订单编号
            shebeiOrderEntity.setShebeiId(shebeiId);//机电设备
                        shebeiOrderEntity.setYonghuId(userId);//用户

            shebeiOrderEntity.setKehuId(kehuId);
            shebeiOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            shebeiOrderEntity.setShebeiOrderTypes(101);//订单类型
            shebeiOrderEntity.setInsertTime(new Date());//订单创建时间
            shebeiOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
//            if(shebeiOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(shebeiEntity.getShebeiNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(kehuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;


                    shebeiOrderEntity.setShebeiOrderTruePrice(money);

                }
//            }
            shebeiOrderList.add(shebeiOrderEntity);
            shebeiList.add(shebeiEntity);

        }
        shebeiOrderService.insertBatch(shebeiOrderList);
        shebeiService.updateBatchById(shebeiList);
        yonghuService.updateById(yonghuEntity);

        return R.ok();
    }


    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            ShebeiOrderEntity shebeiOrder = shebeiOrderService.selectById(id);//当前表service
            Integer buyNumber = shebeiOrder.getBuyNumber();
            Integer shebeiId = shebeiOrder.getShebeiId();
            if(shebeiId == null)
                return R.error(511,"查不到该机电设备");
            ShebeiEntity shebeiEntity = shebeiService.selectById(shebeiId);
            if(shebeiEntity == null)
                return R.error(511,"查不到该机电设备");
            Double shebeiNewMoney = shebeiEntity.getShebeiNewMoney();
            if(shebeiNewMoney == null)
                return R.error(511,"机电设备价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;

                //计算金额
                Double money = shebeiEntity.getShebeiNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;



            shebeiEntity.setShebeiKucunNumber(shebeiEntity.getShebeiKucunNumber() + buyNumber);

            shebeiOrder.setShebeiOrderTypes(102);//设置订单状态为已退款
            shebeiOrderService.updateAllColumnById(shebeiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            shebeiService.updateById(shebeiEntity);//更新订单中机电设备的信息

            return R.ok();
    }

    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ShebeiOrderEntity  shebeiOrderEntity = shebeiOrderService.selectById(id);
        shebeiOrderEntity.setShebeiOrderTypes(104);//设置订单状态为收货
        shebeiOrderService.updateById( shebeiOrderEntity);
        return R.ok();
    }

}

