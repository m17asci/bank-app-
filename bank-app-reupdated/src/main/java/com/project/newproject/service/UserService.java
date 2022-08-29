package com.project.newproject.service;

import com.project.newproject.dto.UserRequestDTO;
import com.project.newproject.dto.UserResponseDTO;
import com.project.newproject.entity.User;
import com.project.newproject.exception.UserNotFound;
import com.project.newproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDTO addUser(UserRequestDTO requestDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(requestDTO, User.class)), UserResponseDTO.class);
    }

    public List<UserResponseDTO> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUser(String  tcIdentity) {
        return modelMapper
                .map(userRepository
                        .findByTcIdentity(tcIdentity)
                        .orElseThrow(() -> new UserNotFound("Kullanıcı bulunamadı")), UserResponseDTO.class);

    }

    public UserResponseDTO updateUser(UserRequestDTO newUserRequest) {
        User oldUser = userRepository.findByTcIdentity(newUserRequest.getTcIdentity()).orElseThrow(() -> new UserNotFound("Kullanıcı bulunamadı"));

        userRepository.findByTcIdentity(newUserRequest.getTcIdentity())
                .ifPresent(user -> {
            user.setName(newUserRequest.getName());
            user.setSurname(newUserRequest.getSurname());
            user.setAccountBalance(newUserRequest.getAccountBalance());
            userRepository.save(user);});

        return modelMapper.map(userRepository.findById(oldUser.getId()).get(), UserResponseDTO.class);
    }

    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound("Kullanıcı bulunamadı."));
        userRepository.deleteById(user.getId());
    }

}
