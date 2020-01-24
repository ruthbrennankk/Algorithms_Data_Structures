import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class ArithTest {

	@Test
	void testValidatePrefixOrder() 
	{
		Arith arith = new Arith();
		
		String[] testNull = null;
		assertFalse("tests the programs response to a null input", arith.validatePrefixOrder(testNull));
		
		String[] test = {"+", " ", "7", " ", "2"};
		assertTrue("tests on a valid prefix equation", arith.validatePrefixOrder(test));
		
		test = new String[13];
		test[0]="+";
		test[1]=" ";
		test[2]="+";
		test[3]=" ";
		test[4]="2";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="3";
		test[9]=" ";
		test[10]="2";
		test[11]=" ";	//prefix expression : + + 2 * 3 2 4
		test[12]="4"; 	//infix expressions : 2 + 3 * 2 + 4
		assertTrue("tests on a longer valid prefix equation", arith.validatePrefixOrder(test));
		
		String[] test3 = {"+", "+", "+", "*", "-"};
		assertFalse("tests on a invalid prefix equation", arith.validatePrefixOrder(test3));
		
		test = new String[13];
		test[0]="+";
		test[1]=" ";
		test[2]="+";
		test[3]=" ";
		test[4]="2";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="3";
		test[9]=" ";
		test[10]="2";
		test[11]="6";	//prefix expression : + + 2 * 3 264
		test[12]="4"; 	
		assertFalse("tests on a longer invalid prefix equation", arith.validatePrefixOrder(test));
	}

	@Test
	void testValidatePostfixOrder() {
		Arith arith = new Arith();
		
		String[] testNull = null;
		assertFalse("tests the programs response to a null input", arith.validatePostfixOrder(testNull));
		
		String[] test = {"7", " ", "2", " ", "+"};
		assertTrue("tests on a valid postfix equation", arith.validatePostfixOrder(test));
		
		String[] test3 = {"+", "+", "+", "*", "-"};
		assertFalse("tests on a invalid postfix equation", arith.validatePostfixOrder(test3));
		
		test = new String[13];
		test[0]="10";
		test[1]=" ";
		test[2]="2";
		test[3]=" ";
		test[4]="8";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="+";
		test[9]=" ";
		test[10]="3";
		test[11]=" ";
		test[12]="-"; 	
		assertTrue("tests on a longer valid postfix equation", arith.validatePostfixOrder(test));
	}

	@Test
	void testEvaluatePrefixOrder() {
		Arith arith = new Arith();
		
		String[] test = {"+", " ", "7", " ", "2"};
		assertEquals("tests on a valid postfix equation",9, arith.evaluatePrefixOrder(test));
		
		test = new String[13];
		test[0]="+";
		test[1]=" ";
		test[2]="+";
		test[3]=" ";
		test[4]="2";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="3";
		test[9]=" ";
		test[10]="2";
		test[11]=" ";	//prefix expression : + + 2 * 3 2 4
		test[12]="4"; 	
		assertEquals("tests on a valid postfix equation",12, arith.evaluatePrefixOrder(test));
		
		test = new String[13];
		test[0]="-";
		test[1]=" ";
		test[2]="6";
		test[3]=" ";
		test[4]="/";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="3";
		test[9]=" ";
		test[10]="7";
		test[11]=" ";	//prefix expression : - 6 / * 3 7 7
		test[12]="7"; 	
		assertEquals("tests on a valid postfix equation",3, arith.evaluatePrefixOrder(test));
	}

	@Test
	void testEvaluatePostfixOrder() {
		Arith arith = new Arith();
		
		String[] test = {"7", " ", "2", " ", "+"};
		assertEquals("tests on a valid postfix equation",9, arith.evaluatePostfixOrder(test));
		
		test = new String[9];
		test[0]="7";
		test[1]=" ";
		test[2]="9";
		test[3]=" ";
		test[4]="8";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="+";			//postfix expression : 7 9 8 * +	
		assertEquals("tests on a valid postfix equation",79, arith.evaluatePostfixOrder(test));
		
		test = new String[5];
		test[0]="81";
		test[1]=" ";
		test[2]="9";
		test[3]=" ";
		test[4]="/";			//postfix expression : 81 9 /	
		assertEquals("tests on a valid postfix equation",9, arith.evaluatePostfixOrder(test));
		
		test = new String[13];
		test[0]="4";
		test[1]=" ";
		test[2]="55";
		test[3]=" ";
		test[4]="+";
		test[5]=" ";
		test[6]="62";
		test[7]=" ";
		test[8]="23";
		test[9]=" ";
		test[10]="-";
		test[11]=" ";	//postfix expression : 4 55 + 62 23 - *
		test[12]="*"; 	
		assertEquals("tests on a longer valid postfix equation",2301, arith.evaluatePostfixOrder(test));
	
		test = new String[13];
		test[0]="10";
		test[1]=" ";
		test[2]="2";
		test[3]=" ";
		test[4]="8";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="+";
		test[9]=" ";
		test[10]="3";
		test[11]=" ";
		test[12]="-"; 	
		assertEquals("tests on a longer valid postfix equation",23, arith.evaluatePostfixOrder(test));
		
	}

	@Test
	void testConvertPrefixToPostfix() {
		Arith arith = new Arith();
		
		String[] test = {"+", " ", "7", " ", "2"}; //+ 7 2
		String result = "72+";
		assertEquals("tests on a valid postfix equation",result, makeString(arith.convertPrefixToPostfix(test)) );
				
		result = "232*+4+";
		test = new String[13];
		test[0]="+";
		test[1]=" ";
		test[2]="+";
		test[3]=" ";
		test[4]="2";
		test[5]=" ";
		test[6]="*";
		test[7]=" ";
		test[8]="3";
		test[9]=" ";
		test[10]="2";
		test[11]=" ";	//prefix expression : + + 2 * 3 2 4
		test[12]="4"; 	
		assertEquals("tests on a valid postfix equation",result, makeString(arith.convertPrefixToPostfix(test)));
		
		result = "98+107+*";
		test = new String[13];
		test[0]="*";
		test[1]=" ";
		test[2]="+";
		test[3]=" ";
		test[4]="9";
		test[5]=" ";
		test[6]="8";
		test[7]=" ";
		test[8]="+";
		test[9]=" ";
		test[10]="10";
		test[11]=" ";	
		test[12]="7"; 	
		assertEquals("tests on a valid postfix equation",result, makeString(arith.convertPrefixToPostfix(test)));
	}

	private String makeString(String a[]) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < a.length; i++) {
		   if (a[i] != null) {
			   result.append( a[i] );
		   }
		   //result.append( optional separator );
		}
		return result.toString();
	}
	
	@Test
	void testConvertPostfixToPrefix() {
		Arith arith = new Arith();
		
		String[] test = {"7", " ", "2", " ", "+"};
		String result = "+27";
		assertEquals("tests on a valid postfix equation",result, makeString(arith.convertPostfixToPrefix(test)) );
				
		result = "/981";
		test = new String[5];
		test[0]="81";
		test[1]=" ";
		test[2]="9";
		test[3]=" ";
		test[4]="/";			//postfix expression : 81 9 /
		assertEquals("tests on a valid postfix equation", result, makeString(arith.convertPostfixToPrefix(test)));
				
	}

	@Test
	void testConvertPrefixToInfix() {
		Arith arith = new Arith();
		
		String[] test = { "+", "*", "-", "1", "2", "3", "-", "10", "+", "3", "/", "6", "3" };
		String result = "("+ "("+ "("+ "1"+ "-" + "2"+ ")"+ "*"+ "3"+ ")"+ "+"+ "("+ "10"+ "-"+ "("+ "3"+ "+"+ "("+ "6"+ "/"+ "3"+ ")"+ ")"+ ")"+ ")";
		assertEquals("tests on a valid prefix equation",result, makeString(arith.convertPrefixToInfix(test)) );
					
	}

	@Test
	void testConvertPostfixToInfix() {
		Arith arith = new Arith();
		
		String[] test = { "7", "8", "3", "*", "28", "+", "+"};
		String result = "((28+(3*8))+7)";
		assertEquals("tests on a valid postfix equation",result, makeString(arith.convertPostfixToInfix(test)) );
		
	}

}
