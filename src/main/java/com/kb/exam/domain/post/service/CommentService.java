package com.kb.exam.domain.post.service;

import com.kb.exam.domain.post.vo.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    public List<CommentResponse> getComments(long postSeq) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        return commentResponses;
    }

    public CommentResponse getComment(long postSeq, long commentSeq) {
        CommentResponse commentResponse = new CommentResponse();
        return commentResponse;
    }

    public CommentResponse addComment(long postSeq, long userSeq, String content) {
        return new CommentResponse();
    }

    public CommentResponse updateComment(long postSeq, long commentSeq, long userSeq, String content) {
        return new CommentResponse();
    }

    public CommentResponse deleteComment(long postSeq, long commentSeq, long userSeq) {
        return new CommentResponse();
    }
}
