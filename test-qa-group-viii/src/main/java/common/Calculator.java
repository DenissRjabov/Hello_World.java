package common;

public class Calculator {


    public static float calculator(float fistElement, float secondElement, String operator) {

        float result = 0;

        if (operator.equals("+"))
            { result = fistElement + secondElement;}
        if (operator.equals("-"))
            { result = fistElement - secondElement;}
        if (operator.equals("*"))
            { result = fistElement * secondElement;}
        if (operator.equals(":"))
            {  if(!(secondElement == 0))
                result = fistElement / secondElement;
               else {
                System.out.println("Divide to zero forbidden");
                }
            }

        return result;
    }
}
