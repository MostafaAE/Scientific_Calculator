
import java.math.BigDecimal;
import java.math.MathContext;


public class CalcEval {
        int pos = -1, chCode;
        String expression;
        double temp;
        BigDecimal factor;
        boolean isFunc = false,exception=false;
        
        

	public boolean isException() {
			return exception;
		}

	public CalcEval(final String s ) {
		this.expression=s;
	}

        //get the next character to parse or -1 if if we reached the end of the string
    	void nextChar() {
    		chCode = (++pos < expression.length()) ? expression.charAt(pos) : -1;
    	}

        //skip any spaces in the string
        //check if ch == charToEat to perform the right operation
    	boolean isChar(int charToEat) {
    		while (chCode == ' ')
    			nextChar();
    		if (chCode == charToEat) {
    			nextChar();
    			return true;
    			}
    		return false;
    	}

    	BigDecimal eval() {
    		nextChar();
    		BigDecimal val = evalExpression();

                //if we did not reach the end of the string by parsing then throw an exception (unknown char in the string)
    		if (pos < expression.length())
                    exception= true;
    			//throw new RuntimeException("Unexpected: " + (char)chCode);
    		return val;
    	}


        // expression = term | expression `+` term | expression `-` term
        // first we will call parseTerm() to get the value of the term then check for + or - and do the operation with the next term
        BigDecimal evalExpression() {

        	BigDecimal val = evalTerm();
            while(true) {
            	if(isChar('+'))
                	val=val.add(evalTerm()) ; // addition
                else if (isChar('-'))
                	val= val.subtract(evalTerm()); // subtraction
                else
                	return val;
            }
        }
        // term = factor | term `*` factor | term `/` factor
        BigDecimal evalTerm() {
            BigDecimal val = evalFactor();
            while(true){
                if(isChar('*'))
                	val= val.multiply(evalFactor()); // multiplication
                else if (isChar('/'))
                	{
                		factor=evalFactor();
                		if(factor.doubleValue() < 0.0000000001  )
                			exception=true;
                			
                		else
                			val= val.divide(factor,MathContext.DECIMAL64) ; // division
                	}
                //evaluating yroot(x)
                else if(chCode=='r')
                {

                    int startPos = this.pos;
                    temp=val.doubleValue();

                    while (chCode >= 'a' && chCode <= 'z')
                        nextChar();

                    String func = expression.substring(startPos, this.pos);
                    val = evalFactor();

                    if (func.equals("root"))
                        if(val.doubleValue()<0 && temp%2==0 )
                        {
                            exception= true;
                        }
                    else
                        {
                            if(val.doubleValue()>=0){
                                val= BigDecimal.valueOf(Math.pow(val.doubleValue(), 1.0/temp));
                            }
                            else{
                                val= BigDecimal.valueOf(Math.pow(Math.abs(val.doubleValue()), 1.0/temp));
                                val=val.negate();
                            }
                        }
                        
                }
                //evaluating nPr & //evaluating nCr
                else if(chCode =='P' || chCode=='C')
                {
                    int startPos = this.pos;
                    temp=val.doubleValue();
                        nextChar();

                    String func = expression.substring(startPos, this.pos);
                    val = evalFactor();
                    if((val.doubleValue() - temp) >=0)
                    	exception = true;
                    else 
                    {
                    if (func.equals("P"))
                        val= nPr(temp,val.doubleValue());
                    else if (func.equals("C"))
                        val= nCr(temp,val.doubleValue());
                    }
                }
            
                else
                	return val;
            }
        }

        // factor = `+` factor | `-` factor | `(` expression `)`
        //        | number | functionName factor | factor `^` factor
        BigDecimal evalFactor() {

            if (isChar('+'))
                return evalFactor(); // unary plus
            if (isChar('-'))
            	return evalFactor().negate(); // unary minus

            BigDecimal val = null;
            int startPos = this.pos;

            if (isChar('(')) { // parentheses
                val = evalExpression();
                //eat(')');
	            if(isFunc && isChar(')'))
	            {
	                isFunc=false;
	                return val;
	            }
	            else
	                isChar(')');

            }
            else if ((chCode >= '0' && chCode <= '9') || chCode == '.')
            { // numbers
                while ((chCode >= '0' && chCode <= '9') || chCode == '.')
                	nextChar();
                
                val = new BigDecimal(expression.substring(startPos, this.pos));

            }
            //getting the value of pi
            else if (isChar('π'))
                val=BigDecimal.valueOf(Math.PI);
            //getting the value of e
            else if (isChar('e'))
                val=BigDecimal.valueOf(Math.E);
            //checking for functions
            else if (chCode >= 'a' && chCode <= 'z')
            { // functions
                while (chCode >= 'a' && chCode <= 'z')
                	nextChar();
                //storing function name in func to match it with our calc functions
                String func = expression.substring(startPos, this.pos);
                    isFunc=true;
                    val = evalFactor();

                //roots
                if (func.equals("sqrt"))
                {
                    if(val.doubleValue()<0.0000000001)
                        exception=true;
                    else
                        val= BigDecimal.valueOf(Math.sqrt(val.doubleValue()));
                }
                	
                else if (func.equals("cbrt"))
                        val= BigDecimal.valueOf(Math.cbrt(val.doubleValue()));
                //trigometric functions
                else if (func.equals("sin"))
                	val= BigDecimal.valueOf(Math.sin(Math.toRadians(val.doubleValue())));
                else if (func.equals("cos"))
                	val= BigDecimal.valueOf(Math.cos(Math.toRadians(val.doubleValue())));
                else if (func.equals("tan"))
                {
                	if( (val.doubleValue()-val.intValue()) ==0 &&( (val.intValue()/90) %2 ==1) )
						exception=true;
                	else
                            val= BigDecimal.valueOf(Math.tan(Math.toRadians(val.doubleValue())));
                }
                //is x >1 return NaN (0<= x <=1) it returns a radian value
                else if (func.equals("asin"))
                {
                	if(val.doubleValue()<0 || val.doubleValue()>1)
                		exception=true;
                	else
                		val= BigDecimal.valueOf(Math.asin(val.doubleValue()));
                }
                else if (func.equals("acos"))
                {
                	if(val.doubleValue()<0 || val.doubleValue()>1)
                		exception=true;
                	else
                		val= BigDecimal.valueOf(Math.acos(val.doubleValue()));
                }
                else if (func.equals("atan"))
                {
                	if(val.doubleValue()<0 || val.doubleValue()>1)
                		exception=true;
                	else
                		val= BigDecimal.valueOf(Math.atan(val.doubleValue()));
                }
                else if (func.equals("sinh"))
                	val= BigDecimal.valueOf(Math.sinh(Math.toRadians(val.doubleValue())));
                else if (func.equals("cosh"))
                	val= BigDecimal.valueOf(Math.cosh(Math.toRadians(val.doubleValue())));
                else if (func.equals("tanh"))
                	val= BigDecimal.valueOf(Math.tanh(Math.toRadians(val.doubleValue())));
                //log and log10
                else if (func.equals("ln"))
                {
                	if(val.doubleValue()<0)
                		exception=true;
                	else
                		val= BigDecimal.valueOf(Math.log(val.doubleValue()));
                }
               	else if (func.equals("log"))
               	{
               		if(val.doubleValue()<0)
                            exception=true;
                	else
                            val= BigDecimal.valueOf( Math.log10(val.doubleValue()) );
               	}
               	else
                    exception = true;
                	//throw new RuntimeException("Unknown function: " + func);
                   
            }
           
            else {
                exception = true;
                //throw new RuntimeException("Unexpected: " + (char)chCode);
            }

            //powers (x^y)
            if (isChar('^'))
            {
            	factor =evalFactor();

                if(factor.doubleValue()== -1 && val.doubleValue()== 0)
                        exception=true;
                else
                    val=BigDecimal.valueOf( Math.pow(val.doubleValue(), factor.doubleValue()) ); // exponentiation
                
            	
            }
            
            //evaluating factorial
            else if(chCode == '!')
            {
                startPos = this.pos;
                temp=val.doubleValue();
                	nextChar();

                String func = expression.substring(startPos, this.pos);

                if (func.equals("!"))
                    val= factorial(temp);

            }
       
            if(val == (null))
            {
                exception=true;
                val = BigDecimal.ONE;
            }
            return val;
        }


     //factorial method
     BigDecimal factorial(double n) 
     {
        BigDecimal f = new BigDecimal("1");
        if (n<0 || (n -(int)n )>0)
        	exception=true;
        // Multiply f with 2, 3, ...n
        for (int i = 2; i <= n; i++)
            f = f.multiply(BigDecimal.valueOf(i));

        return f;
    }
     //nPr = n! / (n - r)!
     BigDecimal nPr(double n, double r)
    {
    	 if (r<0)
         	exception=true;	 
        return factorial(n).divide(factorial(n - r)) ;
    }
     //nCr = (n!) / (r! * (n-r)!)
    BigDecimal nCr(double n, double r)
    {
    	if (r<0)
         	exception=true;
        return factorial(n).divide((factorial(r).multiply(factorial(n - r))));
    }
    
    

    
}