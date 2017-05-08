package by.beanutils;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SampleBean {
    private String name;
    private Integer age;
    private String[] array;
    private List<String> list;
    private Map<String, String> map;
    private NestedBean nestedBean;
    private URL url;
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public NestedBean getNestedBean() {
        return nestedBean;
    }

    public void setNestedBean(NestedBean nestedBean) {
        this.nestedBean = nestedBean;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    protected static class NestedBean {
        private String nestedProperty;

        public String getNestedProperty() {
            return nestedProperty;
        }

        public void setNestedProperty(String nestedProperty) {
            this.nestedProperty = nestedProperty;
        }
    }
}


