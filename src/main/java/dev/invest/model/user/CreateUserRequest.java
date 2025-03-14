package dev.invest.model.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String login,
        @NotBlank String password
) {
}
