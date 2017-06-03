package com.by.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class BookTest {
//    对于 static 和 final 方法， Mockito 无法对其 when(…).thenReturn(…) 操作。
//    当我们连续两次为同一个方法使用 stub 的时候，他只会只用最新的一次。

    //final类 匿名类 和基本类型无法mock

    @Test
    public void testGetIsbn() {
        String word = "mocked Return";
        Book demo = mock(Book.class);
        when(demo.getIsbn()).thenReturn(word); //无参
//        Mockito.when(demo.getIsbn()).thenReturn(null);
//        Mockito.when(demo.getParam(Mockito.anyString())).thenThrow(new Exception());//异常
        when(demo.getParam(Mockito.anyString())).thenReturn(word);//任意参数
//        verify(mock).someMethod(anyInt(), anyString(), eq("third argument"));参数赋值
        when(demo.getParam(Mockito.matches(".*大爷$"))).thenReturn(word);//匹配参数

        when(demo.getTitle()).thenReturn(word).thenReturn(word + "1"); //多次调用
//        when(demo.getTitle()).thenReturn("one", "two", "three");//多次调用

        when(demo.getParam(anyString())).thenAnswer((invocation) -> {
            Object[] args = invocation.getArguments();
            Object mock = invocation.getMock();
            Object mockMethod = invocation.getMethod();
            return "called with arguments: " + args;
        }); //根据参数不同返回不同的值增加灵活性


//        when(demo).notify();  返回值为void模拟方法
//        doNothing().doThrow(new RuntimeException()).when(demo).setParam(anyString());

        assertEquals(demo.getTitle(), word);
        assertEquals(demo.getTitle(), word + "1");

        assertEquals(demo.getIsbn(), word);
        assertEquals(demo.getParam("RandomStr"), word);
        assertEquals(demo.getParam("隔壁李大爷"), word);

        verify(demo.getIsbn());//验证方法是否被mock且是否为所执行的参数调用
//        verify(demo).put(anyInt(), eq("hello"));

//        when(demo.getTitle()).thenCallRealMethod(); 用真实的方法
        System.out.println(demo.getTitle());

//        verify(mock, timeout(100)).someMethod(); //超时验证
//        verify(mock, timeout(100).times(2)).someMethod();
    }

    /**
     * 测试自定义类型参数的mock
     */
//    @Test
//    public void testMethodWithCustomParameter()
//    {
//        String word= "mocked Return";
//        Demo demo =  Mockito.mock(Demo.class);
//        Mockito.when(demo.methodCustomParameters(Mockito.argThat(new IsParameterClass()),
//                Mockito.anyString())).thenReturn(word);
//        Assert.assertEquals(demo.methodCustomParameters(new ParameterClass(), "你猜"), word);
//    }
//    //自定义一个与之匹配的matcher类
//    class IsParameterClass extends ArgumentMatcher<ParameterClass> {
//        public boolean matches(Object para) {
//            return para.getClass() == ParameterClass.class;
//        }
//    }
    @Test
    public void testGetTitle() {
        List<String> list = mock(List.class);

        when(list.get(anyInt())).thenReturn("hello", "world");

        String result = list.get(0) + list.get(1);

        verify(list, times(2)).get(anyInt()); //校验是不是调用了2次

        //verification using never(). never() is an alias to times(0)
        verify(list, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(list, atLeastOnce()).add("three times"); //至少调用一次
        verify(list, atLeast(2)).add("five times"); //至少调用多次
        verify(list, atMost(5)).add("three times");//最多调用N次

        assertEquals("helloworld", result);
    }
//
//    void testGetAuthors() {
//
//    }
//
//    void testGetPublication() {
//
//    }
//
//    void testGetYearOfPublication() {
//
//    }
//
//    void testGetNumberOfPages() {
//
//    }
//
//    void testGetImage() {
//
//    }
}

