package calculate;
import methods.*;

public class Play {

	public static void main(String[] args) {
		double variable_value_testing =1;
		String input_polynomial = "2 * x ^ 3 - 11.7 * x ^ 2 + 17.7 * x - 5";
		String input_polynomial_dirv = "6 * x ^ 2 + 23.4 * x + 17.7";
		String input_Hyperbolic = "x + 10 - x * cosh ( 50 / x )";
		String input3_Hyperbolic_dirv = "( 50 * sinh ( 50 / x ) / x ) - cosh ( 50 / x ) + 1";
//		test(input0,value);
//		test(input1,value);
//		test(input2,value);
//		test(input3,value);
		
		
		BaseMethod test1_bisection = new Bisection(input_polynomial,input_polynomial_dirv,1,2);
		System.out.println(test1_bisection.getResult());
	}
	static void test(String input,double value_x){
		ShuntingYard s = new ShuntingYard(input,value_x);
		s.printMyConversion();
		System.out.println(  );
		CalculateResult c = new CalculateResult(s.getFinalConversion());
		System.out.println("Result : "+c.getResult());
		System.out.println("\n-----------------------------------------------");
	}
	
}
