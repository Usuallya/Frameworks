package com.wang.sprintboot_cache.mapper;

import com.wang.sprintboot_cache.domain.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @ ClassName  ：UsersMapper
 * @ Author     ：王海奇
 * @ Date       ：Created in 15:21 2018/11/24
 */
@Mapper
public interface UsersMapper {
    @Select("select * from users where id=#{id}")
    public Users getUsers(Integer id);


    @Update("update users set name=#{param1} where id=#{param2}")
    public Integer updateUsers(String name,Integer id);
}
