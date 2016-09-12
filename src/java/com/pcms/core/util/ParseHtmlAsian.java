package com.pcms.core.util;
import java.io.*;
import java.nio.charset.Charset;
  






import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ParseHtmlAsian {
	/**
     * Creates a PDF with the words "Hello World"
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
	/*
    public static void createPdf(String path, String fileName) throws IOException, DocumentException {
    	try{

    		 // step 1  
            String inputFile = fileName;  
            String url = path+"/"+inputFile+".html"; 
            String outputFile =path+"/"+inputFile+".pdf";
            System.out.println(url);  
            // step 2  
   
            ITextRenderer renderer = (ITextRenderer)Class.forName("org.xhtmlrenderer.pdf.ITextRenderer").newInstance(); 
            renderer.setDocument(new File(url));
            OutputStream os = new FileOutputStream(outputFile); 
            // step 3 解决中文支持  
            ITextFontResolver fontResolver = renderer  
                    .getFontResolver();
            fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);     
            renderer.layout();  
            renderer.createPDF(os);  
            os.close();  
            System.out.println("create pdf done!!");  
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
       
    }
    */
    
    public static boolean writeWordFile(String path,String fileName) throws Exception {
        boolean flag = false;
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        try {
               if (!"".equals(path)) {
                      File fileDir = new File(path);
                      if (fileDir.exists()) {
                             String content = readFile(path+"/"+fileName+".html");
                             byte b[] = content.getBytes();
                             bais = new ByteArrayInputStream(b);
                             POIFSFileSystem poifs = new POIFSFileSystem();
                             DirectoryEntry directory = poifs.getRoot();
                             DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                             fos = new FileOutputStream(path+"/"+fileName+".doc");
                             poifs.writeFilesystem(fos);
                             bais.close();
                             fos.close();
                      }
               }

        } catch (IOException e) {
               e.printStackTrace();
        } finally {
               if(fos != null) fos.close();
               if(bais != null) bais.close();
        }
        return flag;
 }
	  /**
     * 读取html文件到字符串
     * @param filename
     * @return
     * @throws Exception
     */
    public static String readFile(String filename) throws Exception {
           StringBuffer buffer = new StringBuffer("");
           BufferedReader br = null;
           try {
                  br = new BufferedReader(new FileReader(filename));
                  buffer = new StringBuffer();
                  while (br.ready())
                         buffer.append((char) br.read());
           } catch (Exception e) {
                  e.printStackTrace();
           } finally {
                  if(br!=null) br.close();
           }
           return buffer.toString();
    }
}
