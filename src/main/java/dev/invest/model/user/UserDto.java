package dev.invest.model.user;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record UserDto(
        UUID id,
        @NotBlank String login,
        @NotBlank String email
) {
}
