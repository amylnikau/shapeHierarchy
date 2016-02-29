package shape.polygon;


import shape.base.CloseShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends CloseShape {

    private int[] xPoints;
    private int[] yPoints;

    public Polygon() {

    }

    public Polygon(Point theCenter, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
    }

    public Polygon(Point theCenter, List<Point> points, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        setPoints(points);
    }

    public void setPoints(List<Point> points){
        xPoints = new int[points.size()];
        yPoints = new int[points.size()];
        int i = 0;
        for (Point p : points) {
            xPoints[i] = p.x;
            yPoints[i++] = p.y;
        }
    }

    public List<Point> getPoints(){
        List<Point> points = new ArrayList<>(xPoints.length);
        for(int i=0;i<xPoints.length;++i)
            points.add(new Point(xPoints[i], yPoints[i]));
        return points;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setColor(getFillColor());
        g.fillPolygon(xPoints, yPoints, xPoints.length);
        g.setColor(getFrameColor());
        g.drawPolygon(xPoints, yPoints, xPoints.length);
    }

    @Override
    public boolean contains(Point pt) {
        return false;
    }

    @Override
    public void move(Point pt) {

    }
}