package com.ysp.ssm.demo.mapper;

import com.ysp.ssm.demo.conf.DevRepository;
import com.ysp.ssm.demo.model.Person;
import com.ysp.ssm.demo.util.IMapper;

/**
 * Created by yuhuanxi on 16/8/21.
 */
// 使用测试环境数据源
@DevRepository
public interface IPersonMapper extends IMapper<Person> {

    Person selectById(Long id);
}
