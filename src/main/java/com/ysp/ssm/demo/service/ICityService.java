package com.ysp.ssm.demo.service;

import com.ysp.ssm.demo.dto.CityDto;
import com.ysp.ssm.demo.model.City;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICityService {

    City getById(Long id);

    long deleteById(Long id);

    // 建议把事物加到 service 层
    @Transactional
    long save(City country);

    List<City> select(Integer offset, Integer limit);

    List<CityDto> selectDto(Integer offset, Integer limit);

    long count();

    String say();
}
