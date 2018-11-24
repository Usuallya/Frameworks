package com.wang.spbchapter4.repository;

import com.wang.spbchapter4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//JPA操作数据库的Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
