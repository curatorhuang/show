# SimpleCalculator.py

class SimpleCalculator:
    def add(self, a, b):
        """加法运算"""
        return a + b

    def subtract(self, a, b):
        """减法运算"""
        return a - b

    def multiply(self, a, b):
        """乘法运算"""
        return a * b

    def divide(self, a, b):
        """除法运算，如果除数为0，则抛出异常"""
        if b == 0:
            raise ValueError("Cannot divide by zero.")
        return a / b

# 测试代码
if __name__ == "__main__":
    calc = SimpleCalculator()

    print("5 + 3 =", calc.add(5, 3))
    print("5 - 3 =", calc.subtract(5, 3))
    print("5 * 3 =", calc.multiply(5, 3))
    try:
        print("5 / 3 =", calc.divide(5, 3))
        print("5 / 0 =", calc.divide(5, 0))
    except ValueError as e:
        print(e)