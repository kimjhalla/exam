package com.kb.exam.domain.post.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Test
    void getComments() {
        commentService.getComments(1,1,10);
    }

    @Test
    void addComment() {
        commentService.addComment(3,1,"댓글2");
    }

    @Test
    void updateComment() {
        commentService.updateComment(1,1,"수정댓글1");
    }

    @Test
    void deleteComment() {
        commentService.deleteComment(1,1);
    }

    @Test
    void deleteComments() {
        commentService.deleteComments(3);
    }
}