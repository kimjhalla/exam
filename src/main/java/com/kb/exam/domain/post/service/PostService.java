package com.kb.exam.domain.post.service;

import com.kb.exam.domain.post.vo.PostDetailResponse;
import com.kb.exam.domain.post.vo.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final CommentService commentService;

    public List<PostResponse> getPosts() {
        List<PostResponse> posts = new ArrayList<>();
        return posts;
    }

    public PostDetailResponse getPost(long postSeq) {
        PostDetailResponse postDetailResponse = new PostDetailResponse();
        postDetailResponse.setComments(commentService.getComments(postSeq));
        return postDetailResponse;
    }
}
