package com.remind.persistence.manager.impl;

import com.remind.persistence.domain.CoinXpath;
import com.remind.persistence.manager.CoinXpathManager;
import com.remind.persistence.mapper.CoinXpathMapper;
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
public class CoinXpathManagerImpl implements CoinXpathManager {

    @Autowired
    private CoinXpathMapper coinXpathMapper;

    @Override
    public CoinXpath randOne() {
        return coinXpathMapper.randOne();
    }

    @Override
    public List<CoinXpath> selectCoinXpathByExample(Example example) {
        return coinXpathMapper.selectByExample(example);
    }
}
