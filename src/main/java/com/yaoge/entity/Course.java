package com.yaoge.entity;

import lombok.Data;

import java.util.Set;

/**
 * create by yaoge
 * 2022/8/22 16:13
 */
@Data
public class Course {
    private Integer id;
    private String name;
    private Set<Account> accounts;
}
