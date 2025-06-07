package com.entity.vo;

import com.entity.LingjianOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 机电零件订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("lingjian_order")
public class LingjianOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "lingjian_order_uuid_number")
    private String lingjianOrderUuidNumber;


    /**
     * 机电零件
     */

    @TableField(value = "lingjian_id")
    private Integer lingjianId;


    /**
     * 客户
     */

    @TableField(value = "kehu_id")
    private Integer kehuId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "lingjian_order_true_price")
    private Double lingjianOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "lingjian_order_types")
    private Integer lingjianOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单编号
	 */
    public String getLingjianOrderUuidNumber() {
        return lingjianOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setLingjianOrderUuidNumber(String lingjianOrderUuidNumber) {
        this.lingjianOrderUuidNumber = lingjianOrderUuidNumber;
    }
    /**
	 * 设置：机电零件
	 */
    public Integer getLingjianId() {
        return lingjianId;
    }


    /**
	 * 获取：机电零件
	 */

    public void setLingjianId(Integer lingjianId) {
        this.lingjianId = lingjianId;
    }
    /**
	 * 设置：客户
	 */
    public Integer getKehuId() {
        return kehuId;
    }


    /**
	 * 获取：客户
	 */

    public void setKehuId(Integer kehuId) {
        this.kehuId = kehuId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getLingjianOrderTruePrice() {
        return lingjianOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setLingjianOrderTruePrice(Double lingjianOrderTruePrice) {
        this.lingjianOrderTruePrice = lingjianOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getLingjianOrderTypes() {
        return lingjianOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setLingjianOrderTypes(Integer lingjianOrderTypes) {
        this.lingjianOrderTypes = lingjianOrderTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
