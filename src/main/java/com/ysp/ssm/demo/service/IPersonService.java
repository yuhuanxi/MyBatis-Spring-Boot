package com.ysp.ssm.demo.service;

import com.ysp.ssm.demo.model.Person;

/**
 * Created by yuhuanxi on 16/8/21.
 */
public interface IPersonService {

    long save(Person person);

    Person selectById(Long id);
}
