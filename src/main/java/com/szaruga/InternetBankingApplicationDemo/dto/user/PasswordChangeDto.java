package com.szaruga.InternetBankingApplicationDemo.dto.user;

public class PasswordChangeDto {
    //todo zrobic to dzis tez
    private String password;
    private String repeatChangePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatChangePassword() {
        return repeatChangePassword;
    }

    public void setRepeatChangePassword(String repeatChangePassword) {
        this.repeatChangePassword = repeatChangePassword;
    }
}
