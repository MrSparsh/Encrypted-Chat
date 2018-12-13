import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class Graph extends JPanel {
    
    public static ArrayList<Double> length=new ArrayList<>();
    public static ArrayList<Double> time=new ArrayList<>();
    
    final int PAD = 30;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "Time taken";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "Length of file";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        // Draw lines.
        g2.setPaint(Color.green.darker());
        double x1,y1,x2=PAD,y2=h-PAD;
        for(int i=0; i < time.size(); i++) {
            x1 = x2;
            y1 =y2;
            x2 = length.get(i)+(i+1)*PAD;
            y2 = h-(i+1)*PAD - time.get(i);
            g2.draw(new Line2D.Double(x1, y1, x2+PAD, y2-PAD));
        }
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < time.size(); i++) {
            double x =(i+1)*PAD+length.get(i);
            double y =h-time.get(i)-(i+1)*PAD;
            y1=h-PAD;
            x1=PAD;
            //for x,y line coordinates
            g2.fill(new Ellipse2D.Double(x+PAD, y1, 4, 4));
            g2.fill(new Ellipse2D.Double(x1, y-PAD, 4, 4));
            g2.drawString("("+Integer.toString((length.get(i)).intValue()),(int)x-15+PAD,(int)y1);
            g2.drawString(",0)",(int)x+5+PAD,(int)y1);
            g2.drawString("(0",(int)x1-15,(int)y-PAD);
            g2.drawString(","+Integer.toString((time.get(i)).intValue())+")",(int)x1+5,(int)y-PAD);
            //for line coordinates
            g2.drawString("("+Integer.toString((length.get(i)).intValue()),(int)x-15+PAD,(int)y-PAD);
            g2.drawString(","+Integer.toString((time.get(i)).intValue())+")",(int)x+10+PAD,(int)y-PAD);
            g2.fill(new Ellipse2D.Double(x+PAD, y-PAD, 4, 4));
        }
    }
    public static void main(String args[])
    {
        Frame f = new Frame();
        f.setBounds(200,200,800,300);
        f.add(new Graph());
        f.setResizable(false);
        f.setVisible(true);
        
    }
}
