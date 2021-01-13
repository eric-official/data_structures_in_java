// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package calculator_group_14;

public class calculator_group_14 {
    public interface Calculator {
        boolean isValidExpression(String expression);
        int calculate(String expression);
    }

    public static class CalcClass implements Calculator {

        @Override
        public boolean isValidExpression(String expression) {
            if(expression.isEmpty() || expression.isBlank()) return false;
            doubleList List1 = new doubleList(expression);
            return List1.isValid();
        }



        @Override
        public int calculate(String expression) {
            doubleList List1 = new doubleList(expression);
            return List1.calculate();
        }

    }

    public static void main(String[] args) {
        CalcClass c1=new CalcClass();
        System.out.println("korrekte Ausdrücke: ");
        System.out.println(c1.isValidExpression("( 12 + ( 4 * 3 ) + 5 * 10 )"));
        System.out.println(c1.calculate("( 12 + ( 4 * 3 ) + 5 * 10 )"));
        System.out.println(c1.isValidExpression("( 10 )"));
        System.out.println(c1.calculate("( 10 )"));
        System.out.println(c1.isValidExpression("10"));
        System.out.println(c1.calculate("10"));
        System.out.println(c1.isValidExpression("10 + ( 2 * 12 ) + 8"));
        System.out.println(c1.calculate("10 + ( 2 * 12 ) + 8"));
        System.out.println(c1.isValidExpression("5 + ( ( 8 + 4 ) * 6 )"));
        System.out.println(c1.calculate("5 + ( ( 8 + 4 ) * 6 )"));
        System.out.println(" ");
        System.out.println("inkorrekte  Ausdrücke: ");
        System.out.println(c1.isValidExpression("( 12 + 4"));
        System.out.println(c1.isValidExpression("12 + + 4"));
        System.out.println(c1.isValidExpression("12 + * 2"));
        System.out.println(c1.isValidExpression("+"));
        System.out.println(c1.isValidExpression("()()()"));
        System.out.println(c1.isValidExpression(""));
        System.out.println(c1.isValidExpression("    "));
    }
}
