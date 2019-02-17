package com.remind.persistence.manager;

import com.remind.persistence.domain.UserMind;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserMindManager {

    public UserMind randOne();


    public List<UserMind> selectUserMindByExample(Example example);

    UserMind selectByPrimaryKey(Long id);
}
