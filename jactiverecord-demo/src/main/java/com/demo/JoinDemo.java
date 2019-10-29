package com.demo;

import me.zzp.ar.Association;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

/**
 * 关联
 * 到了最精彩的部分了！ORM库除了将记录映射成对象，还要将表之间的关联信息面向对象化。
 *
 * jActiveRecord提供与RoR一样的四种关联关系，并做了简化：
 *
 * Table#belongsTo
 * Table#hasOne
 * Table#hasMany
 * Table#hasAndBelongsToMany
 * 每个方法接收一个字符串参数name作为关系的名字，并返回Association关联对象，拥有以下三个方法：
 *
 * by：指定外键的名字，默认使用name + "_id"作为外键的名字。
 * in：指定关联表的名字，默认与name相同。
 * through：关联组合，参数为其他已经指定的关联的名字。即通过其他关联实现跨表访问（join多张表）。
 *
 * 关联总结
 * 一对一：有外键的表用belongsTo；无外键的表用hasOne。
 * 一对多：有外键的表用belongsTo；无外键的表用hasMany。
 * 多对多：两个多方都用hasAndBelongsToMany；映射表用belongsTo。
 * 通过through可以任意组合其他关联。
 */
public class JoinDemo {

    public static void main(String[] args) {

        DB db = DB.open("jdbc:mysql://localhost:3306/active-record?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false", "root", "root");
        /**
         * 一对多 student==>teacher
         */
        Table student = db.active("student");
        student.hasMany("teacher").by("student_id");
        Table teacher = db.active("student");
        teacher.belongsTo("student").by("student_id").in("student");
        Record one = student.find(1);
        Table oneStudent = one.get("teacher");
        for (Record oneTeacher : oneStudent.all()) {
            System.out.println("一对多"+oneTeacher);
        }
        /**
         * 多对多
         */
        student.hasMany("sc").by("student_id");
        student.hasAndBelongsToMany("my_course").by("course_id").in("course").through("sc");
        Record two = student.find(1);
        Table twoStudent = two.get("my_course");
        for (Record course : twoStudent.all()) {
            System.out.println("多对多"+course);
        }
    }
}
