package common;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

//import org.jfree.chart.*;
//import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.renderer.category.BarRenderer;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.ui.*;

public class ChartUtil {

	/**
	 * * 图片保存的根目录(Tomcat的中webapps目录下项目的images文件夹)
	 * @param filename
	 * @return
	 */
	public String Savepath() {
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String testpath = path.substring(0, path.lastIndexOf("WEB-INF"));
		String filepath = testpath + "images/";
		String JFpath = filepath + "core/";
		return JFpath;
	}

	/**
	 * 柱状图,折线图 数据集 方法
	 */
	public CategoryDataset getBarData(String[] rowKeys, String[] columnKeys, double[][] data) {
		return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
	}
	
	/**
	 * 判断路径是否存在 方法
	 */
	public void isChartPathExist(String chartPath) {
		File file = new File(chartPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public JFreeChart createBarChart3D(String charTitle, String x, String y, CategoryDataset Dataset, String charName){
		return 	createBarChart3D(charTitle, x, y, Dataset, charName, "Statistics Figure");
	}
	/**
	 * @Title: 柱状图样式 createBarChart3D
	 * @param @param charTitle
	 * @param @param x
	 * @param @param y
	 * @param @param Dataset
	 * @param @param charName
	 * @param @return 设定文件
	 * @return JFreeChart 返回类型
	 * @throws
	 */
	public JFreeChart createBarChart3D(String charTitle, String x, String y, CategoryDataset Dataset, String charName, String chartTitle) {	
		//JFreeChart chart = ChartFactory.createLineChart(charTitle, x, y, Dataset, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart chart = ChartFactory.createBarChart3D(charTitle, x, y, Dataset, PlotOrientation.VERTICAL, true, true, false);
		//chart.setTitle(new TextTitle("Budget Statistics Figure", new Font("Arial", Font.ITALIC, 22)));
		// 设置图标题的字体重新设置title
		Font font = new Font("Arial", Font.BOLD, 20);
		TextTitle title = new TextTitle(chartTitle);
		title.getBackgroundPaint();
		// 设置字体颜色
		title.setPaint(Color.BLUE);
		title.setFont(font);
		chart.setTitle(title);
		
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();
		// 设置纵坐标的标题字体和大小
		numberaxis.setLabelFont(new Font("Arial", Font.CENTER_BASELINE, 13));
		// 设置丛坐标的坐标值的字体颜色
		numberaxis.setLabelPaint(Color.BLACK);
		// 设置丛坐标的坐标轴标尺颜色
		numberaxis.setTickLabelPaint(Color.RED);
		// 获取横坐标
		CategoryAxis domainAxis = categoryPlot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.39269908169872414D));
		// 设置横坐标的标题字体和大小
		domainAxis.setLabelFont(new Font("Arial", Font.PLAIN, 13));
		// 设置横坐标的坐标值的字体颜色
		domainAxis.setTickLabelPaint(Color.BLACK);
		// 设置横坐标的坐标值的字体
		domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 8));
		// 这句代码解决了底部汉字乱码的问题
		chart.getLegend().setItemFont(new Font("Arial", 0, 13));
		// 在柱体的上面显示数据
		BarRenderer3D custombarrenderer3d = new BarRenderer3D();
		// 数据字体颜色
		custombarrenderer3d.setBaseItemLabelPaint(Color.BLACK);
		custombarrenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		custombarrenderer3d.setBaseItemLabelsVisible(true);
		categoryPlot.setRenderer(custombarrenderer3d);
		
		CategoryItemRenderer categoryitemrenderer = categoryPlot.getRenderer();
		categoryitemrenderer.setBaseItemLabelsVisible(true);
		BarRenderer barrenderer = (BarRenderer)categoryitemrenderer;
		barrenderer.setItemMargin(0.20000000000000001D);
		
		// 图片路径
		FileOutputStream fos_jpg = null;
		try {
			isChartPathExist(Savepath());
			String chartName = Savepath() + charName;
			fos_jpg = new FileOutputStream(chartName);

			// 将报表保存为JPG文件
			ChartUtilities.writeChartAsJPEG(fos_jpg, chart, 1500, 600);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chart;
	}
	
	public JFreeChart createBarChart(String charTitle, String x, String y, CategoryDataset Dataset, String charName){
		String title = "Statistics Figure";
		return createBarChart(charTitle, x, y, Dataset, charName, title);
	}
	/**
	 * @Title: 柱状图样式 createBarChart
	 * @param @param charTitle
	 * @param @param x
	 * @param @param y
	 * @param @param Dataset
	 * @param @param charName
	 * @param @return 设定文件
	 * @return JFreeChart 返回类型
	 * @throws
	 */
	public JFreeChart createBarChart(String charTitle, String x, String y, CategoryDataset Dataset, String charName, String chartTitle) {
		//JFreeChart chart = ChartFactory.createLineChart(charTitle, x, y, Dataset, PlotOrientation.VERTICAL, true, true, false);
		JFreeChart chart = ChartFactory.createBarChart(charTitle, x, y, Dataset, PlotOrientation.VERTICAL, true, true, false);
		//chart.setTitle(new TextTitle(title, new Font("Arial", Font.ITALIC, 22)));
		// 设置图标题的字体重新设置title
		Font font = new Font("Arial", Font.BOLD, 20);
		TextTitle title = new TextTitle(chartTitle);
		title.getBackgroundPaint();
		// 设置字体颜色
		title.setPaint(Color.BLUE);
		title.setFont(font);
		chart.setTitle(title);
		
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeCrosshairVisible(true);
		categoryPlot.setRangeCrosshairPaint(Color.blue);
		NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// 设置纵坐标的标题字体和大小
		numberaxis.setLabelFont(new Font("Arial", Font.CENTER_BASELINE, 13));
		// 设置丛坐标的坐标值的字体颜色
		numberaxis.setLabelPaint(Color.BLACK);
		// 设置丛坐标的坐标轴标尺颜色
		numberaxis.setTickLabelPaint(Color.RED);		
		// 获取横坐标
		CategoryAxis domainAxis = categoryPlot.getDomainAxis();
		// 设置横坐标的标题字体和大小
		domainAxis.setLabelFont(new Font("Arial", Font.PLAIN, 13));
		// 设置横坐标的坐标值的字体颜色
		domainAxis.setTickLabelPaint(Color.BLACK);
		// 设置横坐标的坐标值的字体
		domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 8));
		// 这句代码解决了底部汉字乱码的问题
		chart.getLegend().setItemFont(new Font("Arial", 0, 13));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.52359877559829882D));
		// 在柱体的上面显示数据
		BarRenderer barrenderer = (BarRenderer)categoryPlot.getRenderer();
		barrenderer.setDrawBarOutline(false);
		// 数据字体颜色
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
		GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		barrenderer.setSeriesPaint(0, gradientpaint);
		barrenderer.setSeriesPaint(1, gradientpaint1);
		barrenderer.setSeriesPaint(2, gradientpaint2);
		barrenderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
		categoryPlot.setRenderer(barrenderer);
		// 图片路径
		FileOutputStream fos_jpg = null;
		try {
			isChartPathExist(Savepath());
			String chartName = Savepath() + charName;
			fos_jpg = new FileOutputStream(chartName);

			// 将报表保存为JPG文件
			ChartUtilities.writeChartAsJPEG(fos_jpg, chart, 1500, 600);

		} catch (Exception e) {
			e.printStackTrace();
			//return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chart;
	}

	/**
	 * 折线图样式
	 * @param chartTitle
	 * @param x
	 * @param y
	 * @param xyDataset
	 * @param charName
	 * @return
	 */
	public JFreeChart createTimeXYChar(String chartTitle, String x, String y, CategoryDataset xyDataset, String charName) {
		JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y, xyDataset, PlotOrientation.VERTICAL, true, true, false);
		
		// 设置消除字体的锯齿渲染(解决中文问题)
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.RED);
		// 设置图标题的字体重新设置title
		Font font = new Font("Arial", Font.BOLD, 20);
		TextTitle title = new TextTitle(chartTitle);
		title.getBackgroundPaint();
		// 设置字体颜色
		title.setPaint(Color.BLUE);
		title.setFont(font);
		chart.setTitle(title);
		
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis valueAxis = plot.getDomainAxis();
		// 设置x轴上面的字体
		valueAxis.setTickLabelFont(new Font("Arial", Font.ITALIC, 12));
		// 设置X轴的标题文字
		valueAxis.setLabelFont(new Font("Arial", Font.CENTER_BASELINE, 13));
		// 设置二级标题（如果需要可以添加二级标题）
		//TextTitle subtitle = new TextTitle("monthly contrast", new Font("Arial", Font.BOLD, 12));
		//chart.addSubtitle(subtitle);
		NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
		// 设置y轴上的字体
		numberAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 13));
		// 设置y轴上的标题字体
		numberAxis.setLabelFont(new Font("Arial", Font.CENTER_BASELINE, 13));
		// 设置底部的字体
		chart.getLegend().setItemFont(new Font("Arial", Font.PLAIN, 12));
		// 设置面板字体
		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
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
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.4));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 横轴上的
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
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
		// XYLineAndShapeRenderer xylineandshaperenderer =
		// (XYLineAndShapeRenderer) categoryplot.getRenderer();//改变曲线颜色
		lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
		lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
		// 显示折点数据
		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineandshaperenderer.setBaseItemLabelsVisible(true);
		// 图片路径
		FileOutputStream fos_jpg = null;
		try {
			isChartPathExist(Savepath());
			String chartName = Savepath() + charName;
			fos_jpg = new FileOutputStream(chartName);

			// 将报表保存为JPG文件
			ChartUtilities.writeChartAsJPEG(fos_jpg, chart, 1500, 600);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chart;
	}
}
