package com.by.introspector;

/**
 * description:
 * create       2017/6/15 10:42
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public final class BeanInfoTest {
    private BeanInfoTest() {
    }

    /**
     * @param args a
     */
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("peida");
        try {
            BeanInfoUtil.getProperty(userInfo, "userName");

            BeanInfoUtil.setProperty(userInfo, "userName");

            BeanInfoUtil.getProperty(userInfo, "userName");

            BeanInfoUtil.setPropertyByIntrospector(userInfo, "userName");

            BeanInfoUtil.getPropertyByIntrospector(userInfo, "userName");

            BeanInfoUtil.setProperty(userInfo, "age");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
