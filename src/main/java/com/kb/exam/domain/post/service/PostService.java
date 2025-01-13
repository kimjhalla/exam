package com.kb.exam.domain.post.service;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.post.entity.Post;
import com.kb.exam.domain.post.repository.PostRepository;
import com.kb.exam.domain.post.vo.PostDetailResponse;
import com.kb.exam.domain.post.vo.PostResponse;
import com.kb.exam.domain.post.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentService commentService;

    public List<PostResponse> getPosts(int page, int size) {
        return postRepository.findAllByPageAndSize(page, size);
    }

    public PostDetailResponse getPost(long postSeq) {
        PostDetailResponse postDetailResponse = new PostDetailResponse();
        postDetailResponse.setComments(commentService.getComments(postSeq));
        return postDetailResponse;
    }

    public CommonResponse addPost(PostVO vo, long userSeq) {
        Post post = new Post(vo, userSeq);
        postRepository.save(post);
        return new CommonResponse();
    }
}
