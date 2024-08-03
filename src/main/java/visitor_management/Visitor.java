package visitor_management;

/**
 * 访客类，用于存储访客信息
 */
public class Visitor {
    private String name;
    private String contact;

    /**
     * 构造方法，初始化访客信息
     *
     * @param name    访客姓名
     * @param contact 访客联系方式
     */
    public Visitor(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    /**
     * 获取访客姓名
     *
     * @return 访客姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 获取访客联系方式
     *
     * @return 访客联系方式
     */
    public String getContact() {
        return contact;
    }

    /**
     * 以字符串形式返回访客信息
     *
     * @return 访客信息字符串
     */
    @Override
    public String toString() {
        return "Visitor{name='" + name + "', contact='" + contact + "'}";
    }
}
