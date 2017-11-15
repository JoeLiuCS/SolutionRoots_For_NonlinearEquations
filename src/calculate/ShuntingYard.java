package calculate;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import polynomial.*;

/**
 *   Class : Shunting-yard algorithm is a method for parsing mathematical 
 *   expressions specified in infix notation.
 *   
 *   1. The different operators have different precedence
 *   number :6   bracket :5  Hyperbolic: 4  PowerRoot:3  mulAndDiv:2  addAndSub:1
 *   
 *   Link : https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 *   Example1: 3 + 4 × 2 ÷ ( 1 − 5 ) ^ 2 ^ 3
 *   		result -> 3 4 2 × 1 5 − 2 3 ^ ^ ÷ +	
 *   Example2:sin ( max ( 2, 3 ) ÷ 3 × 3.1415 )
 *   		result -> 2 3 max 3 ÷ 3.1415 × sin
 * @author shuoqiaoliu
 *
 */
public class ShuntingYard {
		
		// function input
		private String givenFunction;
		// the value for the variable (X)
		private double variableValue;
		// store the all operators
		private Queue<BaseOperators> allOperators;

		public ShuntingYard(String function,double value) {
			givenFunction = function;
			variableValue = value;
			allOperators = new LinkedList<>();
			// set up the token type
			generateTokens();
			// reorder all the operators
			conversion();
		}
		/*
		 * get the result after the conversion
		 */
		public Queue<BaseOperators> getFinalConversion(){
			return allOperators;
		}
		private void conversion(){
			//output queue
			Queue<BaseOperators> conversion_queue = new LinkedList<>();
			// Operator stack
			Stack<BaseOperators> operator_stack = new Stack<>();
			
			while(!allOperators.isEmpty()){
				// get the token from loop each time running
				BaseOperators operator_lookUp = allOperators.poll();
				
				//check it is number or not
				//if it is number push to output queue
				if(operator_lookUp.getType() == 'N'){
					conversion_queue.add(operator_lookUp);
				}
				//get all operator which is less than 5 ,and also the ( bracket
				// but the ( bracket precedence is 6 
				else if(operator_lookUp.getPrecedence()<5 || operator_lookUp.getOperator().equals("(")){
					//if the stack is empty, just push it to stack
					if(operator_stack.isEmpty()){
						operator_stack.push(operator_lookUp);
					}
					//if the stack is not empty
					else{
						// get top of the operator stack, but not move
						BaseOperators operator_top = operator_stack.peek();
						// precedence is same
						if(operator_lookUp.getPrecedence() == operator_top.getPrecedence()){
							//check if it is ( bracket, move top of the stack to queue
							if(operator_lookUp.getOperator().equals("(")){
								conversion_queue.add(operator_stack.pop());
							}
							//else just add to top
							operator_stack.push(operator_lookUp);
						}
						// precedence bigger than
						else if (operator_lookUp.getPrecedence() > operator_top.getPrecedence()){
							operator_stack.push(operator_lookUp);
						}
						// precedence less than
						else{
							//find left bracket push in to stack
							if(operator_top.getOperator().equals("(")){
								operator_stack.push(operator_lookUp);
							}
							// Push all operator in until find right bracket
							else{
								// push all the operators from the bracket 
								while(!operator_stack.isEmpty()){
									conversion_queue.add(operator_stack.pop());
									if(!operator_stack.isEmpty()){
										BaseOperators topCheck = operator_stack.peek();
										if(topCheck.getOperator().equals("(") || operator_stack.isEmpty()){
											break;
										}
									}
								}
								operator_stack.push(operator_lookUp);
							}
						}
					}
				}
				//if it is right bracket
				else{
					BaseOperators lookUp = operator_stack.peek();
					while(!lookUp.getOperator().equals("(")){
						conversion_queue.add(lookUp);
						operator_stack.pop();
						lookUp = operator_stack.peek();
					}
					operator_stack.pop();
				}
			}
			
			// push all remain operator to conversion queue
			if(!operator_stack.isEmpty()){
				while(!operator_stack.isEmpty()){
					conversion_queue.add(operator_stack.pop());
				}
			}
			// set the results back to Store queue
			allOperators= conversion_queue;
		}
		
		/*
		 * Get each token, and define the type for each token
		 * different token has different precedence
		 */
		private void generateTokens() {
			String[] tokens = givenFunction.split(" ");
			for(String token:tokens){
				if(checkIsVariable(token)){
					allOperators.add(new GivenNumber('N',6,String.valueOf(variableValue)));
				}
				else if(checkIsNumber(token)){
					allOperators.add(new GivenNumber('N',6,token));
				}
				else if (checkIsBracket(token)){
					allOperators.add(new Bracket('B',5,token));
				}
				else if (checkIsHyperbolic(token)){
					allOperators.add(new Hyperbolic('H',4,token));
				}
				else if (checkIsTriangle(token)){
					allOperators.add(new Triangle('T',4,token));
				}
				else if (checkIsPowerRoot(token)){
					allOperators.add(new PowerRoot('R',3,token));
				}
				else if (checkIsDivOrMult(token)){
					allOperators.add(new PowerRoot('M',2,token));
				}
				else if (checkIsAddOrSub(token)){
					allOperators.add(new PowerRoot('A',1,token));
				}
				else{
					throw new IllegalArgumentException("The input is not valid!!");
				}
			}
		}
		
		
		//================ Check Operator type functions===================
		/*
		 * check the String is Number
		 */
		private boolean checkIsNumber(String s){
			try{
				Double.parseDouble(s);
				return true;
			}catch(NumberFormatException e){
				return false;
			}
		}
		/*
		 * check the String is bracket
		 */
		private boolean checkIsBracket(String s){
			return s.equals("(") || s.equals(")");
		}
		/*
		 * check the String is sin, cos, tan
		 */
		private boolean checkIsTriangle(String s){
			return s.equals("sin") || s.equals("tan") || s.equals("cos") ;
		}
		/*
		 * check the String is Hyperbolic
		 */
		private boolean checkIsHyperbolic(String s){
			return s.equals("sinh") || s.equals("tanh") || s.equals("cosh") ;
		}
		/*
		 * check the String is variable
		 */
		private boolean checkIsVariable(String s){
			return s.equals("x") ||s.equals("X");
		}
		/*
		 * check the String is Power root
		 */
		private boolean checkIsPowerRoot(String s){
			return s.equals("^");
		}
		/*
		 * check the String is multiplication or division
		 */
		private boolean checkIsDivOrMult(String s){
			return s.equals("*") || s.equals("/");
		}
		/*
		 * check the String is addition or subtraction
		 */
		private boolean checkIsAddOrSub(String s){
			return s.equals("+") || s.equals("-");
		}
		//===================Print=======================
		public void printMyConversion(){
			for(BaseOperators q:allOperators){
				System.out.print(" [ "+q.getOperator() + " ] ");
			}
		}
}
