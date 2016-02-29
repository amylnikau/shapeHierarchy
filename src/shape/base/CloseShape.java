package shape.base;


import java.awt.*;

public abstract class CloseShape extends Shape {

	private Color fillColor = new Color(255,255,255);

	public CloseShape(){

	}

    public CloseShape(Point theCenter) {
        super(theCenter);
    }

    public CloseShape(Point theCenter, int frameWidth, Color frameColor, Color fillColor) {
        super(theCenter, frameWidth, frameColor);
        this.fillColor = fillColor;
    }

    public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
}