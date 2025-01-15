package com.kb.exam.domain.post.service;

import com.kb.exam.common.CommonResponse;
import com.kb.exam.domain.post.entity.Post;
import com.kb.exam.domain.post.entity.PostAttachFile;
import com.kb.exam.domain.post.repository.PostAttachFileRepository;
import com.kb.exam.domain.post.repository.PostRepository;
import com.kb.exam.domain.post.vo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostAttachFileRepository postAttachFileRepository;
    private final CommentService commentService;

    // 게시글 목록 조회
    public List<PostResponse> getPosts(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAllBySizeAndOffset(size, offset);
    }

    // 게시글 상세 조회
    public CommonResponse getPost(long postSeq) {
        Optional<Post> optionalPost = postRepository.findById(postSeq);

        if (optionalPost.isPresent()) {
            PostDetailResponse response = new PostDetailResponse(optionalPost.get());
            response.setComments(commentService.getComments(postSeq, 1, 10));
            response.setAttachFiles(postAttachFileRepository.findAllByPostSeq(postSeq));
            return CommonResponse.success(response);
        } else {
            return CommonResponse.fail("게시글이 존재하지 않습니다.");
        }
    }

    // 게시글 추가
    public CommonResponse addPost(PostAddVO vo, long userSeq) {
        Post post = new Post(vo, userSeq);
        long postSeq = postRepository.save(post).getSeq();

        AddPostAttachFile(vo.postAttachFileAddVOS(), postSeq);

        return new CommonResponse();
    }

    // 게시글 갱신
    @Transactional
    public CommonResponse updatePost(long postSeq, long userSeq, PostUpdateVO vo) {
        Optional<Post> optionalPost = postRepository.findById(postSeq);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getUserSeq() == userSeq) {
                post.setTitle(vo.title());
                post.setContent(vo.content());
                post.setUpdateDate(LocalDateTime.now());
                postRepository.save(post);

                postAttachFileRepository.deleteAllByPostSeq(post.getSeq());

                AddPostAttachFile(vo.postAttachFileAddVOS(), postSeq);

                return CommonResponse.success();
            } else {
                return CommonResponse.fail("잘못된 접근 입니다.");
            }
        } else {
            return CommonResponse.fail("게시글이 존재하지 않습니다.");
        }
    }

    private void AddPostAttachFile(List<PostAttachFileAddVO> postAttachFileAddVOS, long postSeq) {
        // 첨부파일 존재시 데이터 추가
        if (postAttachFileAddVOS != null && !postAttachFileAddVOS.isEmpty()) {
            List<PostAttachFile> postAttachFiles = new ArrayList<>();
            postAttachFileAddVOS.forEach(postAttachFileVO -> postAttachFiles.add(new PostAttachFile(postAttachFileVO, postSeq)));
            postAttachFileRepository.saveAll(postAttachFiles);
        }
    }

    @Transactional
    public CommonResponse deletePost(long postSeq, long userSeq) {
        Optional<Post> optionalPost = postRepository.findById(postSeq);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (post.getUserSeq() == userSeq) {
                postRepository.deleteById(postSeq);
                postAttachFileRepository.deleteAllByPostSeq(postSeq);
                commentService.deleteComments(postSeq);
                return CommonResponse.success();
            } else {
                return CommonResponse.fail("잘못된 접근 입니다.");
            }
        } else {
            return CommonResponse.fail("게시글이 존재하지 않습니다.");
        }
    }
}
