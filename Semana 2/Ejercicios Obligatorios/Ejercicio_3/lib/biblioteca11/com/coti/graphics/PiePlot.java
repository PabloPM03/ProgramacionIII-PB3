package com.coti.graphics;


import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class PiePlot extends JPanel {
    private Graphics2D g2;
    private AffineTransform at;
    private String[] legend;
    private float[] values;
    private Color[] colours;
    private float xmin, xmax, ymin, ymax;
    private float legendX, yLegendSize;
    private final Font titleFont = new Font("Serif", Font.PLAIN, 24);
    private final Font valueFont = new Font("Monospaced", Font.PLAIN, 12);
    private FontMetrics tfm;
    private FontMetrics vfm;
    private final Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension screenResolution;
    
    public PiePlot(float[] values, Color[] colours, String[] legend) {
       this.values = values;
        this.colours = null != colours ? colours : inventColours();
        this.legend = legend;
        screenResolution = tk.getScreenSize();
        /*
         * Aspect ratio must be 3:2 so that no deformation
         * happens on transform.
         */
        
        if (screenResolution.getWidth() > 800)
            this.setPreferredSize(new Dimension(600,400));
        else
            this.setPreferredSize(new Dimension(360,240));
        tfm = getFontMetrics(titleFont);
        vfm = getFontMetrics(valueFont);
    }
    
    public Color[] inventColours() {
    	Color[] samples = {	Color.blue, Color.cyan, Color.gray, Color.green,
    											Color.magenta, Color.orange, Color.pink, Color.yellow,
    											Color.red };
    	int numberOfDefaultColors = samples.length;
    	int numberOfValues = values.length;
    	Color[] list = new Color[numberOfValues];
			int i;
			int index;
    	for(i=0,index=0;i<numberOfValues;i++)
    		{
    				list[i] = samples[(index<numberOfDefaultColors)?index:(index=0)];
    				index++;			
    		}
    	return list;
    }
    
    public AffineTransform calculateTransform() {
        float fx, fy, dx, dy;
        
        /*
         * Destination rectangle is lx by ly pixels.
         */
        
        int maxLx = (int)getSize().getWidth()*8/10;
        int lx = 2*maxLx/3;
        int ly = lx;
        
        /*
         * Distances to left and top border of panel are hx and hy
         */
        int hx = (int)getSize().getWidth()*10/100;
        int hy = (int)getSize().getHeight()/10;
        
        xmin = -2.0f;
        ymin = -2.0f;
        
        xmax = +2.0f;
        ymax = +2.0f;
        
        legendX = xmax;
        yLegendSize = (ymax - ymin);
        
        fx = lx/(xmax-xmin);
        fy = ly/(ymax-ymin);
        dx = -xmin*fx;
        dy = -ymin*fy;
        /*
         * Documentation for this is here
         *
         * http://java.sun.com/j2se/1.5.0/docs/api/java/awt/geom/AffineTransform.html
         */
        /*
         *  This affine transform proyects all pointx inside (-2,-2)x(2,2) onto
         * a Lx by Lx destination rectangle. No problems with aspect ratio.
         */
        AffineTransform af = new AffineTransform(fx,0,0,-fy,dx+hx,hy+ly-dy);
        return af;
    } /* end of calculateAffineTransform */
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D)g;
        at = calculateTransform();
        Rectangle2D.Float destRect = new Rectangle2D.Float(-2.0f, -2.0f,6.0f,4.0f);
        g2.setPaint(Color.BLACK);
        g2.draw(at.createTransformedShape(destRect));
       /*
        * Now we plot the data - actually the smallest code!
        */
        doPiePlot(g2,at);
    }
    
    public void doPiePlot(Graphics2D g2, AffineTransform at) {
        
        Color initialColor = g2.getColor();
        Arc2D.Float slice  = new Arc2D.Float();
        Shape s            = null;
        double start = 0.0;
        double extent = 0.0;
        double total = 0.0;
        double factor = 0.0;
        int numberOfSlices = values.length;
        for(int i=0;i<numberOfSlices;i++)
            total += values[i];
        factor = 360.0/total;
        for(int i=0;i<numberOfSlices;i++){
            extent = values[i]*factor;
            slice.setArcByCenter(0.0, 0.0,      // Center
                    1.75,          // Radius
                    start,         // Start (degrees)
                    extent,        // Extent (degrees)
                    Arc2D.PIE);    // Closure
            start += extent;
            s = at.createTransformedShape(slice);
            g2.setPaint(colours[i]);
            g2.fill(s);
        }
       g2.setPaint(Color.BLACK);
       float yLegendOffset = yLegendSize/10;
       float yLegendSeparation = (8*yLegendSize/10)/(numberOfSlices-1);
       Point2D.Float pOrigen = new Point2D.Float(), pDestino = new Point2D.Float();
       for(int i=0;i<numberOfSlices;i++){
           pOrigen.setLocation(legendX,ymin + yLegendOffset + i*yLegendSeparation);
           at.transform(pOrigen,pDestino);
           g2.setPaint(colours[i]);
           g2.drawString(legend[i],(int)pDestino.getX(),(int)pDestino.getY());
       }
        g2.setPaint(initialColor);
    }
}
