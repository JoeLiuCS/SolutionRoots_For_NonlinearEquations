package polynomial;

public class BaseOperators implements Operators{

	private char type;
	private int precedence;
	private String InputOperator;
	
	public BaseOperators(char type,int precedence,String InputOperator){
		this.type = type;
		this.precedence = precedence;
		this.InputOperator = InputOperator;
	}
	
	
	@Override
	public char getType() {
		return type;
	}

	@Override
	public int getPrecedence() {
		return precedence;
	}

	@Override
	public String getOperator() {
		return InputOperator;
	}
	
}
