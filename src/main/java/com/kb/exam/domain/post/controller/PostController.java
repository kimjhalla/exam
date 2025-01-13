package com.kb.exam.domain.post.controller;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.post.service.CommentService;
import com.kb.exam.domain.post.service.PostService;
import com.kb.exam.domain.post.vo.PostVO;
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

    @GetMapping("")
    public ResponseEntity<CommonResponse> getPosts() {
        return ResponseEntity.ok(new CommonResponse(postService.getPosts()));
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> addPost(@RequestBody PostVO vo) {
        return ResponseEntity.ok(new CommonResponse());
    }

    @GetMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> getPost(@PathVariable long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.getPost(postSeq)));
    }

    @PutMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> updatePost(@PathVariable long postSeq, @RequestBody PostVO vo) {
        return ResponseEntity.ok(new CommonResponse(postService.getPost(postSeq)));
    }

    @DeleteMapping("/{postSeq}")
    public ResponseEntity<CommonResponse> deletePost(@PathVariable long postSeq, @RequestBody PostVO vo) {
        return ResponseEntity.ok(new CommonResponse(postService.getPost(postSeq)));
    }

    @PostMapping("/{postSeq}/comments")
    public ResponseEntity<CommonResponse> addComment(@PathVariable long postSeq, String comment) {
        return ResponseEntity.ok(new CommonResponse(commentService.addComment(postSeq, jwtTokenProvider.getUserSeq(), comment)));
    }

    @GetMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> getComment(@PathVariable long postSeq, @PathVariable long commentSeq) {
        return ResponseEntity.ok(new CommonResponse(commentService.getComment(postSeq, commentSeq)));
    }

    @PutMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> updateComment(@PathVariable long postSeq, @PathVariable long commentSeq, String comment) {
        return ResponseEntity.ok(new CommonResponse(commentService.updateComment(postSeq, commentSeq, jwtTokenProvider.getUserSeq(), comment)));
    }

    @DeleteMapping("/{postSeq}/comments/{commentSeq}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable long postSeq, @PathVariable long commentSeq) {
        return ResponseEntity.ok(new CommonResponse(commentService.deleteComment(postSeq, commentSeq, jwtTokenProvider.getUserSeq())));
    }
}
