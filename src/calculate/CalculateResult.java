package calculate;

import java.util.Queue;
import java.util.Stack;

import polynomial.BaseOperators;
import polynomial.GivenNumber;

public class CalculateResult {
	private Queue<BaseOperators> inputOperators;
	private double result;
	
	public CalculateResult(Queue<BaseOperators> input){
		this.inputOperators = input;
		calcualateByGivenInput();
	}
	
	private void calcualateByGivenInput(){
		Stack<BaseOperators> temp_result = new Stack<>();
		while(!inputOperators.isEmpty()){
			BaseOperators top = inputOperators.poll();
			if(top.getType() == 'N'){
				temp_result.push(top);
			}
			else if (top.getType() == 'T'){
				BaseOperators temp_top = temp_result.pop();
				double num = Double.parseDouble(temp_top.getOperator());
				num = Math.toRadians(num);
				String ans = "";
				if(top.getOperator().equals("sin")){
					ans = String.valueOf(Math.sin(num));
				}
				else if (top.getOperator().equals("cos")){
					ans = String.valueOf(Math.cos(num));
				}
				else if (top.getOperator().equals("tan")){
					ans = String.valueOf(Math.tan(num));
				}
				else{
					throw new IllegalArgumentException("Wrong operator!!");
				}
				temp_result.push(new GivenNumber('N',6,ans));
			}
			else if (top.getType() =='H'){
				BaseOperators temp_top = temp_result.pop();
				double num = Double.parseDouble(temp_top.getOperator());
				num = Math.toRadians(num);
				String ans = "";
				if(top.getOperator().equals("sinh")){
					ans = String.valueOf(Math.sinh(num));
				}
				else if (top.getOperator().equals("cosh")){
					ans = String.valueOf(Math.cosh(num));
				}
				else if (top.getOperator().equals("tanh")){
					ans = String.valueOf(Math.tanh(num));
				}
				else{
					throw new IllegalArgumentException("Wrong operator!!");
				}
				temp_result.push(new GivenNumber('N',6,ans));
			}
			else{
				double num2 = Double.parseDouble(temp_result.pop().getOperator());
				double num1 = Double.parseDouble(temp_result.pop().getOperator()); 
				String ans = "";
				if(top.getOperator().equals("*")){
	                ans = String.valueOf(num1*num2);
	            }
	            if(top.getOperator().equals("/")){
	                ans = String.valueOf(num1/num2);
	            }
	            if(top.getOperator().equals("+")){
	                ans = String.valueOf(num1+num2);
	            }
	            if(top.getOperator().equals("-")){
	                ans = String.valueOf(num1-num2);
	            }
	            if(top.getOperator().equals("^")){
	                ans = String.valueOf(Math.pow(num1,num2));
	            }
	            temp_result.push(new GivenNumber('N',6,ans));
			}
		}
		result = Double.parseDouble(temp_result.pop().getOperator());
	}
	
	public double getResult(){
		return result;
	}
}
