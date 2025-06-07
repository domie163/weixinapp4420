package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LingjianEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 机电零件
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("lingjian")
public class LingjianView extends LingjianEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 机电零件类型的值
	*/
	@ColumnInfo(comment="机电零件类型的字典表值",type="varchar(200)")
	private String lingjianValue;




	public LingjianView() {

	}

	public LingjianView(LingjianEntity lingjianEntity) {
		try {
			BeanUtils.copyProperties(this, lingjianEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "LingjianView{" +
			", lingjianValue=" + lingjianValue +
			"} " + super.toString();
	}
}
