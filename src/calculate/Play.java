package calculate;
import methods.*;

public class Play {
	final static int [] range_poly_1 = {0,1};
	final static int [] range_poly_2 = {1,2};
	final static int [] range_poly_3 = {2,4};
	
	final static String input_polynomial = "2 * x ^ 3 - 11.7 * x ^ 2 + 17.7 * x - 5";
	final static String input_polynomial_dirv = "6 * x ^ 2 - 23.4 * x + 17.7";
	
	final static int [] range_hype = {120,130};
	final static String input_Hyperbolic = "x + 10 - x * cosh ( 50 / x )";
	final static String input3_Hyperbolic_dirv = "( 50 * sinh ( 50 / x ) / x ) - cosh ( 50 / x ) + 1";
	
	
	public static void main(String[] args) {
		test_poly_range1();
		
		
		
		
		
//		BaseMethod test4_bisection = new Bisection(input_Hyperbolic,input3_Hyperbolic_dirv,
//				range_hype[0],range_hype[1]);
//		System.out.println(test4_bisection.getResult());
		
		
	}
	static void test_poly_range1(){
		BaseMethod test1_bisection = new Bisection(input_polynomial,input_polynomial_dirv,
															range_poly_1[0],range_poly_1[1]);
		System.out.println(test1_bisection.getResult());


		BaseMethod test1_FalsePosition = new FalsePosition(input_polynomial,input_polynomial_dirv,
															range_poly_1[0],range_poly_1[1]);
		System.out.println(test1_FalsePosition.getResult());
		
		BaseMethod test1_NewtonRaphson = new NewtonRaphson(input_polynomial,input_polynomial_dirv,
																range_poly_1[0]);
		System.out.println(test1_NewtonRaphson.getResult());
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
