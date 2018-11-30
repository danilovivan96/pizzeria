package ru.mail.danilov.pizzeria.service.dto;

public class ChangePasswordDto {
    private Long user;
    private String oldPassword;
    private String newPassword;
    private String replicateNewPassword;

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReplicateNewPassword() {
        return replicateNewPassword;
    }

    public void setReplicateNewPassword(String replicateNewPassword) {
        this.replicateNewPassword = replicateNewPassword;
    }
}
