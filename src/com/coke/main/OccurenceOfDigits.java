package com.coke.main;

public class OccurenceOfDigits {

	public static void main(String[] args) throws Exception {

		if(validateInputArguments(args))
		{
			getOccurrence(args);
		}
	}

	private static boolean validateInputArguments(String[] args) throws Exception
	{
		boolean isValid = true;
		try{
			if(!(args.length > 0 && args.length <= 3)) {
				throw new Exception("Input argument length is incorrect");
			}
			if(!( Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) )
			{
				throw new Exception("Start no. or End no. in argument list is not > 0");
			}
			if(Integer.parseInt(args[0]) > Integer.parseInt(args[1]) )
			{
				throw new Exception("Start no. >  End no. is not valid");
			}

			String stringOfDigit = (args[2]);
			String[] listOfDigits = stringOfDigit.split(",");
			for (String s :listOfDigits )
			{
				if(!(Integer.parseInt(s) > 0 && Integer.parseInt(s) < 10 )) {
					throw new Exception("digit "+ s +" in list is not between 1 and 9");
				}
			}		
		}
		catch (Exception e) 
		{
			System.out.println("Please enter valid input argument list. \n"
					+ e.getMessage());
			isValid = false;
		}
		return isValid;
	}

	static void getOccurrence(String[] args)
	{

		int start = Integer.parseInt(args[0]); 
		int end = Integer.parseInt(args[1]);
		String stringOfDigit = (args[2]);

		String[] listOfDigits = stringOfDigit.split(",");
		for (String s :listOfDigits )
		{
			int digit = Integer.parseInt(s);
			if(digit > 0 && digit < 10 ) {
				int i;
				int count = 0;
				for (i= start; i<=end ; i++)
				{
					int j=i;
					while(j>0) {
						if( j%10==digit)
						{count++;}
						j=j/10;
					}
				}
				System.out.println(digit + " occurs "+count+" times" );
			}

		}}}