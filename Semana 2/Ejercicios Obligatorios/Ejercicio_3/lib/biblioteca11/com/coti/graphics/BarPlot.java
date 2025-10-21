/*
 * BarPlot.java
 *
 * Created on 20 de marzo de 2007, 11:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor..
 */
package com.coti.graphics;

import com.coti.guitools.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JPanel;

/**
 * This component produces bar plots. Overlapping plots are not supported. The
 * title, X axis and Y axis legends are parameterized. Grid Lines and numbers
 * can be shown or hidden.
 *
 * @author bruegel
 * @version 0.7 2007-03-22
 */
public class BarPlot extends JPanel {

    /**
     * If true, scale factors x and y will be the same.
     */
    private boolean preserveAspectRatio;
    /**
     * A two-column table of double.
     */
    private double[][] data;
    /**
     * Width of the destination rectangle.
     */
    private double lx;
    /**
     * Height of the destination rectangle
     */
    private double ly;
    /**
     * Distance from left border of the component to left border of destination
     * rectangle. Measured in pixels and calculated as a percentage of actual
     * size.
     */
    private double hx;
    /**
     * Distance from the top of the component to the upper border of the
     * destination rectangle, measured in pixels.
     */
    private double vy;
    /**
     * Horizontal scale factor. It has been calculated taking into account an
     * expanded primitive rectangle (the original boundary is increased by 10%
     * on all sides and centered on the primitive rectangle).
     */
    private double fx;
    /**
     * fy vertical scale factor.
     */
    private double fy;
    /**
     * Horizontal displacement, necessary after scaling by fx, fy.
     */
    private double dx;
    /**
     * Same as dx for the Y coordinate.
     */
    private double dy;
    /**
     * New minimum x for expanded primitive rectangle.
     */
    private double pXmin;
    /**
     * New maximum X for expanded primitive rectangle.
     */
    private double pXmax;
    /**
     * Lower coordinate of the expanded primitive rectangle.
     */
    private double pYmin;
    /**
     * Upper coordinate of the expanded primitive rectangle.
     */
    private double pYmax;
    /**
     * True minimum x of primitive values
     */
    private double xmin;
    /**
     * In primitive coordinates, the horizontal size of a bar. It is build in
     * such a way that only one half of total horizonal space is occupied by
     * bars.
     */
    private double barWidth;
    /**
     * True maximum x of primitive values
     */
    private double xmax;
    /**
     * True minimum X for primitive rectangle.
     */
    private double ymin;
    /**
     * True maximum Y for primitive rectangle.
     */
    private double ymax;

    private double temp;
    private Font titleFont, numberFont;
    private String plotTitle, xAxeLegend, yAxeLegend;
    private FontMetrics titleFM, numberFM;
    /**
     * If true, horizontal grid lines and numbers are shown. On by default.
     */
    private boolean horizontalGridlines;
    /**
     * If true, vertical grid lines and numbers are drawn. On by default.
     */
    private boolean verticalGridlines;
    private Graphics2D g2; // Plotting is done in this component
    private boolean showDebugInfo;

    /**
     * Simple constructor for a double table.Aspect ratio is not preserved.
     * @param d table of x-y values to be plotted
     */
    public BarPlot(double[][] d) {
        super();
        setUpFonts();

        this.data = d;
        this.preserveAspectRatio = false;

    }

    /**
     * A constructor for a plot with no data. Data must be supplied by means of
     * a call to setData.
     */
    public BarPlot() {
        super();
        setUpFonts();

        data = null;
        this.preserveAspectRatio = false;

    }

    /**
     * Builds a plot with a three-piece legend: name of plot, x axis legend and
     * y axis legend (in that order).
     *
     * @param d A double[][2] for data
     * @param title_and_units A 3-string String[]: ame of plot, x axis legend
     * and y axis legend (in that order).
     */
    public BarPlot(double[][] d, String[] title_and_units) {
        super();
        setUpFonts();

        this.data = d;
        this.preserveAspectRatio = false;

        this.horizontalGridlines = true;
        this.verticalGridlines = true;
        this.plotTitle = title_and_units[0];
        this.xAxeLegend = title_and_units[1];
        this.yAxeLegend = title_and_units[2];

    }

    public void setUpFonts() {
        this.numberFont = new Font("monospaced", Font.PLAIN, 14);
        this.titleFont = new Font("serif", Font.BOLD, 18);
        this.titleFM = this.getFontMetrics(titleFont);
        this.numberFM = this.getFontMetrics(numberFont);
        this.showDebugInfo = false;
        this.plotTitle = "Plot title";
        this.xAxeLegend = "X Axis legend";
        this.yAxeLegend = "Y Axis legend";

    }

    public void calculateTransformFactors() {
        /*
        * Pick up the size of this component; if too small
        * a 300x200 rectangle will be used.
        *
        * Also, it defines the size and placement of the
        * destination rectangle
        * lx - the width
        * ly - the height
        * hx - distance from the left border of the panel
        * vy - distance from the top border of the panel
        *
        * These are final measurements.
         */
        int panelWidth, panelHeight;

        panelWidth = this.getSize().width;
        panelHeight = this.getSize().height;

        lx = panelWidth * 7 / 10;
        ly = panelHeight * 7 / 10;
        hx = panelWidth * 2 / 10;
        vy = panelHeight * 1 / 10;
        /*
         * This method produces the following parameters:
         *
         * double fx - X-axe scaling
         * double fy - Y-axe scaling
         * double dx - X-axe displacement
         * double dy - Y-axe displacement
         *
         * The actual size of the primitive rectangle is increased by
         * 10% on all sides when calculating these factors. Thus the
         * real points will never reach the borders of the destination
         * rectangle.
         *
         * This transform intends to proyect the expanded primitive
         * rectangle onto the destination rectangle.
         */
        if (null == data) {
            JFrameAI.errMess(this.getParent(), "No data", "BarPlot Error");
        }

        xmin = xmax = data[0][0];
        ymin = ymax = data[0][1];
        /*
         * Get the primitive rectangle
         */
        for (int i = 0; i < data.length; i++) {
            temp = data[i][0];
            if (temp < xmin) {
                xmin = temp;
            } else if (temp > xmax) {
                xmax = temp;
            }
            temp = data[i][1];
            if (temp < ymin) {
                ymin = temp;
            } else if (temp > ymax) {
                ymax = temp;
            }
        }
        /*
         * Build the expanded primitive rectangle
         */

        pXmin = xmin - 0.1 * (xmax - xmin);
        pXmax = xmax + 0.1 * (xmax - xmin);
        pYmin = ymin - 0.1 * (ymax - ymin);
        pYmax = ymax + 0.1 * (ymax - ymin);

        fx = lx / (pXmax - pXmin);
        fy = ly / (pYmax - pYmin);
        /*
         * On occasion, we will want a plot in which squares
         * turn into squares. This is how to do it. Normally the
         * x dimension will be bigger, but this code makes sure
         * of getting the correct scaling.
         */
        if (preserveAspectRatio) {
            fx = Math.min(fx, fy);
            fy = fx;
        }
        dx = -pXmin * fx;
        dy = -pYmin * fy;
        barWidth = (xmax - xmin) / (2.0 * data.length);
    }

    /**
     * Applies an affine transform to the Point2D.Double passed as an argument.
     *
     * @param aPoint The point (written in real coordinates) that must be
     * projected onto the destination rectangle.
     * @return The destination point, written in screen coordinates.
     */
    public Point2D.Double transform(Point2D.Double aPoint) {
        Point2D.Double transformedPoint = new Point2D.Double();
        transformedPoint.x = hx + aPoint.x * fx + dx;
        transformedPoint.y = vy + ly - (aPoint.y * fy + dy);
        if (this.showDebugInfo) {
            System.out.println(transformedPoint.x + "*" + transformedPoint.y);
        }
        return transformedPoint;
    }

    public Point2D.Double transform(double x, double y) {
        Point2D.Double transformedPoint = new Point2D.Double(hx + x * fx + dx, vy + ly - (y * fy + dy));
        if (this.showDebugInfo) {
            System.out.println(transformedPoint.x + "->" + transformedPoint.y);
        }
        return transformedPoint;
    }

    /*
     * (0,0) is the upper left corner
     */
    /**
     * A method to plot straight lines between two points. Coordinates must be
     * screen coordinates, not real coordinates.
     *
     * @param start One end of the line, written in screen coordinates.
     * @param end The other end of the line, written in screen coordinates.
     */
    public void plotDestinationSegment(Point2D.Double start, Point2D.Double end) {
        g2.draw(new Line2D.Double(start, end));
    }

    public void plotSegment(Point2D.Double start, Point2D.Double end) {
        g2.draw(new Line2D.Double(transform(start), transform(end)));
    }

    /**
     * True if all Y values are strictly greater than zero. Then one must use
     * plotBarFromPositiveYTopYmin, so that a small segment will be shown even
     * for the minimum height bar.
     *
     * @return True if all Y values are greater than 0.
     */
    public boolean allValuesPositive() {
        for (double[] data1 : data) {
            if (data1[1] <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * True if Y greater than or equal to 0 for all Y values. Then one must use
     * plotBarFromPositiveYTopYmin.
     *
     * @return True if Y greater than or equal to zero for all Y.
     */
    public boolean allValuesPositiveOrZero() {
        for (double[] data1 : data) {
            if (data1[1] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * True if Y smaller than 0 for all Y. Then one must use plotBarFrompYmaxToNegativeY.
     * @return True if Y smaller than 0 for all Y.
     */
    public boolean allValuesNegative() {
        for (double[] data1 : data) {
            if (data1[1] >= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * True if Y smaller than or equal to 0 for all Y. Then one must use plotBarFrompYmaxToNegativeY.
     * @return True if Y smaller than or equal to 0 for all Y.
     */
    public boolean allValuesNegativeOrZero() {
        for (double[] data1 : data) {
            if (data1[1] > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * True if Y smaller than 0 or Y greater than 0 at least for one set of points. Then one must use
     * plotBarFromPositiveYToZero(ulp) if y smaller than 0 or
     * plotBarFromZeroToNegativeY(ulp) if y bigger than 0.
     *
     * @return True if both positive and negative Y values exist.
     */
    public boolean positiveAndNegativeValues() {
        boolean foundPositive = false, foundNegative = false;
        for (double[] data1 : data) {
            if (data1[1] < 0) {
                foundNegative = true;
            }
            if (data1[1] > 0) {
                foundPositive = true;
            }
        }
        return foundPositive && foundNegative;
    }

    /**
     * Plots a bar from a positive Y value to the lower border of the
     * destination rectangle, which is at pYmin in real coordinates. Used for
     * all-positive (non null) Y.
     *
     * @param upperLeft The top of the bar, in real (not screen) coordinates).
     * This point gives the height of the primitive point and is used
     * (transformed) as the top of the bar in screen coordinates. The width
     * comes from the barWidth, after transformation. The lower edge of the bar
     * is at the (transformed) height of pYmin. This bar is used when all Y
     * values are greater than zero. There must be some lower border for all
     * bars, and it should not be zero because zero does not belong to the set
     * of real heights, and would be transformed into a screen height that
     * likely to be outsid the destination rectangle. Hence we use the lower
     * border of that rectangle as a reference. All bars will have a length,
     * event the ones that are of minimum heigth.
     */
    public void plotBarFromPositiveYTopYmin(Point2D.Double upperLeft) {
        /*
         * The upperLeft argument indicates the upper edge of the bar,
         * which must come down to the X Axe. This axe is taken to be
         * at pYmin, since 0 might be a long way off and produce bar charts
         * that are excessively long in the tooth. It is a good compromise.
         *
         * This method should be used when all Y values are positive.
         */
        Point2D.Double lowerRight = new Point2D.Double();
        lowerRight.x = upperLeft.x + barWidth;
        lowerRight.y = pYmin;
        Point2D.Double tu = transform(upperLeft);
        Point2D.Double tl = transform(lowerRight);
        Rectangle2D.Double rd = new Rectangle2D.Double(tu.x - (lx / (5.0 * data.length)), tu.y, tl.x - tu.x, tl.y - tu.y);
        g2.fill(rd);
    }

    /**
     * Used when one has positive and negative values of Y.
     *
     * @param upperLeft The top of the bar in real (not screen) coordinates.
     */
    public void plotBarFromPositiveYToZero(Point2D.Double upperLeft) {
        /*
         * The upperLeft argument indicates the upper edge of the bar,
         * which must come down to the X Axe. This axe is taken to be
         * at pYmin, since 0 might be a long way off and produce bar charts
         * that are excessively long in the tooth. It is a good compromise.
         *
         * This method should be used when all Y values are positive.
         */
        Point2D.Double lowerRight = new Point2D.Double();
        lowerRight.x = upperLeft.x + barWidth;
        lowerRight.y = 0.0;
        Point2D.Double tu = transform(upperLeft);
        Point2D.Double tl = transform(lowerRight);
        Rectangle2D.Double rd = new Rectangle2D.Double(tu.x - (lx / (5.0 * data.length)), tu.y, tl.x - tu.x, tl.y - tu.y);
        g2.fill(rd);
    }

    /**
     * Used when one has positive and negative values of Y.
     *
     * @param lowerLeft The bottom of the bar in real (not screen) coordinates.
     */
    public void plotBarFromZeroToNegativeY(Point2D.Double lowerLeft) {
        /*
         * The lowerLeft argument indicates the position of the lower edge
         * of the bar, which starts on the X Axe.
         */
        Point2D.Double upperLeft = new Point2D.Double();
        upperLeft.x = lowerLeft.x;
        upperLeft.y = 0;
        Point2D.Double lowerRight = new Point2D.Double();
        lowerRight.x = lowerLeft.x + barWidth;
        lowerRight.y = lowerLeft.y;
        Point2D.Double tu = transform(upperLeft);
        Point2D.Double tl = transform(lowerRight);
        /*
         * Bars are displaced left half a bar width
         */
        Rectangle2D.Double rd = new Rectangle2D.Double(tu.x - (lx / (5.0 * data.length)), tu.y + 1, tl.x - tu.x, tl.y - tu.y - 1);
        g2.fill(rd);
    }

    /**
     * Used when all Y values are negative or zero.
     *
     * @param lowerLeft The bottom of the bar, in real (not screen) coordinates.
     * We use this bar when all Y values are negative or zero. Then all bars
     * come down from the top, both if Y values are negative and if they are
     * zero. This is because bars must have a length, even when they have the
     * minimum value. If Y is always less than zero, we cannot plot a bar from
     * zero height, because zero does not belong to the set of Y values, and
     * would be plotted outside the destination rectangle. If Y reaches 0, then
     * we must plot the bar from a point above zero, and this can only be (as in
     * the previous case) the transformed height of yMax. Thus the upper limit
     * of the bar is always inside the destination rectangle, and all bars have
     * a length (even if they have the smalles values). Hence, to summarize:
     *
     * plotBarFromPositiveYTopYmin - for Y greater than 0 or Y greater than or equal to 0
     * plotBarFromPositiveYToZero\ - Y smaller than 0 and Y greater than 0
     * plotBarFromZeroToNegativeY/ plotBarFrompYmaxToNegativeY - for Y smaller than 0
     *
     * This will always produce a reasonable bar plot.
     */
    public void plotBarFrompYmaxToNegativeY(Point2D.Double lowerLeft) {
        /*
         * The lowerLeft argument indicates the position of the lower edge
         * of the bar, which starts on the X Axe.
         */
        Point2D.Double upperLeft = new Point2D.Double();
        upperLeft.x = lowerLeft.x;
        upperLeft.y = pYmax;
        Point2D.Double lowerRight = new Point2D.Double();
        lowerRight.x = lowerLeft.x + barWidth;
        lowerRight.y = lowerLeft.y;
        Point2D.Double tu = transform(upperLeft);
        Point2D.Double tl = transform(lowerRight);
        /*
         * Bars are displaced left half a bar width
         */
        Rectangle2D.Double rd = new Rectangle2D.Double(tu.x - (lx / (5.0 * data.length)), tu.y + 1, tl.x - tu.x, tl.y - tu.y - 1);
        g2.fill(rd);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        this.calculateTransformFactors();
        /*
         * Expanded rectangle
         */
        plotDestinationSegment(transform(pXmax, pYmin), transform(pXmax, pYmax));
        plotDestinationSegment(transform(pXmax, pYmax), transform(pXmin, pYmax));
        plotDestinationSegment(transform(pXmin, pYmax), transform(pXmin, pYmin));
        plotDestinationSegment(transform(pXmin, pYmin), transform(pXmax, pYmin));
        /*
         * Primitive boundary rectangle
         */
        plotDestinationSegment(transform(xmin, ymin), transform(xmax, ymin));
        plotDestinationSegment(transform(xmax, ymin), transform(xmax, ymax));
        plotDestinationSegment(transform(xmax, ymax), transform(xmin, ymax));
        plotDestinationSegment(transform(xmin, ymax), transform(xmin, ymin));
        /*
         * This code shows the X Axis, if visible
         */
        Stroke oldStroke;
        if (ymin * ymax <= 0) {
            Point2D.Double xAxeLeft = new Point2D.Double(xmin, 0.0);
            Point2D.Double xAxeRight = new Point2D.Double(xmax, 0.0);
            xAxeLeft.x -= barWidth / 2;
            xAxeRight.x += barWidth / 2;
            g2.setColor(Color.RED);
            oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(2.0f));
            plotDestinationSegment(transform(xAxeLeft), transform(xAxeRight));
            g2.setColor(Color.BLACK);
            g2.setStroke(oldStroke);// Leave stroke as it was
        }
        /*
      * This code shows horizontal marks and values. Will overlap if too many
      * bar or if bar values too close. No solution but showing arbitrary
      * vertical values and lines.
      *
      * Also always remember we work on primitive coordinates, then transform
      * them to screen coordinates. Some tweaking may be done after the
      * transform is done, since we know we work (then I say) in pixels.
         */
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setMinimumIntegerDigits(2);

        Point2D.Double p = new Point2D.Double(), q = null;
        /*
         * Numbers should be right-aligned on the left hand side of the
         * expanded primitive regtangle. Actually once they are I move
         * them left 10 pixels.
         */
        p.x = pXmin; // Position of the left border of the expanded rectangle
        String tickValue;
        float temptativeYlegendPos = 0.0f;
        setFont(numberFont);
        if (horizontalGridlines) {
            for (int i = 0; i < data.length; i++) {
                p.y = ymin + i * (ymax - ymin) / (data.length - 1);
                q = transform(p);
                tickValue = nf.format(ymin + i * (ymax - ymin) / (data.length - 1));
                g2.drawString(tickValue, temptativeYlegendPos = (float) q.x - numberFM.stringWidth(tickValue) - 10, (float) q.y);
            }
            /*
         * This code shows horizontal grid lines
             */

            Point2D.Double left, right, tLeft, tRight;
            left = new Point2D.Double();
            right = new Point2D.Double();
            left.x = pXmin;
            right.x = pXmax;
            g2.setPaint(Color.GREEN);
            for (int i = 0; i < data.length; i++) {
                left.y = ymin + i * (ymax - ymin) / (data.length - 1);
                right.y = left.y;
                tLeft = transform(left);
                tRight = transform(right);
                plotDestinationSegment(tLeft, tRight);
            }

            /*
             *  This code shows the Y axis legend
             */
            g2.setPaint(Color.BLACK);
            /*
             *  This takes care of Y legend positioning. It may move out
             * of the screen for diminutive plots, but it will not overlap
             * any existing numbers.
             */
            double xPos = temptativeYlegendPos - numberFM.getMaxDescent() - 10;
            double yPos = vy + ly / 2f + numberFM.stringWidth(this.yAxeLegend) / 2;
            g2.rotate(-1.57);
            g2.drawString(this.yAxeLegend, -(float) yPos, (float) xPos);
            g2.rotate(1.57);
            /*
             *  This code shows the X axis legend
             */
            xPos = hx + lx / 2 - numberFM.stringWidth(this.xAxeLegend) / 2;
            yPos = vy + ly + 2 * (numberFM.getMaxDescent() + 10) + 10;
            g2.drawString(this.xAxeLegend, (float) xPos, (float) yPos);

        }
        /*
     * This code shows the bars, depending on the set of data
         */
        double x, y;
        g2.setPaint(Color.GREEN);
        if (allValuesPositive()) {
            for (int i = 0; i < data.length; i++) {
                x = data[i][0];
                y = data[i][1];
                Point2D.Double ulp = new Point2D.Double(x, y);
                plotBarFromPositiveYTopYmin(ulp);
            }
        } else if (allValuesNegative()) {
            for (int i = 0; i < data.length; i++) {
                x = data[i][0];
                y = data[i][1];
                Point2D.Double ulp = new Point2D.Double(x, y);
                plotBarFrompYmaxToNegativeY(ulp);
            }
        } else if (allValuesPositiveOrZero()) {
            for (int i = 0; i < data.length; i++) {
                x = data[i][0];
                y = data[i][1];
                Point2D.Double ulp = new Point2D.Double(x, y);
                plotBarFromPositiveYTopYmin(ulp);
            }
        } else if (allValuesNegativeOrZero()) {
            for (int i = 0; i < data.length; i++) {
                x = data[i][0];
                y = data[i][1];
                Point2D.Double ulp = new Point2D.Double(x, y);
                plotBarFrompYmaxToNegativeY(ulp);
            }
        } else if (positiveAndNegativeValues()) {
            for (int i = 0; i < data.length; i++) {
                x = data[i][0];
                y = data[i][1];
                Point2D.Double ulp = new Point2D.Double(x, y);
                if (y > 0) {
                    plotBarFromPositiveYToZero(ulp);
                } else if (y < 0) {
                    plotBarFromZeroToNegativeY(ulp);
                }
            }

        }
        g2.setPaint(Color.BLACK);
        /*
         * This code shows vertical grid lines and ticks
         */
        if (verticalGridlines) {
            Point2D.Double down, up, tDown, tUp;
            down = new Point2D.Double();
            up = new Point2D.Double();
            down.y = pYmin;
            up.y = pYmax;
            for (int i = 0; i < data.length; i++) {
                // Ticks
                g2.setPaint(Color.BLACK);
                x = data[i][0];
                y = pYmin;
                p = new Point2D.Double(x, y);
                q = transform(p);
                tickValue = nf.format(x);
                g2.drawString(tickValue,
                        (float) q.x - numberFM.stringWidth(tickValue) / 2,
                        (float) q.y + numberFM.getMaxAscent() + 5);
                // Gridlines
                g2.setPaint(Color.GREEN);
                tDown = transform(down);
                tUp = transform(up);
                tDown.x = q.x;
                tUp.x = q.x;
                plotDestinationSegment(tDown, tUp);
            }
        }

        /*
         * This code shows the title string
         */
        g2.setPaint(Color.BLACK);
        p.x = pXmin + (pXmax - pXmin) / 2;
        p.y = pYmax;
        q = transform(p);
        g2.setFont(titleFont);
        g2.drawString(plotTitle, (float) q.x - titleFM.stringWidth(plotTitle) / 2, (float) q.y - titleFM.getMaxDescent() - 5);
    }

    public void setPlotTitle(String plotTitle) {
        this.plotTitle = plotTitle;
    }

    public void setXAxeLegend(String xAxeLegend) {
        this.xAxeLegend = xAxeLegend;
    }

    public void setYAxeLegend(String yAxeLegend) {
        this.yAxeLegend = yAxeLegend;
    }

    public void setHorizontalGridlines(boolean horizontalGridlines) {
        this.horizontalGridlines = horizontalGridlines;
    }

    public void setVerticalGridlines(boolean verticalGridlines) {
        this.verticalGridlines = verticalGridlines;
    }

    public void setData(double[][] d) {
        this.data = d;
        this.revalidate();
    }

    public void setData(float[][] d) {
        data = new double[d.length][d[0].length];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                data[i][j] = d[i][j];
            }
        }
        this.revalidate();
    }

    public void setPreserveAspectRatio(boolean preserveAspectRatio) {
        this.preserveAspectRatio = preserveAspectRatio;
    }

    public void setShowDebugInfo(boolean ShowDebugInfo) {
        this.showDebugInfo = ShowDebugInfo;
    }
}
