package by.xml2bean;

import java.io.Serializable;

/*
 * <TRANSRESULT>
	-				<RETURNCODE>000000</RETURNCODE>
	-				<MESSAGE>操作成功</MESSAGE>
	-		</ TRANSRESULT >
	-	    <SERVER>
	-	          <SERVERTYPE></SERVERTYPE>
	-	          <SERVERURL></SERVERURL>
	-	    </SERVER>
	8011请求上传地址核心返回
	
 */
public class TransbodyRes8011 implements Transbody,Serializable {

	private static final long serialVersionUID = 8269706495376495653L;
	
	private Transresult TRANSRESULT;
	
	private Server SERVER;
	
	public Transresult getTRANSRESULT() {
		return TRANSRESULT;
	}

	public void setTRANSRESULT(Transresult tRANSRESULT) {
		TRANSRESULT = tRANSRESULT;
	}

	public Server getSERVER() {
		return SERVER;
	}

	public void setSERVER(Server sERVER) {
		SERVER = sERVER;
	}
	
	public class Server {
		public String SERVERTYPE;
		public String SERVERURL;
	}
	
}
