package com.by.xml2bean;

import com.thoughtworks.xstream.XStream;

public class Test {
	public static void testReq80011() {
		Transdata ts = new Transdata();
		Transhead th = new Transhead();
		th.setTRANSCODE("80011");
		Transbody tb = new TransbodyReq8011("000001");
		ts.setTransbody(tb);
		ts.setTranshead(th);
		XStream xstream = new XStream();
		xstream.aliasSystemAttribute(null, "class");
		xstream.alias("TRANSDATA", Transdata.class);
		System.out.println("Req8011\n"+xstream.toXML(ts));
	}
	
	public static void testRes80011() {
		String xml = "<TRANSDATA><TRANSHEAD><VERSION>1.0</VERSION><SYSCODE>002</SYSCODE><TRANUSER>admin</TRANUSER>"
				+ "<TRANPASSWD>111111</TRANPASSWD>" + " <TRANSCODE>80001</TRANSCODE>" + "</TRANSHEAD>" + " <TRANSBODY>"
				+ "  <TRANSRESULT><RETURNCODE>000000</RETURNCODE><MESSAGE>操作成功</MESSAGE></TRANSRESULT>" 
				+ "<SERVER><SERVERTYPE>qcloud</SERVERTYPE><SERVERURL>http://www.qcloud.com</SERVERURL></SERVER></TRANSBODY>" + "</TRANSDATA>";
		XStream xs1 = new XStream();
		xs1.alias("TRANSDATA", Transdata.class);
		xs1.alias("TRANSBODY", Transbody.class, TransbodyRes8011.class);
		Transdata tmp = (Transdata) xs1.fromXML(xml);
		
		String user = tmp.getTranshead().getTRANUSER();
		TransbodyRes8011 user1 = (TransbodyRes8011)tmp.getTransbody();
		String tmp1 =  user1.getTRANSRESULT().RETURNCODE;
		String tmp2 =  user1.getSERVER().SERVERURL;
		System.out.println("Req8011\n"+user+"-----"+tmp1+"---"+tmp2);
	}
	
	public static void testReq80012() {
		Transdata ts = new Transdata();
		Transhead th = new Transhead();
		th.setTRANSCODE("80012");
		ts.setTranshead(th);
		
		TransbodyReq8012 tb = new TransbodyReq8012();
		TransbodyReq8012.DOC doc = new TransbodyReq8012.DOC();
		doc.DOCCODE = "123";
		doc.GROUPNO = "321";
		doc.CHANNEL = "123";
		doc.BUSSTYPE = "123";
		doc.SUBTYPE = "123";
		doc.NUMPAGES = "123";
		doc.MANAGECOM = "123";
		doc.SCANNO = "123";
		doc.SCANOPERATOR = "123";
		doc.SCANDATE = "123";
		doc.SCANTYPE = "123";
		doc.DOCID = "123";
		doc.SERVERTYPE = "123";
		
		TransbodyReq8012.PAGE page = new TransbodyReq8012.PAGE();
		page.PAGENO = "pageno";
		page.PAGENAME = "pagename";
		page.PAGEPATH = "pagepath";
		
		TransbodyReq8012.PAGE page1 = new TransbodyReq8012.PAGE();
		page1.PAGENO = "pageno1";
		page1.PAGENAME = "pagename1";
		page1.PAGEPATH = "pagepath1";
		
		doc.PAGE.add(page);
		doc.PAGE.add(page1);
		
		TransbodyReq8012.DOC doc2 = new TransbodyReq8012.DOC();
		doc2.DOCCODE = "56765";
		tb.getDocs().add(doc);
		tb.getDocs().add(doc2);
		
		ts.setTransbody(tb);
		XStream xstream = new XStream();
		xstream.aliasSystemAttribute(null, "class");
		xstream.alias("TRANSDATA", Transdata.class);
		xstream.alias("DOC", TransbodyReq8012.DOC.class);
		xstream.alias("PAGE", TransbodyReq8012.PAGE.class);
		xstream.addImplicitCollection(TransbodyReq8012.class, "docs");
		xstream.addImplicitCollection(TransbodyReq8012.DOC.class, "PAGE");
		System.out.println("Req8012\n"+xstream.toXML(ts));
	}
	
	public static void testRes80012() {
		String xml = "<TRANSDATA><TRANSHEAD><VERSION>1.0</VERSION><SYSCODE>002</SYSCODE><TRANUSER>admin</TRANUSER>"
				+ "<TRANPASSWD>111111</TRANPASSWD>" + " <TRANSCODE>80012</TRANSCODE>" + "</TRANSHEAD>" + " <TRANSBODY>"
				+ "  <TRANSRESULT><RETURNCODE>000000</RETURNCODE><MESSAGE>操作成功</MESSAGE></TRANSRESULT>" 
				+ "<DOC><DOCCODE>123</DOCCODE><GROUPNO>321</GROUPNO><CHANNEL>123</CHANNEL>"
				+"<BUSSTYPE>123</BUSSTYPE> <SUBTYPE>123</SUBTYPE><NUMPAGES>123</NUMPAGES></DOC>"
				+"<DOC><DOCCODE>56765</DOCCODE></DOC>"
				+"</TRANSBODY>" + "</TRANSDATA>";
		System.out.println(xml);
		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);  
		xstream.alias("TRANSDATA", Transdata.class);
		xstream.alias("TRANSBODY", Transbody.class, TransbodyRes8012.class);
//		xstream.addImplicitCollection(TransbodyReq8012.class, "docs");
		xstream.alias("DOC", TransbodyReq8012.DOC.class);

//		xstream.addImplicitCollection(TransbodyReq8012.DOC.class, "PAGE");
		
		Transdata tmp = (Transdata) xstream.fromXML(xml);
		
		String user = tmp.getTranshead().getTRANUSER();
		TransbodyRes8012 user1 = (TransbodyRes8012)tmp.getTransbody();
		System.out.println(user1.getDocs().size());
		System.out.println("Req8012\n"+user+"-----"+user1.getDocs().get(0).BUSSTYPE+"---"+user1.getDocs().get(1));
	}
	
	
	public static void main(String[] args) {
//		testReq80011();
//		testRes80011();
		
		testReq80012();
//		testRes80012();
	}
	
}
