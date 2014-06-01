package string;

public class StringToLong {

	/**
	 * Test
	 * Method to convert String to long
	 * 
	 * @param charSeq
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public static long stringToLong(char[] charSeq)
			throws NumberFormatException, Exception {

		if (charSeq == null)
			throw new NumberFormatException("EXCEPTION - input string is null");

		if (charSeq.length == 0)
			throw new NumberFormatException("EXCEPTION - input string is empty");

		boolean negative = false;
		int position = 0;
		int length = charSeq.length;
		int dec = 0;
		long longValue = 0;

		if (charSeq[0] == '-' || charSeq[0] == '+') {
			if (charSeq[0] == '-')
				negative = true;

			position = 1;
			length--;
		}

		while (length > 0) {
			longValue *= 10;

			dec = charSeq[position] - 48;

			if (dec > 9 || dec < 0)
				throw new NumberFormatException(
						"EXCEPTION - Illegal characters in input string. "
								+ "The String should contain characters between 0 - 9");

			longValue += dec;

			position++;
			length--;
		}

		if (!negative && longValue < 0) {
			throw new NumberFormatException(
					"EXCEPTION - Out of range after conversion. MAX value is "
							+ Long.MAX_VALUE);
		}

		if (negative && longValue == -longValue) {
			return longValue;
		}

		if (negative && longValue < 0) {
			throw new NumberFormatException(
					"EXCEPTION - Out of range after conversion. MIN value is "
							+ Long.MIN_VALUE);
		}

		return negative ? -longValue : longValue;
	}

	public static void main(String[] args) {
		try {
			
			
			System.out.println("MAX : " + Long.MAX_VALUE);
			System.out.println("MIN : " + Long.MIN_VALUE);
			System.out.println();

			String inputString = "-9223372036854775808";
			System.out.println("\nINPUT: " + inputString);
			long ans = stringToLong(inputString.toCharArray());
			System.out.println("String " + inputString + " in Long is " + ans);

			/*
			 * inputString = "-9223372036854775807";
			 * System.out.println("INPUT: " + inputString); ans =
			 * stringToLong(inputString.toCharArray());
			 * System.out.println("String " + inputString + " in Long is " +
			 * ans);
			 */

			/*
			 * inputString = "-9223372036854775809";
			 * System.out.println("INPUT: " + inputString); ans =
			 * stringToLong(inputString.toCharArray());
			 * System.out.println("String " + inputString + " in Long is " +
			 * ans);
			 */

			inputString = "1L"; 
			
			System.out.println("\nINPUT: " + inputString);
			ans = stringToLong(inputString.toCharArray());
			System.out.println("String " + inputString + " in Long is " + ans);

			/*
			 * inputString = "9223372036854775807"; System.out.println("INPUT: "
			 * + inputString); ans = stringToLong(inputString.toCharArray());
			 * System.out.println("String " + inputString + " in Long is " +
			 * ans);
			 */

			/*
			 * inputString = "9223372036854775806"; System.out.println("INPUT: "
			 * + inputString); ans = stringToLong(inputString.toCharArray());
			 * System.out.println("String " + inputString + " in Long is " +
			 * ans);
			 */

			/*
			 * inputString = "9223372036854775808"; System.out.println("INPUT: "
			 * + inputString); ans = stringToLong(inputString.toCharArray());
			 * System.out.println("String " + inputString + " in Long is " +
			 * ans);
			 */

			inputString = "5635ababch";
			System.out.println("\nINPUT: " + inputString);
			ans = stringToLong(inputString.toCharArray());
			System.out.println("String " + inputString + " in Long is " + ans);

		} catch (NumberFormatException numberFormatException) {
			System.out.println(numberFormatException.toString());

		} catch (Exception exception) {
			System.out.println(exception.toString());

		}

	}
}
