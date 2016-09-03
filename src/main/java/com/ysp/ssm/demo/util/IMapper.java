package com.ysp.ssm.demo.util;

import java.util.List;
import java.util.Map;

/**
 * @author shipeng.yu
 * @version V1.0
 * @date 2015年8月28日 上午11:23:14
 * @description mapper基础接口
 */
public interface IMapper<O> {

    O selectOne(Map<String, Object> params);

    List<O> select(Map<String, Object> params);

    long count(Map<String, Object> params);

    int insert(O user);

    int insertBatch(List<O> users);

    int update(O user);

    int delete(Map<String, Object> params);
}
