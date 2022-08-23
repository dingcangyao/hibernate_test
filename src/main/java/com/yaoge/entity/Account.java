package com.yaoge.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * create by yaoge
 * 2022/8/22 16:10
 * 学生
 */
@Setter
@Getter
public class Account {
    private Integer id;
    private String name;
    private Set<Course> courses;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
