package methods;

public class ModifiedSecant extends BaseMethod{

	private double range_begin;
	
	public ModifiedSecant(String fx, String dirivative_fx,double range_begin) {
		super(fx, dirivative_fx);
		this.range_begin = range_begin;
		getRoot();
	}
	
	public void getRoot(){
		getRoot_recursion(range_begin,1.0);
	}
	public void getRoot_recursion(double num_start,double error_percentage){
		//show error
		System.out.println("ModifiedSecant method error precentage:" + error_percentage);
		if(error_percentage < minimum_error){
			setResult(num_start);
			return;
		}
		double next_postion  = ModifiedSecant_format(num_start);
		double error = check_errorPrecentage(num_start,next_postion);
		getRoot_recursion(next_postion,error);
		
	}
	//formula function
	private double ModifiedSecant_format(double postion_start){
		double start_value = CalculateFX_byGivenValue(postion_start);
		double part_A_nam = modified_constant*postion_start;
		double part_A_dena= CalculateFX_byGivenValue(postion_start+modified_constant*postion_start) - start_value;
		double result = postion_start - start_value * part_A_nam / part_A_dena;
		return result;
	}

}
