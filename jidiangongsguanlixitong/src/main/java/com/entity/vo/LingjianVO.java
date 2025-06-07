package com.entity.vo;

import com.entity.LingjianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 机电零件
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("lingjian")
public class LingjianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 机电零件名称
     */

    @TableField(value = "lingjian_name")
    private String lingjianName;


    /**
     * 机电零件编号
     */

    @TableField(value = "lingjian_uuid_number")
    private String lingjianUuidNumber;


    /**
     * 机电零件照片
     */

    @TableField(value = "lingjian_photo")
    private String lingjianPhoto;


    /**
     * 机电零件类型
     */

    @TableField(value = "lingjian_types")
    private Integer lingjianTypes;


    /**
     * 机电零件库存
     */

    @TableField(value = "lingjian_kucun_number")
    private Integer lingjianKucunNumber;


    /**
     * 现价/积分
     */

    @TableField(value = "lingjian_new_money")
    private Double lingjianNewMoney;


    /**
     * 机电零件热度
     */

    @TableField(value = "lingjian_clicknum")
    private Integer lingjianClicknum;


    /**
     * 机电零件介绍
     */

    @TableField(value = "lingjian_content")
    private String lingjianContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "lingjian_delete")
    private Integer lingjianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：机电零件名称
	 */
    public String getLingjianName() {
        return lingjianName;
    }


    /**
	 * 获取：机电零件名称
	 */

    public void setLingjianName(String lingjianName) {
        this.lingjianName = lingjianName;
    }
    /**
	 * 设置：机电零件编号
	 */
    public String getLingjianUuidNumber() {
        return lingjianUuidNumber;
    }


    /**
	 * 获取：机电零件编号
	 */

    public void setLingjianUuidNumber(String lingjianUuidNumber) {
        this.lingjianUuidNumber = lingjianUuidNumber;
    }
    /**
	 * 设置：机电零件照片
	 */
    public String getLingjianPhoto() {
        return lingjianPhoto;
    }


    /**
	 * 获取：机电零件照片
	 */

    public void setLingjianPhoto(String lingjianPhoto) {
        this.lingjianPhoto = lingjianPhoto;
    }
    /**
	 * 设置：机电零件类型
	 */
    public Integer getLingjianTypes() {
        return lingjianTypes;
    }


    /**
	 * 获取：机电零件类型
	 */

    public void setLingjianTypes(Integer lingjianTypes) {
        this.lingjianTypes = lingjianTypes;
    }
    /**
	 * 设置：机电零件库存
	 */
    public Integer getLingjianKucunNumber() {
        return lingjianKucunNumber;
    }


    /**
	 * 获取：机电零件库存
	 */

    public void setLingjianKucunNumber(Integer lingjianKucunNumber) {
        this.lingjianKucunNumber = lingjianKucunNumber;
    }
    /**
	 * 设置：现价/积分
	 */
    public Double getLingjianNewMoney() {
        return lingjianNewMoney;
    }


    /**
	 * 获取：现价/积分
	 */

    public void setLingjianNewMoney(Double lingjianNewMoney) {
        this.lingjianNewMoney = lingjianNewMoney;
    }
    /**
	 * 设置：机电零件热度
	 */
    public Integer getLingjianClicknum() {
        return lingjianClicknum;
    }


    /**
	 * 获取：机电零件热度
	 */

    public void setLingjianClicknum(Integer lingjianClicknum) {
        this.lingjianClicknum = lingjianClicknum;
    }
    /**
	 * 设置：机电零件介绍
	 */
    public String getLingjianContent() {
        return lingjianContent;
    }


    /**
	 * 获取：机电零件介绍
	 */

    public void setLingjianContent(String lingjianContent) {
        this.lingjianContent = lingjianContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getLingjianDelete() {
        return lingjianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setLingjianDelete(Integer lingjianDelete) {
        this.lingjianDelete = lingjianDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
