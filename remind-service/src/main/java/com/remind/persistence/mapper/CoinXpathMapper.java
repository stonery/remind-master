package com.remind.persistence.mapper;


import com.remind.persistence.MyMapper;
import com.remind.persistence.domain.CoinXpath;
import org.springframework.stereotype.Component;

/**
 * UserInfoMapper
 * 
 * @author zhaofeng
 * @since 2018-05-11 17:59
 */
@Component
public interface CoinXpathMapper extends MyMapper<CoinXpath> {

	/**
	 * 随机查询一条数据
	 * 
	 * @return
	 */
	public CoinXpath randOne();
}
