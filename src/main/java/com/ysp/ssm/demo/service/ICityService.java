package com.ysp.ssm.demo.service;

import com.ysp.ssm.demo.dto.CityDto;
import com.ysp.ssm.demo.model.City;

import java.util.List;

/**
 * Created by yuhuanxi on 16/8/18.
 */
public interface ICityService {

    City getById(Long id);

    long deleteById(Long id);

    long save(City country);

    List<City> select(Integer offset, Integer limit);

    List<CityDto> selectDto(Integer offset, Integer limit);

    long count();

    String say();
}
