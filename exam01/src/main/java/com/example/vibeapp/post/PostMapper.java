package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    List<Post> findAll(@Param("offset") int offset, @Param("limit") int limit);

    Optional<Post> findById(Long no);

    void save(Post post);

    void update(@Param("no") Long no, @Param("dto") PostUpdateDto dto);

    void deleteById(Long no);

    void incrementViews(Long no);

    int count();
}
