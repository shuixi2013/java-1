package com.by.log;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author lifeTime
 * 
 */
public abstract class AbstractEDlogger implements IEDlogger {
	Map<Object, Object> loggerMsgs;
	private static Logger logger = LogManager.getLogger(AbstractEDlogger.class);
	Object lastMsgKey;
	boolean isAdd = false;

	public AbstractEDlogger() {
		loggerMsgs = new LinkedHashMap<Object, Object>();
	}

	public AbstractEDlogger(Map<Object, Object> msgsMap) {
		loggerMsgs = msgsMap;
	}

	public boolean isAddModel() {
		return isAdd;
	}

	public void addMsg(Object key, Object msg) {
		if (!isAddModel()) {
			loggerMsgs.clear();
		}
		if (key == null) {
			StringBuffer keyTemp = new StringBuffer();
			keyTemp.append(getLoggerClass() == null ? "" : getLoggerClass().getName());
//			keyTemp.append(DateTimeUtils.format(Calendar.getInstance().getTime(), defaultFormatStr));
			lastMsgKey = keyTemp;
		} else
			lastMsgKey = key;

		loggerMsgs.put(lastMsgKey, msg);
	}

	protected void addMsg(Object msg) {
		addMsg(null, msg);
	}

	public void logger(Object msg) {
		addMsg(msg);
		showMsg(null);
	}

	/**
	 * Ϊ�����log4j���logger.info();
	 * 
	 * @param msg
	 */
	public void info(Object msg) {
		logger(msg);
	}

	protected void showMsg(Object key) {
		if (key == null)
			logger.info(loggerMsgs.get(lastMsgKey));
		else
			logger.info(loggerMsgs.get(key));
	}

	protected void showMsg() {
		showMsg(null);
	}

	public Map<Object, Object> getAllLogger() {
		return loggerMsgs;
	}

	public void clearMsg() {
		loggerMsgs.clear();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Iterator<Object> it = loggerMsgs.keySet().iterator(); it.hasNext();) {
			sb.append(loggerMsgs.get(it.next()).toString());
		}
		return sb.toString();
	}

}
