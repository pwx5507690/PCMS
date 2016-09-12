package com.pcms.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;


public class ExportExcel {

    /* public void exportExcel(Map<String, String> filedNameMapping,
    		List<ExamApply> dataList,InputStream input, OutputStream out, String pattern,String fileName) throws IOException {
    //	第一步：处理模版文件
    	//获取模版excel
    	HSSFWorkbook teplateWork = new HSSFWorkbook(input);
        //获取一个表格
        HSSFSheet tsheet = teplateWork.getSheetAt(0);
        //获取实际行数
        int rowsNum = tsheet.getPhysicalNumberOfRows();
        //获取模版第一行
        HSSFRow trow = tsheet.getRow(0);
        //获取实际列数
        int colsNum = trow.getPhysicalNumberOfCells();
        
     //   第二步：生成一个新的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);
        
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");
        
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i=0; i<colsNum; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(trow.getCell(i).getStringCellValue());
            cell.setCellValue(text);
        }

        int index = 0;
        if(dataList!=null && dataList.size()>0){
        	for(int i=rowsNum; i<dataList.size()+rowsNum; i++){
        		index++;
        		row = sheet.createRow(index);
        		ExamApplyInfo examApplyInfo = dataList.get(i-rowsNum);
        		for(int j=0; j<colsNum; j++){
        			String titleName = trow.getCell(j).getStringCellValue();
        			String fieldName = filedNameMapping.get(titleName);
        			HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style2);
                    if(fieldName!=null&&!"".equals(fieldName)){
                    	String getMethodName = "get"
                    			+ fieldName.substring(0, 1).toUpperCase()
                    			+ fieldName.substring(1);
                    	try {
                    		Class tCls = examApplyInfo.getClass();
                    		Method getMethod = tCls.getMethod(getMethodName,
                    				new Class[] {});
                    		Object value = getMethod.invoke(examApplyInfo, new Object[] {});
                    		// 判断值的类型后进行强制类型转换
                    		String textValue = null;
                    		// if (value instanceof Integer) {
                    		// int intValue = (Integer) value;
                    		// cell.setCellValue(intValue);
                    		// } else if (value instanceof Float) {
                    		// float fValue = (Float) value;
                    		// textValue = new HSSFRichTextString(
                    		// String.valueOf(fValue));
                    		// cell.setCellValue(textValue);
                    		// } else if (value instanceof Double) {
                    		// double dValue = (Double) value;
                    		// textValue = new HSSFRichTextString(
                    		// String.valueOf(dValue));
                    		// cell.setCellValue(textValue);
                    		// } else if (value instanceof Long) {
                    		// long longValue = (Long) value;
                    		// cell.setCellValue(longValue);
                    		// }
                    		if(value!=null&&!"".equals(value)){
                    			if (value instanceof Date) {
                    				Date date = (Date) value;
                    				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    				textValue = sdf.format(date);
                    			} else if (value instanceof byte[]) {
                    				// 有图片时，设置行高为60px;
                    				row.setHeightInPoints(60);
                    				// 设置图片所在列宽度为80px,注意这里单位的一个换算
                    				sheet.setColumnWidth(i, (short) (35.7 * 80));
                    				// sheet.autoSizeColumn(i);
                    				byte[] bsValue = (byte[]) value;
                    				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                    						1023, 255, (short) 6, index, (short) 6, index);
                    				anchor.setAnchorType(2);
                    				patriarch.createPicture(anchor, workbook.addPicture(
                    						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    			} else {
                    				// 其它数据类型都当作字符串简单处理
                    				textValue = value.toString();
                    			}
                    			// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    			if (textValue != null) {
                    				Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    				Matcher matcher = p.matcher(textValue);
                    				if (matcher.matches()) {
                    					// 是数字当作double处理
                    					cell.setCellValue(Double.parseDouble(textValue));
                    				} else {
                    					HSSFRichTextString richString = new HSSFRichTextString(
                    							textValue);
                    					HSSFFont font3 = workbook.createFont();
                    					font3.setColor(HSSFColor.BLUE.index);
                    					richString.applyFont(font3);
                    					cell.setCellValue(richString);
                    				}
                    			}
                    		}
                    	} catch (Exception e) {
                    		// TODO Auto-generated catch block
                    		e.printStackTrace();
                    	}
                    }
        		}
        	}
        }
	    
        try {
        	workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    public void exportTrainExcel(Map<String, String> filedNameMapping,
    		List<TrainApplyInfo> dataList,InputStream input, OutputStream out, String pattern,String fileName) throws IOException {
    	//第一步：处理模版文件
    	//获取模版excel
    	HSSFWorkbook teplateWork = new HSSFWorkbook(input);
        //获取一个表格
        HSSFSheet tsheet = teplateWork.getSheetAt(0);
        //获取实际行数
        int rowsNum = tsheet.getPhysicalNumberOfRows();
        //获取模版第一行
        HSSFRow trow = tsheet.getRow(0);
        //获取实际列数
        int colsNum = trow.getPhysicalNumberOfCells();
        
     //   第二步：生成一个新的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);
        
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");
        
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i=0; i<colsNum; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(trow.getCell(i).getStringCellValue());
            cell.setCellValue(text);
        }

        int index = 0;
        if(dataList!=null && dataList.size()>0){
        	for(int i=rowsNum; i<dataList.size()+rowsNum; i++){
        		index++;
        		row = sheet.createRow(index);
        		TrainApplyInfo trainApplyInfo = dataList.get(i-rowsNum);
        		for(int j=0; j<colsNum; j++){
        			String titleName = trow.getCell(j).getStringCellValue();
        			String fieldName = filedNameMapping.get(titleName);
        			HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style2);
                    if(fieldName!=null&&!"".equals(fieldName)){
                    	String getMethodName = "get"
                    			+ fieldName.substring(0, 1).toUpperCase()
                    			+ fieldName.substring(1);
                    	try {
                    		Class tCls = trainApplyInfo.getClass();
                    		Method getMethod = tCls.getMethod(getMethodName,
                    				new Class[] {});
                    		Object value = getMethod.invoke(trainApplyInfo, new Object[] {});
                    		// 判断值的类型后进行强制类型转换
                    		String textValue = null;
                    		if(value!=null&&!"".equals(value)){
                    			if (value instanceof Date) {
                    				Date date = (Date) value;
                    				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    				textValue = sdf.format(date);
                    			} else if (value instanceof byte[]) {
                    				// 有图片时，设置行高为60px;
                    				row.setHeightInPoints(60);
                    				// 设置图片所在列宽度为80px,注意这里单位的一个换算
                    				sheet.setColumnWidth(i, (short) (35.7 * 80));
                    				// sheet.autoSizeColumn(i);
                    				byte[] bsValue = (byte[]) value;
                    				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                    						1023, 255, (short) 6, index, (short) 6, index);
                    				anchor.setAnchorType(2);
                    				patriarch.createPicture(anchor, workbook.addPicture(
                    						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    			} else {
                    				// 其它数据类型都当作字符串简单处理
                    				textValue = value.toString();
                    			}
                    			// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    			if (textValue != null) {
                    				Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    				Matcher matcher = p.matcher(textValue);
                    				if (matcher.matches()) {
                    					// 是数字当作double处理
                    					cell.setCellValue(Double.parseDouble(textValue));
                    				} else {
                    					HSSFRichTextString richString = new HSSFRichTextString(
                    							textValue);
                    					HSSFFont font3 = workbook.createFont();
                    					font3.setColor(HSSFColor.BLUE.index);
                    					richString.applyFont(font3);
                    					cell.setCellValue(richString);
                    				}
                    			}
                    		}
                    	} catch (Exception e) {
                    		// TODO Auto-generated catch block
                    		e.printStackTrace();
                    	}
                    }
        		}
        	}
        }
	    
        try {
        	workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    
    /**
     * 生成浙江省建设行业技能人员合格人员名册
     * @param filedNameMapping
     * @param dataList
     * @param input
     * @param out
     * @param pattern
     * @param fileName
     * @throws IOException
     *//*
    public void exportPassExcel(Map<String, String> filedNameMapping,
    		List<CertiPassInfoVo> dataList,InputStream input, OutputStream out, String pattern,String fileName) throws IOException {
    	第一步：处理模版文件
    	//获取模版excel
    	HSSFWorkbook teplateWork = new HSSFWorkbook(input);
        //获取一个表格
        HSSFSheet tsheet = teplateWork.getSheetAt(0);
        //获取模版标题
        HSSFRow trowhead = tsheet.getRow(0);
        //获取模版第一行
        HSSFRow trow = tsheet.getRow(1);
        
        第二步：生成一个新的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        
        if(dataList != null && dataList.size()>0){
        	int size = dataList.size();
        	int slength = 1000;
        	int count = 0;
        	if(size < slength){
        		count = 1;
        	}else{
        		count = size/slength;
        	}
        	for(int i =0;i<count+1;i++){
        		List<CertiPassInfoVo> sublist = null;
        		if(count == 1){
        			addSheet(workbook,trowhead, trow, dataList, filedNameMapping,"第"+i+1+"页");
        			break;
        		}else{
        			if((i+1)*slength <=size){
        				sublist = dataList.subList(i*slength, (i+1)*slength);
        				addSheet(workbook,trowhead, trow, sublist, filedNameMapping,"第"+i+1+"页");
        			}else if(i*slength < size){
        				sublist = dataList.subList(i*slength, size);
        				addSheet(workbook,trowhead, trow, sublist, filedNameMapping,"第"+i+1+"页");
        			}
        		}
        	}
        }
        try {
        	workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    } */
    
    /**
     * 新增sheet
     * @param workbook
     * @param trowhead
     * @param trow
     * @param subList
     * @param filedNameMapping
     * @param sheetname
     */
	/* 
    public void addSheet(HSSFWorkbook workbook,HSSFRow trowhead,HSSFRow trow,List<CertiPassInfoVo> subList,Map<String, String> filedNameMapping,String sheetname){
    	  HSSFSheet sheet = workbook.createSheet(sheetname);
    	  //获取实际列数
          int colsNum = trow.getPhysicalNumberOfCells();
          // 设置表格默认列宽度为15个字节
          sheet.setDefaultColumnWidth(15);
          // 生成一个样式
          HSSFCellStyle style = workbook.createCellStyle();
          // 设置这些样式
          style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
          style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          // 生成一个字体
          HSSFFont font = workbook.createFont();
          font.setColor(HSSFColor.VIOLET.index);
          font.setFontHeightInPoints((short) 12);
          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
          // 把字体应用到当前的样式
          style.setFont(font);
          // 生成并设置另一个样式
          HSSFCellStyle style2 = workbook.createCellStyle();
          style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
          style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
          style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
          style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
          style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
          style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
          style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
          // 生成另一个字体
          HSSFFont font2 = workbook.createFont();
          font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
          // 把字体应用到当前的样式
          style2.setFont(font2);
          // 生成并设置另一个样式
          HSSFCellStyle style3 = workbook.createCellStyle();
          style3.setFillForegroundColor(HSSFColor.WHITE.index);
          style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
          style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
          style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
          style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
          style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
          style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
          style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
          // 生成另一个字体
          HSSFFont font3 = workbook.createFont();
          font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
          font3.setFontHeightInPoints((short)18);
          // 把字体应用到当前的样式
          style3.setFont(font3);
          // 声明一个画图的顶级管理器
          HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
          
          // 产生表格标题行
          HSSFRow rowHead = sheet.createRow(0);
          for (int i=0; i<colsNum; i++) {
              HSSFCell cell = rowHead.createCell(i);
          }
          sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colsNum-1));
          HSSFRichTextString texthead = new HSSFRichTextString(trowhead.getCell(0).getStringCellValue());
          rowHead.getCell(0).setCellStyle(style3);
          rowHead.getCell(0).setCellValue(texthead);
          HSSFRow row = sheet.createRow(1);
          for (int i=0; i<colsNum; i++) {
              HSSFCell cell = row.createCell(i);
              cell.setCellStyle(style);
              HSSFRichTextString text = new HSSFRichTextString(trow.getCell(i).getStringCellValue());
              cell.setCellValue(text);
          }

          int index = 1;
          if(subList!=null && subList.size()>0){
          	for(int i=0; i<subList.size(); i++){
          		index++;
          		row = sheet.createRow(index);
          		CertiPassInfoVo certiInfo = subList.get(i);
          		for(int j=0; j<colsNum; j++){
          			String titleName = trow.getCell(j).getStringCellValue();
          			String fieldName = filedNameMapping.get(titleName);
          			HSSFCell cell = row.createCell(j);
                      cell.setCellStyle(style2);
                      if(fieldName!=null&&!"".equals(fieldName)){
                      	String getMethodName = "get"
                      			+ fieldName.substring(0, 1).toUpperCase()
                      			+ fieldName.substring(1);
                      	try {
                      		Class tCls = certiInfo.getClass();
                      		Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
                      		Object value = getMethod.invoke(certiInfo, new Object[] {});
                      		// 判断值的类型后进行强制类型转换
                      		String textValue = null;
                      		if(value!=null&&!"".equals(value)){
                      			if (value instanceof Date) {
                      				Date date = (Date) value;
                      				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                      				textValue = sdf.format(date);
                      			} else if (value instanceof byte[]) {
                      				// 有图片时，设置行高为60px;
                      				row.setHeightInPoints(60);
                      				// 设置图片所在列宽度为80px,注意这里单位的一个换算
                      				sheet.setColumnWidth(i, (short) (35.7 * 80));
                      				byte[] bsValue = (byte[]) value;
                      				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                      						1023, 255, (short) 6, index, (short) 6, index);
                      				anchor.setAnchorType(2);
                      				patriarch.createPicture(anchor, workbook.addPicture(
                      						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                      			} else {
                      				// 其它数据类型都当作字符串简单处理
                      				textValue = value.toString();
                      			}
                      			// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                      			if (textValue != null) {
                      				Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                      				Matcher matcher = p.matcher(textValue);
                      				if (matcher.matches()) {
                      					// 是数字当作double处理
                      					cell.setCellValue(Double.parseDouble(textValue));
                      				} else {
                      					HSSFRichTextString richString = new HSSFRichTextString(
                      							textValue);
                      					HSSFFont font4 = workbook.createFont();
                      					font4.setColor(HSSFColor.BLUE.index);
                      					richString.applyFont(font4);
                      					cell.setCellValue(richString);
                      				}
                      			}
                      		}
                      	} catch (Exception e) {
                      		// TODO Auto-generated catch block
                      		e.printStackTrace();
                      	}
                      }
          		}
          	}
          }
    }
    public void exportExamPlanDetailExcel(Map<String, String> filedNameMapping,
    		List<ExamPlanDetailCount> dataList,InputStream input, OutputStream out, String pattern,String fileName) throws IOException {
    //	第一步：处理模版文件
    	//获取模版excel
    	HSSFWorkbook teplateWork = new HSSFWorkbook(input);
        //获取一个表格
        HSSFSheet tsheet = teplateWork.getSheetAt(0);
        //获取实际行数
        int rowsNum = tsheet.getPhysicalNumberOfRows();
        //获取模版第一行
        HSSFRow trow = tsheet.getRow(1);
        //获取实际列数
        int colsNum = trow.getPhysicalNumberOfCells();
        
   //     第二步：生成一个新的excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setWrapText(true);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("leno");
        // 产生表格标题行
        HSSFRow row = sheet.createRow(1);
        for (short i=0; i<colsNum; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(trow.getCell(i).getStringCellValue());
            cell.setCellValue(text);
        }
        sheet.setColumnWidth(5, 15000);
        int index = 1;
        if(dataList!=null && dataList.size()>0){
        	for(int i=rowsNum; i<dataList.size()+rowsNum; i++){
        		index++;
        		row = sheet.createRow(index);
        		ExamPlanDetailCount examPlanDetailCount = dataList.get(i-rowsNum);
        		if (index == 2) {
        			sheet.createRow(0).createCell(0).setCellValue("考核机构："+examPlanDetailCount.getOrgname());
        			//sheet.createRow(0).createCell(1).setCellValue(examPlanDetailCount.getOrgname());
				}
        		for(int j=0; j<colsNum; j++){
        			String titleName = trow.getCell(j).getStringCellValue();
        			String fieldName = filedNameMapping.get(titleName);
        			HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(style2);
                    if(fieldName!=null&&!"".equals(fieldName)){
                    	String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
                    	try {
                    		Class tCls = examPlanDetailCount.getClass();
                    		int firsttime = examPlanDetailCount.getFirsttime();
							int basic =examPlanDetailCount.getBasic();
							int safe =examPlanDetailCount.getSafe();
							int handle =examPlanDetailCount.getHandle();
                    		if (fieldName.equals("excelNum")) {
                    			int value = i-rowsNum+1;
                    			cell.setCellValue(value);
							}else if(fieldName.equals("examplace")){
								String value = examPlanDetailCount.getPname()+"（"+examPlanDetailCount.getAddr()+"）";
								cell.setCellValue(value);
							}else if(fieldName.equals("examsubject")){
								String value ="";
								if (firsttime >0 ) {
									value ="理论、安全、操作技能";
								}else{
									if (basic > 0) {
										if ("".equals(value)) {
											value = "理论";
										}else{
											value += "、理论";
										}
									}
									if (safe > 0) {
										if ("".equals(value)) {
											value = "安全";
										}else{
											value += "、安全";
										}
									}
									if (handle > 0) {
										if ("".equals(value)) {
											value = "操作技能";
										}else{
											value += "、操作技能";
										}
									}
								}
								cell.setCellValue(value);
							}else if(fieldName.equals("examtime")){
								String value="";
								String basicstr="理论:"+getdatetoStrin(examPlanDetailCount.getExamdate())+"  "+getdatetoStrin(examPlanDetailCount.getBstime())+"~"+getdatetoStrin(examPlanDetailCount.getBetime());
								String safestr="安全:"+getdatetoStrin(examPlanDetailCount.getExamdate())+"  "+getdatetoStrin(examPlanDetailCount.getSstime())+"~"+getdatetoStrin(examPlanDetailCount.getSetime());
								String handstr ="操作技能:"+getdatetoStrin(examPlanDetailCount.getExamdate())+"  "+getdatetoStrin(examPlanDetailCount.getRstime())+"~"+getdatetoStrin(examPlanDetailCount.getRetime());
								if (firsttime >0 ) {
									value =basicstr+"\r\n";
									value +=safestr+"\r\n";
									value +=handstr;
								}else{
									if (basic > 0) {
										if ("".equals(value)) {
											value =basicstr;
										}else{
											value += "\r\n"+basicstr;
										}
									}
									if (safe > 0) {
										if ("".equals(value)) {
											value = safestr;
										}else{
											value += "\r\n"+safestr;
										}
									}
									if (handle > 0) {
										if ("".equals(value)) {
											value = handstr;
										}else{
											value += "\r\n"+ handstr;
										}
									}
								}
								cell.setCellValue(value);
							}else{
								Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
								Object value = getMethod.invoke(examPlanDetailCount, new Object[] {});
	                    		// 判断值的类型后进行强制类型转换
	                    		String textValue = null;
	                    		if(value!=null&&!"".equals(value)){
	                    			if (value instanceof Date) {
	                    				Date date = (Date) value;
	                    				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    				textValue = sdf.format(date);
	                    			}else {
	                    				// 其它数据类型都当作字符串简单处理
	                    				textValue = value.toString();
	                    			}
	                    			// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
	                    			if (textValue != null) {
	                    				Pattern p = Pattern.compile("^//d+(//.//d+)?$");
	                    				Matcher matcher = p.matcher(textValue);
	                    				if (matcher.matches()) {
	                    					// 是数字当作double处理
	                    					cell.setCellValue(Double.parseDouble(textValue));
	                    				} else {
	                    					HSSFRichTextString richString = new HSSFRichTextString(
	                    							textValue);
	                    					HSSFFont font3 = workbook.createFont();
	                    					font3.setColor(HSSFColor.BLUE.index);
	                    					richString.applyFont(font3);
	                    					cell.setCellValue(richString);
	                    				}
	                    			}
	                    		}
							}
                    	} catch (Exception e) {
                    		// TODO Auto-generated catch block
                    		e.printStackTrace();
                    	}
                    }
        		}
        	}
        }
	    
        try {
        	workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static String getdatetoStrin(String examdate){
		if (examdate.length() == 8) {
			examdate = examdate.substring(0,4)+"-"+examdate.substring(4,6)+"-"+examdate.substring(6,8);
		}else if(examdate.length() == 4){
			examdate = examdate.substring(0,2)+":"+examdate.substring(2,4);
		}
		return examdate;
	}
    
    public String toUtf8String(String s){ 
		StringBuffer sb = new StringBuffer(); 
		for (int i=0;i<s.length();i++){ 
		    char c = s.charAt(i); 
		    if (c >= 0 && c <= 255){
		    	sb.append(c);
		    }else{ 
		    	byte[] b; 
		        try{ 
		        	b = Character.toString(c).getBytes("utf-8");
		        } 
		        catch (Exception ex) { 
		            b = new byte[0]; 
		        } 
		        for (int j = 0; j < b.length; j++) { 
		            int k = b[j]; 
		            if (k < 0) k += 256; 
		            sb.append("%" + Integer.toHexString(k).toUpperCase()); 
		        } 
		    } 
		} 
		return sb.toString(); 
	} */
}