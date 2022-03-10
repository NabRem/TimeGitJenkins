package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
		
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {Time.getTotalSeconds("10:00");});
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"00:01:00", "00:01:59", "00:01:12"})
	void testGetSecondsGoodBoundaries(String candidate) {
		int seconds=Time.getSeconds(candidate);
		assertTrue("The Seconds are not in Boundary and Good", (seconds>=00&&seconds<=59));
	}
	
	@Test 
	void testGetSecondsBad() {
		assertThrows(NumberFormatException.class,
				()->{Time.getTotalSeconds("10:00:00:0000");});
	}

	@ParameterizedTest
	@ValueSource(strings = {"00:59:00","00:00:59","00:30:00"})
	void testGetTotalMinutesGoodBoundaries(String candidate) {
		int minutes=Time.getTotalMinutes(candidate);
		assertTrue("The Minutes are not in Boundary and Good",(minutes>=00&&minutes<=59));
	}
	
	@Test 
	void testGetMinutesBad() {
		assertThrows(StringIndexOutOfBoundsException.class,
				()->{Time.getTotalSeconds("10:00");});
	}

	@ParameterizedTest
	@ValueSource(strings = {"00:59:59","12:59:59","05:10:33"})
	void testGetTotalHoursGoodBoundary(String candidate) {
		int hours=Time.getTotalHours(candidate);
		assertTrue("The Minutes are not in Boundary and Good",(hours>=00&&hours<=12));
	}
	
	@Test
	void testGetTotalHoursBad() {
		int hours=Time.getTotalHours("13:00:00");
		assertTrue("The Hours are not calculated accurately", hours>12);
	}
	
	@Test
	void testGetMilliSeconds() {
		int mill = Time.getTotalMilliSeconds("10:50:52:005");
		assertTrue("The Milliseconds were not calculated properly",mill==005);
	}

}
