package com.kb.exam.domain.post.controller;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.post.service.CommentService;
import com.kb.exam.domain.post.service.PostService;
import com.kb.exam.domain.post.vo.PostAddVO;
import com.kb.exam.domain.post.vo.PostUpdateVO;
import com.kb.exam.util.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "POST", description = "게시글/답글 관련 API")
@RequestMapping("/v1/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    // 게시글 목록 페이징 조회
    @GetMapping("")
    @Operation(summary = "게시글 조회 API", description = "게시글 목록을 page와 size를 받아 조회한다. POST_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> getPosts(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(new CommonResponse(postService.getPosts(page, size)));
    }

    // 게시글 상세 조회
    @GetMapping("/{postSeq}")
    @Operation(summary = "게시글 상세 조회 API", description = "특정 게시글을 조회한다. 특정 게시글의 답글 목록도 조회. POST_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> getPost(@PathVariable("postSeq") long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.getPost(postSeq)));
    }

    // 게시글 등록
    @PostMapping("")
    @Operation(summary = "게시글 등록 API", description = "신규 게시글을 등록한다. POST_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> addPost(@RequestBody PostAddVO vo) {
        return ResponseEntity.ok(postService.addPost(vo, jwtTokenProvider.getUserSeq()));
    }

    // 게시글 수정
    @PutMapping("/{postSeq}")
    @Operation(summary = "게시글 수정 API", description = "기존 게시글을 수정한다. 동일한 작성자만 수정가능. POST_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> updatePost(@PathVariable("postSeq") long postSeq, @RequestBody PostUpdateVO vo) {
        return ResponseEntity.ok(new CommonResponse(postService.updatePost(postSeq, jwtTokenProvider.getUserSeq(), vo)));
    }

    // 게시글 삭제
    @DeleteMapping("/{postSeq}")
    @Operation(summary = "게시글 삭제 API", description = "기존 게시글을 삭제한다. 동일한 작성자만 삭제가능. POST_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> deletePost(@PathVariable("postSeq") long postSeq) {
        return ResponseEntity.ok(new CommonResponse(postService.deletePost(postSeq, jwtTokenProvider.getUserSeq())));
    }

    // 특정 게시글의 답글 목록 페이징 조회
    @GetMapping("/{postSeq}/comments")
    @Operation(summary = "특정 게시글의 댓글 목록 조회 API", description = "댓글 목록을 page와 size를 받아 조회한다. POST_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> getComments(@PathVariable("postSeq") long postSeq, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(new CommonResponse(commentService.getComments(postSeq, page, size)));
    }

    // 특정 게시글의 답글 등록
    @PostMapping("/{postSeq}/comments")
    @Operation(summary = "특정 게시글의 답글 등록 API", description = "특정 게시글의 답글을 등록한다. COMMENT_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> addComment(@PathVariable("postSeq") long postSeq, @RequestParam("content") String content) {
        return ResponseEntity.ok(new CommonResponse(commentService.addComment(postSeq, jwtTokenProvider.getUserSeq(), content)));
    }

    // 특정 답글 수정
    @PutMapping("/{postSeq}/comments/{commentSeq}")
    @Operation(summary = "특정 게시글의 답글 수정 API", description = "특정 게시글의 답글을 수정한다. 동일한 작성자만 수정가능. COMMENT_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> updateComment(@PathVariable("commentSeq") long commentSeq, @RequestParam("content") String content) {
        return ResponseEntity.ok(new CommonResponse(commentService.updateComment(commentSeq, jwtTokenProvider.getUserSeq(), content)));
    }

    // 특정 답글 삭제
    @DeleteMapping("/{postSeq}/comments/{commentSeq}")
    @Operation(summary = "특정 게시글의 답글 삭제 API", description = "특정 게시글의 답글을 삭제한다. 동일한 작성자만 삭제가능. COMMENT_USER 권한 접근 가능")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable("commentSeq") long commentSeq) {
        return ResponseEntity.ok(commentService.deleteComment(commentSeq, jwtTokenProvider.getUserSeq()));
    }
}
