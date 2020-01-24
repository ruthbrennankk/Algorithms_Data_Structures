import java.util.ArrayList;

//-------------------------------------------------------------------------
/**
*  Utility class containing validation/evaluation/conversion operations
*  for prefix and postfix arithmetic expressions.
*
*  @author  Ruth Brennan
*  @version 1/12/15 13:03:48
*/

public class Arith 
{
	//~ Validation methods ..........................................................
	
	/**
	* Validation method for prefix notation.
	*
	* @param prefixLiterals : an array containing the string literals hopefully in prefix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return true if the parameter is indeed in prefix notation, and false otherwise.
	**/
	public static boolean validatePrefixOrder(String prefixLiterals[])
	{	if (prefixLiterals != null) {
			int count = 1;
			 for (int i=0; i<prefixLiterals.length && count>=0; i++) {
			 	String token = prefixLiterals[i];
			 	if (isInteger(token)) {
			 		count--;
			 	} else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
			 		count++;
			 	}
			 }
			 if  (count<0 || count>0) {
			 	return false;
			 } else {
			 	return true;
			 }
		}
		return false;
		 
	}
	
	public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
		    return true;
		}
	
	
	/**
	* Validation method for postfix notation.
	*
	* @param postfixLiterals : an array containing the string literals hopefully in postfix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return true if the parameter is indeed in postfix notation, and false otherwise.
	**/
	public static boolean validatePostfixOrder(String postfixLiterals[])
	{
		if (postfixLiterals!=null) {
			int count = 0;
		    for (int i=0; i<postfixLiterals.length && count>=0; i++) {
		    	String token = postfixLiterals[i];
		    	if (isInteger(token)) {
		    		count++;
		    	} else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
		    		count--;
		    	}
		    }
		    if  (count<1 || count>1) {
			 	return false;
			 } else {
			 	return true;
			 }
		} 
		return false;	  
	}
	
	
	//~ Evaluation  methods ..........................................................
	
	
	/**
	* Evaluation method for prefix notation.
	*
	* @param prefixLiterals : an array containing the string literals in prefix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return the integer result of evaluating the expression
	**/
	public static int evaluatePrefixOrder(String prefixLiterals[])
	{
		  	boolean space=true;
		  	ArrayList<Integer> stack = new ArrayList<Integer>();
		  	String token;
		  	for (int i=prefixLiterals.length -1; i>=0; i--) {
		  		token = prefixLiterals[i];
		  		if (isInteger(token)) {
		  			if (space) {
		  				push(stack, Integer.parseInt(token) );
		  			} else {
		  				push(stack, (pop(stack)*10)+ Integer.parseInt(token));
		  			}
		  			space = false;
		  		} else if (token.equals(" ")) {
		  			space = true;
		  		} else {
		  			int answer;
		  			switch (token) {
		  				case "*":
		  					answer = pop(stack)* pop(stack);
		  					push(stack,answer);
		  					break;
		  				case "/":
		  					answer = pop(stack)/pop(stack);
		  					push(stack,answer);
		  					break;
		  				case "+":
		  					answer = pop(stack)+pop(stack);
		  					push(stack,answer);
		  					break;
		  				case "-":
		  					answer = pop(stack)-pop(stack);
		  					push(stack,answer);
		  					break;
		  				default:
		  					//TODO
		  					break;
		  			}
		  			space = false;
		  		}
		  	}
		  	return pop( stack );
	}
	
	public static int pop(ArrayList<Integer> stack)          //pop integer element
	{
	   int tmp = stack.get((stack.size()- 1));
	   stack.remove( (stack.size()- 1) );
	   return( tmp );      
	}
	
	public static String popS(ArrayList<String> stack)      //pop String element
	{
		String tmp = stack.get((stack.size()- 1));
		stack.remove( (stack.size()- 1) );
		return tmp ; 
	}
	
	public static void push(ArrayList<Integer> stack, int x)      //push integer element
	{
	   stack.add(x);
	}
	
	public static void push(ArrayList<String> stack, String x)      //push String element
	{
	   stack.add(x);
	}
	
	/**
	* Evaluation method for postfix notation.
	*
	* @param postfixLiterals : an array containing the string literals in postfix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return the integer result of evaluating the expression
	**/
	public static int evaluatePostfixOrder(String postfixLiterals[])
	{
		  	boolean space=true;
		  	ArrayList<Integer> stack = new ArrayList<Integer>();
		  	String token;
		  	int tmpa;
		  	int tmpb;
		  	for (int i=0; i<postfixLiterals.length; i++) {
		  		token = postfixLiterals[i];
		  		if (isInteger(token)) {
		  			if (space) {
		  				push(stack, Integer.parseInt(token) );
		  			} else {
		  				push(stack, (pop(stack)*10)+ Integer.parseInt(token));
		  			}
		  			space = false;
		  		} else if (token.equals(" ")) {
		  			space = true;
		  		} else {
		  			int answer;
		  			switch (token) {
		  				case "*":
		  					answer = pop(stack)* pop(stack);
		  					push(stack,answer);
		  					break;
		  				case "/":
		  					tmpa = pop(stack);
		  					tmpb = pop(stack);
		  					answer = tmpb / tmpa;
		  					push(stack,answer);
		  					break;
		  				case "+":
		  					answer = pop(stack)+pop(stack);
		  					push(stack,answer);
		  					break;
		  				case "-":
		  					tmpa = pop(stack);
		  					tmpb = pop(stack);
		  					answer = tmpb - tmpa;
		  					push(stack,answer);
		  					break;
		  				default:
		  					//TODO
		  					break;
		  			}
		  			space = false;
		  		}
		  	}
		  	return pop( stack );
	}
	
	//~ Conversion  methods ..........................................................
	
	/**
	* Converts prefix to postfix.
	*
	* @param prefixLiterals : an array containing the string literals in prefix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return the expression in postfix order.
	**/
	public static String[] convertPrefixToPostfix(String prefixLiterals[])
	{
		ArrayList<String> stack = new ArrayList<String>();
		  String[] infix = new String[prefixLiterals.length*2];
		  String token;
		  String temp = null;
		  for (int i=prefixLiterals.length -1; i>=0; i--) {
			  token = prefixLiterals[i];
		  	if (isInteger(token)) {
		  		push(stack, token);
		 	} else if ( token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-") ){
		 		temp = popS(stack)+popS(stack)+token;
		 		push(stack, temp);
		  	}
		  }
		  //from string to 
		  char[] tmp = temp.toCharArray();
		  int j=0;
		  boolean no = false;
		  for (int i=0; i<tmp.length; i++) {
				  if(Character.isDigit(tmp[i]) ) {
					  if (no) {
						  j--;
						  String string = infix[j];
						  infix[j] = string + Character.toString(tmp[i]);
						  j++;
					  } else {
						  infix[j] = Character.toString(tmp[i]);
						  j++;
						  no=true;
					  } 
				  } 
				  else {
					  infix[j] = Character.toString(tmp[i]);
					  j++;
					  no=false;
				  }
		  }
		  return infix;
	}
	
	/**
	* Converts postfix to prefix.
	*
	* @param prefixLiterals : an array containing the string literals in postfix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return the expression in prefix order.
	**/
	public static String[] convertPostfixToPrefix(String postfixLiterals[])
	{
		ArrayList<String> stack = new ArrayList<String>();
		  String[] postfix = new String[postfixLiterals.length*2];
		  String token;
		  String temp = null;
		  for (int i=0; i<postfixLiterals.length; i++) {
			  	token = postfixLiterals[i];
			  	if (isInteger(token)) {
			  		push(stack, token);
			 	} else if ( token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-") ){
			 		temp = token+popS(stack)+popS(stack);
			 		push(stack, temp);
			  	}
		  }
		  //from string to 
		  char[] tmp = temp.toCharArray();
		  int j=0;
		  boolean no = false;
		  for (int i=0; i<tmp.length; i++) {
				  if(Character.isDigit(tmp[i]) ) {
					  if (no) {
						  j--;
						  String string = postfix[j];
						  postfix[j] = string + Character.toString(tmp[i]);
						  j++;
					  } else {
						  postfix[j] = Character.toString(tmp[i]);
						  j++;
						  no=true;
					  } 
				  } 
				  else {
					  postfix[j] = Character.toString(tmp[i]);
					  j++;
					  no=false;
				  }
		  }
		  
		  return postfix;
	}
	
	/**
	* Converts prefix to infix.
	*
	* @param infixLiterals : an array containing the string literals in prefix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return the expression in infix order.
	**/
	public static String[] convertPrefixToInfix(String prefixLiterals[])
	{
		  ArrayList<String> stack = new ArrayList<String>();
		  String[] infix = new String[prefixLiterals.length*2];
		  String token;
		  String temp = null;
		  for (int i=prefixLiterals.length -1; i>=0; i--) {
			  token = prefixLiterals[i];
		  	if (isInteger(token)) {
		  		push(stack, token);
		 	} else if ( token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-") ){
		 		temp = "("+popS(stack)+token+popS(stack)+ ")";
		 		push(stack, temp);
		  	}
		  }
		  //from string to 
		  char[] tmp = temp.toCharArray();
		  int j=0;
		  boolean no = false;
		  for (int i=0; i<tmp.length; i++) {
				  if(Character.isDigit(tmp[i]) ) {
					  if (no) {
						  j--;
						  String string = infix[j];
						  infix[j] = string + Character.toString(tmp[i]);
						  j++;
					  } else {
						  infix[j] = Character.toString(tmp[i]);
						  j++;
						  no=true;
					  } 
				  } 
				  else {
					  infix[j] = Character.toString(tmp[i]);
					  j++;
					  no=false;
				  }
		  }
		  
		  return infix;
	}
	
	/**
	* Converts postfix to infix.
	*
	* @param infixLiterals : an array containing the string literals in postfix order.
	* The method assumes that each of these literals can be one of:
	* - "+", "-", "*", or "/"
	* - or a valid string representation of an integer.
	*
	* @return the expression in infix order.
	**/	
	public static String[] convertPostfixToInfix(String postfixLiterals[])
	{
		  ArrayList<String> stack = new ArrayList<String>();
		  String[] postfix = new String[postfixLiterals.length*2];
		  String token;
		  String temp = null;
		  for (int i=0; i<postfixLiterals.length; i++) {
			  	token = postfixLiterals[i];
			  	if (isInteger(token)) {
			  		push(stack, token);
			 	} else if ( token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-") ){
			 		temp = "("+popS(stack)+token+popS(stack)+ ")";
			 		push(stack, temp);
			  	}
		  }
		  //from string to 
		  char[] tmp = temp.toCharArray();
		  int j=0;
		  boolean no = false;
		  for (int i=0; i<tmp.length; i++) {
				  if(Character.isDigit(tmp[i]) ) {
					  if (no) {
						  j--;
						  String string = postfix[j];
						  postfix[j] = string + Character.toString(tmp[i]);
						  j++;
					  } else {
						  postfix[j] = Character.toString(tmp[i]);
						  j++;
						  no=true;
					  } 
				  } 
				  else {
					  postfix[j] = Character.toString(tmp[i]);
					  j++;
					  no=false;
				  }
		  }
		  
		  return postfix;
	}
}