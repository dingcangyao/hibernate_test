package com.yaoge.entity;

import lombok.Data;

import java.util.Set;

/**
 * create by yaoge
 * 2022/8/22 11:48
 */
@Data
public class Customer {
    private Integer id;
    private String name;
    private Set<Orders> orders;

}
