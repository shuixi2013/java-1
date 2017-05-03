package com.by.net.url;

import java.net.*;

public class InetSocketAddressTest {
	public static void main(String[] args){
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1",8080);
		System.out.println(isa.isUnresolved());
	}
}
