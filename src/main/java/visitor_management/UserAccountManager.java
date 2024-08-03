package visitor_management;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserAccountManager {
    private Map<String, String> userAccounts;

    public UserAccountManager() {
        userAccounts = new ConcurrentHashMap<>(); // 1.使用线程安全的集合类
        initializeSampleAccounts(); // 4. 初始化示例账号
    }

    public void addUser(String username, String password) {
        if (userAccounts.containsKey(username)) {
            System.out.println("用户名已存在！");
        } else {
            if (isValid(username, password)) { // 2.增强输入验证
                String encryptedPassword = encryptPassword(password); // 1.数据加密存储
                userAccounts.put(username, encryptedPassword);
                System.out.println("用户添加成功！");
            } else {
                System.out.println("用户名或密码不符合要求！");
            }
        }
    }

    public boolean authenticateUser(String username, String password) {
        String encryptedPassword = encryptPassword(password);
        return userAccounts.containsKey(username) && userAccounts.get(username).equals(encryptedPassword);
    }

    private boolean isValid(String username, String password) { // 2.增强输入验证
        return username != null && username.length() >= 3 && password != null && password.length() >= 6;
    }

    private String encryptPassword(String password) { // 1.数据加密存储
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不可用");
        }
    }

    private void initializeSampleAccounts() { // 4. 初始化示例账号
        addUser("user1", "password1");
        addUser("user2", "password2");
        addUser("admin", "admin123");
    }

    public static void main(String[] args) {
        UserAccountManager manager = new UserAccountManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 添加用户");
            System.out.println("2. 验证用户");
            System.out.println("3. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 处理换行符

            switch (choice) {
                case 1:
                    System.out.print("请输入用户名：");
                    String username = scanner.nextLine();
                    System.out.print("请输入密码：");
                    String password = scanner.nextLine();
                    manager.addUser(username, password);
                    break;
                case 2:
                    System.out.print("请输入用户名：");
                    username = scanner.nextLine();
                    System.out.print("请输入密码：");
                    password = scanner.nextLine();
                    if (manager.authenticateUser(username, password)) {
                        System.out.println("验证成功！");
                    } else {
                        System.out.println("用户名或密码错误！");
                    }
                    break;
                case 3:
                    System.out.println("退出程序");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择！");
                    break;
            }
        }
    }
}
