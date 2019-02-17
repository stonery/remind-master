package com.remind.persistence.manager;


import com.remind.persistence.domain.Bitcoin;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * UserInfoManager
 * 
 * @author zhaofeng
 * @since 2018-05-11 18:01
 */
public interface BitcoinManager {

	public Bitcoin randOne();

	public List<Bitcoin> selectBitcoinByExample(Example example);
}
