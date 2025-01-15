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

@RequestMapping("/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    // 게시글 목록 페이징 조회
    @GetMapping("")
    public ResponseEntity<CommonResponse> getPosts(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(new CommonResponse(postService.getPosts(page, size)));
    }

    // 게시글 상세 조회
    @GetMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> getPost(@PathVariable("postSeq") long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.getPost(postSeq)));
    }

    // 게시글 등록
    @PostMapping("")
    public ResponseEntity<CommonResponse> addPost(@RequestBody PostAddVO vo) {
        return ResponseEntity.ok(postService.addPost(vo, jwtTokenProvider.getUserSeq()));
    }

    // 게시글 수정
    @PutMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> updatePost(@PathVariable("postSeq") long postSeq, @RequestBody PostUpdateVO vo) {
        return ResponseEntity.ok(new CommonResponse(postService.updatePost(postSeq, jwtTokenProvider.getUserSeq(), vo)));
    }

    // 게시글 삭제
    @DeleteMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> deletePost(@PathVariable("postSeq") long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.deletePost(postSeq, jwtTokenProvider.getUserSeq())));
    }

    // 특정 게시글의 댓글 등록
    @PostMapping("/{postSeq}/comments")
    public ResponseEntity<CommonResponse> addComment(@PathVariable("postSeq") long postSeq, @RequestParam("content") String content) {
        return ResponseEntity.ok(new CommonResponse(commentService.addComment(postSeq, jwtTokenProvider.getUserSeq(), content)));
    }

    // 특정 댓글 수정
    @PutMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> updateComment(@PathVariable("commentSeq") long commentSeq, @RequestParam("content") String content) {
        return ResponseEntity.ok(new CommonResponse(commentService.updateComment(commentSeq, jwtTokenProvider.getUserSeq(), content)));
    }

    // 특정 댓글 삭제
    @DeleteMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable("commentSeq") long commentSeq) {
        return ResponseEntity.ok(commentService.deleteComment(commentSeq, jwtTokenProvider.getUserSeq()));
    }
}
