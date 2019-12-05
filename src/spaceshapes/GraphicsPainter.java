package spaceshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(Color.GRAY);
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaeshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
	}

	public Color getColor() {
		return _g.getColor();
	}

	public void setColor(Color c) {
		_g.setColor(c);
	}

	@Override
	public void translate(int x, int y) {
		_g.translate(x, y);

	}

	@Override
	public void drawCentredText(String str, int x, int y, int width, int height) {

		if (str != null) {
			x += (width - _g.getFontMetrics().stringWidth(str)) / 2;
			y += height/2;
			int ascent = _g.getFontMetrics().getAscent();
			int descent = _g.getFontMetrics().getDescent();
			if (ascent > descent) {
				y += (ascent - descent) / 2;
			} else if (descent > ascent) {
				y += (descent - ascent) / 2;
			}
			_g.drawString(str, x, y);
		}
	}

	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img, x, y, width, height, null);
	}

}
