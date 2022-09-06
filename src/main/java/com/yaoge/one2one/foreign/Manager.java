package com.yaoge.one2one.foreign;

import lombok.Getter;
import lombok.Setter;

/**
 * create by yaoge
 * 2022/8/29 17:49
 */
@Getter
@Setter
public class Manager {
    private Integer mgrId;
    private String mgrName;
    private Department dept;
}
