package shape.polygon;


import shape.base.CloseShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon extends CloseShape {

    private int nPoints;
    private int[] xPoints;
    private int[] yPoints;

    private static final int MIN_LENGTH = 4;

    public Polygon() {

    }

    public Polygon(Point theCenter, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
    }

    public Polygon(Point theCenter, List<Point> points, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        setPoints(points);
    }

    public void setPoints(List<Point> points) {
        nPoints = points.size();
        xPoints = new int[nPoints];
        yPoints = new int[nPoints];
        int i = 0;
        for (Point p : points) {
            xPoints[i] = p.x;
            yPoints[i++] = p.y;
        }
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>(nPoints);
        for (int i = 0; i < nPoints; ++i)
            points.add(new Point(xPoints[i], yPoints[i]));
        return points;
    }

    public int getPointsSize() {
        return nPoints;
    }

    public void addPoint(Point pt) {
        if (nPoints >= xPoints.length || nPoints >= yPoints.length) {
            int newLength = nPoints * 2;
            if (newLength < MIN_LENGTH) {
                newLength = MIN_LENGTH;
            } else if ((newLength & (newLength - 1)) != 0) {
                newLength = Integer.highestOneBit(newLength);
            }
            xPoints = Arrays.copyOf(xPoints, newLength);
            yPoints = Arrays.copyOf(yPoints, newLength);
        }
        xPoints[nPoints] = pt.x;
        yPoints[nPoints] = pt.y;
        nPoints++;
        setLocation(computeCenter());
    }

    public void setLastPoint(Point pt){
        xPoints[nPoints-1] = pt.x;
        yPoints[nPoints-1] = pt.y;
        setLocation(computeCenter());
    }

    private Point computeCenter(){
        Point centroid = new Point(0,0);
        double signedArea = 0.0;
        double x0; // Current vertex X
        double y0; // Current vertex Y
        double x1; // Next vertex X
        double y1; // Next vertex Y
        double a;  // Partial signed area

        for (int i=0; i<nPoints-1; ++i)
        {
            x0 = xPoints[i];
            y0 = yPoints[i];
            x1 = xPoints[i+1];
            y1 = yPoints[i+1];
            a = x0*y1 - x1*y0;
            signedArea += a;
            centroid.x += (x0 + x1)*a;
            centroid.y += (y0 + y1)*a;
        }

        x0 = xPoints[nPoints-1];
        y0 = yPoints[nPoints-1];
        x1 = xPoints[0];
        y1 = yPoints[0];
        a = x0*y1 - x1*y0;
        signedArea += a;
        centroid.x += (x0 + x1)*a;
        centroid.y += (y0 + y1)*a;

        signedArea *= 0.5;
        centroid.x /= (6.0*signedArea);
        centroid.y /= (6.0*signedArea);

        return centroid;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setColor(getFillColor());
        g.fillPolygon(xPoints, yPoints, nPoints);
        g.setColor(getFrameColor());
        g.drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public boolean contains(Point pt) {
        int hits = 0;

        int lastx = xPoints[nPoints - 1];
        int lasty = yPoints[nPoints - 1];
        int curx, cury;

        // Walk the edges of the polygon
        for (int i = 0; i < nPoints; lastx = curx, lasty = cury, i++) {
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
        int deltaX = pt.x - theCenter.x;
        int deltaY = pt.y - theCenter.y;
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] += deltaX;
            yPoints[i] += deltaY;
        }
        super.move(pt);
    }
}