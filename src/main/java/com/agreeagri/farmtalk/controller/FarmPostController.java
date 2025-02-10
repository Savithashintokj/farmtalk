package com.agreeagri.farmtalk.controller;

import com.agreeagri.farmtalk.model.dto.FarmPostDTO;
import com.agreeagri.farmtalk.service.FarmPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class FarmPostController {

    @Autowired
    private FarmPostService farmPostService;

    // ✅ Create Post
    @PostMapping
    public ResponseEntity<FarmPostDTO> createPost(@RequestBody FarmPostDTO farmPostDTO) {
        FarmPostDTO createdPost = farmPostService.createPost(farmPostDTO);
        return ResponseEntity.ok(createdPost);
    }

    // ✅ Update Post
    @PutMapping("/{postId}")
    public ResponseEntity<FarmPostDTO> updatePost(@PathVariable UUID postId, @RequestBody FarmPostDTO farmPostDTO) {
        FarmPostDTO updatedPost = farmPostService.updatePost(postId, farmPostDTO);
        return ResponseEntity.ok(updatedPost);
    }

    // ✅ Delete Post
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        farmPostService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get Post by ID
    @GetMapping("/{postId}")
    public ResponseEntity<FarmPostDTO> getPostById(@PathVariable UUID postId) {
        Optional<FarmPostDTO> post = farmPostService.getPost(postId);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Get All Posts
    @GetMapping
    public ResponseEntity<List<FarmPostDTO>> getAllPosts() {
        List<FarmPostDTO> posts = farmPostService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
