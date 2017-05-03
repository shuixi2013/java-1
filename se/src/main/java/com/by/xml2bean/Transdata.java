package com.by.xml2bean;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("TRANSDATA")
public class Transdata implements Serializable {
	
	private static final long serialVersionUID = 8611882721598777219L;

	private Transhead TRANSHEAD;
	
	private Transbody TRANSBODY;
	
	public Transhead getTranshead() {
		return TRANSHEAD;
	}
	public void setTranshead(Transhead transhead) {
		this.TRANSHEAD = transhead;
	}
	public Transbody getTransbody() {
		return TRANSBODY;
	}
	public void setTransbody(Transbody transbody) {
		this.TRANSBODY = transbody;
	}
	
}

