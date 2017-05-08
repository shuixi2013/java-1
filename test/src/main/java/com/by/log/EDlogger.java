package by.log;

public class EDlogger extends AbstractEDlogger {

	Class<?> targetClass;

	public EDlogger() {

	}

	public EDlogger(Class<?> clazz, boolean isAdd) {
		this.targetClass = clazz;
		this.isAdd = isAdd;
	}

	public EDlogger(Class<?> clazz) {
		this(clazz, false);
	}

	public Class<?> getLoggerClass() {
		return targetClass;
	}

}
