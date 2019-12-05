package spaceshapes;

import java.awt.Color;

public class DynamicShape extends Shape {
	
	protected boolean _filled = false;
	protected Color _color;
	/**
	 * Default constructor that creates an DynamicShape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicShape() {
		super();
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
		
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
	}
	
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
	}
	
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_text = text;
		_color = color;
	}
	
	/**
	 * Paints this DynamicShape object using the supplied Painter object.
	 */
	public void paint(Painter painter) {
		if (_filled) {
			Color oldColor = painter.getColor();
			painter.setColor(_color);
			
			painter.fillRect(_x, _y, _width, _height);
			painter.drawCentredText(_text, _x, _y, _width, _height);
			painter.setColor(oldColor);
		} else {
			painter.drawRect(_x, _y, _width, _height);
			painter.drawCentredText(_text, _x, _y, _width, _height);
		}
	}
	
	//public void fillRect(Painter painter, int x, int y, int width, int height) {
	//	painter.fillRect(x, y, width, height);
	//}
	@Override
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		//bounce off bottom or top wall
		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			_x = nextX;
			_y = nextY;
			//this.paint(painter);
			_filled = false;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			_x = nextX;
			_y = nextY;
			//this.paint(painter);
			_filled = false;
		}
		
		//bounce off left or right wall. gives priority to bouncing off side walls
		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			_filled = true;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			_filled = true;
		}

		_x = nextX;
		_y = nextY;
	}
}
