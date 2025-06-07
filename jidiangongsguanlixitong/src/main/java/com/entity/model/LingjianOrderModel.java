package com.entity.model;

import com.entity.LingjianOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 机电零件订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LingjianOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单编号
     */
    private String lingjianOrderUuidNumber;


    /**
     * 机电零件
     */
    private Integer lingjianId;


    /**
     * 客户
     */
    private Integer kehuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double lingjianOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer lingjianOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单编号
	 */
    public String getLingjianOrderUuidNumber() {
        return lingjianOrderUuidNumber;
    }


    /**
	 * 设置：订单编号
	 */
    public void setLingjianOrderUuidNumber(String lingjianOrderUuidNumber) {
        this.lingjianOrderUuidNumber = lingjianOrderUuidNumber;
    }
    /**
	 * 获取：机电零件
	 */
    public Integer getLingjianId() {
        return lingjianId;
    }


    /**
	 * 设置：机电零件
	 */
    public void setLingjianId(Integer lingjianId) {
        this.lingjianId = lingjianId;
    }
    /**
	 * 获取：客户
	 */
    public Integer getKehuId() {
        return kehuId;
    }


    /**
	 * 设置：客户
	 */
    public void setKehuId(Integer kehuId) {
        this.kehuId = kehuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getLingjianOrderTruePrice() {
        return lingjianOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setLingjianOrderTruePrice(Double lingjianOrderTruePrice) {
        this.lingjianOrderTruePrice = lingjianOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getLingjianOrderTypes() {
        return lingjianOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setLingjianOrderTypes(Integer lingjianOrderTypes) {
        this.lingjianOrderTypes = lingjianOrderTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
