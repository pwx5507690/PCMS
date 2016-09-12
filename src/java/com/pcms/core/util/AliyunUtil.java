package com.pcms.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

public class AliyunUtil {
	
	private static final Log logger = LogFactory.getLog(AliyunUtil.class);
	/**
	 * 上传文件
	 * @param putpath cts/map
	 * @param file  110101.jpg
	 * @throws FileNotFoundException
	 */
	public static void putObject(String putpath,File file) throws FileNotFoundException {
		/* if(putpath != null && !"".equals(putpath)){
			if(putpath.lastIndexOf("/") != (putpath.length()-1)){
				putpath = putpath + "/";
			}
		} */
		
	    // 初始化OSSClient
		OSSClient client = new OSSClient(AliyunConfig.endpoint,AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);

	    // 获取指定文件的输入流
	    InputStream content = new FileInputStream(file);
	    // 创建上传Object的Metadata
	    ObjectMetadata meta = new ObjectMetadata();

	    // 必须设置ContentLength
	    meta.setContentLength(file.length());
	    // 上传Object.
	    logger.info("开始上传文件："+putpath);
	    PutObjectResult result = client.putObject(AliyunConfig.bucketName,putpath, content, meta);

	    logger.info("上传信息为："+result.getETag());
	    // 打印ETag
	    System.out.println(result.getETag());
	}
	
	
	/**
	 * 获取指定位置的文件流
	 * @param filepath cts/map
	 * @param filename 110101.jpg
	 * @return
	 * @throws IOException
	 */
	public static InputStream getObject(String filepath, String filename) throws IOException {

	    // 初始化OSSClient
		OSSClient client = new OSSClient(AliyunConfig.endpoint, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);

	    // 获取Object，返回结果为OSSObject对象
	    OSSObject object = client.getObject(AliyunConfig.bucketName, filepath+filename);
        
	    // 获取Object的输入流
	    InputStream objectContent = object.getObjectContent();
	    logger.info("-------------获取文件路径："+filepath+filename);
	    return objectContent;

	    // 关闭流
	   // objectContent.close();
	}
	
	
	/**
	 * 获取文件的访问地址
	 * @param filepath
	 * @param filename
	 * @return
	 */
	public String getFileUrl(String filepath, String filename){
		return "http://"+AliyunConfig.bucketName+".oss.aliyuncs.com//"+filepath+"/"+filename;
	}
	
	
	/**
	 * 获取文件目录
	 * @param filepath
	 * @param filename
	 * @return
	 */
	public static String getFileUrl(String filepath){
		return "http://"+AliyunConfig.bucketName+".oss-cn-haidian-a.aliyuncs.com//"+filepath;
	}
	
	/**
	 * 复制文件
	 */
	public static void copy(String key,String copykey){
		 // 初始化OSSClient
		OSSClient client = new OSSClient(AliyunConfig.endpoint, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);
		 // 获取Object，返回结果为OSSObject对象
	    OSSObject object = client.getObject(AliyunConfig.bucketName,key);
	    InputStream inputStream = object.getObjectContent();
	    ObjectMetadata meta = new ObjectMetadata();
	    meta.setContentLength(object.getObjectMetadata().getContentLength());
	    PutObjectResult result = client.putObject(AliyunConfig.bucketName,copykey, inputStream, meta);
	    logger.info("-----------文件复制结果："+result.getETag());
	    logger.info("-----------文件复制路径为："+copykey);
	    System.out.println(result.getETag());
	}
	
	/**
	 * 删除文件
	 */
	public static void remove(String key){
		 // 初始化OSSClient
		OSSClient client = new OSSClient(AliyunConfig.endpoint, AliyunConfig.accessKeyId, AliyunConfig.accessKeySecret);
		logger.info("文件路径为："+key);
		 // 获取Object，返回结果为OSSObject对象		
		client.deleteObject(AliyunConfig.bucketName, key);
		
	}
	
}
