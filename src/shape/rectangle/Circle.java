package shape.rectangle;


import java.awt.*;

public class Circle extends Ellipse {

	public Circle(){

	}

    public Circle(Point theCenter, Point cornerPoint) {
        super(theCenter, cornerPoint);
    }

    public Circle(Point theCenter, Point cornerPoint, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, cornerPoint, frameWidth, frameColor, fillColor);
    }

    @Override
    protected void adaptCornerPoint(Point theCenter){
        Point cornerPoint = getCornerPoint();
        int deltaX = theCenter.x-cornerPoint.x;
        int deltaY = theCenter.y-cornerPoint.y;
        if (deltaX<0)
            cornerPoint.translate(2 * deltaX, 0);
        if (deltaY<0)
            cornerPoint.translate(0, 2 * deltaY);
        cornerPoint.setLocation(cornerPoint.x, theCenter.y-theCenter.x+cornerPoint.x);
    }
}