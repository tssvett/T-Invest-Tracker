package dev.invest.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDto(
        UUID id,
        @NotBlank String username,
        @Email @NotBlank String email) {
}
