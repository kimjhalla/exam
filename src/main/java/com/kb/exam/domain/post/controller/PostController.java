package com.kb.exam.domain.post.controller;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.post.service.CommentService;
import com.kb.exam.domain.post.service.PostService;
import com.kb.exam.domain.post.vo.PostAddVO;
import com.kb.exam.domain.post.vo.PostUpdateVO;
import com.kb.exam.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    // 게시글 목록 페이징 조회
    @GetMapping("")
    public ResponseEntity<CommonResponse> getPosts(int page, int size) {
        return ResponseEntity.ok(new CommonResponse(postService.getPosts(page, size)));
    }

    // 게시글 상세 조회
    @GetMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> getPost(@PathVariable long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.getPost(postSeq)));
    }

    // 게시글 등록
    @PostMapping("")
    public ResponseEntity<CommonResponse> addPost(@RequestBody PostAddVO vo) {
        return ResponseEntity.ok(postService.addPost(vo, jwtTokenProvider.getUserSeq()));
    }

    // 게시글 수정
    @PutMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> updatePost(@PathVariable long postSeq, @RequestBody PostUpdateVO vo) {
        return ResponseEntity.ok(new CommonResponse(postService.updatePost(vo, jwtTokenProvider.getUserSeq())));
    }

    // 게시글 삭제
    @DeleteMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> deletePost(@PathVariable long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.deletePost(postSeq, jwtTokenProvider.getUserSeq())));
    }

    // 특정 게시글의 댓글 목록 조회
    @PostMapping("/{postSeq}/comments")
    public ResponseEntity<CommonResponse> addComment(@PathVariable long postSeq, String comment) {
        return ResponseEntity.ok(new CommonResponse(commentService.addComment(postSeq, jwtTokenProvider.getUserSeq(), comment)));
    }

    // 특정 댓글 수정
    @PutMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> updateComment(@PathVariable long postSeq, @PathVariable long commentSeq, String comment) {
        return ResponseEntity.ok(new CommonResponse(commentService.updateComment(commentSeq, jwtTokenProvider.getUserSeq(), comment)));
    }

    // 특정 댓글 삭제
    @DeleteMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable long postSeq, @PathVariable long commentSeq) {
        return ResponseEntity.ok(new CommonResponse(commentService.deleteComment(commentSeq, jwtTokenProvider.getUserSeq())));
    }
}
