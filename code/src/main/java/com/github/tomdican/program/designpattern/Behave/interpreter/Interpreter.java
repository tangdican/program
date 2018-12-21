package com.github.tomdican.program.designpattern.Behave.interpreter;

import java.util.Stack;

public class Interpreter {
    public float evaluate(String expression) {
        Stack<Expression> stack = new Stack<Expression>();
        float result = 0;
        for (String token: expression.split(" ")) {
            if (isPlus(token)) {
                Expression exp = stack.push(new Plus(stack.pop(), stack.pop()));
                if (exp!=null) {
                    result = exp.interpret();
                }
            } else {
                stack.push(new Number(token));
            }
        }

        return result;
    }

    public boolean isPlus(String plus) {
        if ("+".equals(plus)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        float evaluate = interpreter.evaluate("1.1 2.5 +");
        System.out.println(evaluate);
    }

}
