package com.remind.persistence.manager;

import com.remind.persistence.domain.CoinXpath;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface CoinXpathManager {

    public CoinXpath randOne();


    public List<CoinXpath> selectCoinXpathByExample(Example example);
}
