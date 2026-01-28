package com.example.vibeapp.repository;

import com.example.vibeapp.entity.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        // Initialize with 10 dummy records
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(
                    i,
                    "게시글 제목 " + i,
                    "게시글 내용 " + i + "입니다. 이 게시글은 자동 생성된 예제 데이터입니다.",
                    LocalDateTime.now().minusDays(10 - i),
                    LocalDateTime.now().minusDays(10 - i),
                    (int) (Math.random() * 100)));
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }
}
