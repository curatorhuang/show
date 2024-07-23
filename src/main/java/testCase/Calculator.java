package testCase;

// Calculator.java
public class Calculator {

    // 基本加法运算
    public int add(int a, int b) {
        return a + b;
    }

    // 基本减法运算
    public int subtract(int a, int b) {
        return a - b;
    }

    // 基本乘法运算
    public static int multiply(int a, int b) {
        return a * b;
    }

    // 基本除法运算
    public int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Division by zero.");
        }
    }
}