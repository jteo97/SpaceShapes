package spaceshapes.views;


import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;

public class Task1 implements TreeModel{
	
	private ShapeModel _adaptee;
	

	public Task1(ShapeModel root) {
		_adaptee = root;
	}

	@Override
	public Object getRoot() {
		return _adaptee.root();
	}

	@Override
	public Object getChild(Object parent, int index) {

		try {
			if(parent instanceof CarrierShape) {
				CarrierShape parentShape = (CarrierShape)parent;
				return parentShape.shapeAt(index);
			}
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		int result = 0;

		if (parent instanceof CarrierShape) {
			CarrierShape parentShape = (CarrierShape)parent;
			result = parentShape.shapeCount();
		}
		return result;
	}

	@Override
	public boolean isLeaf(Object node) {

		return !(node instanceof CarrierShape);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		int indexOfChild = -1;

		if (parent instanceof CarrierShape) {
			CarrierShape parentShape = (CarrierShape) parent;
			if (child instanceof Shape) {
				Shape childShape = (Shape) child;
				return parentShape.indexOf(childShape);
			}
		}
		return indexOfChild;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		
	}

}
