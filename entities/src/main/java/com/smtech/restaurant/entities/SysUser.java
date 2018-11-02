package com.smtech.restaurant.entities;

import javax.persistence.Entity;

//系统用户
@Entity
public class SysUser extends BaseColumn{

    //系统用户ID
    private String userID;

    private String pwd;
}
