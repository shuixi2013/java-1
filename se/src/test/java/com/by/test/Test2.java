package com.by.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test2 {
	
	public static void main(String[] args) {
		testString();
	}
	
	
	private static void testString(){  
	
	 ScriptEngineManager manager = new ScriptEngineManager();  
	    ScriptEngine engine = manager.getEngineByName("js");  
	    engine.put("age", 21);  
	    engine.put("buf", new StringBuffer());  
	    try {  
	        System.err.println();  
	        long l=0;  
	        // 纯循环,也要100毫秒  
	        l = System.currentTimeMillis();  
	        engine.eval("for(var i=0;i<50000;i++){}");  
	        System.err.println(System.currentTimeMillis()-l);  
	          
	        // 直接字符串拼接,稍微多于4秒  
	        l = System.currentTimeMillis();  
	        engine.eval("var str='a';for(var i=0;i<50000;i++){str+='a';}");  
	        System.err.println(System.currentTimeMillis()-l);  
	          
	        // 数组字符串拼接,稍微小于4秒  
	        l = System.currentTimeMillis();  
	        engine.eval("var str=[];for(var i=0;i<50000;i++){str.push('a');} str.join('');");  
	        System.err.println(System.currentTimeMillis()-l);  
	          
	        // 脚本中调用StringBuffer, 600到700毫秒  
	        l = System.currentTimeMillis();  
	        engine.eval("for(var i=0;i<50000;i++){buf.append('a');}");  
	        System.err.println(System.currentTimeMillis()-l);  
	          
	        // java中字符串直接拼接, 8到15秒,不稳定波动很大  
	        l = System.currentTimeMillis();  
	        String str = "a";  
	        for(int i=0;i<50000;i++){str+="a";}  
	        System.err.println(System.currentTimeMillis()-l);  
	  
	        // java中使用StringBuffer,少于5毫秒  
	        l = System.currentTimeMillis();  
	        StringBuffer buf = new StringBuffer();  
	        for(int i=0;i<50000;i++){buf.append("a");}  
	        System.err.println(System.currentTimeMillis()-l);  
	          
	    } catch (ScriptException e) {  
	        e.printStackTrace();  
	    }  
	}  
	
}
