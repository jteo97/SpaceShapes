package spaceshapes;

import java.util.ArrayList;
import java.util.List;

public class CarrierShape extends Shape{

	private List<Shape> _contents = new ArrayList<Shape>();

	public CarrierShape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		_allCarrierShapes.add(this);
	}
	public CarrierShape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		_allCarrierShapes.add(this);
	}

	public CarrierShape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		_allCarrierShapes.add(this);
	}

	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_allCarrierShapes.add(this);
	}
	
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x,y,deltaX,deltaY,width,height,text);
		_allCarrierShapes.add(this);
		
	}

	@Override
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
		}

		_x = nextX;
		_y = nextY;

		for (int i = 0; i < _contents.size(); i++) {
			_contents.get(i).move(_width, _height);
		}
	}

	public void paint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		painter.translate(_x, _y);
		for (int i = 0; i < _contents.size(); i++) {
			_contents.get(i).paint(painter);

		}
		painter.translate(-_x, -_y);
		painter.drawCentredText(_text, _x, _y, _width, _height);
	}

	void add(Shape shape) throws IllegalArgumentException {
		int width = shape._x + shape._width;
		int height = shape._y + shape._height;

		if (width > _width | height > _height) {
			throw new IllegalArgumentException("Error");
		} 

		for (int i = 0; i < _allCarrierShapes.size(); i++) {
			CarrierShape carriershape = _allCarrierShapes.get(i);
			
			for (int j = 0; j < carriershape._contents.size(); j++) {
				if (carriershape._contents.get(j).equals(shape)) {
					throw new IllegalArgumentException("Error");
				}
			}
		}
		
		_contents.add(shape);
		shape._parent = this;
	}

	void remove(Shape shape) {
		_contents.remove(shape);
		shape._parent = null;
	}

	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		if (index < 0 | index >= _contents.size()) {
			throw new IndexOutOfBoundsException("Error");
		} else {
			return _contents.get(index);
		}
	}

	public int shapeCount() {

		return _contents.size();
	}

	public int indexOf(Shape shape) {
		for (int i = 0; i < _contents.size(); i++) {
			if (_contents.get(i).equals(shape)) {
				return i;
			}
		}
		return -1;
	}

	public boolean contains(Shape shape) {
		return _contents.contains(shape);		
	}
}
