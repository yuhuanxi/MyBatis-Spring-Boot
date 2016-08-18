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

import com.ysp.ssm.demo.dto.CityDto;
import com.ysp.ssm.demo.mapper.CityMapper;
import com.ysp.ssm.demo.model.City;
import com.ysp.ssm.demo.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzh
 * @since 2015-12-19 11:09
 */
@Service
public class CityService implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City getById(Long id) {
//        return cityMapper.selectById(id);
//        return cityMapper.selectByPrimaryKey(id);
        return null;
    }

    @Override
    public long deleteById(Long id) {
//        cityMapper.deleteByPrimaryKey(id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return cityMapper.delete(params);
    }

    @Override
    public long save(City country) {
        return cityMapper.insert(country);
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
