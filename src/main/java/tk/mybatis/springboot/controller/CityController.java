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

package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.dto.CityDto;
import tk.mybatis.springboot.model.City;
import tk.mybatis.springboot.service.CityService;
import tk.mybatis.springboot.util.BaseController;
import tk.mybatis.springboot.util.PagingDto;
import tk.mybatis.springboot.util.ReturnCode;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/cities")
public class CityController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @RequestMapping
    public PageInfo<City> getAll(City city) {
        List<City> countryList = cityService.getAll(city);
        return new PageInfo<City>(countryList);
    }

    @RequestMapping(value = "/list")
    public BaseAjaxResult getAll(Integer curPage, Integer pageSize) {

        if (true) {
            return renderJsonFail(ReturnCode.DATA_NOT_FOUND.getCode(), ReturnCode.DATA_NOT_FOUND.getMsg());
        }

        PagingDto pagingDto = new PagingDto(curPage, pageSize);
        List<City> countryList = cityService.select(pagingDto.getBeginInt(), pagingDto.getPageSize());

        long count = countryList.size();
        pagingDto.setCount(count);
        return renderJsonAjaxPageResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), countryList, pagingDto);

//        return renderJsonAjaxPageResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), countryList, pagingDto);
//        return new PageInfo<City>(countryList);
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

        City city = new City();
        city.setName(name);
        city.setState(state);

        long ret = cityService.save(city);
        LOG.info("insert city id:{}", city.getId());
        if (ret > 0) {
            return renderJsonSuccessed(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
        }
        return renderJsonFail();
    }

    @RequestMapping(value = "/add")
    public City add() {
        return new City();
    }

    @RequestMapping(value = "/view/{id}")
    public City view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        City city = cityService.getById(id);
        return city;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
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
