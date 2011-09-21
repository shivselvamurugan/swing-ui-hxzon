package org.jfree.chart.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

public class BarChartDemo3 extends ApplicationFrame {
    private static final long serialVersionUID = 1L;

    static class CustomRenderer extends BarRenderer {

        private static final long serialVersionUID = 1L;

        public Paint getItemPaint(int i, int j) {
            return colors[j % colors.length];
        }

        private Paint colors[];

        public CustomRenderer(Paint apaint[]) {
            colors = apaint;
        }
    }

    public BarChartDemo3(String s) {
        super(s);
        CategoryDataset categorydataset = createDataset();
        JFreeChart jfreechart = createChart(categorydataset);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartpanel);
    }

    private static CategoryDataset createDataset() {
        double ad[][] = { { 4D, 3D, -2D, 3D, 6D } };
        return DatasetUtilities.createCategoryDataset("Series ", "Category ", ad);
    }

    private static JFreeChart createChart(CategoryDataset categorydataset) {
        JFreeChart jfreechart = ChartFactory.createBarChart("Bar Chart Demo 3", "Category", "Value", categorydataset, PlotOrientation.VERTICAL, false, true, false);
        jfreechart.setBackgroundPaint(Color.lightGray);
        CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
        categoryplot.setNoDataMessage("NO DATA!");
        CustomRenderer customrenderer = new CustomRenderer(new Paint[] { Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.cyan, Color.magenta, Color.blue });
        customrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        customrenderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition itemlabelposition = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45D);
        customrenderer.setBasePositiveItemLabelPosition(itemlabelposition);
        categoryplot.setRenderer(customrenderer);
        CategoryMarker categorymarker = new CategoryMarker("Category 3");
        categorymarker.setLabel("Special");
        categorymarker.setPaint(new Color(221, 255, 221, 128));
        categorymarker.setAlpha(0.5F);
        categorymarker.setLabelAnchor(RectangleAnchor.TOP_LEFT);
        categorymarker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        categorymarker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);
        categoryplot.addDomainMarker(categorymarker, Layer.BACKGROUND);
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setLowerMargin(0.14999999999999999D);
        numberaxis.setUpperMargin(0.14999999999999999D);
        return jfreechart;
    }

    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[]) {
        BarChartDemo3 barchartdemo3 = new BarChartDemo3("Bar Chart Demo 3");
        barchartdemo3.pack();
        RefineryUtilities.centerFrameOnScreen(barchartdemo3);
        barchartdemo3.setVisible(true);
    }
}
