package com.kb.exam.domain.post.service;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.post.entity.Comment;
import com.kb.exam.domain.post.repository.CommentRepository;
import com.kb.exam.domain.post.vo.CommentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentResponse> getComments(long postSeq , int page, int size) {
        int offset = (page - 1) * size;
        return commentRepository.findAllByPostSeqAndSizeAndOffset(postSeq,size,offset);
    }

    @Transactional
    public CommonResponse addComment(long postSeq, long userSeq, String content) {
        commentRepository.save(new Comment(postSeq, userSeq, content));
        return CommonResponse.success();
    }

    @Transactional
    public CommonResponse updateComment(long commentSeq, long userSeq, String content) {
        Optional<Comment> optionalComment = commentRepository.findById(commentSeq);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            if(comment.getUserSeq() == userSeq) {
                comment.setContent(content);
                comment.setUpdateDate(LocalDateTime.now());
                commentRepository.save(comment);
                return CommonResponse.success();
            }else{
                return CommonResponse.fail("잘못된 접근 입니다.");
            }
        }else{
            return CommonResponse.fail("존재하지 않는 댓글 입니다.");
        }
    }

    @Transactional
    public CommonResponse deleteComment(long commentSeq, long userSeq) {
        Optional<Comment> optionalComment = commentRepository.findById(commentSeq);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            if(comment.getUserSeq() == userSeq) {
                commentRepository.deleteById(commentSeq);
                return CommonResponse.success();
            }else{
                return CommonResponse.fail("잘못된 접근 입니다.");
            }
        }else{
            return CommonResponse.fail("존재하지 않는 댓글 입니다.");
        }
    }

    // 게시글 삭제시 댓글도 삭제 처리 하기 위한 메서드
    @Transactional
    public void deleteComments(long postSeq){
        commentRepository.deleteAllByPostSeq(postSeq);
    }
}
