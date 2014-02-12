package action;


import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ItService extends ActionSupport {
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	public static String pdfFile = "";
	public static String toTextFile = "";
	public static String textFile2 = "";
	
	public String itServiceMngInit(){
		try{
			System.out.println("in itServiceMngInit()");
			
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	public void parsePdf(String pdf, String txt) throws IOException {
		try{
			//PrintWriter out = new PrintWriter(new FileOutputStream(txt));
			PdfReader reader= new PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			TextExtractionStrategy strategy;
			
			Rectangle rect = new Rectangle(0, 0, 20, 20);
			RenderFilter filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment------");
			rect = new Rectangle(20, 0, 40, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment2------");
			rect = new Rectangle(40, 0, 60, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment3------");
			rect = new Rectangle(60, 0, 150, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment4------");
			rect = new Rectangle(100, 0, 200, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-----segment5------");
			rect = new Rectangle(200, 0, 300, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			
			System.out.println("-------whole line content-----------");
			rect = new Rectangle(0, 0, 700, 20);
			filter = new RegionTextRenderFilter(rect);
			strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
			System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
			/*for(int x = 20; x < 480; x+=20){
				for(int y = 20; y < 500; y+=20){
					rect.setBottom();
					rect.setRight(x);
					System.out.println("---------Y: " + i);
			}*/
			//for(int i = 1; i <= reader.getNumberOfPages(); i++){
				//strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
				//strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
				//out.println(strategy.getResultantText());
				//System.out.println(strategy.getResultantText());
				//System.out.println("-----------first page-----------");
				//System.out.println(PdfTextExtractor.getTextFromPage(reader, 1, strategy));
				//System.out.println("-----------second page-----------");
				//System.out.println(PdfTextExtractor.getTextFromPage(reader, 2, strategy));
			//}
			//out.flush();
			//out.close();
			/*byte bWrite [] = {11,21,3,4,5,100};
			OutputStream os = new FileOutputStream(txt);
			for(int x = 0; x < bWrite.length; x++){
				os.write(bWrite[x]);
			}
			os.close();
			*/
			
			reader.close();
			
		}catch(Exception ex){
			log.info(ex.getMessage());
		}
	}
	
	public String convertPdfToText(){
		try{
			System.out.println("in convertPdfToText()");
			/*
			String _path = ServletActionContext.getServletContext().getRealPath("/") + "uploadfile\\02.txt";
			InputStream is = new FileInputStream(_path);
			int size = is.available();
			for(int i = 0; i < size; i++){
				System.out.println((char)is.read() + " ");
			}
			is.close();
			*/
			String _contextPath = ServletActionContext.getServletContext().getRealPath("/");
			pdfFile = _contextPath + "uploadfile\\01.pdf";
			toTextFile = _contextPath + "uploadfile\\01.txt";
			parsePdf(pdfFile, toTextFile);
			
			return SUCCESS;
		}catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	
	public String dailyCheckInit(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		try{
			
			
			return SUCCESS;
		}
		catch(Exception ex){
			log.info(ex.getMessage());
			return "error";
		}
	}
	
	

	public static Log getLog() {
		return log;
	}
}
