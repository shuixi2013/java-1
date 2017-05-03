package com.by.test;
import java.io.UnsupportedEncodingException;
import javax.script.*;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.script.ScriptEngineManager;

public class Test {
	public static void main(String[] args) {
		String mockUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_base&state=abc251cc76a1f73f447259061be6b91ce0cdf3be510dcd7048d4fb0f5c1752b1#wechat_redirect";
		String platController = "http://xszctest.aviva-cofco.com.cn/wechatplatform/services/agent/query/agentBywechat?module=aclife&redirect_url={0}";
		String wxappId = "wxefb4589131cfcfab";
		String appShareController = "http://172.28.0.140:8080/ACLifeWei/api/shareControl/"+"123321";
		String tmpFile = MessageFormat.format(platController, appShareController);
		try {
			tmpFile = URLEncoder.encode(tmpFile, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		tmpFile = MessageFormat.format(mockUrl, wxappId,tmpFile);
		System.out.println(tmpFile);
//		Character.
//		Objects.
//		ScriptEngineManager sem = new ScriptEngineManager();
		
	}
}







