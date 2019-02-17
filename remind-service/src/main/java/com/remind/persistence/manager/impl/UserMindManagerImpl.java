package com.remind.persistence.manager.impl;

import com.remind.persistence.domain.UserMind;
import com.remind.persistence.manager.UserMindManager;
import com.remind.persistence.mapper.UserMindMapper;
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
public class UserMindManagerImpl implements UserMindManager {

    @Autowired
    private UserMindMapper userMindMapper;

    @Override
    public UserMind randOne() {
        return userMindMapper.randOne();
    }

    @Override
    public List<UserMind> selectUserMindByExample(Example example) {
        return userMindMapper.selectByExample(example);
    }

    @Override
    public UserMind selectByPrimaryKey(Long id) {
        UserMind userMind = new UserMind();
        userMind.setId(id);
//        return userMindMapper.selectByPrimaryKey(id);
        return userMindMapper.selectByPrimaryKey(userMind);
    }
}
