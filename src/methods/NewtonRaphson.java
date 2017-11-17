package methods;

public class NewtonRaphson extends BaseMethod{

	private double range_begin;
	
	public NewtonRaphson(String fx, String dirivative_fx,double range_begin) {
		super(fx, dirivative_fx);
		this.range_begin = range_begin;
		findRoot();
	}
	
	public void findRoot(){
		findRoot_recursion(range_begin,1.0);
	}
	private void findRoot_recursion(double current_position,double error_precentage){
		// show error 
		System.out.println("Newtons method error precentage: " + error_precentage);
		if(error_precentage<minimum_error){
			setResult(current_position);
			return;
		}
		
		double next_position = NewtonRaphson_format(current_position);
		double error = check_errorPrecentage(current_position,next_position);
		findRoot_recursion(next_position,error);
	}
	
	private double NewtonRaphson_format(double start){
		double result;
		result = start - (CalculateFX_byGivenValue(start)/CalculateDirivativeFX_byGivenValue(start));
		return result;
	}
}
