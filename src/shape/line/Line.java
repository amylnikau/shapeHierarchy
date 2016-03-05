package shape.line;


import java.awt.*;

public class Line extends Ray {

    public Line() {

    }

    public Line(Point theCenter, Point endPoint, int frameWidth, Color frameColor) {
        super(theCenter, endPoint, frameWidth, frameColor);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        Point theCenter = getLocation();
        Point endPoint = getEndPoint();
        setEndPoint(new Point(2*theCenter.x-endPoint.x, 2*theCenter.y-endPoint.y));
        super.draw(g);
    }
}