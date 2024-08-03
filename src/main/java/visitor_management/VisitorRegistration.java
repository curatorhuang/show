package visitor_management;

/**
 * 访客登记类，用于处理访客登记逻辑。
 */
public class VisitorRegistration {
    private VisitorDatabase database;

    /**
     * 构造方法，初始化访客数据库
     */
    public VisitorRegistration() {
        database = new VisitorDatabase();
    }

    /**
     * 登记访客信息
     *
     * @param name    访客姓名
     * @param contact 访客联系方式
     */
    public void registerVisitor(String name, String contact) {
        Visitor visitor = new Visitor(name, contact);
        database.addVisitor(visitor);
        System.out.println("Visitor " + name + " registered successfully.");
    }

    /**
     * 显示所有访客信息
     */
    public void showAllVisitors() {
        System.out.println("All registered visitors:");
        for (Visitor visitor : database.getAllVisitors()) {
        }
    }
}
