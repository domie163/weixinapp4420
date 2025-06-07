package com.dao;

import com.entity.LingjianOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LingjianOrderView;

/**
 * 机电零件订单 Dao 接口
 *
 * @author 
 */
public interface LingjianOrderDao extends BaseMapper<LingjianOrderEntity> {

   List<LingjianOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
