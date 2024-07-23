
import (
	"fmt"
	"regexp"
	"strconv"
	"strings"
)

// 实现了一个简单的四则运算表达式解析和计算器功能
// 计算表达式的函数
func calculate(expression string) (int, error) {
	// 正则表达式匹配数字
	re := regexp.MustCompile(`(\d+)`)
	// 找到所有数字
	numbers := re.FindAllString(expression, -1)

	// 将字符串数字转换为整数
	var nums []int
	for _, numStr := range numbers {
		num, err := strconv.Atoi(numStr)
		if err != nil {
			return 0, err
		}
		nums = append(nums, num)
	}

	// 假设表达式是两个数字和一个运算符，例如 "3+5"

	// 找到运算符
	operator := strings.TrimSpace(strings.Join(re.Split(expression, -1), ""))

	// 根据运算符进行计算
	switch operator {
	case "+":
		return nums[0] + nums[1], nil
	case "-":
		return nums[0] - nums[1], nil
	case "*":
		return nums[0] * nums[1], nil
	case "/":
		if nums[1] == 0 {
			return 0, fmt.Errorf("division by zero")
		}
		return nums[0] / nums[1], nil
	default:
		return 0, fmt.Errorf("unsupported operator: %s", operator)
	}
}

func main() {
	// 从用户获取输入
	fmt.Print("Enter an expression (e.g., '3+5'): ")
	var expression string
	fmt.Scanln(&expression)

	// 计算表达式
	result, err := calculate(expression)
	if err != nil {
		fmt.Println("Error:", err)
	} else {
		fmt.Printf("The result is: %d\n", result)
	}
}