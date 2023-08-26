package com.hw.maxim;

import com.hw.maxim.exceptions.WrongLoginException;
import com.hw.maxim.exceptions.WrongPasswordException;

import java.util.regex.Pattern;

public class Main {

    public static final String REQUIREMENTS = "Логин/пароль должен содержать только латинские буквы, цифры и знак подчеркивания";

    public static void main(String[] args) {
        String login = "login";
        String password = "password";
        String confirmPassword = "password";

        try {
            checkLoginAndPassword(login, password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Проверка логина и пароля выполнена");
        }
    }

    public static void checkLoginAndPassword(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {
        checkLogin(login);
        checkPassword(password, confirmPassword);
    }

    private static void checkLogin(String login) throws WrongLoginException {

        String loginPatternStr = "^[A-Za-z0-9_-]{1,20}$";
        if (!regexpCheck(loginPatternStr, login)) {
            throw new WrongLoginException(String.format("Логин %s не подходит под требования: %s", login, REQUIREMENTS));
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {

        String passwordPatternStr = "^[A-Za-z0-9_-]{1,20}$";
        if (!regexpCheck(passwordPatternStr, password)) {
            throw new WrongPasswordException(String.format("Пароль не подходит под требования: %s", REQUIREMENTS));
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }

    private static boolean regexpCheck(String pattern, String str) {
        Pattern loginPattern = Pattern.compile(pattern);
        return loginPattern.matcher(str).matches();
    }


//    /*
//    * определение, что в строке есть только латинские буквы и подчеркивание согласно таблице ASCII
//    * */
//    private static boolean containsOnlyLatinCharacters(String str) {
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if ((c >= '\u0041' && c <= '\u005A') || (c >= '\u0061' && c <= '\u007A') || (c == '\u005F')) {
//                return true;
//            }
//        }
//        return false;
//    }
}
