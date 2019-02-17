package com.remind.persistence;


import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import tk.mybatis.mapper.common.rowbounds.SelectRowBoundsMapper;

import java.util.List;
import java.util.Map;

/**
 * 自己的MyMapper
 * 
 * @author zhaofeng
 * @since 2018-05-11 18:49
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, SelectRowBoundsMapper<T>,
		SelectByExampleRowBoundsMapper<T>, IdsMapper<T>, ConditionMapper<T> {

	public List<Map<String, Object>> selectBySQL1(String sql);

	public List<Map<String, Object>> selectBySQL(String sql);

	public List<Map<String, Object>> selectTableInfo(String tableName);

}
