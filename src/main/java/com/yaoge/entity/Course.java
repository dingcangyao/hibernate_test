package com.yaoge.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * create by yaoge
 * 2022/8/22 16:13
 */
@Getter
@Setter
public class Course {
    private Integer id;
    private String name;
    private Set<Account> accounts;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
