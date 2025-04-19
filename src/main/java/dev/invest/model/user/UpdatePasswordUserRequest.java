package dev.invest.model.user;

public record UpdatePasswordUserRequest(
        String username,
        String oldPassword,
        String newPassword
) {
}
