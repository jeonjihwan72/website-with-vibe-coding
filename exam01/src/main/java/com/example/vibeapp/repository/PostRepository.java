package com.example.vibeapp.repository;

import com.example.vibeapp.entity.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Post> findById(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst();
    }

    public void incrementViews(Long no) {
        findById(no).ifPresent(post -> post.setViews(post.getViews() + 1));
    }

    public void save(Post post) {
        long nextId = posts.stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;
        post.setNo(nextId);
        posts.add(post);
    }

    public void deleteById(Long no) {
        posts.removeIf(post -> post.getNo().equals(no));
    }

    public int count() {
        return posts.size();
    }

    public List<Post> findPage(int offset, int limit) {
        return posts.stream()
                .sorted((p1, p2) -> p2.getNo().compareTo(p1.getNo())) // Descending order by 'no'
                .skip(offset)
                .limit(limit)
                .toList();
    }
}
