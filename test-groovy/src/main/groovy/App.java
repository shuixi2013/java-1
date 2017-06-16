/**
 * Hello world!
 */
public class App {
    private String name;

    public static void main(String[] args) {
        GroovyTestMain groovyMain = new GroovyTestMain();
        groovyMain.setAge("12");
        groovyMain.setAlias("test");
        System.out.println(groovyMain.toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String test() {
        return getName();
    }
}
