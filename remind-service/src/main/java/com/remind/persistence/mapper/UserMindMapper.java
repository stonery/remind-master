package com.remind.persistence.mapper;

import com.remind.persistence.MyMapper;
import com.remind.persistence.domain.UserMind;
import org.springframework.stereotype.Component;

@Component
public interface UserMindMapper  extends MyMapper<UserMind> {

    public UserMind randOne();
}