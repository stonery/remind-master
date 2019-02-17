package com.remind.persistence.manager.impl;

import com.remind.persistence.domain.Bitcoin;
import com.remind.persistence.manager.BitcoinManager;
import com.remind.persistence.mapper.BitcoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * UserInfoManager
 *
 * @author zhaofeng
 * @since 2018-05-11 18:01
 */
@Repository
public class BitcoinManagerImpl implements BitcoinManager {

    @Autowired
    private BitcoinMapper bitcoinMapper;

    @Override
    public Bitcoin randOne() {
        return bitcoinMapper.randOne();
    }

    @Override
    public List<Bitcoin> selectBitcoinByExample(Example example) {
        return bitcoinMapper.selectByExample(example);
    }
}
