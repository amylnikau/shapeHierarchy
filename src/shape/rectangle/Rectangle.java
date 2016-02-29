package shape.rectangle;

import shape.base.RectangularShape;

import java.awt.*;

public class Rectangle extends RectangularShape {

    public Rectangle() {

    }

    public Rectangle(Point theCenter, Point cornerPoint) {
        super(theCenter, cornerPoint);
    }

    public Rectangle(Point theCenter, Point cornerPoint, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    public void draw(Graphics2D g) {
        int width = getWidth();
        int height = getHeight();
        Point cornerPoint = getCornerPoint();
        g.setStroke(new BasicStroke(getFrameWidth()));
        g.setColor(getFillColor());
        g.fillRect(cornerPoint.x, cornerPoint.y, width, height);
        g.setColor(getFrameColor());
        g.drawRect(cornerPoint.x, cornerPoint.y, width, height);
    }

    @Override
    public boolean contains(Point pt) {
        Point cornerPoint = getCornerPoint();
        int width = getWidth();
        int height = getHeight();
        return pt.x >= cornerPoint.x && pt.x <= cornerPoint.x + width &&
                pt.y >= cornerPoint.y && pt.y <= cornerPoint.y + height;
    }

}