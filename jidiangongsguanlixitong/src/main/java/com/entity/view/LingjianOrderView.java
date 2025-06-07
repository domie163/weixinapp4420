package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LingjianOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 机电零件订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("lingjian_order")
public class LingjianOrderView extends LingjianOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String lingjianOrderValue;

	//级联表 客户
		/**
		* 客户编号
		*/

		@ColumnInfo(comment="客户编号",type="varchar(200)")
		private String kehuUuidNumber;
		/**
		* 客户姓名
		*/

		@ColumnInfo(comment="客户姓名",type="varchar(200)")
		private String kehuName;
		/**
		* 客户手机号
		*/

		@ColumnInfo(comment="客户手机号",type="varchar(200)")
		private String kehuPhone;
		/**
		* 客户身份证号
		*/

		@ColumnInfo(comment="客户身份证号",type="varchar(200)")
		private String kehuIdNumber;
		/**
		* 客户头像
		*/

		@ColumnInfo(comment="客户头像",type="varchar(200)")
		private String kehuPhoto;
		/**
		* 客户邮箱
		*/

		@ColumnInfo(comment="客户邮箱",type="varchar(200)")
		private String kehuEmail;
		/**
		* 现有余额
		*/
		@ColumnInfo(comment="现有余额",type="decimal(10,2)")
		private Double newMoney;
	//级联表 机电零件
		/**
		* 机电零件名称
		*/

		@ColumnInfo(comment="机电零件名称",type="varchar(200)")
		private String lingjianName;
		/**
		* 机电零件编号
		*/

		@ColumnInfo(comment="机电零件编号",type="varchar(200)")
		private String lingjianUuidNumber;
		/**
		* 机电零件照片
		*/

		@ColumnInfo(comment="机电零件照片",type="varchar(200)")
		private String lingjianPhoto;
		/**
		* 机电零件类型
		*/
		@ColumnInfo(comment="机电零件类型",type="int(11)")
		private Integer lingjianTypes;
			/**
			* 机电零件类型的值
			*/
			@ColumnInfo(comment="机电零件类型的字典表值",type="varchar(200)")
			private String lingjianValue;
		/**
		* 机电零件库存
		*/

		@ColumnInfo(comment="机电零件库存",type="int(11)")
		private Integer lingjianKucunNumber;
		/**
		* 现价/积分
		*/
		@ColumnInfo(comment="现价/积分",type="decimal(10,2)")
		private Double lingjianNewMoney;
		/**
		* 机电零件热度
		*/

		@ColumnInfo(comment="机电零件热度",type="int(11)")
		private Integer lingjianClicknum;
		/**
		* 机电零件介绍
		*/

		@ColumnInfo(comment="机电零件介绍",type="longtext")
		private String lingjianContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer lingjianDelete;
	//级联表 用户
		/**
		* 用户编号
		*/

		@ColumnInfo(comment="用户编号",type="varchar(200)")
		private String yonghuUuidNumber;
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public LingjianOrderView() {

	}

	public LingjianOrderView(LingjianOrderEntity lingjianOrderEntity) {
		try {
			BeanUtils.copyProperties(this, lingjianOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getLingjianOrderValue() {
		return lingjianOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setLingjianOrderValue(String lingjianOrderValue) {
		this.lingjianOrderValue = lingjianOrderValue;
	}


	//级联表的get和set 客户

		/**
		* 获取： 客户编号
		*/
		public String getKehuUuidNumber() {
			return kehuUuidNumber;
		}
		/**
		* 设置： 客户编号
		*/
		public void setKehuUuidNumber(String kehuUuidNumber) {
			this.kehuUuidNumber = kehuUuidNumber;
		}

		/**
		* 获取： 客户姓名
		*/
		public String getKehuName() {
			return kehuName;
		}
		/**
		* 设置： 客户姓名
		*/
		public void setKehuName(String kehuName) {
			this.kehuName = kehuName;
		}

		/**
		* 获取： 客户手机号
		*/
		public String getKehuPhone() {
			return kehuPhone;
		}
		/**
		* 设置： 客户手机号
		*/
		public void setKehuPhone(String kehuPhone) {
			this.kehuPhone = kehuPhone;
		}

		/**
		* 获取： 客户身份证号
		*/
		public String getKehuIdNumber() {
			return kehuIdNumber;
		}
		/**
		* 设置： 客户身份证号
		*/
		public void setKehuIdNumber(String kehuIdNumber) {
			this.kehuIdNumber = kehuIdNumber;
		}

		/**
		* 获取： 客户头像
		*/
		public String getKehuPhoto() {
			return kehuPhoto;
		}
		/**
		* 设置： 客户头像
		*/
		public void setKehuPhoto(String kehuPhoto) {
			this.kehuPhoto = kehuPhoto;
		}

		/**
		* 获取： 客户邮箱
		*/
		public String getKehuEmail() {
			return kehuEmail;
		}
		/**
		* 设置： 客户邮箱
		*/
		public void setKehuEmail(String kehuEmail) {
			this.kehuEmail = kehuEmail;
		}

		/**
		* 获取： 现有余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 现有余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}
	//级联表的get和set 机电零件

		/**
		* 获取： 机电零件名称
		*/
		public String getLingjianName() {
			return lingjianName;
		}
		/**
		* 设置： 机电零件名称
		*/
		public void setLingjianName(String lingjianName) {
			this.lingjianName = lingjianName;
		}

		/**
		* 获取： 机电零件编号
		*/
		public String getLingjianUuidNumber() {
			return lingjianUuidNumber;
		}
		/**
		* 设置： 机电零件编号
		*/
		public void setLingjianUuidNumber(String lingjianUuidNumber) {
			this.lingjianUuidNumber = lingjianUuidNumber;
		}

		/**
		* 获取： 机电零件照片
		*/
		public String getLingjianPhoto() {
			return lingjianPhoto;
		}
		/**
		* 设置： 机电零件照片
		*/
		public void setLingjianPhoto(String lingjianPhoto) {
			this.lingjianPhoto = lingjianPhoto;
		}
		/**
		* 获取： 机电零件类型
		*/
		public Integer getLingjianTypes() {
			return lingjianTypes;
		}
		/**
		* 设置： 机电零件类型
		*/
		public void setLingjianTypes(Integer lingjianTypes) {
			this.lingjianTypes = lingjianTypes;
		}


			/**
			* 获取： 机电零件类型的值
			*/
			public String getLingjianValue() {
				return lingjianValue;
			}
			/**
			* 设置： 机电零件类型的值
			*/
			public void setLingjianValue(String lingjianValue) {
				this.lingjianValue = lingjianValue;
			}

		/**
		* 获取： 机电零件库存
		*/
		public Integer getLingjianKucunNumber() {
			return lingjianKucunNumber;
		}
		/**
		* 设置： 机电零件库存
		*/
		public void setLingjianKucunNumber(Integer lingjianKucunNumber) {
			this.lingjianKucunNumber = lingjianKucunNumber;
		}

		/**
		* 获取： 现价/积分
		*/
		public Double getLingjianNewMoney() {
			return lingjianNewMoney;
		}
		/**
		* 设置： 现价/积分
		*/
		public void setLingjianNewMoney(Double lingjianNewMoney) {
			this.lingjianNewMoney = lingjianNewMoney;
		}

		/**
		* 获取： 机电零件热度
		*/
		public Integer getLingjianClicknum() {
			return lingjianClicknum;
		}
		/**
		* 设置： 机电零件热度
		*/
		public void setLingjianClicknum(Integer lingjianClicknum) {
			this.lingjianClicknum = lingjianClicknum;
		}

		/**
		* 获取： 机电零件介绍
		*/
		public String getLingjianContent() {
			return lingjianContent;
		}
		/**
		* 设置： 机电零件介绍
		*/
		public void setLingjianContent(String lingjianContent) {
			this.lingjianContent = lingjianContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getLingjianDelete() {
			return lingjianDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setLingjianDelete(Integer lingjianDelete) {
			this.lingjianDelete = lingjianDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户编号
		*/
		public String getYonghuUuidNumber() {
			return yonghuUuidNumber;
		}
		/**
		* 设置： 用户编号
		*/
		public void setYonghuUuidNumber(String yonghuUuidNumber) {
			this.yonghuUuidNumber = yonghuUuidNumber;
		}

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "LingjianOrderView{" +
			", lingjianOrderValue=" + lingjianOrderValue +
			", lingjianName=" + lingjianName +
			", lingjianUuidNumber=" + lingjianUuidNumber +
			", lingjianPhoto=" + lingjianPhoto +
			", lingjianKucunNumber=" + lingjianKucunNumber +
			", lingjianNewMoney=" + lingjianNewMoney +
			", lingjianClicknum=" + lingjianClicknum +
			", lingjianContent=" + lingjianContent +
			", lingjianDelete=" + lingjianDelete +
			", kehuUuidNumber=" + kehuUuidNumber +
			", kehuName=" + kehuName +
			", kehuPhone=" + kehuPhone +
			", kehuIdNumber=" + kehuIdNumber +
			", kehuPhoto=" + kehuPhoto +
			", kehuEmail=" + kehuEmail +
			", newMoney=" + newMoney +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
