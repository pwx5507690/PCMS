package com.pcms.core.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipException;

public class ZipUtil {

    private static final int BUFFER = 104;

    public static void unZipNoFile(String fileName, String destFilePath) {
        try {
            ZipFile zipFile = new ZipFile(fileName, "GBK");
            Enumeration emu = zipFile.getEntries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();
                if (entry.isDirectory()) {
                    File dir = new File(destFilePath + entry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                }
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                String name = getFileNameByPath(entry.getName());
                File file = new File(destFilePath + name);
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);
                byte[] buf = new byte[BUFFER];
                int len = 0;
                while ((len = bis.read(buf, 0, BUFFER)) != -1) {
                    fos.write(buf, 0, len);
                }
                bos.flush();
                bos.close();
                bis.close();
                System.out.println("解压文件：" + file.getName());
            }
            zipFile.close();
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> unZipNoFile(File filePath, String destFilePath) {
        List<String> zipList = new ArrayList<String>();
        try {
            ZipFile zipFile = new ZipFile(filePath, "GBK");
            Enumeration emu = zipFile.getEntries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();
                if (entry.isDirectory()) {
                    File dir = new File(destFilePath + entry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    continue;
                }
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                String name = getFileNameByPath(entry.getName());
                File file = new File(destFilePath + name);
                File parent = file.getParentFile();
                if (parent != null && (!parent.exists())) {
                    parent.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);
                byte[] buf = new byte[BUFFER];
                int len = 0;
                while ((len = bis.read(buf, 0, BUFFER)) != -1) {
                    fos.write(buf, 0, len);
                }
                bos.flush();
                bos.close();
                bis.close();
                System.out.println("解压文件：" + file.getName());
                zipList.add(file.getName());
            }
            zipFile.close();
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipList;
    }

    /**
     * 验证文件是否存在于压缩包
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean isExist(File filePath, String fileName) {

        boolean isExist = false;
        ZipEntry entry = null;
        String fileName_ = null;
        int index = -1;
        try {
            ZipFile zipFile = new ZipFile(filePath);
            Enumeration<?> entries = zipFile.getEntries();
            while (entries.hasMoreElements()) {
                entry = (ZipEntry) entries.nextElement();
                fileName_ = entry.getName();
                index = fileName_.lastIndexOf("/");
                if (index > -1) {
                    fileName_ = fileName_.substring(index + 1);
                }
                fileName_ = fileName_.toLowerCase();
                if (fileName_.equals(fileName)) {
                    isExist = true;
                    break;
                } else {
                    continue;
                }
            }
            zipFile.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return isExist;
    }
    
    public static boolean isPics(String filename) {
        boolean flag = false;
        if (filename.indexOf(".") > -1) {
            String tmpName = filename.substring(filename.lastIndexOf("."), filename.length());
            tmpName = tmpName.toLowerCase();
            if (tmpName.equals(".jpg") || tmpName.equals(".ico") || tmpName.equals(".bmp") || tmpName.equals(".png")) {
                flag = true;
            }
        }
        return flag;
    }

    public static void createDirectory(String dir) {

        File directory = new File(dir);
        if (directory.mkdir()) {
        } else // 使用mkdirs()方法可以创建多层级目录
        {
            if (directory.mkdirs()) {
            } else {
            }
        }
    }

    public static void download(String urlString, String filename) {
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[2048];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean Copy(String start, String end) {
        try {
            // 要拷贝的图片
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(start)));
            // 目标的地址
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(end)));
            try {
                int val = -1;
                while ((val = bis.read()) != -1) {
                    bos.write(val);
                }
                bos.flush();
                bos.close();
                bis.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getFileNameByPath(String path) {
        String name = "";
        if (path != null && path.length() > 0) {
            int index = path.lastIndexOf("/");
            int index2 = path.lastIndexOf("\\");
            if (index > index2) {
                name = path.substring(index + 1);
            } else {
                name = path.substring(index2 + 1);
            }
        }
        return name;
    }
}
