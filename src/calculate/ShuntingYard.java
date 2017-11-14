package calculate;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import polynomial.*;

public class ShuntingYard {
	
		private String givenFunction;
		private double variableValue;
		private Queue<BaseOperators> allOperators;
		
		public ShuntingYard(String function,double value) {
			givenFunction = function;
			variableValue = value;
			allOperators = new LinkedList<>();
			generateTokens();
			conversion();
		}
		public Queue<BaseOperators> getFinalConversion(){
			return allOperators;
		}
		private void conversion(){
			Queue<BaseOperators> conversion_queue = new LinkedList<>();
			Stack<BaseOperators> operator_stack = new Stack<>();
			
			while(!allOperators.isEmpty()){
				BaseOperators operator_lookUp = allOperators.peek();
				
				if(operator_lookUp.getType() == 'N'){
					conversion_queue.add(operator_lookUp);
				}
				else if(operator_lookUp.getPrecedence()<5 || operator_lookUp.getOperator().equals("(")){
					if(operator_stack.isEmpty()){
						operator_stack.push(operator_lookUp);
					}
					else{
						BaseOperators operator_top = operator_stack.peek();
						// precedence is same
						if(operator_lookUp.getPrecedence() == operator_top.getPrecedence()){
							if(operator_lookUp.getOperator().equals("(")){
								conversion_queue.add(operator_top);
								operator_stack.pop();
							}
							operator_stack.push(operator_lookUp);
						}
						// precedence >
						else if (operator_lookUp.getPrecedence() > operator_top.getPrecedence()){
							operator_stack.push(operator_lookUp);
						}
						// precedence <
						else{
							//find left bracket push in to stack
							if(operator_top.getOperator().equals("(")){
								operator_stack.push(operator_lookUp);
							}
							// Push all operator in until find right bracket
							else{
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
				allOperators.poll();
			}
			
			if(!operator_stack.isEmpty()){
				
				while(!operator_stack.isEmpty()){
					conversion_queue.add(operator_stack.pop());
				}
			}
			allOperators= conversion_queue;
		}
		
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
		private boolean checkIsNumber(String s){
			try{
				Double.parseDouble(s);
				return true;
			}catch(NumberFormatException e){
				return false;
			}
		}
		private boolean checkIsBracket(String s){
			return s.equals("(") || s.equals(")");
		}
		private boolean checkIsTriangle(String s){
			return s.equals("sin") || s.equals("tan") || s.equals("cos") ;
		}
		private boolean checkIsHyperbolic(String s){
			return s.equals("sinh") || s.equals("tanh") || s.equals("cosh") ;
		}
		private boolean checkIsVariable(String s){
			return s.equals("x") ||s.equals("X");
		}
		private boolean checkIsPowerRoot(String s){
			return s.equals("^");
		}
		private boolean checkIsDivOrMult(String s){
			return s.equals("*") || s.equals("/");
		}
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
