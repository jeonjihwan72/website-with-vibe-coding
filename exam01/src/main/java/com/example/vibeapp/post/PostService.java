package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션 설정
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getPostsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAll(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalCount = postRepository.count();
        return (int) Math.ceil((double) totalCount / size);
    }

    @Transactional // 쓰기 작업이 포함되므로 별도의 트랜잭션 설정
    public PostResponseDto findById(Long no) {
        postRepository.incrementViews(no); // 조회수 증가 (변경 감지 동작)
        Post post = postRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        return PostResponseDto.from(post);
    }

    @Transactional
    public void create(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postRepository.save(post); // em.persist() 실행
    }

    @Transactional
    public void update(Long no, PostUpdateDto updateDto) {
        // JPA의 변경 감지(Dirty Checking)를 활용하여 업데이트
        Post post = postRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));

        // 엔티티 필드 수정 시 트랜잭션 종료 시점에 DB 반영됨
        updateDto.updateEntity(post);
        post.setUpdatedAt(java.time.LocalDateTime.now());
    }

    @Transactional
    public void delete(Long no) {
        postRepository.deleteById(no); // em.remove() 실행
    }
}
