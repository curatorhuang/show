import numpy as np
import matplotlib.pyplot as plt


# 用于计算多项式插值的Python代码，使用拉格朗日插值法进行插值计算，并绘制结果图表
def lagrange_interpolation(x, y, xi):
    n = len(x)
    yi = 0
    for i in range(n):
        li = 1
        for j in range(n):
            if i != j:
                li *= (xi - x[j]) / (x[i] - x[j])  # 1
        yi += li * y[i]  # 2
    return yi


def plot_lagrange_interpolation(x, y, x_interp, y_interp):
    plt.figure(figsize=(10, 6))
    plt.plot(x, y, 'bo', label='Data points')  # 3
    plt.plot(x_interp, y_interp, 'r-', label='Lagrange interpolation')  # 4
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend()
    plt.title('Lagrange Polynomial Interpolation')
    plt.grid(True)  # 5
    plt.show()


# 数据点
x_points = np.array([0, 1, 2, 3, 4, 5])
y_points = np.array([1, 2, 0, 2, 1, 3])

# 插值点
x_interp = np.linspace(min(x_points), max(x_points), 100)
y_interp = [lagrange_interpolation(x_points, y_points, xi) for xi in x_interp]  # 6

# 绘制插值结果
plot_lagrange_interpolation(x_points, y_points, x_interp, y_interp)
