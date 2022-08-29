package com.project.newproject.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {

    String name;
    String surname;
    BigDecimal accountBalance;
    String createdDate;
    String updatedDate;
    String tcIdentity;

}
