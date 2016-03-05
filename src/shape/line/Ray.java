package shape.line;


import java.awt.*;

public class Ray extends Segment {

    public Ray() {

    }

    public Ray(Point theCenter, Point endPoint, int frameWidth, Color frameColor) {
        super(theCenter, endPoint, frameWidth, frameColor);
    }

    @Override
    public void setEndPoint(Point endPoint, boolean smooth) {
        if(endPoint.x>0 && endPoint.x<Toolkit.getDefaultToolkit().getScreenSize().getWidth() &&
                endPoint.y>0 && endPoint.y<Toolkit.getDefaultToolkit().getScreenSize().getHeight())
            endPoint = getOutScreenPoint(endPoint);
        super.setEndPoint(endPoint, smooth);
    }

    public Point getOutScreenPoint(Point pt) {
        Point theCenter = getLocation();
        Point result = new Point();
        double deltaX = pt.x - theCenter.x;
        double deltaY = pt.y - theCenter.y;
        if (deltaX==0 && deltaY==0)
            return pt;
        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            double height;
            if (deltaY < 0)
                height = -1;
            else
                height = Toolkit.getDefaultToolkit().getScreenSize().getHeight() + 1;
            result.setLocation(deltaX / deltaY * (height - theCenter.y) + theCenter.x, height);
        } else {
            double width;
            if (deltaX < 0)
                width = -1;
            else
                width = Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 1;
            result.setLocation(width, deltaY / deltaX * (width - theCenter.x) + theCenter.y);
        }
        return result;
    }
}