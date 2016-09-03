package com.ysp.ssm.demo.controller;

import com.ysp.ssm.demo.model.Person;
import com.ysp.ssm.demo.service.IPersonService;
import com.ysp.ssm.demo.util.BaseController;
import com.ysp.ssm.demo.util.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shipeng.yu
 * @Time: 2016年09月03日 下午9:59
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @RequestMapping(value = "/new")
    public BaseAjaxResult addCity(String name, Integer age, String address) {

        Person person = new Person(name, age, address);

        long ret = personService.save(person);

        LOG.info("insert city id:{}", person.getId());

        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }

        return renderJsonFail();
    }

    @RequestMapping(value = "/{id}")
    public BaseAjaxResult selectById(@PathVariable("id") Long id) {

        Person person = personService.selectById(id);

        if (person != null) {
            LOG.info("insert city id:{}", person.getId());
            return renderJsonAjaxResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), person);
        }

        return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
    }
}
