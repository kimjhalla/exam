package com.kb.exam.domain.post.repository;

import com.kb.exam.domain.post.entity.Post;
import com.kb.exam.domain.post.vo.PostResponse;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    @NativeQuery(value = """
            SELECT seq, userSeq, title
            FROM POST
            ORDER BY seq desc
            LIMIT :size OFFSET :offset
            """)
    List<PostResponse> findAllBySizeAndOffset(@Param("size") int size, @Param("offset") int offset);
}
