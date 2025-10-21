package com.coti.graphics;
/**
 *@author coti@usal.es
 *@version 0.8
 *
 * Last updated March 2007
 *
 * This version makes use of a fixed number (6) of grid lines.
 * Also, this version includes a paintGridAndAxes method that
 * makes it easier to override paintComponent in such a way that
 * various versions of doPlot can be called.
 *
 * Today is 12/03/07. It is now 20:49
 * 
 * This version includes a rational way to increase the size
 * of the enclosing rectangle for the primitive table. It can be adapted
 * easily to a 3D prism of course. If I can find a way to create affine transforms
 * for 3D points, then I can plot 3D points. Of course this does not solve
 * the surface problem (line hiding, polygon hiding, shading) but it is a doable
 * step I think. I must rethink the affine transform.
 **/
import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.swing.JPanel;

/**
 * This is an abstract class (it is missing doPlot). Its derivatives will
 * produce a Bar Plot, Pie Chart or Linear Plot (so far).
 */
public abstract class BasicPlot extends JPanel {
    protected float[] xdata, ydata; /* input data */
    protected int[] px,py; /* final or drawable data */
    protected final Font titleFont = new Font("Serif", Font.PLAIN, 24);
    protected final Font valueFont = new Font("Monospaced", Font.PLAIN, 12);
    protected FontMetrics tfm;
    protected FontMetrics vfm;
    protected final Toolkit tk = Toolkit.getDefaultToolkit();
    protected Dimension screenResolution;
    protected String[] strings;
    protected float xmax, xmin, ymax, ymin;
    protected Graphics2D g2;
    protected AffineTransform at;
    /**
     * Horizontal size of destination rectangle
     */
    int lx;
    /**
     * Vertical size of destination rectangle
     */
    int ly;
    /**
     * Distance from left border of container panel to left border of destination rectangle.
     */
    int hx;
    /**
     * Distance from top of container panel to top of destination rectangle.
     */
    int hy;
    protected float fx, fy, dx, dy;
    
    /**
     * Minimum X
     * @return A float, the minimum value of X coordinates
     */
    protected float getXmin(){return xmin;};
    /**
     * Maximum X
     * @return A float, the maximum value of x coordinates.
     */
    protected float getXmax(){return xmax;};
    /**
     * Minimum Y
     * @return A float, the minimum value of Y coordinates.
     */
    protected float getYmin(){return ymin;};
    /**
     * Maximum Y
     * @return float   The maximum value of Y coordinates
     */
    protected float getYmax(){return ymax;};
    /**
     * Produces a standard-sized panel, depending on screen resolution.
     * Might accept bigger sizes for bigger screens (?).
     */
    public void setUpJPanel() {
        screenResolution = tk.getScreenSize();
        if (screenResolution.getWidth() > 800)
            setPreferredSize(new Dimension(640,480));
        else
            setPreferredSize(new Dimension(320,240));
        tfm = getFontMetrics(titleFont);
        vfm = getFontMetrics(valueFont);
    }
    /**
     * Constructor for float[][] as data and title,
     * name of X unis and name of Y units
     * @param data A float[][] in which the first column contains X values
     * and the second contains Y values.
     * @param str A list of three components:
     *    The title of the plot
     *    The legend of the X axis
     *    The legend of the Y axis
     *
     */
    public BasicPlot(float[][] data, String str[]) {
        strings = str;
        setUpJPanel();
        if (null == data)
        	return;
        int number_of_points = data.length;
        xdata = new float[number_of_points];
        ydata = new float[number_of_points];
        for(int i=0;i<number_of_points;i++) {
            xdata[i] = data[i][0];
            ydata[i] = data[i][1];
        }
    }
    public BasicPlot(double[][] data, String str[]) {
        strings = str;
        setUpJPanel();
        if (null == data)
        	return;
        int number_of_points = data.length;
        xdata = new float[number_of_points];
        ydata = new float[number_of_points];
        for(int i=0;i<number_of_points;i++) {
            xdata[i] = (float)data[i][0];
            ydata[i] = (float)data[i][1];
        }
    }
    /**
     * This constructor admits two float[] which contain X and Y data,
     * respectively. The third argument contains first of all the title of the
     * graph, and then the unit names for the X and Y axes.
     * @param xd List of float values for x coordinates of points.
     * @param yd List of float values for y coordinates of points
     * @param str List of three strings: title, x axis legend and y axis legend
     */
    public BasicPlot(float[] xd, float[] yd, String str[]) {
        strings = str;
        xdata = xd;
        ydata = yd;
        setUpJPanel();
    }
    /**
     * This constructor admits first of all two lists of integers,
     * which are pairs (x,y) of points to be plotted. The third argument
     * contains first of all the title of the graph, and then two more strings.
     * These are the strings that will be shown as unit names.
     * @param xd List of integer x values
     * @param yd List of integer y values
     * @param str Title, X legend and Y legend
     */
    public BasicPlot(int[] xd, int[] yd, String str[]) {
        strings = str;
        int length = xd.length;
        xdata = new float[length];
        ydata = new float[length];
        for(int i=0;i<xd.length;i++) {
            xdata[i] = xd[i];
            ydata[i] = yd[i];
        }
        setUpJPanel();
    }
    /**
     *
     * calculateAffineTransform()
     * @return The affine transform that will be applied to all lines painted.
     */
    public AffineTransform calculateTransform() {
        float tempx, tempy;
        if (null == xdata || null == ydata)
        	return null;
        /* width of plotting rectangle */
        lx = (int)getSize().getWidth()*7/10;
        /* height of plotting rectangle */
        ly = (int)getSize().getHeight()*7/10;
        /* distance of plotting rectangle to left border of JPanel */
        hx = (int)getSize().getWidth()*20/100;
        /* distance of plotting rectangle to top border of JPanel */
        hy = (int)getSize().getHeight()/10;
        xmax = xmin = xdata[0];
        ymax = ymin = ydata[0];
        // Find maximum and minimum values
        for(int i=0;i<xdata.length;i++) {
            tempx = xdata[i]; tempy = ydata[i];
            if (tempx < xmin) xmin = tempx; else if (tempx > xmax) xmax = tempx;
            if (tempy < ymin) ymin = tempy; else if (tempy > ymax) ymax = tempy;
        }
        /*
         * We will consider that the primitive rectangle is bigger
         * than it really is
         */
        xmin -= 0.1f*(xmax-xmin);
        ymin -= 0.1f*(ymax-ymin);
        xmax += 0.1f*(xmax-xmin);
        ymax += 0.1f*(ymax-ymin);
        
        fx = lx/(xmax-xmin);
        fy = ly/(ymax-ymin);
        dx = -xmin*fx;
        dy = -ymin*fy;
        /*
         * Documentation for this is here
         *
         * http://java.sun.com/j2se/1.5.0/docs/api/java/awt/geom/AffineTransform.html
         */
        AffineTransform af = new AffineTransform(fx,0,0,-fy,dx+hx,hy+ly-dy);
        return af;
    } /* end of calculateAffineTransform */
    
    /**
     * Abstract method, must be overridden in derivations
     * @param g2 The Affine Transform that scales primitive table points to 
     * plottable (destination rectangle) points.
     * @param xdata A float[] with the X coordinates
     * @param ydata A float[] with the Y coordinates
     * @param at The affine transform calculated previously
     */
    abstract void doPlot(Graphics2D g2, float[] xdata, float[] ydata,
            AffineTransform at);
    
    public void paintDestinationRectangle() {
                        /*
                  * And we show a destination rectangle
                  */
        g2.setPaint(Color.black);
        Rectangle2D.Float boundary = new Rectangle2D.Float(	xmin,
                ymin,
                xmax-xmin,
                ymax-ymin);
        g2.draw(at.createTransformedShape(boundary));

    }
    public void paintGridAndAxes(Graphics g){
        /*
         * Put up a title
         */
        g2.setFont(titleFont);
        String title = strings[0];
        int tposx = hx + (lx - tfm.stringWidth(title))/2;
        int tposy = tfm.getHeight()+5;
        g2.drawString(title,tposx,tposy);
        /*
         * Basically, the use of an affine transform allows us to always
         * work in a real-world coordinate system. Everything is
         * translated to the proper point on screen.
         */
        Line2D.Float tempLine;
        float temp;
        g2.setPaint(Color.lightGray);
        /*
         * We will now plot a grid and values.
         * First we do the grid, then the values.
         *
         * These are vertical grid lines, with x values below the
         * X axis. Numbers will have 2 decimal digits and I would
         * like the "." to be aligned as much as possible with the
         * vertical grid line.
         */
        int yOffset = 0;
        int xOffset = 0;
        float xRange = (xmax-xmin);
        
        int numLines=6;
        float xLineSep = xRange/(numLines-1);
        String tempAsString = null;
        Point2D.Float p2d;
        Point2D.Float p2dd = new Point2D.Float();
        g2.setFont(valueFont);
        for(int i=0;i<numLines;i++) {
            temp = xmin + i*xLineSep;
            p2d = new Point2D.Float(temp,ymin);
            at.transform(p2d, p2dd);
            xOffset = vfm.stringWidth("4")+ vfm.stringWidth(",")/2; /* :p */
            yOffset = vfm.getHeight()+ 5;
            
            g2.setPaint(Color.black);
            tempAsString = String.format("%1.2g",temp);
            g2.drawString(tempAsString,
                    (int)p2dd.getX()- xOffset,
                    (int)p2dd.getY()+ yOffset);
            g2.setPaint(Color.green);
            g2.drawLine((int)p2dd.getX(),hy,(int)p2dd.getX(), hy+ly);
        }
        /*
         * Now we do the same for the horizontal grid and values
         */
        g2.setPaint(Color.lightGray);
        float yRange = (ymax-ymin);
        float yLineSep = yRange/(numLines-1);
        for(int i=0;i<numLines;i++) {
            temp = ymin + i*yLineSep;
            p2d = new Point2D.Float(xmin,temp);
            at.transform(p2d, p2dd);
            tempAsString = String.format("%1.2g",temp);
            xOffset = vfm.stringWidth(tempAsString) + 35;
            yOffset = vfm.getHeight()/3;
            g2.setPaint(Color.black);
            g2.drawString(tempAsString,
                    (int)p2dd.getX() - xOffset,
                    (int)p2dd.getY() + yOffset);
            g2.setPaint(Color.green);
            g2.drawLine(hx,(int)p2dd.getY(),hx+lx,(int)p2dd.getY());
        }
        g2.setPaint(Color.red);
        String tempValue;
        int sw;
        /*
         * Now we show the name of the y-units, properly rotated and centered
         * on the left hand side of the destination rectangle.
         */
        g2.rotate(-1.57f);
        String s = strings[2]; /* name of the Y units */
        sw = vfm.stringWidth(s);
        g2.drawString(s, -(hy +(ly-sw)/2 +sw),95*hx/100);
        g2.rotate(1.57f);
                        /*
                                And now we show the name of the x-units, also centered
                         */
        s = strings[1]; /* the name of x-units */
        sw = vfm.stringWidth(s);
        g2.drawString(s,hx+ (lx-sw)/2,hy+ly+3*vfm.getHeight());
        paintDestinationRectangle();
        /*
                 * If visible, this will draw X-axis and Y-axis
                 * We paint them last so they will always be on top
                 */
        g2.setPaint(Color.red);
        if (ymin < 0.0f && ymax > 0.0f) {
            tempLine = new Line2D.Float(xmin,0f,xmax,0f);
            g2.draw(at.createTransformedShape(tempLine));
        }
        if (xmin < 0.0f && xmax > 0.0f) {
            tempLine = new Line2D.Float(0f,ymin,0f,ymax);
            g2.draw(at.createTransformedShape(tempLine));
        }
    }
    /**
     * We override the method in order to do our own painting
     * @param g The graphics environment; will be modified by the Affine Transform we apply.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2= (Graphics2D)g;
        at = calculateTransform();
        if (null == at)
        	return;
        paintGridAndAxes(g);
       /*
        * Now we plot the data - actually the smallest code!
        */
        doPlot(g2,xdata,ydata,at);
    }
    public void setData(float[][] data) {
    	xdata = new float[data.length];
    	ydata = new float[data.length];
    	for(int i=0;i<data.length;i++)
    		{
    			xdata[i] = data[i][0];
    			ydata[i] = data[i][1];
    		}
    	this.revalidate();
    }
    public void setData(double[][] data) {
    	xdata = new float[data.length];
    	ydata = new float[data.length];
    	for(int i=0;i<data.length;i++)
    		{
    			xdata[i] = (float)data[i][0];
    			ydata[i] = (float)data[i][1];
    		}
    	this.revalidate();
    }
}