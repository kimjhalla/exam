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
        posts.add(new PostResponse(1,1,"제목"));
        posts.add(new PostResponse(2,2,"제목"));
        return posts;
    }

    public PostDetailResponse getPost(long postSeq) {
        PostDetailResponse postDetailResponse = new PostDetailResponse();
        postDetailResponse.setComments(commentService.getComments(postSeq));
        return postDetailResponse;
    }
}
