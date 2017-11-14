package polynomial;

public interface Operators {
	/**
	 * @return the type of operator
	 */
	char getType();
	/**
	 * @return the precedence of operator
	 */
	int getPrecedence();
	/**
	 * @return the input operator
	 */
	String getOperator();
}
