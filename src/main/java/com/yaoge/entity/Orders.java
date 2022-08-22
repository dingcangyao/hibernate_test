package com.yaoge.entity;

import lombok.Data;

/**
 * create by yaoge
 * 2022/8/22 11:53
 */
@Data
public class Orders {
    private Integer id;
    private String name;
    private Customer customer;
}
