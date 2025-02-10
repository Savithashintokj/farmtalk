package com.agreeagri.farmtalk.controller;

import com.agreeagri.farmtalk.model.dto.UserAccountDTO;
import com.agreeagri.farmtalk.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    // ✅ Create User
    @PostMapping
    public ResponseEntity<UserAccountDTO> createUser(@RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO savedUser = userAccountService.createUserAccount(userAccountDTO);
        return ResponseEntity.ok(savedUser);
    }

    // ✅ Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> updateUser(
            @PathVariable int userId,
            @RequestBody UserAccountDTO userAccountDTO) {
        UserAccountDTO updatedUser = userAccountService.updateUserAccount(userId, userAccountDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // ✅ Delete User
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userAccountService.deleteUserAccount(userId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get User by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserAccountDTO> getUserById(@PathVariable int userId) {
        Optional<UserAccountDTO> user = userAccountService.getUserAccount(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
