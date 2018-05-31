package com.resolution.infra.utils;

public class RegexUtil {
    public static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
    public static final String PHONE_PATTERN = "^\\+?([0-9\\-]?){7,11}[0-9]$";
}