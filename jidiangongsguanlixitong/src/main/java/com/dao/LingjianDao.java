package com.dao;

import com.entity.LingjianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LingjianView;

/**
 * 机电零件 Dao 接口
 *
 * @author 
 */
public interface LingjianDao extends BaseMapper<LingjianEntity> {

   List<LingjianView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
