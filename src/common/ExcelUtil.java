package common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	// 新建excel 2007 以前版本的文件
	public static Workbook CreateHSSFWorkBook(){
		Workbook wb = new HSSFWorkbook();
		return wb;
	}
	// 新建excel 2007 以后版本的文件
	public static Workbook CreateXSSFWorkBook(){
		Workbook wb = new XSSFWorkbook();
		return wb;
	}
	
	// 生成Excel
	public static void createExcelFile(String filePath,Workbook wb) throws IOException{
        //String file = "d://businessplan.xls";
        if(wb instanceof XSSFWorkbook) filePath += "x";
        FileOutputStream out = new FileOutputStream(filePath);
        wb.write(out);
        out.close();
	}
	
	// 新建SHEET
	public static Sheet CreateSheet(Workbook wb,String sheetName){
		Sheet sheet = wb.createSheet(sheetName);
		return sheet;
	}
	// 设定Sheet属性
	public static void SetSheetPropertyHSSF(Sheet sheet){
        //turn off gridlines
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        
        //the following three statements are required only for HSSF
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);
	}
	// 设定Sheet属性
	public static void SetSheetPropertyXSSF(Sheet sheet){
        //turn off gridlines
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
	}
	
	
	// 设定单元格的样式
    public static Map<String, CellStyle> CreateStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        DataFormat df = wb.createDataFormat();

        CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("header_date", style);

        Font font1 = wb.createFont();
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font1);
        styles.put("cell_b", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font1);
        styles.put("cell_b_centered", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_b_date", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_g", style);

        Font font2 = wb.createFont();
        font2.setColor(IndexedColors.BLUE.getIndex());
        font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font2);
        styles.put("cell_bb", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_bg", style);

        Font font3 = wb.createFont();
        font3.setFontHeightInPoints((short)14);
        font3.setColor(IndexedColors.DARK_BLUE.getIndex());
        font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font3);
        style.setWrapText(true);
        styles.put("cell_h", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setWrapText(true);
        styles.put("cell_normal", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        styles.put("cell_normal_centered", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_normal_date", style);

        style = CreateBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setIndention((short)1);
        style.setWrapText(true);
        styles.put("cell_indented", style);

        style = CreateBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("cell_blue", style);

        return styles;
    }
    // 设定单元格边界样式
    public static CellStyle CreateBorderedStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
    // 设定单元格的宽度
    public static void SetCellsWidth(Sheet sheet,int[] cellsWidth){
    	for(int i = 0; i<cellsWidth.length; i++){
    		sheet.setColumnWidth(i, 256*cellsWidth[i]);
    	}
    }
    // 对单元格赋值
    public static void SetCellsValue(int count, Sheet sheet, Map<String, CellStyle> styles, String[] datas){
    	Row row = sheet.createRow(count);
    	Cell cell;
    	String styleName;
    	for(int i = 0; i<datas.length; i++){
            cell = row.createCell(i);
            styleName = "cell_normal";
            cell.setCellValue(datas[i]);
            cell.setCellStyle(styles.get(styleName));
    	}
    }
    // 新建标题行
    public static void CreateHeadRow(Sheet sheet, String[] titles,float rowHeight,Map<String, CellStyle> styles){
    	// 新建第一行
    	Row headerRow = sheet.createRow(0);
    	// 设定行高
    	headerRow.setHeightInPoints(rowHeight);
    	
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(styles.get("header"));
        }
    }
}
