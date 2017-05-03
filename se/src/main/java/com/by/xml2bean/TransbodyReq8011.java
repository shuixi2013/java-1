package com.by.xml2bean;

import java.io.Serializable;

public class TransbodyReq8011 implements Transbody,Serializable {

	private static final long serialVersionUID = 4873515063988840984L;
	
	private String MANAGECOM = ""; //机构管理号

	public TransbodyReq8011(String mANAGECOM) {
		super();
		MANAGECOM = mANAGECOM;
	}

	public String getMANAGECOM() {
		return MANAGECOM;
	}

	public void setMANAGECOM(String mANAGECOM) {
		MANAGECOM = mANAGECOM;
	}
	
}
