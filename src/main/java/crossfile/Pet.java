package crossfile;

public class Pet {
    // 这个一个测试文档123
    //这里也有冲突
    private  String name;
    private  int age;
    private  String type;
    private  String color;
    private  String owner;
    private boolean  vaccinated;
    private boolean  spayed;


    public Pet(String name, int age, String type, String color, String owner) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.color = color;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

}

