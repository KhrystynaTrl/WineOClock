package it.wineoclock.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank(message = "email mandatory")
    private String email;
    @NotBlank(message = "password mandatory")
    private String password;
    @Valid
    private UserDetailDto userDetail;

    public UserDto(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetailDto getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetailDto userDetail) {
        this.userDetail = userDetail;
    }
}
