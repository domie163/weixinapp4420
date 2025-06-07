package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShebeiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 机电设备订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shebei_order")
public class ShebeiOrderView extends ShebeiOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String shebeiOrderValue;

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
	//级联表 机电设备
		/**
		* 机电设备名称
		*/

		@ColumnInfo(comment="机电设备名称",type="varchar(200)")
		private String shebeiName;
		/**
		* 机电设备编号
		*/

		@ColumnInfo(comment="机电设备编号",type="varchar(200)")
		private String shebeiUuidNumber;
		/**
		* 机电设备照片
		*/

		@ColumnInfo(comment="机电设备照片",type="varchar(200)")
		private String shebeiPhoto;
		/**
		* 机电设备类型
		*/
		@ColumnInfo(comment="机电设备类型",type="int(11)")
		private Integer shebeiTypes;
			/**
			* 机电设备类型的值
			*/
			@ColumnInfo(comment="机电设备类型的字典表值",type="varchar(200)")
			private String shebeiValue;
		/**
		* 机电设备库存
		*/

		@ColumnInfo(comment="机电设备库存",type="int(11)")
		private Integer shebeiKucunNumber;
		/**
		* 现价/积分
		*/
		@ColumnInfo(comment="现价/积分",type="decimal(10,2)")
		private Double shebeiNewMoney;
		/**
		* 机电设备热度
		*/

		@ColumnInfo(comment="机电设备热度",type="int(11)")
		private Integer shebeiClicknum;
		/**
		* 机电设备介绍
		*/

		@ColumnInfo(comment="机电设备介绍",type="longtext")
		private String shebeiContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer shebeiDelete;
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


	public ShebeiOrderView() {

	}

	public ShebeiOrderView(ShebeiOrderEntity shebeiOrderEntity) {
		try {
			BeanUtils.copyProperties(this, shebeiOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getShebeiOrderValue() {
		return shebeiOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setShebeiOrderValue(String shebeiOrderValue) {
		this.shebeiOrderValue = shebeiOrderValue;
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
	//级联表的get和set 机电设备

		/**
		* 获取： 机电设备名称
		*/
		public String getShebeiName() {
			return shebeiName;
		}
		/**
		* 设置： 机电设备名称
		*/
		public void setShebeiName(String shebeiName) {
			this.shebeiName = shebeiName;
		}

		/**
		* 获取： 机电设备编号
		*/
		public String getShebeiUuidNumber() {
			return shebeiUuidNumber;
		}
		/**
		* 设置： 机电设备编号
		*/
		public void setShebeiUuidNumber(String shebeiUuidNumber) {
			this.shebeiUuidNumber = shebeiUuidNumber;
		}

		/**
		* 获取： 机电设备照片
		*/
		public String getShebeiPhoto() {
			return shebeiPhoto;
		}
		/**
		* 设置： 机电设备照片
		*/
		public void setShebeiPhoto(String shebeiPhoto) {
			this.shebeiPhoto = shebeiPhoto;
		}
		/**
		* 获取： 机电设备类型
		*/
		public Integer getShebeiTypes() {
			return shebeiTypes;
		}
		/**
		* 设置： 机电设备类型
		*/
		public void setShebeiTypes(Integer shebeiTypes) {
			this.shebeiTypes = shebeiTypes;
		}


			/**
			* 获取： 机电设备类型的值
			*/
			public String getShebeiValue() {
				return shebeiValue;
			}
			/**
			* 设置： 机电设备类型的值
			*/
			public void setShebeiValue(String shebeiValue) {
				this.shebeiValue = shebeiValue;
			}

		/**
		* 获取： 机电设备库存
		*/
		public Integer getShebeiKucunNumber() {
			return shebeiKucunNumber;
		}
		/**
		* 设置： 机电设备库存
		*/
		public void setShebeiKucunNumber(Integer shebeiKucunNumber) {
			this.shebeiKucunNumber = shebeiKucunNumber;
		}

		/**
		* 获取： 现价/积分
		*/
		public Double getShebeiNewMoney() {
			return shebeiNewMoney;
		}
		/**
		* 设置： 现价/积分
		*/
		public void setShebeiNewMoney(Double shebeiNewMoney) {
			this.shebeiNewMoney = shebeiNewMoney;
		}

		/**
		* 获取： 机电设备热度
		*/
		public Integer getShebeiClicknum() {
			return shebeiClicknum;
		}
		/**
		* 设置： 机电设备热度
		*/
		public void setShebeiClicknum(Integer shebeiClicknum) {
			this.shebeiClicknum = shebeiClicknum;
		}

		/**
		* 获取： 机电设备介绍
		*/
		public String getShebeiContent() {
			return shebeiContent;
		}
		/**
		* 设置： 机电设备介绍
		*/
		public void setShebeiContent(String shebeiContent) {
			this.shebeiContent = shebeiContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getShebeiDelete() {
			return shebeiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setShebeiDelete(Integer shebeiDelete) {
			this.shebeiDelete = shebeiDelete;
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
		return "ShebeiOrderView{" +
			", shebeiOrderValue=" + shebeiOrderValue +
			", shebeiName=" + shebeiName +
			", shebeiUuidNumber=" + shebeiUuidNumber +
			", shebeiPhoto=" + shebeiPhoto +
			", shebeiKucunNumber=" + shebeiKucunNumber +
			", shebeiNewMoney=" + shebeiNewMoney +
			", shebeiClicknum=" + shebeiClicknum +
			", shebeiContent=" + shebeiContent +
			", shebeiDelete=" + shebeiDelete +
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
