package methods;

public class Secant extends BaseMethod{

	private double range_begin;
	private double range_end;
	
	public Secant(String fx, String dirivative_fx,double range_begin,double range_end) {
		super(fx, dirivative_fx);
		this.range_begin = range_begin;
		this.range_end = range_end;
	}
	
	public void findRoot(){
		
	}
	private void findRoot_recursion(double error_percentage){
		if(error_percentage<minimum_error){
			return;
		}
		
	}
	
	private double Secant_format(double postion_start,double position_end){
		double start_value =CalculateFX_byGivenValue(postion_start);
		double end_value =CalculateFX_byGivenValue(position_end);
		double part_A = (postion_start-position_end) / (start_value-end_value);
		double result = postion_start - start_value * part_A;
		return result;
	}

}
