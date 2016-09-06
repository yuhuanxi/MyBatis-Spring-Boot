package com.ysp.ssm.demo.service.impls;

import com.google.code.ssm.api.ReadThroughSingleCache;
import com.ysp.ssm.demo.mapper.IPersonMapper;
import com.ysp.ssm.demo.model.Person;
import com.ysp.ssm.demo.service.IPersonService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonMapper personMapper;

    @Override
    public long save(Person person) {
        return personMapper.insert(person);
    }

    @Override
    @ReadThroughSingleCache(namespace = "demo", expiration = 30000)
    public Person selectById(Long id) {
        System.out.println("person 缓存未命中");
        return personMapper.selectById(id);
    }
}
