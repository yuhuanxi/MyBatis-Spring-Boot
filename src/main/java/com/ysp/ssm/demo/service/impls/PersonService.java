package com.ysp.ssm.demo.service.impls;

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
    public Person selectById(Long id) {
        return personMapper.selectById(id);
    }
}
