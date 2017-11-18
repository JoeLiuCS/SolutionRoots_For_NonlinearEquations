package methods;

public class FalsePosition extends BaseMethod{
	
	private double range_begin;
	private double range_end;
	
	public FalsePosition(String fx, String dirivative_fx, double range_begin,double range_end) {
		super(fx, dirivative_fx);
		this.range_begin = range_begin;
		this.range_end = range_end;
		findRoot();
	}
	
	public void findRoot(){
		double first_nextPosition = falsePosition_format(range_begin,range_end);
		double a_value = CalculateFX_byGivenValue(range_begin);
		double c_value = CalculateFX_byGivenValue(first_nextPosition);
		char check_place = a_or_b_replace(a_value,c_value);
		if(check_place == 'b'){
			findRoot_recursion(range_begin,first_nextPosition,first_nextPosition,1.0);
		}
		// equal to a
		else{
			findRoot_recursion(first_nextPosition,range_end,first_nextPosition,1.0);
		}
	}
	
	private void findRoot_recursion(double num_start,double num_end,
											double perious_position,double error_precentage){
		// show error 
		System.out.println("FalsePosition method error precentage: " + error_precentage);
		
		if(error_precentage < minimum_error){
			setResult(perious_position);
			return;
		}
		double nextPosition = falsePosition_format(num_start,num_end);
		double a_value = CalculateFX_byGivenValue(num_start);
		double c_value = CalculateFX_byGivenValue(nextPosition);
		char check_place = a_or_b_replace(a_value,c_value);
		double error = check_errorPrecentage(perious_position,nextPosition);
		
		if(check_place == 'b'){
			findRoot_recursion(num_start,nextPosition,nextPosition,error);
		}
		// equal to a
		else{
			findRoot_recursion(nextPosition,num_end,nextPosition,error);
		}
	}
	//formula function
	private double falsePosition_format(double postion_start,double position_end){
		double start_value = CalculateFX_byGivenValue(postion_start);
		double end_value =CalculateFX_byGivenValue(position_end);
		double result = (postion_start*end_value - position_end*start_value)/(end_value-start_value);
		return result;
	}
	
	private char a_or_b_replace(double a_value,double c_value){
		double check_negative = a_value*c_value;
		if(check_negative<0){
			return 'b';
		}
		else{
			return 'a';
		}
	}

}
