package methods;

public class Bisection extends BaseMethod{
	final char char_null = '\u0000';

	private double range_begin;
	private double range_end;
	
	/*
	 * Input f(x) f'(x)  and range [a,b]
	 */
	public Bisection(String fx, String dirivative_fx,double range_begin,double range_end) {
		super(fx, dirivative_fx);
		this.range_begin = range_begin;
		this.range_end = range_end;
		findRoot();
	}
	
	/*
	 * the first run could not find the error percentage
	 * Beginning = 100%
	 * then jump to recursion function
	 */
	private void findRoot(){
		double mid = (range_begin+range_end)/2;
		double a_value = CalculateFX_byGivenValue(range_begin);
		double b_value =CalculateFX_byGivenValue(range_end);
		double c_value = CalculateFX_byGivenValue(mid);
		char check_replace = a_or_b_replace(a_value,b_value,c_value);
		if(check_replace=='a'){
			findRoot_recursion(mid,range_end,c_value,b_value,mid,1.0);
		}
		
		if(check_replace=='b'){
			findRoot_recursion(range_begin,mid,a_value,c_value,mid,1.0);
		}
	}
	
	/*
	 * recursion function
	 * find the mid point, then keep to calculation
	 */
	private void findRoot_recursion(double num_start,double num_end,
										double value_start,double value_end,
											double prerious_mid,double error_precentage)
	{
		if(error_precentage < minimum_error){
			setResult(num_start);
			return;
		}
		double mid = (num_start+num_end)/2;
		double c_value = CalculateFX_byGivenValue(mid);
		char check_replace = a_or_b_replace(value_start,value_end,c_value);
		double error = check_errorPrecentage(prerious_mid,mid);
		
		System.out.println("Bisection error precentage: " + error);
		if(check_replace=='a'){
			findRoot_recursion(mid,num_end,c_value,value_end,mid,error);
		}
		
		if(check_replace=='b'){
			findRoot_recursion(num_start,mid,value_start,c_value,mid,error);
		}
	}
	

	/*
	 * If c value is positive, it will replace positive positive. or otherelse
	 */
	private char a_or_b_replace(double a_value,double b_value,double c_value){
		char positive = char_null;
		char negative = char_null;
		char result = char_null;
		if(a_value>0){
			positive = 'a';
			negative = 'b';
		}
		else{
			positive = 'b';
			negative = 'a';
		}
		if(c_value <= 0){
			result = negative;
		}
		else{
			result = positive;
		}
		return result;
	}
	

}
