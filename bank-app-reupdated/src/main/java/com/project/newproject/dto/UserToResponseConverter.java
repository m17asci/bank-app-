package com.project.newproject.dto;

import com.project.newproject.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserToResponseConverter {

    public UserResponseDTO userToRequestUserDTOConverter(User user) {
        return UserResponseDTO.builder()
                .accountBalance(user.getAccountBalance())
                .surname(user.getSurname())
                .name(user.getName())
                .tcIdentity(user.getTcIdentity())
                .build();
    }
}
