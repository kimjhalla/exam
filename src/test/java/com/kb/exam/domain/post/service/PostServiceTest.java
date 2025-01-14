package com.kb.exam.domain.post.service;

import com.kb.exam.domain.post.vo.PostAddVO;
import com.kb.exam.domain.post.vo.PostAttachFileAddVO;
import com.kb.exam.domain.post.vo.PostUpdateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    public void getPosts() {
        postService.getPosts(1, 5);
    }

    @Test
    public void getPost() {
        System.out.println(postService.getPost(1).getTitle());
    }

    @Test
    public void addPost() {
        List<PostAttachFileAddVO> postAttachFileAddVOS = new ArrayList<>();
        postAttachFileAddVOS.add(new PostAttachFileAddVO("파일명1", "cdn경로/파일명1.pdf"));
        postAttachFileAddVOS.add(new PostAttachFileAddVO("파일명2", "cdn경로/파일명2.pdf"));
        postService.addPost(new PostAddVO("제목2", "내용2", postAttachFileAddVOS), 1);
    }

    @Test
    public void updatePost() {
        List<PostAttachFileAddVO> postAttachFileAddVOS = new ArrayList<>();
        postAttachFileAddVOS.add(new PostAttachFileAddVO("변경파일명1", "cdn경로/변경파일명1.pdf"));
        postAttachFileAddVOS.add(new PostAttachFileAddVO("변경파일명2", "cdn경로/변경파일명2.pdf"));
        System.out.println(postService.updatePost(1, 1, new PostUpdateVO("변경제목1", "변경내용1", postAttachFileAddVOS)));
    }

    @Test
    public void deletePost() {
        System.out.println(postService.deletePost(2, 1));
    }
}