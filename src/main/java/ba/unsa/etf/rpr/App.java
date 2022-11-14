package ba.unsa.etf.rpr;

/**
 * Author: @Benjamin Kadic
 * Class App
 * Class that parses and validates console input from args parameter
 * klasa koja parsira ulaz s konzole iz parametra args i vrši njegovu validaciju
 */
public class App 
{
    public static void main( String[] args )
    {

        StringBuilder input = new StringBuilder();
        for(int i = 0; i<args.length; i++){
            input.append(args[i]);
            if(i!=args.length-1) input.append(" ");
        }
        try{
            double result = ExpressionEvaluator.evaluate(input.toString());
            System.out.println(" = "+result);
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
