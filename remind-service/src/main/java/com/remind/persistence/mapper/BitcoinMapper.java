package com.remind.persistence.mapper;


import com.remind.persistence.MyMapper;
import com.remind.persistence.domain.Bitcoin;
import org.springframework.stereotype.Component;

/**
 * UserInfoMapper
 * 
 * @author zhaofeng
 * @since 2018-05-11 17:59
 */
@Component
public interface BitcoinMapper extends MyMapper<Bitcoin> {

	/**
	 * 随机查询一条数据
	 * 
	 * @return
	 */
	public Bitcoin randOne();
}
