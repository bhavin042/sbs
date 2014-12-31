package sbs.bank.validations;

public class formValidations {
       
	public static void specialCharacterValidation(String fieldName, String msg, int size) throws Exception
	{
		if(fieldName == null || fieldName.isEmpty())
		{
			throw new Exception(msg + " : Must be entered");
		}
		if(fieldName.length()>size)
		{
			throw new Exception(msg + " : Length should be less than "+size);
		}
		boolean t = fieldName.matches("^[A-Za-z0-9-,.].*$");
		if(!t)
		{
			throw new Exception(msg + " : Special characters are not allowed");
		}
		
	}
	public static void mailValidation(String fieldName, String msg) throws Exception
	{
		if(fieldName == null || fieldName.isEmpty())
		{
			throw new Exception(msg + " : Must be entered");
		}
		if(fieldName.length()>40)
		{
			throw new Exception(msg + " : Length should be less than 40");
		}
		boolean t = fieldName.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		if(!t)
		{
			throw new Exception(msg + " : Invalid E-mail");
		}
	}
	
	public static void balanceValidation(String fieldName, String msg) throws Exception
	{
		if(fieldName == null || fieldName.isEmpty())
		{
			throw new Exception(msg + " : Must be entered");
		}
		if(fieldName.length()>10)
		{
			throw new Exception(msg + " :This much amount cannot be transfered");
		}
		boolean t = fieldName.matches("[1-9][0-9]*(\\.[0-9]{1,2})?");
		if(!t)
		{
			throw new Exception(msg + " : Invalid amount entered");
		}
	}
	
	public static void passwordValidation(String fieldName1, String fieldName2, String msg) throws Exception
	{
		if(fieldName1 == null || fieldName1.isEmpty() || fieldName2 == null || fieldName2.isEmpty())
		{
			throw new Exception(msg + " : doesn't match");
		}
		if(fieldName1.length() != fieldName2.length() )
		{
			throw new Exception(msg + " : doesn't match");
		}
		if(!fieldName1.equals(fieldName2))
		{
			throw new Exception(msg + " : doesn't match");
		}
		if (fieldName1.equals(fieldName2)) {

			fieldName1 = fieldName2;
            boolean hasUppercase = !fieldName1.equals(fieldName1.toLowerCase());
            boolean hasLowercase = !fieldName1.equals(fieldName1.toUpperCase());
            boolean hasNumber = fieldName1.matches(".*\\d.*");
            

            if (!hasUppercase) {
            	throw new Exception(msg + " : needs an upper case <br>");
               
            }

            if (!hasLowercase) {
            	throw new Exception(msg + " : needs an lower case <br>");
               
            }

            if (!hasNumber) {
            	throw new Exception(msg + " : needs an number <br>");
               
            }
        }
		
	}
}

