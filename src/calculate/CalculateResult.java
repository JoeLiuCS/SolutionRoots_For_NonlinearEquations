package calculate;

import java.util.Queue;
import java.util.Stack;

import operators.BaseOperators;
import operators.GivenNumber;
/**
 * 
 * This class finish the calculation by given operator order
 * Example:  
 * 			Given :223*-
 * 			1: 2-3 = -1   -> Given become : 2 -1  *
 * 			2: 2 * -1 = -2  -> Given become : -2
 * 			result : -2
 * 
 * Video : https://www.youtube.com/watch?v=y_snKkv0gWc
 * @author shuoqiaoliu
 *
 */
public class CalculateResult {
	// the finished order operators
	private Queue<BaseOperators> inputOperators;
	private double result;
	
	public CalculateResult(Queue<BaseOperators> input){
		this.inputOperators = input;
		calcualateByGivenInput();
	}
	
	private void calcualateByGivenInput(){
		// stack to save the result
		Stack<BaseOperators> temp_result = new Stack<>();
		//read all operators
		while(!inputOperators.isEmpty()){
			BaseOperators top = inputOperators.poll();
			//if it is number
			if(top.getType() == 'N'){
				temp_result.push(top);
			}
			// operator is sin cos or tan
			else if (top.getType() == 'T'){
				BaseOperators temp_top = temp_result.pop();
				double num = Double.parseDouble(temp_top.getOperator());
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
			// operator is hyperbolic 
			else if (top.getType() =='H'){
				BaseOperators temp_top = temp_result.pop();
				double num = Double.parseDouble(temp_top.getOperator());
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
			// Operator is * / + - or ^
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
		//get final result
		result = Double.parseDouble(temp_result.pop().getOperator());
	}
	
	public double getResult(){
		return result;
	}
}
