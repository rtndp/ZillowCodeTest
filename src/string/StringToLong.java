package string;

public class StringToLong {

	/**
	 * Test Method to convert String to long
	 * 
	 * @param charSeq
	 * @return
	 * 
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public static long stringToLong(char[] charSeq)
			throws NumberFormatException, Exception {
		/**
		 * If the input String is null, I am throwing a NumberFormatException,
		 * with a custom EXCEPTION Message
		 */
		if (charSeq == null)
			throw new NumberFormatException("EXCEPTION - input string is null");

		/**
		 * Similarly, if the input String is of length 0, a
		 * NumberFormatException is thrown
		 */
		if (charSeq.length == 0)
			throw new NumberFormatException("EXCEPTION - input string is empty");

		/**
		 * negative is a boolean flag to indicate if the input String has a -ve
		 * sign, indicating that the number would possibly be -ve
		 */
		boolean negative = false;

		int position = 0;
		int length = charSeq.length;
		int dec = 0;

		/**
		 * This is the long value that gets constructed incrementally with every
		 * character
		 */
		long longValue = 0;

		/**
		 * Check if the first character is - or +
		 */
		if (charSeq[0] == '-' || charSeq[0] == '+') {
			/**
			 * if the first character is -, set boolean flag to true
			 */
			if (charSeq[0] == '-')
				negative = true;

			/**
			 * This is to indicate that, the parsing should begin from the first
			 * character, and not the zeroth character, since it is already
			 * accounted for
			 */
			position = 1;
			length--;
		}

		/**
		 * This condition checks if the single character strings contains only +
		 * or - are by checking if its length is 0 after omitting the first
		 * character
		 */
		if (length == 0)
			throw new NumberFormatException(
					"EXCEPTION - Illegal characters in input string. "
							+ "The String should contain characters between 0 - 9");

		while (length > 0) {
			longValue *= 10;

			/**
			 * Characters 0 - 9, have the corresponding decimal range of 48 to
			 * 57. This line converts the character to its respective decimal
			 * equivalent
			 */
			dec = charSeq[position] - 48;

			/**
			 * Here the decimal equivalent is checked for legality. Characters
			 * between 0 - 9 are considered legal
			 */
			if (dec > 9 || dec < 0)
				throw new NumberFormatException(
						"EXCEPTION - Illegal characters in input string. "
								+ "The String should contain characters between 0 - 9");

			longValue += dec;

			position++;
			length--;

			/**
			 * This condition is designed to handle the +ve overflow i.e.,
			 * 9223372036854775808, this number overflows beyond its permissible
			 * Long.MAX value
			 */
			if (!negative && longValue < 0 && length == 0) {
				throw new NumberFormatException(
						"EXCEPTION - Out of range after conversion. MAX value is "
								+ Long.MAX_VALUE);
			}

			/**
			 * This special condition checks for the boundary condition where
			 * the input value is exactly equal to the Long.MIN value.
			 * 
			 */
			if (negative && longValue == -longValue && length == 0) {
				return longValue;
			}

			/**
			 * This condition is designed to handle the -ve overflow i.e.,
			 * -9223372036854775809, this number overflows beyond its
			 * permissible Long.MIN value
			 */
			if (negative && longValue < 0 && length == 0) {
				throw new NumberFormatException(
						"EXCEPTION - Out of range after conversion. MIN value is "
								+ Long.MIN_VALUE);
			}
		}
		/**
		 * Based on the input value, long value with desired +ve and -ve is
		 * returned
		 */
		return negative ? -longValue : longValue;

	}

	public static void main(String[] args) {
		try {
			System.out.println(Long.parseLong("+9223372036854775807"));
			/**
			 * System.out.println("MAX : " + Long.MAX_VALUE);
			 * System.out.println("MIN : " + Long.MIN_VALUE);
			 */

			/**
			 * The following commented section of the main method has all
			 * possible cases cover during testing. It also indicates weather
			 * the input is VALID or INVALID and in each case, the reason for
			 * its validity or invalidity is mentioned
			 */

			/**
			 * VALID String, MAX value of long -
			 */
			String inputString = "9223372036854775807";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "+9223372036854775807L";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "9223372036854775807L";

			/**
			 * VALID String, MIN value of long -
			 */
			// String inputString = "-9223372036854775808";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "-9223372036854775808L";

			/**
			 * INVALID input String, overflow beyond MAX value-
			 */
			// String inputString = "9223372036854775808";

			/**
			 * INVALID input string, overflow beyond MIN value-
			 */
			// String inputString = "-9223372036854775809";

			/**
			 * INVALID input String with an illegal character
			 */
			// String inputString = "+245324+23452L";

			/**
			 * INVALID input String, only one character '+'
			 */
			// String inputString = "+";

			/**
			 * INVALID input String, only one character '-'
			 */
			// String inputString = "-";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "-0L";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "+0L";

			/**
			 * VALID input String, some random number
			 */
			// String inputString = "0";

			/**
			 * VALID input String, some random number
			 */
			// String inputString = "-0";

			/**
			 * VALID input String, some random number
			 */
			// String inputString = "+0";

			/**
			 * INVALID input String, made of up - and L
			 */
			// String inputString = "-L";

			/**
			 * INVALID input String, made of up + and L
			 */
			// String inputString = "+L";

			/**
			 * VALID input String, some random number
			 */
			// String inputString = "45243";

			/**
			 * VALID input String, some random +ve number
			 */
			// String inputString = "+45243";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "+45243L";

			/**
			 * VALID input String, some random -ve number
			 */
			// String inputString = "-45243";

			/**
			 * INVALID input String, although the numerical part of it is in
			 * range, it has a trailing char 'L'
			 */
			// String inputString = "-45243L";

			/**
			 * INVALID input String, set of characters
			 */
			// String inputString = "ababd";

			/**
			 * INVALID input String, very very long number, causing overflow
			 */
			// String inputString = "4524354635635635635735736573437357";

			System.out.println("\nINPUT: " + inputString);

			/**
			 * Call to stringToLong method
			 */
			long ans = stringToLong(inputString.toCharArray());

			System.out.println("String " + inputString + " in Long is " + ans);

		} catch (NumberFormatException numberFormatException) {
			/**
			 * All exceptions relating to number format are handled here
			 */
			System.out.println(numberFormatException.toString());

		} catch (Exception exception) {
			/**
			 * Any other types of exceptions are handled here
			 */
			System.out.println(exception.toString());

		}

	}
}
