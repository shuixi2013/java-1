import App
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

/**
 * description:
 * create       2017/6/16 18:39
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 *
 */
class AppTest {
    static App app

    @BeforeClass
    static void before() {
        app = new App(name: "baoyang")
    }

    @Test
    void test() {
        String name = app.name
        Assert.assertEquals(name,"baoyang")
    }
}
