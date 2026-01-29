package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        model.addAttribute("posts", postService.getPostsByPage(page, pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postService.getTotalPages(pageSize));
        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.findById(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String createForm(Model model) {
        model.addAttribute("post", new PostCreateDto());
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String create(@Valid @ModelAttribute("post") PostCreateDto postCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return "post/post_new_form";
        }
        postService.create(postCreateDto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{no}/edit")
    public String editForm(@PathVariable("no") Long no, Model model) {
        PostResponseDto postDto = postService.findById(no);
        PostUpdateDto updateDto = new PostUpdateDto(no, postDto.getTitle(), postDto.getContent());
        model.addAttribute("post", updateDto);
        model.addAttribute("no", no);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable("no") Long no, @Valid @ModelAttribute("post") PostUpdateDto postUpdateDto,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("no", no);
            return "post/post_edit_form";
        }
        postService.update(no, postUpdateDto);
        return "redirect:/posts/" + no;
    }

    @GetMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }
}
