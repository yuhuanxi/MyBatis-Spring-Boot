package com.ysp.ssm.demo.controller;

import com.ysp.ssm.demo.model.Person;
import com.ysp.ssm.demo.service.IPersonService;
import com.ysp.ssm.demo.util.BaseController;
import com.ysp.ssm.demo.util.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuhuanxi on 16/8/21.
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/new")
    public BaseAjaxResult addCity(String name, Integer age, String addrs) {

        Person person = new Person(name, age, addrs);
        long ret = personService.save(person);
        LOG.info("insert city id:{}", person.getId());
        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }
        return renderJsonFail();
    }

}
