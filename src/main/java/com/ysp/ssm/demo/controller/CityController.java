/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ysp.ssm.demo.controller;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import com.ysp.ssm.demo.dto.CityDto;
import com.ysp.ssm.demo.mail.MailUtil;
import com.ysp.ssm.demo.model.City;
import com.ysp.ssm.demo.service.ICityService;
import com.ysp.ssm.demo.util.BaseController;
import com.ysp.ssm.demo.util.PagingDto;
import com.ysp.ssm.demo.util.ReturnCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/cities")
public class CityController extends BaseController {

    private static final Logger LOG = LogManager.getLogger(CityController.class);

    // 从 application.yml 中获取单个值
    @Value("${environments.dev.url}")
    private String url;

    // application.yml 中 my.servers 的值属于 list
    // @Value("${my.servers[0]}")
    @Value("${my.servers[1]}")
    private String server;

    @Autowired
    @Qualifier("javaMailSender")
    JavaMailSender javaMailSender;

    /**
     * 发送简单邮件
     *
     * @throws Exception
     */
    @RequestMapping(value = "/mail")
    public void sendSimpleMail() throws Exception {
        MailUtil.sendSimpleMail(javaMailSender);
    }

    // 这里使用 @Resource也能达到同样效果,@AutoWired 为 Spring 提供的注解,默认按 type 装配
    // @Resource 默认按 name 装配
    // @Resource
    @Autowired
    private ICityService cityService;

    @Autowired
    @Qualifier("cache")
    private Cache cache;

    @RequestMapping
    public BaseAjaxResult getAll(Integer curPage, Integer pageSize) throws TimeoutException, CacheException {

        LOG.info("application.yml 中的 dev url 值为:{}", url);
        LOG.info("application.yml 中的 servers[1] 值为:{}", server);

        long count = cityService.count();

        if (count > 0) {
            PagingDto pagingDto = new PagingDto(curPage, pageSize);
            pagingDto.setCount(count);

            List<City> countryLists = cityService.select(pagingDto.getBeginInt(), pagingDto.getPageSize());
            if (CollectionUtils.isNotEmpty(countryLists))
                return renderJsonAjaxPageResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), countryLists, pagingDto);
        }
        return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
    }

    /**
     * 测试缓存
     * <p>
     * TODO 缓存的一些 annotation 无效,例如 @ReadThroughSingleCache
     *
     * @return
     */
    @RequestMapping(value = "/cache/show")
    public BaseAjaxResult testCache() {

        String test = "";

        try {
            test = cache.get("test", SerializationType.JAVA);
            if (test != null) {
                LOG.info("from cache test:{}", test);
            } else {
                test = "Hello World";
                cache.set("test", 30, test, SerializationType.JAVA);
                LOG.info("set cache test:{}", test);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return renderJsonAjaxResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), test);
    }

    /**
     * 移除缓存
     *
     * @param key 缓存的 key
     * @return
     */
    @RequestMapping(value = "/cache/delete", method = RequestMethod.DELETE)
    public BaseAjaxResult removeCache(String key) {
        try {
            boolean delete = cache.delete(key);
            LOG.info("delete:{}", delete);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
    }

    @RequestMapping(value = "/one")
    public BaseAjaxResult selectById(Long id) {

        if (id == null || id < 1) {
            return renderJsonFail(ReturnCode.PARAMER_NOT_INVALID.getCode(), ReturnCode.PARAMER_NOT_INVALID.getMsg());
        }
        City city = cityService.getById(id);
        if (city != null) {
            try {
                cache.set("city", 300, city, SerializationType.JAVA);
            } catch (TimeoutException | CacheException e) {
                e.printStackTrace();
            }
            return renderJsonAjaxResult(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), city);
        }
        return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
    }

    @RequestMapping(value = "/lists")
    public BaseAjaxResult getAllDto(Integer curPage, Integer pageSize) {

        PagingDto pagingDto = new PagingDto(curPage, pageSize);
        List<CityDto> countryList = cityService.selectDto(pagingDto.getBeginInt(), pagingDto.getPageSize());

        long count = countryList.size();
        pagingDto.setCount(count);
        return renderJsonAjaxPageResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), countryList, pagingDto);
    }

    @RequestMapping(value = "/new")
    public BaseAjaxResult addCity(String name, String state) {

        City city = new City(name, state);

        long ret = cityService.save(city);

        if (ret > 0) {
            LOG.info("insert city id:{}", city.getId());
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }
        return renderJsonFail();
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Long id) {
        ModelMap result = new ModelMap();
        cityService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(City city) {
        ModelMap result = new ModelMap();
        String msg = city.getId() == null ? "新增成功!" : "更新成功!";
        cityService.save(city);
        result.put("city", city);
        result.put("msg", msg);
        return result;
    }

}
