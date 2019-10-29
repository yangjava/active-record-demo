package com.demo;

import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

import java.util.List;
/**
 * jActiveRecord提供了下列查询方法：
 *
 * Record find(int id)：返回指定id的记录。
 * List<Record> all()：返回符合约束的所有记录。
 * List<Record> paging(int page, int size)：基于all()的分页查询，page从0开始。
 * Record first()：基于all()，返回按id排序的第一条记录。
 * Record last()：基于all()，返回按id排序的最后一条记录。
 * List<Record> where(String condition, Object... args)：基于all()，返回符合条件的所有记录。条件表达式兼容java.sql.PreparedStatement。
 * Record first(String condition, Object... args)：基于where()，返回按id排序的第一条记录。
 * Record last(String condition, Object... args)：基于where()，返回按id排序的最后一条记录。
 * List<Record> findBy(String key, Object value)：基于all()，返回指定列与value相等的所有记录。
 * Record findA(String key, Object value)：基于findBy()，返回按id排序的第一条记录。
 */

/**
 * 通过查询获得目标对象，接着可以做一些更新操作。
 *
 */

/**
 *  删除
 * Table#delete和Record#destroy都能删除一条记录，Table#purge能删除当前约束下所有的记录。
 */
public class TableDemo {

    public static void main(String[] args) {
        DB db = DB.open("jdbc:mysql://localhost:3306/active-record?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false", "root", "root");
        Table student = db.active("student");
        /**
         * 查询操作
         */
        Record record = student.find(1);
        System.out.println(record);
        List<Record> all = student.all();
        all.stream().forEach(e-> System.out.println(e));
        Record first = student.first();
        System.out.println(first);
        List<Record> where = student.where("id =1 ");
        where.stream().forEach(e-> System.out.println(e));
        String name = record.get("name");
        System.out.println(name);
        /**
         * 更新操作
         */
        Record jim = student.find(1);
        jim.set("name", "新杨京京").save();
        jim.update("name:", "新杨京京");
        /**
         * 删除操作
         * 如果记录不存在,会报空指针
         */
        student.find(2).destroy();
        student.delete(student.find(2));

    }

}
