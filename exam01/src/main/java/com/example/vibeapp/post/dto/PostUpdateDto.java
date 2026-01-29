package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostUpdateDto {

    private Long no;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    private String title;

    private String content;

    public PostUpdateDto() {
    }

    public PostUpdateDto(Long no, String title, String content) {
        this.no = no;
        this.title = title;
        this.content = content;
    }

    public void updateEntity(Post post) {
        post.setTitle(this.title);
        post.setContent(this.content);
    }

    // Getters and Setters
    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
