package by.time.joda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import by.time.joda.factory.SystemFactory;

public class LocalDateTimeTest {
	private static Logger logger = LogManager.getLogger(LocalDateTimeTest.class);

	public static void main(String[] args) {

		example3_1();
		example3_2();
	}

	private static void example3_1() {
		LocalDate now = SystemFactory.getClock().getLocalDate();
		logger.info("Now:" + now.toString());
		LocalTime localTime = new LocalTime(13, 30, 26, 0);// 1:30:26PM
		logger.info("Then:localTime " + localTime.toString());
	}

	private static void example3_2() {
		LocalDate now = SystemFactory.getClock().getLocalDate();
		LocalDate lastDayOfPreviousMonth = now.minusMonths(1).dayOfMonth().withMaximumValue();
		logger.info("Now:" + lastDayOfPreviousMonth.toString());
		LocalDate electionDate = now.monthOfYear().setCopy(11) // November
				.dayOfMonth() // Access Day Of Month Property
				.withMinimumValue() // Get its minimum value
				.plusDays(6) // Add 6 days
				.dayOfWeek() // Access Day Of Week Property
				.setCopy(1) // Set to Monday (it will round down)
				.plusDays(1); // Gives us Tuesday
		logger.info("Then:" + electionDate.toString());
	}

}
