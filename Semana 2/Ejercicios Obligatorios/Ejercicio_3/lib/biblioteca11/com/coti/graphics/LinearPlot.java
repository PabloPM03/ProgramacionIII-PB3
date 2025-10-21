package com.coti.graphics;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * This class is a JPanel that produces linear plots, that is, a series of lines
 * that joint 2D points. Axis and grid plotting are automatic, as is scaling.
 */
public class LinearPlot extends BasicPlot {
    /**
     * This constructor needs the table of data (a bidimensional table actually) and
     * also the title of the plot and the names of X and Y units, to be used as a
     * titles for axes.
     * @param data the table to be represented
     * @param str the title of the plot, and the titles of both axes
     */
    public LinearPlot(float[][] data, String str[]) {
        super(data, str);
    }
    public LinearPlot(double[][] data, String str[]) {
        super(data, str);
    }
    /**
     * X and Y values are given separately as float[].A double[] param is in order.
     * @param xd the list of x values
     * @param yd the list of corresponding y values
     * @param str the title of the plot
     */
    public LinearPlot(float[] xd, float[] yd, String str[]) {
        super(xd,yd,str);
    }
    /**
     * For plotting two lists of int coordinates.
     * @param xd the list of x values
     * @param yd the list of corresponding y values
     * @param str the title of the plot
     */
    public LinearPlot(int[] xd, int[] yd, String str[]) {
        super(xd,yd,str);
    }
    /**
     * The actual plotting routine, based on an instance of AffineTransform
     */
    @Override
    public void doPlot(	Graphics2D g2,
            float[] xdata,
            float[] ydata,
            AffineTransform at) {
        g2.setPaint(Color.black);
        for(int i=0;i<xdata.length-1;i++)
            g2.draw(at.createTransformedShape(new Line2D.Float(	xdata[i],
                    ydata[i],
                    xdata[i+1],
                    ydata[i+1])));
    }
}