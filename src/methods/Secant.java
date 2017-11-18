package methods;

public class Secant extends BaseMethod{

	private double range_begin;
	private double range_end;
	
	public Secant(String fx, String dirivative_fx,double range_begin,double range_end) {
		super(fx, dirivative_fx);
		this.range_begin = range_begin;
		this.range_end = range_end;
		findRoot();
	}
	
	public void findRoot(){
		findRoot_recursion(range_begin,range_end,1.0);
	}
	private void findRoot_recursion(double num_start,double num_end,double error_percentage){
		
		// show error 
		System.out.println("Secant method error precentage: " + error_percentage);
				
		if(error_percentage<minimum_error){
			setResult(num_start);
			return;
		}
		
		double next_position = Secant_format(num_start,num_end);
		double error = check_errorPrecentage(num_start,next_position);
		findRoot_recursion(next_position,num_start,error);
	}
	
	private double Secant_format(double postion_start,double position_end){
		double start_value =CalculateFX_byGivenValue(postion_start);
		double end_value =CalculateFX_byGivenValue(position_end);
		double part_A = (postion_start-position_end) / (start_value-end_value);
		double result = postion_start - start_value * part_A;
		return result;
	}

}
