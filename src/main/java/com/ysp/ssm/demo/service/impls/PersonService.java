package com.ysp.ssm.demo.service.impls;

import com.ysp.ssm.demo.mapper.IPersonMapper;
import com.ysp.ssm.demo.model.Person;
import com.ysp.ssm.demo.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuhuanxi on 16/8/21.
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonMapper personMapper;

    @Override
    public long save(Person person) {
        return personMapper.insert(person);
    }
}
