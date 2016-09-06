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

package com.ysp.ssm.demo.service.impls;

import com.google.code.ssm.api.ReadThroughSingleCache;
import com.ysp.ssm.demo.dto.CityDto;
import com.ysp.ssm.demo.mapper.ICityMapper;
import com.ysp.ssm.demo.model.City;
import com.ysp.ssm.demo.service.ICityService;
import com.ysp.ssm.demo.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityService implements ICityService {

    private static final Logger LOG = LogManager.getLogger(CityService.class);

    @Autowired
    private ICityMapper cityMapper;

    @Autowired  // service 层调用 service 比较好,便于后期维护
    private IPersonService personService;

    @Override
    @ReadThroughSingleCache(namespace = "demo", expiration = 30000)
    public City getById(Long id) {
        System.out.println("没有缓存命中");
        LOG.info(personService.selectById(15L));
        return cityMapper.selectById(id);
    }

    @Override
    public long deleteById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return cityMapper.deleteById(id);
    }

    @Override
    public long save(City city) {
        return cityMapper.insert(city);
//        cityMapper.insert(new City("AA", "AA"));
//        cityMapper.insert(new City("BB", "BB"));
//        cityMapper.insert(new City("CC", "AA"));
//        cityMapper.insert(new City("DD", "AA"));
//        cityMapper.insert(new City("EE", "AA"));
//        cityMapper.insert(new City("FFFFFFFF", "AA"));
//        cityMapper.insert(new City("GG", "AA"));
//        cityMapper.insert(new City("HH", "AA"));
//        cityMapper.insert(new City("JJ", "AA"));
    }

    @Override
    public List<City> select(Integer offset, Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        return cityMapper.selectLimit(params);
    }

    @Override
    public List<CityDto> selectDto(Integer offset, Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        return cityMapper.selectDtoLimit(params);
    }

    @Override
    public long count() {
        Map<String, Object> params = new HashMap<>();
        return cityMapper.count(params);
    }

    @Override
    public String say() {
        return "Say Hello";
    }
}
