package testCase;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

public class SolutionTest {

    private Solution solution;

    @BeforeEach
    public void setUp() {
        solution = new Solution();
    }

    /**
     * 测试正常场景：输入数组为[1, 2, 0]
     * 预期结果：返回3
     */
    @Test
    @DisplayName("测试正常场景：输入数组为[1, 2, 0]")
    public void testFirstMissingPositive_NormalScenario() {
        int[] nums = {1, 2, 0};
        int result = solution.firstMissingPositive(nums);
        assertEquals(3, result);
    }

    /**
     * 测试正常场景：输入数组为[3, 4, -1, 1]
     * 预期结果：返回2
     */
    @Test
    @DisplayName("测试正常场景：输入数组为[3, 4, -1, 1]")
    public void testFirstMissingPositive_Case2() {
        int[] nums = {3, 4, -1, 1};
        int result = solution.firstMissingPositive(nums);
        assertEquals(2, result);
    }

    /**
     * 测试边界条件：输入数组为[]
     * 预期结果：返回1
     */
    @Test
    @DisplayName("测试边界条件：输入数组为[]")
    public void testFirstMissingPositive_EmptyArray() {
        int[] nums = {};
        int result = solution.firstMissingPositive(nums);
        assertEquals(1, result);
    }

    /**
     * 测试边界条件：输入数组为[1]
     * 预期结果：返回2
     */
    @Test
    @DisplayName("测试边界条件：输入数组为[1]")
    public void testFirstMissingPositive_SingleElementArray() {
        int[] nums = {1};
        int result = solution.firstMissingPositive(nums);
        assertEquals(2, result);
    }

    /**
     * 测试异常处理：输入数组为null
     * 预期结果：抛出NullPointerException
     */
    @Test
    @DisplayName("测试异常处理：输入数组为null")
    public void testFirstMissingPositive_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            solution.firstMissingPositive(null);
        });
    }
}
