package shape.line;


import java.awt.*;

public class Segment extends shape.base.Shape {

    private Point endPoint;

    public Segment() {

    }

    public Segment(Point theCenter, Point endPoint, int frameWidth, Color frameColor) {
        super(theCenter, frameWidth, frameColor);
        this.endPoint = endPoint;
    }

    @Override
    public void draw(Graphics2D g) {
        Point startPoint = getLocation();
        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setColor(getFrameColor());
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    @Override
    public boolean contains(Point pt) {
        Point theCenter = getLocation();
        int a = endPoint.y - theCenter.y;
        int b = endPoint.x - theCenter.x;
        double d = (a * pt.x - b * pt.y + b * theCenter.y - a * theCenter.x) / (Math.sqrt(a * a + b * b));
        return Math.abs(d) < getFrameWidth() / 2;
    }

    @Override
    public void move(Point pt) {
        Point theCenter = getLocation();
        setEndPoint(new Point(endPoint.x + pt.x - theCenter.x, endPoint.y + pt.y - theCenter.y));
        super.move(pt);
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        setEndPoint(endPoint, false);
    }

    public void setEndPoint(Point endPoint, boolean smooth) {
        if (!smooth)
            this.endPoint = endPoint;
        else {
            Point theCenter = getLocation();
            if (Math.abs(theCenter.x - endPoint.x) < Math.abs(theCenter.y - endPoint.y))
                this.endPoint = new Point(theCenter.x, endPoint.y);
            else
                this.endPoint = new Point(endPoint.x, theCenter.y);
        }
    }
}