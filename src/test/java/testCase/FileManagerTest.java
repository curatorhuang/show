
package testCase;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.*;

public class FileManagerTest {
    private FileManager fileManager;
    private final String TEST_FILE_NAME = "testFile.txt";
    private final String TEST_CONTENT = "Hello, world!";

    @Before
    public void setUp() {
        fileManager = new FileManager();
    }

    @After
    public void tearDown() {
        File file = new File(TEST_FILE_NAME);
        file.delete();
    }

    // 测试创建文件
    @Test
    public void testCreateFile() {
        fileManager.createFile(TEST_FILE_NAME);
        File file = new File(TEST_FILE_NAME);
        assertTrue("文件应该被创建", file.exists());
    }

    // 测试写入文件
    @Test
    public void testWriteFile() {
        fileManager.createFile(TEST_FILE_NAME);
        fileManager.writeFile(TEST_FILE_NAME, TEST_CONTENT);
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_NAME))) {
            content = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("文件内容应该匹配", TEST_CONTENT, content);
    }

    // 测试读取文件
    @Test
    public void testReadFile() {
        fileManager.createFile(TEST_FILE_NAME);
        fileManager.writeFile(TEST_FILE_NAME, TEST_CONTENT);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        fileManager.readFile(TEST_FILE_NAME);
        assertTrue("输出应该包含文件内容", outContent.toString().contains(TEST_CONTENT));
    }

    // 测试删除文件
    @Test
    public void testDeleteFile() {
        fileManager.createFile(TEST_FILE_NAME);
        fileManager.deleteFile(TEST_FILE_NAME);
        File file = new File(TEST_FILE_NAME);
        assertFalse("文件应该被删除", file.exists());
    }
}

