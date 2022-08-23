package com.yaoge.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * create by yaoge
 * 2022/8/22 11:48
 */
@Getter
@Setter
public class Customer {
    private Integer id;
    private String name;
    private Set<Orders> orders;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 用来测试是否 proxy 和 no-proxy的区别
     * @return
     */
    public void prin(){
        System.out.println("hahaha");
    }
}
