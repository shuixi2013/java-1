package by.log;

public interface IEDlogger {

	void logger(Object msg);

	void addMsg(Object key, Object msg);

	Class<?> getLoggerClass();

	void clearMsg();
}
