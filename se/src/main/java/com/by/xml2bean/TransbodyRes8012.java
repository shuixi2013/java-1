package com.by.xml2bean;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

//-	<TRANSBODY>
//-		<TRANSRESULT>
//-				<RETURNCODE>000000</RETURNCODE>
//-				<MESSAGE>操作成功</MESSAGE>
//-		</ TRANSRESULT >
//-	<DOC>
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
//-				<DOCID>341234</DOCID>
//-	               <SCANTYPE>0</ SCANTYPE >
//-	          <SERVERTYPE>01</SERVERTYPE>
//-	</DOC>
//-	</TRANSBODY>
public class TransbodyRes8012 implements Transbody,Serializable {

	private static final long serialVersionUID = -6264124051435313270L;
	
	private Transresult TRANSRESULT;
	
	@XStreamImplicit(itemFieldName="DOC")
	private List<DOC> docs;
	
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
	}
	
	public Transresult getTRANSRESULT() {
		return TRANSRESULT;
	}

	public void setTRANSRESULT(Transresult tRANSRESULT) {
		TRANSRESULT = tRANSRESULT;
	}

}
