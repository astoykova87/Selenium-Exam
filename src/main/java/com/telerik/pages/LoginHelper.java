package com.telerik.pages;

public class LoginHelper {
    private LoginPage loginPage;

    public LoginHelper(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void loginWithValidCredentials(String email, String password) {
        loginPage.navigate();
        loginPage.submitLoginForm(email, password);
    }
}
