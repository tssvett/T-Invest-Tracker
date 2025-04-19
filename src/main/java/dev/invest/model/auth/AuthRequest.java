package dev.invest.model.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(

        @NotBlank
        String login,

        @Size(min = 3)
        @NotBlank
        String password
) {
}
