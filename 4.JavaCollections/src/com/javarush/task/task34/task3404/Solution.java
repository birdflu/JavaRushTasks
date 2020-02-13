package com.javarush.task.task34.task3404;

/* 
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)", 0); //expected output 0.5 6
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        String e = correctHeadMinus(removeSpaces(expression));

        if (isDigit(e)) {
            System.out.print(format(e, countOperation));
            return;
        }

        Builder builder = new Builder(e, countOperation);
        builder.walk();
        recurse(builder.getResultExpression(),builder.getCountOperation());
    }

    private void generateAssert(final String expression, int countOperation, final String mainExpression) {
        String e = correctHeadMinus(removeSpaces(expression));

        if (isDigit(e)) {
            System.out.format("\nassert ((\"%s\").equals(test(\"%s\", 0)));",format(e, countOperation), mainExpression);

            return;
        }

        Builder builder = new Builder(e, countOperation);
        builder.walk();
        generateAssert(builder.getResultExpression(),builder.getCountOperation(),mainExpression);
    }

    private String correctHeadMinus(String expression) {
        if (expression.length() > 1 && expression.charAt(0) == '-' && expression.charAt(1) == '(')
            return "0"+expression;
        else return expression;
    }

    private boolean isDigit(String expression) {
        return expression.matches("(-?(\\d*\\.\\d*))|(-?(\\d*))")
                || expression.matches("\\(((-?(\\d*\\.\\d*))|(-?(\\d*)))\\)");
    }

    private String removeSpaces(String expression) {
        return expression.replaceAll(" ", "");
    }

    private String format(String expression, int countOperation) {
        if (expression.charAt(0) == '(' && expression.charAt(expression.length()-1) == ')')
            expression = expression.substring(1, expression.length()-1);
        double res = Double.parseDouble(expression);
        return  String.format("%s %d", Builder.round(res,2), countOperation);
    }

    public Solution() {
        //don't delete
    }
}
