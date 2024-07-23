package org.example;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class FileScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入文件夹路径
        System.out.print("请输入文件夹路径: ");
        String folderPath = scanner.nextLine();

        // 提示用户输入文件后缀名
        System.out.print("请输入文件后缀名: ");
        String fileExtension = scanner.nextLine();

        // 扫描文件夹并打印符合条件的文件
        scanFolder(folderPath, fileExtension);

        // 计算特定后缀名文件的数量
        int count = countFilesWithExtension(folderPath, fileExtension);
        System.out.println("文件夹中共有 " + count + " 个 " + fileExtension + " 文件");

        // 提示用户输入具体文件名以获取其大小
        System.out.print("请输入要获取大小的文件名: ");
        String fileName = scanner.nextLine();
        long fileSize = getFileSize(folderPath, fileName);
        if (fileSize != -1) {
            System.out.println("文件 " + fileName + " 的大小是: " + fileSize + " 字节");
        } else {
            System.out.println("文件 " + fileName + " 不存在");
        }
    }

    public static void scanFolder(String folderPath, String fileExtension) {
        File folder = new File(folderPath);

        if (!folder.isDirectory()) {
            System.out.println("指定的路径不是一个文件夹");
            return;
        }

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(fileExtension);
            }
        };

        File[] files = folder.listFiles(filter);
        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println("找到文件: " + file.getAbsolutePath());
            }
        } else {
            System.out.println("没有找到符合条件的文件");
        }
    }

    public static int countFilesWithExtension(String folderPath, String fileExtension) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("指定的路径不是一个文件夹");
            return 0;
        }

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(fileExtension);
            }
        };

        File[] files = folder.listFiles(filter);
        return files != null ? files.length : 0;
    }

    public static long getFileSize(String folderPath, String fileName) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            System.out.println("指定的路径不是一个文件夹");
            return -1;
        }

        File file = new File(folderPath + File.separator + fileName);
        if (!file.isFile()) {
            System.out.println("指定的文件不存在");
            return -1;
        }

        return file.length();
    }
    // 获取文件个数
}

