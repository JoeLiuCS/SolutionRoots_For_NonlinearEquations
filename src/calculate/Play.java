package calculate;
import methods.*;

public class Play {
	
	public static void main(String[] args) {
		test_poly_range1();
		test_poly_range2();
		test_poly_range3();
		test_Hyperbolic_range1();
	}
	
	
	final static double start_position_1 = 0.01;
	final static double start_position_2 = 1.5;
	final static int [] range_poly_1 = {0,1};
	final static int [] range_poly_2 = {1,2};
	final static int [] range_poly_3 = {3,4};
	
	final static String input_polynomial = "2 * x ^ 3 - 11.7 * x ^ 2 + 17.7 * x - 5";
	final static String input_polynomial_dirv = "6 * x ^ 2 - 23.4 * x + 17.7";
	
	final static int [] range_hype = {120,130};
	final static String input_Hyperbolic = "x + 10 - x * cosh ( 50 / x )";
	final static String input3_Hyperbolic_dirv = "( 50 * sinh ( 50 / x ) / x ) - cosh ( 50 / x ) + 1";
	
	static void test_poly_range1(){
		System.out.println("======================= Test 1 ===========================");
		BaseMethod test1_bisection = new Bisection(input_polynomial,input_polynomial_dirv,
															range_poly_1[0],range_poly_1[1]);
		System.out.println("Result : "+test1_bisection.getResult()+"\n--------------------------------------------------");


		BaseMethod test1_FalsePosition = new FalsePosition(input_polynomial,input_polynomial_dirv,
															range_poly_1[0],range_poly_1[1]);
		System.out.println("Result : "+test1_FalsePosition.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_NewtonRaphson = new NewtonRaphson(input_polynomial,input_polynomial_dirv,
															range_poly_1[0]);
		System.out.println("Result : "+test1_NewtonRaphson.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_Secant = new Secant(input_polynomial,input_polynomial_dirv,
															range_poly_1[0],range_poly_1[1]);
		System.out.println("Result : "+test1_Secant.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_ModifiedSecant = new ModifiedSecant(input_polynomial,input_polynomial_dirv,
															start_position_1);
		System.out.println("Result : "+test1_ModifiedSecant.getResult()+"\n--------------------------------------------------");
		
	}
	static void test_poly_range2(){
		System.out.println("======================= Test 2 ===========================");
		BaseMethod test1_bisection = new Bisection(input_polynomial,input_polynomial_dirv,
															range_poly_2[0],range_poly_2[1]);
		System.out.println("Result : "+test1_bisection.getResult()+"\n--------------------------------------------------");


		BaseMethod test1_FalsePosition = new FalsePosition(input_polynomial,input_polynomial_dirv,
															range_poly_2[0],range_poly_2[1]);
		System.out.println("Result : "+test1_FalsePosition.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_NewtonRaphson = new NewtonRaphson(input_polynomial,input_polynomial_dirv,
															start_position_2);
		System.out.println("Result : "+test1_NewtonRaphson.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_Secant = new Secant(input_polynomial,input_polynomial_dirv,
															range_poly_2[0],range_poly_2[1]);
		System.out.println("Result : "+test1_Secant.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_ModifiedSecant = new ModifiedSecant(input_polynomial,input_polynomial_dirv,
															start_position_2);
		System.out.println("Result : "+test1_ModifiedSecant.getResult()+"\n--------------------------------------------------");
		
	}
	
	static void test_poly_range3(){
		System.out.println("======================= Test 3 ===========================");
		BaseMethod test1_bisection = new Bisection(input_polynomial,input_polynomial_dirv,
															range_poly_3[0],range_poly_3[1]);
		System.out.println("Result : "+test1_bisection.getResult()+"\n--------------------------------------------------");


		BaseMethod test1_FalsePosition = new FalsePosition(input_polynomial,input_polynomial_dirv,
															range_poly_3[0],range_poly_3[1]);
		System.out.println("Result : "+test1_FalsePosition.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_NewtonRaphson = new NewtonRaphson(input_polynomial,input_polynomial_dirv,
																range_poly_3[0]);
		System.out.println("Result : "+test1_NewtonRaphson.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_Secant = new Secant(input_polynomial,input_polynomial_dirv,
															range_poly_3[0],range_poly_3[1]);
		System.out.println("Result : "+test1_Secant.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_ModifiedSecant = new ModifiedSecant(input_polynomial,input_polynomial_dirv,
															range_poly_3[0]);
		System.out.println("Result : "+test1_ModifiedSecant.getResult()+"\n--------------------------------------------------");
		
	}
	
	static void test_Hyperbolic_range1(){
		System.out.println("======================= Test 4 ===========================");
		BaseMethod test1_bisection = new Bisection(input_Hyperbolic,input3_Hyperbolic_dirv,
																	range_hype[0],range_hype[1]);
		System.out.println("Result : "+test1_bisection.getResult()+"\n--------------------------------------------------");


		BaseMethod test1_FalsePosition = new FalsePosition(input_Hyperbolic,input3_Hyperbolic_dirv,
																	range_hype[0],range_hype[1]);
		System.out.println("Result : "+test1_FalsePosition.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_NewtonRaphson = new NewtonRaphson(input_Hyperbolic,input3_Hyperbolic_dirv,
																	range_hype[0]);
		System.out.println("Result : "+test1_NewtonRaphson.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_Secant = new Secant(input_Hyperbolic,input3_Hyperbolic_dirv,
																	range_hype[0],range_hype[1]);
		System.out.println("Result : "+test1_Secant.getResult()+"\n--------------------------------------------------");
		
		BaseMethod test1_ModifiedSecant = new ModifiedSecant(input_Hyperbolic,input3_Hyperbolic_dirv,
																	range_hype[0]);
		System.out.println("Result : "+test1_ModifiedSecant.getResult()+"\n--------------------------------------------------");
		
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
