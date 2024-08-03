package visitor_management;

/**
 * 主类，用于启动访客登记系统
 */
public class Main {
    public static void main(String[] args) {
        // 创建访客登记实例
        VisitorRegistration registration = new VisitorRegistration();
        // 注册一些访客
        registration.registerVisitor("Alice", "123-456-7890");
        registration.registerVisitor("Bob", "098-765-4321");
        // 显示所有访客
        registration.showAllVisitors();
    }
}
