package com.mayh.blog;


import com.mayh.blog.po.Comment;
import com.mayh.blog.service.CommentService;
import com.mayh.blog.service.CommentServiceImpl;
import com.mayh.blog.utils.MarkdownUtil;
import com.mayh.blog.utils.TitleExtract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.List;

@SpringBootTest
public class UnitTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void test() {

        String s = MarkdownUtil.markdown2Html("找出必要信息，拆分成小的信息列表(原子性数据)，使易于查询\n" +
                "\n" +
                "原子性数据 #规则一 具有原子性数据的列中不会有多个类型相同的值 #规则二 具有原子性数据的表中不会有多个储存同类型的列\n" +
                "\n" +
                "#第一范式1NF　FIRST NORMAL FORM　 使用主键 人造主键　自然主键 规则 １．主键独一无 二。 ２．创建记录时必须插入主键的值，主键不能为NULL。 ３．主键不可以更改。\n" +
                "\n" +
                "#取得原来建表的SQL语句 SHOW CREATE TABLE MY_TABLE; //会有反撇号　SQL用于辨别列名，可以直接使用 #SHOW 的作用 SHOW　COLUMNS FROM tablename //查看所有列 SHOW CRATE DATABASE databasename SHOW INDEX FROM tablename SHOW WARNINGS //取得确切的警告内容\n" +
                "\n" +
                "#添加主键 PRIMARY KEY (列名)\n" +
                "\n" +
                "#自动递增 AUTO_INCREMENT\n" +
                "\n" +
                "#ALTER ALTER TABLE tablename ADD COLUMN ......... //用于为现有的表添加新的列");
        System.out.println(s);
    }

    @Test
    public void test01() {
        String s = "[wang[li]";
        String[] split = s.split("\\[|\\]");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }

    }

    @Test
    public void CommentMethod() {

    }
}
