package com.pcms.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class FileUtil {

    private static final Logger logger = Logger.getLogger(FileUtil.class);

    private final static int BUFFER = 1024;

    private URL base = this.getClass().getResource("/");

    public static String getRootPath() {
        return Thread.currentThread().getContextClassLoader().getResource("").toString().replace('/', '\\')
                .replace("file:", "").replace("classes\\", "").substring(1);
    }

    public static File createFile(String path) throws IOException {
        FileUtil.makeDir(path.substring(0, path.lastIndexOf(File.separator) + 1));
        File filename = new File(path);
        if (!filename.exists()) {
            filename.createNewFile();
        }
        return filename;
    }

    public static boolean copyTo(String strSourceFileName, String strDestDir) {
        File fileSource = new File(strSourceFileName);
        File fileDest = new File(strDestDir);
        if (!fileSource.exists() || !fileSource.isFile()) {
            logger.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
            return false;
        }

        if (!fileDest.isDirectory() || !fileDest.exists()) {
            if (!fileDest.mkdirs()) {
                logger.debug("目录文件夹不存，在创建目标文件夹时失败!");
                return false;
            }
        }

        try {
            String strAbsFilename = strDestDir + File.separator + fileSource.getName();

            FileInputStream fileInput = new FileInputStream(strSourceFileName);
            FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

            logger.debug("开始拷贝文件");

            int count = -1;

            long nWriteSize = 0;
            long nFileSize = fileSource.length();

            byte[] data = new byte[BUFFER];

            while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

                fileOutput.write(data, 0, count);

                nWriteSize += count;

                long size = (nWriteSize * 100) / nFileSize;
                long t = nWriteSize;

                String msg = null;

                if (size <= 100 && size >= 0) {
                    msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
                    logger.debug(msg);
                } else if (size > 100) {
                    msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
                    logger.debug(msg);
                }

            }

            fileInput.close();
            fileOutput.close();

            logger.debug("拷贝文件成功!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);
        if (!fileDelete.exists() || !fileDelete.isFile()) {
            logger.debug(strFileName + "不存在!");
            return false;
        }
        return fileDelete.delete();
    }

    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = delete(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功 能: 移动文件(只能移动文件) 参 数: strSourceFileName: 是指定的文件全路径名 strDestDir:
     * 移动到指定的文件夹中 返回值: 如果成功true; 否则false
     *
     * @param strSourceFileName
     * @param strDestDir
     * @return
     */
    public static boolean moveFile(String strSourceFileName, String strDestDir) {
        if (copyTo(strSourceFileName, strDestDir)) {
            return delete(strSourceFileName);
        } else {
            return false;
        }
    }

    /**
     * 功 能: 创建文件夹 参 数: strDir 要创建的文件夹名称 返回值: 如果成功true;否则false
     *
     * @param strDir
     * @return
     */
    public static boolean makeDir(String strDir) {
        File fileNew = new File(strDir);

        if (!fileNew.exists()) {
            return fileNew.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 功 能: 删除文件夹 参 数: strDir 要删除的文件夹名称 返回值: 如果成功true;否则false
     *
     * @param strDir
     * @return
     */
    public static boolean removeDir(String strDir) {
        File rmDir = new File(strDir);
        if (rmDir.isDirectory() && rmDir.exists()) {
            String[] fileList = rmDir.list();

            for (int i = 0; i < fileList.length; i++) {
                String subFile = strDir + File.separator + fileList[i];
                File tmp = new File(subFile);
                if (tmp.isFile()) {
                    tmp.delete();
                } else if (tmp.isDirectory()) {
                    removeDir(subFile);
                }
            }
            rmDir.delete();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 写入文件
     *
     */
    public static void contentToTxt(String filePath, String content) {
        String s1 = new String();// 内容更新
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();// 不存在则创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));
            logger.info(s1);
            input.close();
            s1 += content;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     *
     */
    public static StringBuffer ReadTxtFile(String FileName) throws Exception {
        FileReader fr = new FileReader(FileName);
        BufferedReader br = new BufferedReader(fr);
        char[] buffer = new char[1024];
        StringBuffer sb = new StringBuffer();
        int len = 0;
        while ((len = br.read(buffer)) != -1) {
            sb.append(buffer, 0, len);
        }
        if (br != null) {
            br.close();
        }
        if (fr != null) {
            fr.close();
        }
        return sb;
    }

    public static String readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            FileUtil.showAvailableBytes(in);
            StringBuffer str = new StringBuffer();
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                String s = new String(tempbytes, 0, byteread, "UTF-8");
                str.append(s);
            }
            return str.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @throws IOException
     *
     */
    public static String downloadByUrl(String sURL, String sName, String strDestDir)
            throws FileNotFoundException, IOException {
        logger.info("下载地址======" + sURL + "文件名称=======" + sName);
        int nStartPos = 0;
        int nRead = 0;
        File fp = new File(strDestDir);
        logger.info("下载文件路径===========" + strDestDir);
        if (!fp.isDirectory()) {
            fp.mkdir();
        }
        HttpURLConnection httpConnection = null;
        URL url = new URL(sURL);
        // 打开连接
        httpConnection = (HttpURLConnection) url.openConnection();
        // 获得文件长度
        long nEndPos = getFileSize(sURL);
        if (nEndPos == -1) {
            throw new FileNotFoundException();
        }
        FileUtil.createFile(strDestDir + sName);
        RandomAccessFile oSavedFile = new RandomAccessFile(strDestDir + sName, "rw");
        httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
        String sProperty = "bytes=" + nStartPos + "-";
        // 告诉服务器book.rar这个文件从nStartPos字节开始传
        httpConnection.setRequestProperty("RANGE", sProperty);
        InputStream input = httpConnection.getInputStream();
        byte[] b = new byte[1024];
        // 读取网络文件,写入指定的文件中
        while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
            oSavedFile.write(b, 0, nRead);
            nStartPos += nRead;
        }
        input.close();
        oSavedFile.close();
        httpConnection.disconnect();

        return strDestDir + sName;
    }

    // 获得文件长度
    public static long getFileSize(String sURL) {
        int nFileLength = -1;
        HttpURLConnection httpConnection = null;
        try {
            URL url = new URL(sURL);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestProperty("User-Agent", "Internet Explorer");

            int responseCode = httpConnection.getResponseCode();
            if (responseCode >= 400) {
                System.err.println("Error Code : " + responseCode);
                return -2; // -2 represent access is error
            }
            String sHeader;
            for (int i = 1;; i++) {
                sHeader = httpConnection.getHeaderFieldKey(i);
                if (sHeader != null) {
                    if (sHeader.equals("Content-Length")) {
                        nFileLength = Integer.parseInt(httpConnection.getHeaderField(sHeader));
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }
        return nFileLength;
    }

    /**
     * 获取文件名称
     *
     */
    public static String getZipFilename(String url) {
        int index = url.lastIndexOf("/");
        String s = url.substring(index + 1);
        return s;
    }

    public static String unzip(ZipInputStream inputStream, String outputDirectory, String pwd) {
        String path = "";
        ZipEntry zipEntry = null;
        FileOutputStream outputStream = null;
        FileUtil.makeDir(outputDirectory);
        try {
            while ((zipEntry = inputStream.getNextEntry()) != null) {
                String name = zipEntry.getName().replace("/", File.separator);
                name = name.replace("\\", File.separator);
                if (zipEntry.isDirectory()) {
                    name = name.substring(0, name.length() - 1);
                    File file = new File(outputDirectory + name);
                    file.mkdir();
                } else {
                    File file = createFile(outputDirectory + name);

                    // 普通解压缩文件
                    if (pwd == null || pwd.trim().equals("")) {
                        String filep = outputDirectory + name;
                        int nRead = 0;
                        RandomAccessFile oSavedFile = new RandomAccessFile(filep, "rw");
                        byte[] b = new byte[1024];
                        // 读取网络文件,写入指定的文件中
                        while ((nRead = inputStream.read(b, 0, 1024)) > 0) {
                            oSavedFile.write(b, 0, nRead);
                        }
                        if (filep.contains(".txt")) {
                            path += filep + ";";
                        }
                        oSavedFile.close();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }

    /**
     * 发送请求
     *
     * @param url 请求地址
     * @param filePath 文件在服务器保存路径
     * @return
     ** @throws IOException
     */
    public static int uploadToUrl(String url, String filePath) throws Exception {
        logger.info("url=================" + url);
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return -1;

        }
        /**
         * * 第一部分
         */
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        /**
         * * 设置关键值
         */
        con.setRequestMethod("POST");
        // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        // post方式不能使用缓存
        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        // ////////必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(head);
        // 文件正文部分
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");
        // 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        /**
         * * 读取服务器响应，必须读取,否则提交不成功
         */
        int reposCode = con.getResponseCode();
        logger.info("reposCode=========================" + reposCode);
        return reposCode;
    }

    public static void Copy(File oldfile, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    logger.info(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            logger.error("Copy error");
            e.printStackTrace();
        }
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        FileInputStream fi = null;

        FileOutputStream fo = null;

        FileChannel in = null;

        FileChannel out = null;

        try {

            fi = new FileInputStream(sourceFile);

            fo = new FileOutputStream(targetFile);

            in = fi.getChannel();// 得到对应的文件通道

            out = fo.getChannel();// 得到对应的文件通道

            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                fi.close();

                in.close();

                fo.close();

                out.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                copyFile(sourceFile, new File(targetDir + File.separator + file[i].getName()));
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    public static void main(String[] args) {
        try {
            FileUtil.copyFile(new File("E:\\QQ图片20150616163956.jpg"),
                    new File("D:\\work" + File.separator + "220802198802221520" + "."
                            + FilenameUtils.getExtension("E:\\QQ图片20150616163956.jpg").toLowerCase()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 文件上传 方式2 返回状态码 1表示成功,-1表示失败
     */
    public static String fileChannelCopy(File s, File t) {
        String status = "1";
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            status = "-1";
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                status = "-1";
                e.printStackTrace();
            }
        }
        return status;
    }

    /**
     * 文件上传方式三
     */
    /**
     * 名字前加上时间戳
     */
    public static String getOutName(String name) {
        Long lon = System.currentTimeMillis();
        return lon.toString() + "_" + name;
    }
}
