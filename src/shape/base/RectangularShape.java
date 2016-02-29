package shape.base;

import java.awt.*;

public abstract class RectangularShape extends CloseShape {

    private Point cornerPoint;
    private int width;
    private int height;

    public RectangularShape() {

    }

    public RectangularShape(Point theCenter, Point cornerPoint) {
        super(theCenter);
        setCornerPoint(cornerPoint);
    }

    public RectangularShape(Point theCenter, Point cornerPoint, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor, fillColor);
        setCornerPoint(cornerPoint);
    }

    public void move(Point pt) {
        Point theCenter = getLocation();
        cornerPoint.translate(pt.x - theCenter.x, pt.y - theCenter.y);
        super.move(pt);
    }

    public Point getCornerPoint() {
        return cornerPoint;
    }

    public void setCornerPoint(Point cornerPoint) {
        this.cornerPoint = cornerPoint;
        Point theCenter = getLocation();
        adaptCornerPoint(theCenter);
        width = 2 * (theCenter.x - cornerPoint.x);
        height = 2 * (theCenter.y - cornerPoint.y);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    protected void adaptCornerPoint(Point theCenter) {
        int deltaX = theCenter.x - cornerPoint.x;
        int deltaY = theCenter.y - cornerPoint.y;
        if (deltaX < 0)
            cornerPoint.translate(2 * deltaX, 0);
        if (deltaY < 0)
            cornerPoint.translate(0, 2 * deltaY);
    }
}