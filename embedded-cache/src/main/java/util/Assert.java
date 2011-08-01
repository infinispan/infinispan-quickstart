package util;

public class Assert {

	private Assert() {
	}
	
	public static void assertTrue(boolean condition) {
		if (!condition)
			throw new AssertionError();
	}
	
	public static void assertFalse(boolean condition) {
		if (condition)
			throw new AssertionError();
	}
	
	public static void assertEqual(int expected, int actual) {
		if (expected != actual)
			throw new AssertionError("Expected " + expected + " but was " + actual);
	}
	
	public static void assertEqual(Object expected, Object actual) {
		if (expected == null && actual == null)
			return;
		else if (expected == null && actual != null)
			throw new AssertionError("Expected null but was " + actual);
		else if (!expected.equals(actual))
			throw new AssertionError("Expected " + expected + " but was " + actual);
	}
	
}

