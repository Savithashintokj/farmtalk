package com.agreeagri.farmtalk.service;

import com.agreeagri.farmtalk.model.dto.FarmPostDTO;
import com.agreeagri.farmtalk.model.entity.FarmPost;
import com.agreeagri.farmtalk.model.entity.UserAccount;
import com.agreeagri.farmtalk.model.repository.FarmPostRepository;
import com.agreeagri.farmtalk.model.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmPostService {

    @Autowired
    private FarmPostRepository farmPostRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    // ✅ Create Post (DTO Request, Converts to Entity)
    @Transactional
    public FarmPostDTO createPost(FarmPostDTO farmPostDto) {
        UserAccount user = userAccountRepository.findById(farmPostDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        FarmPost farmPost = convertToEntity(farmPostDto);
        farmPost.setUserAccount(user);

        FarmPost savedPost = farmPostRepository.save(farmPost);
        return convertToDTO(savedPost);
    }

    // ✅ Update Post (DTO Request, Converts to Entity)
    @Transactional
    public FarmPostDTO updatePost(UUID postId, FarmPostDTO farmPostDto) {
        FarmPost existingPost = farmPostRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        UserAccount user = userAccountRepository.findById(farmPostDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Updating existing post
        existingPost.setUserAccount(user);
        existingPost.setTitle(farmPostDto.getTitle());
        existingPost.setDetails(farmPostDto.getDetails());
        existingPost.setPhoto(farmPostDto.getPhoto());
        existingPost.setLikesCount(farmPostDto.getLikesCount());
        FarmPost updatedPost = farmPostRepository.save(existingPost);
        return convertToDTO(updatedPost);
    }

    // ✅ Delete Post
    @Transactional
    public void deletePost(UUID postId) {
        if (!farmPostRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }
        farmPostRepository.deleteById(postId);
    }

    // ✅ Get Post by ID
    public Optional<FarmPostDTO> getPost(UUID postId) {
        return farmPostRepository.findById(postId).map(this::convertToDTO);
    }

    // ✅ Get All Posts
    public List<FarmPostDTO> getAllPosts() {
        List<FarmPost> posts = farmPostRepository.findAll();
        List<FarmPostDTO> postDTOs = new ArrayList<>();
        for (FarmPost post : posts) {
            postDTOs.add(convertToDTO(post));
        }
        return postDTOs;
    }

    // ✅ Convert Entity to DTO
    private FarmPostDTO convertToDTO(FarmPost farmPost) {
        FarmPostDTO dto = new FarmPostDTO();
        dto.setPostId(farmPost.getPostId());
        dto.setUserId(farmPost.getUserAccount().getUserId());
        dto.setTitle(farmPost.getTitle());
        dto.setDetails(farmPost.getDetails());
        dto.setPhoto(farmPost.getPhoto());
        dto.setLikesCount(farmPost.getLikesCount());
        return dto;
    }

    // ✅ Convert DTO to Entity
    private FarmPost convertToEntity(FarmPostDTO dto) {
        FarmPost farmPost = new FarmPost();
        farmPost.setTitle(dto.getTitle());
        farmPost.setDetails(dto.getDetails());
        farmPost.setPhoto(dto.getPhoto());
        farmPost.setLikesCount(dto.getLikesCount());
        UserAccount user = userAccountRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        farmPost.setUserAccount(user); // ✅ Set UserAccount entity
        return farmPost;

    }
}
