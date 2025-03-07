package com.agreeagri.farmtalk.service;

import com.agreeagri.farmtalk.model.dto.UserAccountDTO;
import com.agreeagri.farmtalk.model.entity.UserAccount;
import com.agreeagri.farmtalk.model.repository.OtpRepository;
import com.agreeagri.farmtalk.model.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import com.agreeagri.farmtalk.model.entity.Otp;

@Service
public class UserAccountService {
    private final SecureRandom random = new SecureRandom();

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserAccountRepository userAccountRepository;


    // ✅ Create User Account (DTO as Request, Returns DTO)

    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = convertToEntity(userAccountDTO);
        UserAccount savedUser = userAccountRepository.save(userAccount);
        otpService.saveOtp(savedUser.getUserId());
        return convertToDTO(savedUser);
    }

    // ✅ Update User Account (DTO as Request, Converts to Entity)

    public UserAccountDTO updateUserAccount(Long userId, UserAccountDTO userAccountDTO) {
        Optional<UserAccount> existingUserOpt = userAccountRepository.findById(userId);
        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserAccount existingUser = existingUserOpt.get();
        existingUser.setFirstName(userAccountDTO.getFirstName());
        existingUser.setLastName(userAccountDTO.getLastName());
        existingUser.setIdNumber(userAccountDTO.getIdNumber());
        existingUser.setEmail(userAccountDTO.getEmail());
        existingUser.setMobile(userAccountDTO.getMobile());
        existingUser.setAddressLine1(userAccountDTO.getAddressLine1());
        existingUser.setAddressLine2(userAccountDTO.getAddressLine2());
        existingUser.setTown(userAccountDTO.getTown());
        existingUser.setPostcode(userAccountDTO.getPostcode());
        existingUser.setCountry(userAccountDTO.getCountry());
        existingUser.setProfilePhoto(userAccountDTO.getProfilePhoto());

        UserAccount updatedUser = userAccountRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    // ✅ Delete User Account

    public void deleteUserAccount(Long userId) {
        if (!userAccountRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userAccountRepository.deleteById(userId);
    }

    // ✅ Get User Account by ID
    public Optional<UserAccountDTO> getUserAccount(Long userId) {
        return userAccountRepository.findById(userId).map(this::convertToDTO);
    }

    // ✅ Convert Entity to DTO
    private UserAccountDTO convertToDTO(UserAccount userAccount) {
        UserAccountDTO dto = new UserAccountDTO();
        dto.setUserId(userAccount.getUserId());
        dto.setFirstName(userAccount.getFirstName());
        dto.setLastName(userAccount.getLastName());
        dto.setIdNumber(userAccount.getIdNumber());
        dto.setEmail(userAccount.getEmail());
        dto.setMobile(userAccount.getMobile());
        dto.setAddressLine1(userAccount.getAddressLine1());
        dto.setAddressLine2(userAccount.getAddressLine2());
        dto.setTown(userAccount.getTown());
        dto.setPostcode(userAccount.getPostcode());
        dto.setCountry(userAccount.getCountry());
        dto.setProfilePhoto(userAccount.getProfilePhoto());
        return dto;
    }

    // ✅ Convert DTO to Entity
    private UserAccount convertToEntity(UserAccountDTO dto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(dto.getFirstName());
        userAccount.setLastName(dto.getLastName());
        userAccount.setIdNumber(dto.getIdNumber());
        userAccount.setEmail(dto.getEmail());
        userAccount.setMobile(dto.getMobile());
        userAccount.setAddressLine1(dto.getAddressLine1());
        userAccount.setAddressLine2(dto.getAddressLine2());
        userAccount.setTown(dto.getTown());
        userAccount.setPostcode(dto.getPostcode());
        userAccount.setCountry(dto.getCountry());
        userAccount.setProfilePhoto(dto.getProfilePhoto());
        return userAccount;
    }
}
