package dev.invest.model.user;

import java.util.UUID;

public record UserDto(
        UUID uuid, 
        String username,
        String email) {
}
