package com.wang.spbchapter4.mapper;

import com.wang.spbchapter4.domain.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMapper {
    @Select("INSERT INTO users(name,age) VALUES(#{username},#{age})")
    void insertUser(Users user);

}
