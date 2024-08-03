package visitor_management;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginSystem {

    private Map<String, String> userDatabase;

    public LoginSystem() {
        userDatabase = new HashMap<>();
        // 初始化示例用户
        userDatabase.put("user1", hashPassword("password1"));
        userDatabase.put("user2", hashPassword("password2"));
    }

    public boolean authenticate(String username, String password) {
        if (!isUsernameValid(username) || !isPasswordValid(password)) {
            return false;
        }

        String storedPasswordHash = userDatabase.get(username);
        if (storedPasswordHash != null && storedPasswordHash.equals(hashPassword(password))) {
            return true;
        }
        return false;
    }

    private boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z]+$"); // 仅允许英文字符
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        LoginSystem system = new LoginSystem();
        Scanner scanner = new Scanner(System.in);

        String username = "user123";
        String password = "pass456";

        if (system.authenticate(username, password)) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败，用户名或密码错误！");
        }

        scanner.close();
    }
}
