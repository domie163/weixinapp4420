package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ShebeiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 机电设备
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("shebei")
public class ShebeiView extends ShebeiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 机电设备类型的值
	*/
	@ColumnInfo(comment="机电设备类型的字典表值",type="varchar(200)")
	private String shebeiValue;




	public ShebeiView() {

	}

	public ShebeiView(ShebeiEntity shebeiEntity) {
		try {
			BeanUtils.copyProperties(this, shebeiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "ShebeiView{" +
			", shebeiValue=" + shebeiValue +
			"} " + super.toString();
	}
}
