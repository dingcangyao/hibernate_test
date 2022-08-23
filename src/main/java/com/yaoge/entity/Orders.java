package com.yaoge.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * create by yaoge
 * 2022/8/22 11:53
 */
@Getter
@Setter
public class Orders {
    private Integer id;
    private String name;
    private Customer customer;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
