package visitor_management;

import java.util.ArrayList;
import java.util.List;

/**
 * 访客数据库类，用于存储和管理访客信息
 */
public class VisitorDatabase {
    private List<Visitor> visitors;

    /**
     * 构造方法，初始化访客列表
     */
    public VisitorDatabase() {
        visitors = new ArrayList<>();
    }

    /**
     * 添加访客到数据库
     *
     * @param visitor 访客对象
     */
    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    /**
     * 获取所有访客信息
     *
     * @return 访客列表
     */
    public List<Visitor> getAllVisitors() {
        return visitors;
    }
}
