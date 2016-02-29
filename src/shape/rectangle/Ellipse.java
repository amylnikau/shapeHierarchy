package shape.rectangle;

import shape.base.RectangularShape;

import java.awt.*;

public class Ellipse extends RectangularShape {

    public Ellipse() {

    }

    public Ellipse(Point theCenter, Point cornerPoint) {
        super(theCenter, cornerPoint);
    }

    public Ellipse(Point theCenter, Point cornerPoint, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    public void draw(Graphics2D g) {
        int width = getWidth();
        int height = getHeight();
        g.setStroke(new BasicStroke(getFrameWidth()));
        Point cornerPoint = getCornerPoint();
        g.setColor(getFillColor());
        g.fillOval(cornerPoint.x, cornerPoint.y, width, height);
        g.setColor(getFrameColor());
        g.drawOval(cornerPoint.x, cornerPoint.y, width, height);
    }

    @Override
    public boolean contains(Point pt) {
        int width = getWidth();
        int height = getHeight();
        Point theCenter = getLocation();
        double alpha = (double) (pt.x - theCenter.x) / width;
        double beta = (double) (pt.y - theCenter.y) / height;
        return 4 * (alpha * alpha + beta * beta) < 1;
    }
}