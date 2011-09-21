package org.jfree.chart.demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialPointer;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

public class DialDemo1 extends JFrame {
    private static final long serialVersionUID = 1L;

    static class DemoPanel extends JPanel implements ChangeListener {

        private static final long serialVersionUID = 1L;

        public void stateChanged(ChangeEvent changeevent) {
            dataset.setValue(new Integer(slider.getValue()));
        }

        JSlider slider;
        DefaultValueDataset dataset;

        public DemoPanel() {
            super(new BorderLayout());
            dataset = new DefaultValueDataset(10D);
            DialPlot dialplot = new DialPlot();
            dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
            dialplot.setDataset(dataset);
            StandardDialFrame simpledialframe = new StandardDialFrame();
            simpledialframe.setBackgroundPaint(Color.lightGray);
            simpledialframe.setForegroundPaint(Color.darkGray);
            dialplot.setDialFrame(simpledialframe);
            GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
            DialBackground dialbackground = new DialBackground(gradientpaint);
            dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            dialplot.setBackground(dialbackground);
            DialTextAnnotation dialtextannotation = new DialTextAnnotation("Temperature");
            dialtextannotation.setFont(new Font("Dialog", 1, 14));
            dialtextannotation.setRadius(0.69999999999999996D);
            dialplot.addLayer(dialtextannotation);
            DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
            dialplot.addLayer(dialvalueindicator);
            StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10.0, 4);
            standarddialscale.setTickRadius(0.88D);
            standarddialscale.setTickLabelOffset(0.14999999999999999D);
            standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
            dialplot.addScale(0, standarddialscale);
            StandardDialRange standarddialrange = new StandardDialRange(40D, 60D, Color.red);
            standarddialrange.setInnerRadius(0.52000000000000002D);
            standarddialrange.setOuterRadius(0.55000000000000004D);
            dialplot.addLayer(standarddialrange);
            StandardDialRange standarddialrange1 = new StandardDialRange(10D, 40D, Color.orange);
            standarddialrange1.setInnerRadius(0.52000000000000002D);
            standarddialrange1.setOuterRadius(0.55000000000000004D);
            dialplot.addLayer(standarddialrange1);
            StandardDialRange standarddialrange2 = new StandardDialRange(-40D, 10D, Color.green);
            standarddialrange2.setInnerRadius(0.52000000000000002D);
            standarddialrange2.setOuterRadius(0.55000000000000004D);
            dialplot.addLayer(standarddialrange2);
            DialPointer.Pointer pointer = new DialPointer.Pointer();
            dialplot.addLayer(pointer);
            DialCap dialcap = new DialCap();
            dialcap.setRadius(0.10000000000000001D);
            dialplot.setCap(dialcap);
            JFreeChart jfreechart = new JFreeChart(dialplot);
            jfreechart.setTitle("Demo Dial 1");
            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(400, 400));
            slider = new JSlider(-40, 60);
            slider.setMajorTickSpacing(10);
            slider.setPaintLabels(true);
            slider.addChangeListener(this);
            add(chartpanel);
            add(slider, "South");
        }
    }

    public DialDemo1(String s) {
        super(s);
        setDefaultCloseOperation(3);
        setContentPane(createDemoPanel());
    }

    public static JPanel createDemoPanel() {
        return new DemoPanel();
    }

    public static void main(String args[]) {
        DialDemo1 dialdemo1 = new DialDemo1("JFreeChart - Demo Dial 1");
        dialdemo1.pack();
        dialdemo1.setVisible(true);
    }
}
