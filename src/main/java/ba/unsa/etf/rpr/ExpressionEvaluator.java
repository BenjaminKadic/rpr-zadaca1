package ba.unsa.etf.rpr;

import java.util.InputMismatchException;
import java.util.Stack;

/**
 * Author: @Benjamin Kadic
 * Class Evaluate
 * Class that Evaluates (fully parenthesized) arithmetic expressions using Dijkstra's two-stack algorithm
 * Input must have parenthesis for each operator!!
 * Klasa koja računa aritmetički izraz (u zagradama) koristeći Dijkstra algoritam sa dva steka
 * Na ulazu mora biti jedan operator po zagradi!!
 */

public class ExpressionEvaluator {

    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULT = "*";
    private static final String DIV = "/";
    private static final String SQRT = "sqrt";

    private static Stack<String> ops;
    private static Stack<Double> values;
    private static final RuntimeException e = new RuntimeException("Pogresan format unosa");

    /**
     * Method stringToStringArray
     * Method that returns an array of strings based on method parameter string
     * it makes difference between each word in parameter
     * metoda koja vraca niz stringova tako što razlikuje svaku riječ iz parametra string
     */
    private static String[] stringToStringArray(String string){
        return string.split(" ");
    }
    /**
     * Method evaluates
     * Method which evaluates arithmetic expression from parameter input using
     * Dijkstra's two-stack algorithm
     * Metoda koja računa aritmetički izraz (u zagradama) koristeći Dijkstra algoritam sa dva steka
     */
    public static Double evaluate(String input) throws RuntimeException {
        ops = new Stack<>();
        values = new Stack<>();
        int number_of_parenthesis=0;
        String[] in = stringToStringArray(input);
        for(String c : in) {
            if (LEFT_PARENTHESIS.equals(c)) number_of_parenthesis++;
            else if (isOperator(c)) {
                ops.push(c);
            } else if (RIGHT_PARENTHESIS.equals(c)) {
                if(values.isEmpty()) throw e;
                String op = ops.pop();
                Double two = values.pop();
                Double result;
                number_of_parenthesis--;
                if (SQRT.equals(op)) {
                    result = Math.sqrt(two);
                } else {
                    if(values.isEmpty()) throw e;
                    Double one = values.pop();
                    result = apply(one, op, two);
                    if (result.isNaN()) throw e;
                }
                values.push(result);
            } else try{
                values.push(Double.parseDouble(c));
            }catch(Exception temp){
                throw e;
            }
        }
        if(number_of_parenthesis!=0) throw new InputMismatchException( "Pogresan broj zagrada");
        return values.pop();

    }

    /**
     * Method isOperand
     * Method that checks if given String is equal to one of predetermined operators
     * Metoda koja provjerava da li je String jednak jednom od operatora
     */
    private static boolean isOperator(String c) {
        return PLUS.equals(c) || MINUS.equals(c) || DIV.equals(c) || MULT.equals(c) ||  SQRT.equals(c);
    }

    /**
     * Method apply
     * Method that applies operation given by parameter operator on first and second value
     * Metoda koja vrši operaciju određenu parametrom operator nad dvije vrijednosti iz parametara
     */

    private static Double apply(Double firstValue, String operator, Double secondValue) {
        if (PLUS.equals(operator)) {
            return firstValue + secondValue;
        } else if (MINUS.equals(operator)) {
            return firstValue - secondValue;
        } else if (MULT.equals(operator)) {
            return firstValue * secondValue;
        } else if (DIV.equals(operator)) {
            return firstValue / secondValue;
        }
        return Double.NaN;
    }
}