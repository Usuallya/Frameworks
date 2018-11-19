package cn.bupt.mybatis.service;

import cn.bupt.mybatis.domain.People;

import java.util.List;

public interface IPeopleService {
    List<People> show()throws Exception;
}
