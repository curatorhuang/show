package testCase;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class SortTest {

    @Test
    @DisplayName("测试正常场景：已排序数组")
    public void testBubbleSort_SortedArray() {
        // 创建数组
        int[] arr = {1, 2, 3, 4, 5};

        // 调用被测方法
        Sort.bubbleSort(arr);

        // 验证结果
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    @DisplayName("测试正常场景：未排序数组")
    public void testBubbleSort_UnsortedArray() {
        // 创建数组
        int[] arr = {5, 2, 8, 3, 1};

        // 调用被测方法
        Sort.bubbleSort(arr);

        // 验证结果
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
    }

    @Test
    @DisplayName("测试边界条件：数组长度为1")
    public void testBubbleSort_ArrayLength1() {
        // 创建数组
        int[] arr = {1};

        // 调用被测方法
        Sort.bubbleSort(arr);

        // 验证结果
        assertArrayEquals(new int[]{1}, arr);
    }

    @Test
    @DisplayName("测试边界条件：数组长度为0")
    public void testBubbleSort_ArrayLength0() {
        // 创建数组
        int[] arr = {};

        // 调用被测方法
        Sort.bubbleSort(arr);

        // 验证结果
        assertArrayEquals(new int[0], arr);
    }

    @Test
    @DisplayName("测试异常处理：输入为null")
    public void testBubbleSort_NullInput() {
        // 验证异常
        assertThrows(NullPointerException.class, () -> {
            Sort.bubbleSort(null);
        });
    }
}
