package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<PostListDto> getPostsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.findAll(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalCount = postMapper.count();
        return (int) Math.ceil((double) totalCount / size);
    }

    public PostResponseDto findById(Long no) {
        postMapper.incrementViews(no);
        Post post = postMapper.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        return PostResponseDto.from(post);
    }

    public void create(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        postMapper.save(post);
    }

    public void update(Long no, PostUpdateDto updateDto) {
        // 존재 여부 확인
        postMapper.findById(no)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post number: " + no));
        postMapper.update(no, updateDto);
    }

    public void delete(Long no) {
        postMapper.deleteById(no);
    }
}
