package common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.text.DecimalFormat;

class CalculatorTest {

    public float result = 0;
    DecimalFormat df = new DecimalFormat("#.00");

    @Test
    void testCalculatorSum() {

        result =  Calculator.calculator( 2,  4, "+");
        Assertions.assertEquals(6,result);

    }

    @Test
    void testCalculatorDeduct() {

        result = Calculator.calculator(8, 4, "-");
        Assertions.assertEquals(4,result);

    }
    @Test
    void testCalculatorMultiply() {

        result = Calculator.calculator(5, 2, "*");
        Assertions.assertEquals(10,result);

    }

    @Test
    void testCalculatorDivide() {

        result = Calculator.calculator(15, 5, ":");
        Assertions.assertEquals(3,result);

    }

    @Test
    void testCalculatorDivideFloat() {

        result = Calculator.calculator(2, 5, ":");
        Assertions.assertEquals(Float.valueOf(df.format(0.40)), Float.valueOf(df.format(result)));

    }

    @Test
    void testCalculatorDeductFloat() {

        result = Calculator.calculator(8.94f, 10.23f, "-");
        Assertions.assertEquals(Float.valueOf(df.format(-1.29)), Float.valueOf(df.format(result)));

    }

    @Test
    void testCalculatorDivideZero() {

        result = Calculator.calculator(15, 0, ":");
        Assertions.assertEquals(0, result);

    }

}

