package com.by.xml2bean;

import java.io.Serializable;

/**
 * 核心交互报文头,请求和返回一致
 * @author baoyang
 */
public class Transhead implements Serializable {
	
	private static final long serialVersionUID = -5553098528733782327L;
	
	private String VERSION = "1.0";
	private String SYSCODE = "002";
	private String TRANUSER =""; //如果 默认为null,生成xml的时候不会出现
	private String TRANPASSWD ="";
	private String TRANSCODE =""; //请求类型
	
	public Transhead(){
		super();
	}
	
	public Transhead(String vERSION, String sYSCODE, String tRANUSER, String tRANPASSWD, String tRANSCODE) {
		super();
		VERSION = vERSION;
		SYSCODE = sYSCODE;
		TRANUSER = tRANUSER;
		TRANPASSWD = tRANPASSWD;
		TRANSCODE = tRANSCODE;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getSYSCODE() {
		return SYSCODE;
	}
	public void setSYSCODE(String sYSCODE) {
		SYSCODE = sYSCODE;
	}
	public String getTRANUSER() {
		return TRANUSER;
	}
	public void setTRANUSER(String tRANUSER) {
		TRANUSER = tRANUSER;
	}
	public String getTRANPASSWD() {
		return TRANPASSWD;
	}
	public void setTRANPASSWD(String tRANPASSWD) {
		TRANPASSWD = tRANPASSWD;
	}
	public String getTRANSCODE() {
		return TRANSCODE;
	}
	public void setTRANSCODE(String tRANSCODE) {
		TRANSCODE = tRANSCODE;
	}

}
