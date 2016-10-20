// Written by :- Jeremy Thompson
// Date :- 10/10/2000
// lab Session for week 2 
// Stage 2: Clock Face and Stage 3: Clock hands

   import java.awt.*;
   import java.awt.geom.*;
   import java.lang.Math.*;
   import java.util.Date;
   import java.util.Calendar;
   import java.util.StringTokenizer;

   public class myClock extends java.applet.Applet  
   {
      public void paint(Graphics g)
      {
      
      // Gets size of the window
         Graphics2D g2 = (Graphics2D) g;
         double gw = getSize().width;
         double gh = getSize().height;
      
      
      // center of window
         double cx = gw *0.5; 
         double cy = gh *0.5;
      
      
      // width & height circle made equal 
         if (gw > gh) {
            gw = gh;}
         else {
            gh = gw;}
      
      
         double radius = (gh/2.2); // Outer circle
         double tfaceRadius = radius - 15; // Face circle
      
      
         double w = 2 * radius;
         double h = 2 * radius;
      
      //draws circle
         Shape myShape;	
         myShape = new Ellipse2D.Double(cx - radius,cy - radius,w,h); 
         Color tmyColour = new Color(230, 230, 150);
         g2.setPaint(tmyColour);
         g2.fill(myShape);
      
      //draws border
         g2.setPaint(Color.black);
         BasicStroke tStroke = new BasicStroke((float)3.0);
         g2.setStroke(tStroke);
         g2.draw(myShape);
      
      
         BasicStroke tNormalStroke = new BasicStroke();
         g2.setStroke(tNormalStroke);
      
      
      //TextAttribute dave = new TextAttribute.JUSTIFICATION(0.5);
         Font tfont = new Font("Serif",Font.PLAIN,(int)(radius / 13 /*0.06*/)); // 0.08 = 13th
         g2.setFont(tfont);
      
      
         int tx = 0,ty =0;
      
      
      // creates digital clock
         Calendar tDate = Calendar.getInstance();
         int thours = tDate.get(Calendar.HOUR);
         if (thours == 0) { 
            thours = 12; }
         int tminutes = tDate.get(Calendar.MINUTE);
         int tseconds = tDate.get(Calendar.SECOND);
      
         g2.setPaint(Color.white);
         myShape = new Rectangle2D.Double((cx + (radius * 0.25)), (cy - (radius * 0.05)), (radius * 0.43), (radius * 0.10));
         g2.fill(myShape);
         g2.setPaint(Color.black);
         g2.draw(myShape);
      
         g2.drawString(thours + ":" + tminutes + ":" + tseconds, (int)(cx + (radius * 0.33)), (int)(cy + (radius * 0.029)));
      
         tfont = new Font("Serif",Font.BOLD, (int)(radius * 0.10));
         g2.setFont(tfont);
         g2.drawString("OMEGA", (int)(cx - (radius * 0.18)), (int)(cy - (radius * 0.50)));
      
         tfont = new Font("Serif",Font.ITALIC, (int)(radius * 0.10));
         g2.setFont(tfont);
      
      
      //Plots figures on face of clock
         for (int i = 1; i <= 12; i++)
         {
         
         // Hour Dashes
            int x = (int)findX(i,6,tfaceRadius,cx);
            int y = (int)findY(i,6,tfaceRadius,cy);
         
            tx = (int)findX(i,6,10,x);
            ty = (int)findY(i,6,10,y);
         
            g2.setPaint(Color.black);
            g2.drawLine((int)x,(int)y, tx, ty);
         
         
         // Clock numerals
            tx = (int)findX(i,6,tfaceRadius*0.95,cx);// - 5;
            ty = (int)findY(i,6,tfaceRadius*0.95,cy);// + 5;
         
         
            g2.drawString("" + i, tx - 6, ty + 9);
         
         }
      
      
      // Minute dashes 
         for (int i = 1; i <= 60; i++)
         {
         
            int x = (int)findX(i, 30, tfaceRadius+5, cx);
            int y = (int)findY(i, 30, tfaceRadius+5, cy);
         
            tx = (int)findX(i, 30, 5, x);
            ty = (int)findY(i, 30, 5, y);
         
            g2.drawLine(x, y, tx, ty);
         
         }
        
      
      // Generates the hour hand
         tx = (int)findX(thours, 6, tfaceRadius * 0.6, cx); // 1080
         ty = (int)findY(thours, 6, tfaceRadius * 0.6, cy);
         g2.setStroke(tStroke);
         g2.drawLine((int)cx,(int)cy, tx, ty);
      
      
      // places the minute hand
         tx = (int)findX(tminutes, 30, tfaceRadius * 0.8, cx);
         ty = (int)findY(tminutes, 30, tfaceRadius * 0.8, cy);
         tStroke = new BasicStroke((float)2.0);
         g2.setStroke(tStroke);
         g2.drawLine((int)cx, (int)cy, tx, ty);
         g2.setStroke(tNormalStroke);
      
      
      // places the second hand
         tx = (int)findX(tseconds, 30, tfaceRadius * 0.9, cx);
         ty = (int)findY(tseconds, 30, tfaceRadius * 0.9, cy);
         g2.drawLine((int)cx, (int)cy, tx, ty);
      
      }
   
   
   
      private double findX (double pi, int pb, double pRadius, double pcx)
      {
      
         double tx = ( (Math.sin( (Math.PI * -pi) / pb) * -pRadius) + pcx); //x
         return tx;
      
      }
   
   
   
      private double findY (double pi, int pb, double pRadius, double pcy)
      {
      
         double ty = ( (Math.cos( (Math.PI * -pi) / pb) * -pRadius) + pcy); //y
         return ty;
      
      }
   
   
      public String getAppletInfo() 
      {  
         return "myApplet v.1.3";
      }
   }
