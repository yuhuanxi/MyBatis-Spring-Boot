package com.ysp.ssm.demo.dto;

/**
 * @author: shipeng.yu
 * @time: 2016年09月03日 下午10:03
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class CityDto {

    private String address;

    private String name;

    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
