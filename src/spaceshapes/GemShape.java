package spaceshapes;

public class GemShape extends Shape{

	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
	}
	
	public void paint(Painter painter) {
		painter.drawOval(_x,_y,_width,_height);
		painter.drawCentredText("this is a Gem Shape", _x, _y, _width, _height);
	}

}
