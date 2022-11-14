package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static ba.unsa.etf.rpr.ExpressionEvaluator.evaluate;
import static org.junit.jupiter.api.Assertions.*;
import ba.unsa.etf.rpr.ExpressionEvaluator;

import java.util.InputMismatchException;

class ExpressionEvaluatorTest {

    @Test
    void evaluateException() {
        assertThrows(RuntimeException.class, ()->{evaluate("( 2 + ( ) 3");});
    }

    @Test
    void evaluatePlus() {
        assertEquals(5d, evaluate("( 4 + 1 )"));
    }

    @Test
    void evaluateMinus() {
        assertEquals(3d, evaluate("( 5 - 2 )"));
    }
    @Test
    void evaluateMultiply() {
        assertEquals(18d, evaluate("( 6 * 3 )"));
    }
    @Test
    void evaluateDivide() {
        assertEquals(1.75d, evaluate("( 7 / 4 )"));
    }
    @Test
    void evaluateSqrt() {
        assertEquals(3d, evaluate("( sqrt 9 )"));
    }
    @Test
    void evaluateMultipleOperators() {
        assertEquals(8d, evaluate("( ( sqrt 9 ) + 5 )"));
    }
    @Test
    void evaluateMultipleOperators2() {
        assertEquals(4d, evaluate("( sqrt ( 9 + 7 ) )"));
    }
    @Test
    void evaluateInputMismatchException() {
        assertThrows(InputMismatchException.class, () -> { evaluate("( ( 1 + 3 )"); });
    }
    @Test
    void evaluateWrongNumberInput(){
        assertThrows(RuntimeException.class, () -> { evaluate("( 1 + z )"); });
    }
    @Test
    void evaluateEmptyValStack(){
        assertThrows(RuntimeException.class, () -> { evaluate("( 1 + ( ) + 2"); });
    }



}