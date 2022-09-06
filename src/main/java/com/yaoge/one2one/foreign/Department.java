package com.yaoge.one2one.foreign;

import lombok.Getter;
import lombok.Setter;

/**
 * create by yaoge
 * 2022/8/29 17:48
 */
@Getter
@Setter
public class Department {

    private Integer deptId;
    private String deptName;
    private Manager mgr;

}
