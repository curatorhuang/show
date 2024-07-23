package testCase;

// AdvancedCalculator.java
public class AdvancedCalculator {

    // 扩展的幂运算
    public double power(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    // 扩展的阶乘运算
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial of a negative number is undefined.");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = Calculator.multiply((int) result, i); // 调用父类的multiply方法
        }
        return result;
    }
}