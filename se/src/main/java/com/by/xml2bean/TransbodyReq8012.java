package com.by.xml2bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//<DOC>
//-				<DOCCODE>2323233232</DOCCODE>
//-				<GROUPNO></GROUPNO>
//-				<CHANNEL></CHANNEL>
//-				<BUSSTYPE>TB</BUSSTYPE>
//-				<SUBTYPE>DWBX001</SUBTYPE>
//-			     <NUMPAGES>2</NUMPAGES>
//-				<MANAGECOM>86</MANAGECOM>
//-				<SCANNO>TB06000000001</SCANNO>
//-				<SCANOPERATOR>admin001</SCANOPERATOR>
//-				<SCANDATE>2014-06-30 12:12:33</SCANDATE>
//-	              <SCANTYPE>0</ SCANTYPE>
//-				<DOCID></DOCID>
//-	              <SERVERTYPE>01</SERVERTYPE>
//-	              <PAGE>
//-					<PAGENO>1</ PAGENO>
//				<PAGENAME>3.tif</PAGENAME>
//-					<PAGEPATH></ PAGEPATH>
//-				</PAGE>
//-				<PAGE>
//-					<PAGENO>2</ PAGENO >
//-					<PAGENAME>41234.tif</PAGENAME>
//-					<PAGEPATH>’</ PAGEPATH>
//-				</PAGE>
//-		</DOC>
public class TransbodyReq8012 implements Transbody,Serializable {

	private static final long serialVersionUID = -5516458734124140937L;
	
	private List<DOC> docs = new ArrayList<DOC>();

	public List<DOC> getDocs() {
		return docs;
	}

	public void setDocs(List<DOC> docs) {
		this.docs = docs;
	}

	public static class DOC {
		public String DOCCODE;
		public String GROUPNO;
		public String CHANNEL;
		public String BUSSTYPE;
		public String SUBTYPE;
		public String NUMPAGES;
		public String MANAGECOM;
		public String SCANNO;
		public String SCANOPERATOR;
		public String SCANDATE;
		public String SCANTYPE;
		public String DOCID;
		public String SERVERTYPE;
		public List<PAGE> PAGE = new  ArrayList<PAGE>();
	}
	
	public static class PAGE {
		public String PAGENO;
		public String PAGENAME;
		public String PAGEPATH;
	}
	
}
