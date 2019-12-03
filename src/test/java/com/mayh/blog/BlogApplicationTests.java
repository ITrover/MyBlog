package com.mayh.blog;

import com.mayh.blog.po.Comment;
import com.mayh.blog.service.CommentServiceImpl;
import com.mayh.blog.utils.MarkdownUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class BlogApplicationTests {


    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Test
    void contextLoads() {
        List<Comment> comments = commentServiceImpl.listCommentByBlogId((long) 27);
        System.out.println(comments);
    }

    @Test
    void MarkdownTest() {

    }

}
