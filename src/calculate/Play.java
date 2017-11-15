package calculate;

public class Play {

	public static void main(String[] args) {
		ShuntingYard s0 = new ShuntingYard("2 * x ^ 3 + 11.7 * x ^ 2 + 17.7 * x - 5",1);
		s0.printMyConversion();
		System.out.println(  );
		CalculateResult c0 = new CalculateResult(s0.getFinalConversion());
		System.out.println("Result : "+c0.getResult());
		
		ShuntingYard s1 = new ShuntingYard("6 * x ^ 2 + 23.4 * x + 17.7",1);
		s1.printMyConversion();
		System.out.println(  );
		CalculateResult c1 = new CalculateResult(s1.getFinalConversion());
		System.out.println("Result : "+c1.getResult());
		
		ShuntingYard s2 = new ShuntingYard("x + 10 - x * cosh ( 50 / x )",1.1);   
		s2.printMyConversion();
		System.out.println(  );
		CalculateResult c2 = new CalculateResult(s2.getFinalConversion());
		System.out.println("Result : "+c2.getResult());
		
		ShuntingYard s3 = new ShuntingYard("( 50 * sinh ( 50 / x ) / x ) - cosh ( 50 / x ) + 1",1.1);
		s3.printMyConversion();
		System.out.println(  );
		CalculateResult c3 = new CalculateResult(s3.getFinalConversion());
		System.out.println("Result : "+c3.getResult());
	}

}
