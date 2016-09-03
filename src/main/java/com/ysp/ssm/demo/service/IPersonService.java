package com.ysp.ssm.demo.service;

import com.ysp.ssm.demo.model.Person;

public interface IPersonService {

    long save(Person person);

    Person selectById(Long id);
}
