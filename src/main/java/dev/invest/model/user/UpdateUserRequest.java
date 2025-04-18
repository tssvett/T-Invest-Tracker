package dev.invest.model.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank String login,
        @NotBlank String email
) {
}
