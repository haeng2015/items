/**
 * 
 */
package com.panasign.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
/**
 * Excel 操作类，兼容2003跟2007
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-9
 */
public class ExcelUtils {
	private static Logger logger = Logger.getLogger(ExcelUtils.class);
	public static final String CHART_PATH = "E:/Excel/";
	
	public static final String PHONECOUNT = "lineAndShap.jpg";
	
	private static FileOutputStream fileOut = null;
	
	private static BufferedImage bufferImg = null;
	
	public static void main(String[] args) throws Exception {
		List<String> printList = parseExcel(new File("F:\\普通用户信息Excel模板.xls"), 1);
		for (String str : printList) {
			logger.info(str);
		}
	}
	
	/**
	 * 已经过时，请用readExcel()方法
	 * @param excel
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	private static HSSFWorkbook openWorkbookInstance(File excel) throws IOException {
		FileInputStream in = null;
		POIFSFileSystem fs = null;
		try {
			in = new FileInputStream(excel);
			fs = new POIFSFileSystem(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return new HSSFWorkbook(fs);
	}

	/**
	 * 已经过时，请用readExcel()方法
	 * @param excelFile
	 * @param startIndex
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public static List<String> parseExcel(File excelFile, int startIndex) throws Exception {

		List<String> excelList = new ArrayList<String>();

		HSSFWorkbook workbook = openWorkbookInstance(excelFile);

		HSSFSheet sheet = null; // HSSFSheet工作簿对象

		for (int i = 0, sheetNum = workbook.getNumberOfSheets(); i < sheetNum; i++) {
			sheet = workbook.getSheetAt(i);
			// HSSFSheet sheet = openWBInstance(filePath).getSheetAt(0);//
			// HSSFSheet工作簿对象// HSSFSheet

			Iterator<Row> iterator = sheet.rowIterator();// 行迭代器，迭代对象是HSSFRow行对象

			// logger.info(sheet.getLeftCol());
			/**
			 * sheet.getLastRowNum() 最后一行的index,index从getFirstRowNum():0开始
			 * sheet.getPhysicalNumberOfRows() 实际编辑过的 行数，即使后来被删，但任然算构造了 的行
			 */
			HSSFRow row = null;// 工作簿中HSSFRow行对象

			HSSFCell cell = null;// 工作簿中每行中的HSSFCell单元格对象

			int currentRow = 0;// sheet中行的Index

			int sumCell = -1;

			while (iterator.hasNext()) {
				String rowRecord = "";// 封装每行记录的拼接字符串

				row = (HSSFRow) iterator.next();

				currentRow = row.getRowNum();// 获取行Index，以0开始

				if (startIndex != -1 && currentRow == 0) {// 一般工作簿第一行是列字段名称，通常不读取
					sumCell = row.getLastCellNum();// 获取当前行表格的列数
					continue;
				}
				sumCell = (sumCell == -1 ? row.getLastCellNum() : sumCell);

				// 一般工作簿第一行是列字段名称，通常不读取
				for (int k = 0; k < sumCell; k++) {
					String cellData = "";// 封装单元格数据
					cell = row.getCell(k);// 获取单元格对象
					if (cell != null) {
						//int currentCell = cell.getColumnIndex();//一行中单元格的Index位置,获取列Index，以0开始
						/*
						 * 取值前先判断单元格内数据类型，再根据不同方法取值
						 */
						switch (cell.getCellType()) {
    						case HSSFCell.CELL_TYPE_STRING:
    							cellData = cell.getStringCellValue().trim();
    							break;
    						case HSSFCell.CELL_TYPE_NUMERIC:
    							//cellData = DateUtils.getFormatDate(cell.getDateCellValue(), "yyyyMMdd");
    							cellData = String.valueOf((int)cell.getNumericCellValue());
    							break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                cellData = "";
                                break;
    						default:
    							break;
						}
					    //String cellData = cell.toString().trim();
						rowRecord += cellData + ",";
					} else {
						rowRecord += ",";
					}
				}
				excelList.add(rowRecord + "%");// 将Excel中的每行记录用逗号拼接成字符串并以%结尾封装到List中
			}
		}

		return excelList;
	}

	/**
	 * 生成折线图，只针对2003版本，不支持2007
	 * @param data
	 * @param rowKeys
	 * @param columnKeys
	 */
	public static String makeLineAndShapeChart(String chartTitle,
			double[][] data, String[] rowKeys, String[] columnKeys) {
		CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
		String picName = createTimeXYChar(chartTitle, "", "", dataset,
				PHONECOUNT);
		return picName;
	}

	/**
	 * 柱状图，折线图 数据集，只针对2003版本，不支持2007
	 * @param data
	 * @param rowKeys
	 * @param columnKeys
	 * @return
	 */
	public static CategoryDataset getBarData(double[][] data, String[] rowKeys,
			String[] columnKeys) {
		return DatasetUtilities
				.createCategoryDataset(rowKeys, columnKeys, data);

	}

	/**
	 * 生成折线图,返回图片名字，只针对2003版本，不支持2007
	 * @param chartTitle
	 * @param x
	 * @param y
	 * @param xyDataset
	 * @param charName
	 * @return
	 */
	public static String createTimeXYChar(String chartTitle, String x,
			String y, CategoryDataset xyDataset, String charName) {

		JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y,
				xyDataset, PlotOrientation.VERTICAL, true, true, false);

		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.WHITE);
		// 设置图标题的字体重新设置title
		Font font = new Font("隶书", Font.BOLD, 25);
		TextTitle title = new TextTitle(chartTitle);
		title.setFont(font);
		chart.setTitle(title);
		LegendTitle legend = chart.getLegend();
		if (legend != null) {
			legend.setItemFont(new Font("宋体", Font.BOLD, 20));
		}
		// 设置面板字体
		Font labelFont = new Font("宋体", Font.TRUETYPE_FONT, 12);
		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		chart.setBackgroundPaint(Color.WHITE);

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		// x轴 // 分类轴网格是否可见
		categoryplot.setDomainGridlinesVisible(true);
		// y轴 //数据轴网格是否可见
		categoryplot.setRangeGridlinesVisible(true);

		categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩

		categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩

		categoryplot.setBackgroundPaint(Color.lightGray);

		// 设置轴和面板之间的距离
		// categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));

		CategoryAxis domainAxis = categoryplot.getDomainAxis();

		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值

		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的
		// Lable
		// 45度倾斜
		// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.0);
		// 设置距离图片右端距离
		domainAxis.setUpperMargin(0.0);

		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);

		// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();

		lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
		lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见

		// 显示折点数据
		lineandshaperenderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineandshaperenderer.setBaseItemLabelsVisible(true);

		FileOutputStream fos_jpg = null;
		String chartName = null;
		try {
			isFilePathExist(CHART_PATH);
			chartName = CHART_PATH + charName;
			fos_jpg = new FileOutputStream(chartName);
			// 将报表保存为png文件
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 500, 510);
			// return chartName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
				logger.info("create time-createTimeXYChar.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 下载折线图
		// OutputStream os = new FileOutputStream("CHART_PATH+PHONECOUNT");
		OutputStream os;
		try {
			os = new FileOutputStream(chartName);
			// 由ChartUtilities生成文件到一个体outputStream中去
			try {
				ChartUtilities.writeChartAsJPEG(os, chart, 1000, 800);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return chartName;
	}
	
	/**
	 * 我只是个加注释的，我也不知道这个方法什么用，只知道它只针对2003版本，不支持2007，请自行研究
	 * @param sheetName
	 * @param chartName
	 * @param fromFile
	 * @param i
	 * @param toPath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @author: 未知
	 */
	public static Boolean toExcel(String sheetName, String chartName,
			File fromFile, Integer[] i, String toPath)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fromFile));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(sheetName);
		// 处理图片文件，以便产生ByteArray
		ByteArrayOutputStream handlePicture = new ByteArrayOutputStream();
		handlePicture = handlePicture(chartName);
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 100, 50,
				(short) 3, 3, (short) 15, 29);
		patriarch.createPicture(anchor, wb.addPicture(
				handlePicture.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
		for (int j = 0; j < i.length; j++) {
			HSSFRow row = sheet.getRow(j + 1);
			row.getCell(1).setCellValue(i[j]);
		}
		HSSFRow row = sheet.getRow(32);
		row.getCell(1).setCellFormula("SUM(B2:B32)");
		try {
			fileOut = new FileOutputStream(toPath);
			isFilePathExist(toPath);
			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 判断文件是否存在，不存在则创建
	 * @param fileAbsoluteName 文件绝对路径名
	 * @throws IOException
	 * @author: Wu.Liang
	 */
	public static void isFilePathExist(String fileAbsoluteName) throws IOException {
		File file = new File(fileAbsoluteName);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	/**
	 * 我只是个加注释的，我也不知道这个方法用来干嘛的，不过看样子像是个读图片的方法，返回字节数组输出流，请自行研究
	 * @param pathOfPicture
	 * @return
	 * @author: 未知
	 * @throws IOException 
	 */
	private static ByteArrayOutputStream handlePicture(String pathOfPicture) throws IOException {
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		bufferImg = ImageIO.read(new File(pathOfPicture));
		ImageIO.write(bufferImg, "jpeg", byteArrayOut);
		return byteArrayOut;
	}

	 /**
	  * 读取2003格式的Excel文件，封装成二维数组格式
	  * @param file
	  * @return
	  * @throws IOException
	  * @author: Wu.Liang
	  */
    private static List<List<Object>> read2003Excel(File file)  
            throws IOException {  
        List<List<Object>> list = new LinkedList<List<Object>>();  
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));  
        HSSFSheet sheet = hwb.getSheetAt(0);  
        Object value = null;  
        HSSFRow row = null;  
        HSSFCell cell = null;  
        logger.info("读取office 2003 excel内容如下：");  
        for (int i = sheet.getFirstRowNum(); i <= sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            }  
            List<Object> linked = new LinkedList<Object>();  
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }  
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
                // 字符  
                SimpleDateFormat sdf = new SimpleDateFormat(  
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
                switch (cell.getCellType()) {  
                case XSSFCell.CELL_TYPE_STRING:  
                    // logger.info(i + "行" + j + " 列 is String type");  
                    value = cell.getStringCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_NUMERIC:  
                    // logger.info(i + "行" + j  
                    // + " 列 is Number type ; DateFormt:"  
                    // + cell.getCellStyle().getDataFormatString());  
                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
                        value = df.format(cell.getNumericCellValue());  
  
                    } else if ("General".equals(cell.getCellStyle()  
                            .getDataFormatString())) {  
                        value = nf.format(cell.getNumericCellValue());  
                    } else {  
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
                                .getNumericCellValue()));  
                    }  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_BOOLEAN:  
                    // logger.info(i + "行" + j + " 列 is Boolean type");  
                    value = cell.getBooleanCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_BLANK:  
                    // logger.info(i + "行" + j + " 列 is Blank type");  
                    value = "";  
                    System.out.print("  " + value + "  ");  
                    break;  
                default:  
                    // logger.info(i + "行" + j + " 列 is default type");  
                    value = cell.toString();  
                    System.out.print("  " + value + "  ");  
                }  
                if (value == null || "".equals(value)) {  
                    continue;  
                }  
                linked.add(value);  
  
            }  
            logger.info("");  
            list.add(linked);  
        }  
        return list;  
    } 
	
    /**
     * 读取2007格式的excel文件，封装成二维数组格式
     * @param file
     * @return
     * @throws IOException
     * @author: Wu.Liang
     */
    private static List<List<Object>> read2007Excel(File file)  
            throws IOException {  
  
        List<List<Object>> list = new LinkedList<List<Object>>();  
        // String path = System.getProperty("user.dir") +  
        // System.getProperty("file.separator")+"dd.xlsx";  
        // logger.info("路径："+path);  
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径  
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));  
  
        // 读取第一章表格内容  
        XSSFSheet sheet = xwb.getSheetAt(0);  
        Object value = null;  
        XSSFRow row = null;  
        XSSFCell cell = null;  
        logger.info("读取office 2007 excel内容如下：");  
        //循环每一行
        for (int i = sheet.getFirstRowNum(); i <= sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            }  
            List<Object> linked = new LinkedList<Object>();  
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }  
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
                // 字符  
                SimpleDateFormat sdf = new SimpleDateFormat(  
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
  
                switch (cell.getCellType()) {  
                case XSSFCell.CELL_TYPE_STRING:  
                    // logger.info(i + "行" + j + " 列 is String type");  
                    value = cell.getStringCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_NUMERIC:  
                    // logger.info(i + "行" + j  
                    // + " 列 is Number type ; DateFormt:"  
                    // + cell.getCellStyle().getDataFormatString());  
                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
                        value = df.format(cell.getNumericCellValue());  
  
                    } else if ("General".equals(cell.getCellStyle()  
                            .getDataFormatString())) {  
                        value = nf.format(cell.getNumericCellValue());  
                    } else {  
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
                                .getNumericCellValue()));  
                    }  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_BOOLEAN:  
                    // logger.info(i + "行" + j + " 列 is Boolean type");  
                    value = cell.getBooleanCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_BLANK:  
                    // logger.info(i + "行" + j + " 列 is Blank type");  
                    value = "";  
                    // logger.info(value);  
                    break;  
                default:  
                    // logger.info(i + "行" + j + " 列 is default type");  
                    value = cell.toString();  
                    System.out.print("  " + value + "  ");  
                }  
                if (value == null || "".equals(value)) {  
                    continue;  
                }  
                linked.add(value);  
            }  
            logger.info("");  
            list.add(linked);  
        }  
        return list;  
    }  
    
    /**
     * 传入Excel绝对路径文件名，自动判断是2003还是2007类型，解析后返回对象二位数组
     * @param fileAbsoluteName
     * @return List<List<Object>>
     * @throws IOException
     * @author: Wu.Liang
     */
    public static List<List<Object>> readExcel(String fileAbsoluteName) throws IOException{
    	List<List<Object>> list=new LinkedList<List<Object>>();
    	isFilePathExist(fileAbsoluteName);
    	if(fileAbsoluteName.substring(fileAbsoluteName.lastIndexOf(".")).equals(".xls")){
    		//2003处理
    		list = read2003Excel(new File(fileAbsoluteName));
    	}else{
    		//2007处理
    		list = read2007Excel(new File(fileAbsoluteName));
    	}
    	return list;
    }
}
