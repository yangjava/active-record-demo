package com.demo;

import me.zzp.ar.DB;
import me.zzp.ar.Table;

import java.util.Map;
import java.util.Set;

/**
 * createTable方法的第一个参数是数据库表的名字，之后可以跟随任意个描述字段的参数，格式是名字+类型，用空格隔开。
 *
 * 此外jActiveRecord还会额外添加created_at和updated_at两个字段，类型均为timestamp，分别保存记录被创建和更新的时间。因此，上述代码总共创建了6个字段：id、name、age、note、created_at和updated_at。
 */
public class DbDemo {

    public static void main(String[] args) {
        //打开连接
        DB db = DB.open("jdbc:mysql://localhost:3306/active-record?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false", "root", "root");
        //创建数据库
        Table studentTable = db.createTable("student", "name varchar(20)", "age int","note varchar(20)");
        //选中当前数据表
        Table student = db.active("student");
        //支持命名参数
        student.create("name:", "杨京京", "age:", "18");
        //支持列名
        student.create("name", "杨京京2", "age", "18","note", "说明参数");

    }
}
