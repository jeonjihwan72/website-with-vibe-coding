package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getPostsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findPage(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalCount = postRepository.count();
        return (int) Math.ceil((double) totalCount / size);
    }

    public PostResponseDto findById(Long no) {
        postRepository.incrementViews(no);
        Post post = postRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        return PostResponseDto.from(post);
    }

    public void create(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postRepository.save(post);
    }

    public void update(Long no, PostUpdateDto updateDto) {
        Post post = postRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        updateDto.updateEntity(post);
    }

    public void delete(Long no) {
        postRepository.deleteById(no);
    }
}
