package methods;

import calculate.*;

/**
 * User have to give the f(x) function and f'(x) function
 * @author shuoqiaoliu
 *
 */
public class BaseMethod {
		private String fx;
		private String dirivative_fx;
		private double result;
		
		public BaseMethod(String fx,String dirivative_fx){
			this.fx = fx;
			this.dirivative_fx = dirivative_fx;
		}
		public void setResult(double num){
			result = num;
		}
		public double getResult(){
			return result;
		}
		/*
		 * by given value find the result for f(x)
		 */
		public double CalculateFX_byGivenValue(double value){
			return Calculate(this.fx,value);
		}
		/*
		 * by given value find the result for f'(x)
		 */
		public double CalculateDirivativeFX_byGivenValue(double value){
			return Calculate(this.dirivative_fx,value);
		}
		
		/*
		 * shunting yard calculation
		 */
		private double Calculate(String function, double value){
			ShuntingYard s = new ShuntingYard(function,value);
			CalculateResult c = new CalculateResult(s.getFinalConversion());
			return c.getResult();
		}
}
