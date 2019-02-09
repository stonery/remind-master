package com.remind.mapper;

import com.remind.domain.UserMind;
import com.remind.domain.UserMindExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMindMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    int insert(UserMind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    int insertSelective(UserMind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    List<UserMind> selectByExample(UserMindExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    UserMind selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserMind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_mind
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserMind record);
}