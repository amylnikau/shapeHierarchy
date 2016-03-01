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
        int hits = 0;

        int lastx = xPoints[xPoints.length-1];
        int lasty = yPoints[yPoints.length-1];
        int curx, cury;

        // Walk the edges of the polygon
        for (int i = 0; i < xPoints.length; lastx = curx, lasty = cury, i++) {
            curx = xPoints[i];
            cury = yPoints[i];

            if (cury == lasty) {
                continue;
            }

            int leftx;
            if (curx < lastx) {
                if (pt.x >= lastx) {
                    continue;
                }
                leftx = curx;
            } else {
                if (pt.x >= curx) {
                    continue;
                }
                leftx = lastx;
            }

            double test1, test2;
            if (cury < lasty) {
                if (pt.y < cury || pt.y >= lasty) {
                    continue;
                }
                if (pt.x < leftx) {
                    hits++;
                    continue;
                }
                test1 = pt.x - curx;
                test2 = pt.y - cury;
            } else {
                if (pt.y < lasty || pt.y >= cury) {
                    continue;
                }
                if (pt.x < leftx) {
                    hits++;
                    continue;
                }
                test1 = pt.x - lastx;
                test2 = pt.y - lasty;
            }

            if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
                hits++;
            }
        }
        return ((hits & 1) != 0);
    }

    @Override
    public void move(Point pt) {
        Point theCenter = getLocation();
        int deltaX = pt.x-theCenter.x;
        int deltaY = pt.y-theCenter.y;
        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i]+=deltaX;
            yPoints[i]+=deltaY;
        }
        setLocation(pt);
    }
}