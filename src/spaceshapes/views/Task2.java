package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener{
	
	private List<TreeModelListener> _listener = new ArrayList<>();
	
	public Task2(ShapeModel root) {
		super(root);
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_listener.add(l);
		
	}
	
	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		_listener.remove(l);

	}

	@Override
	public void update(ShapeModelEvent event) {
		Shape[] shape = new Shape[1];
		shape[0] = event.operand();
		
		Shape[] paths = null;
		CarrierShape parent = event.parent();
		if (parent != null) {
			List<Shape> path = parent.path();
			paths = new Shape[path.size()];
			
			for (int i = 0; i < path.size(); i++) {
				paths[i] = path.get(i);
			}
		}
		
		int[] index = new int[1];
		index[0] = event.index();
		
		ShapeModel source = event.source();
		
		TreeModelEvent treeEvent;
		
		if (event.eventType() == spaceshapes.ShapeModelEvent.EventType.ShapeAdded) {
			
			treeEvent = new TreeModelEvent(source, paths, index, shape);
			
			for (TreeModelListener listener : _listener) {
				listener.treeNodesInserted(treeEvent);
			}
			
		} else if (event.eventType() == spaceshapes.ShapeModelEvent.EventType.ShapeRemoved) {
			
			treeEvent = new TreeModelEvent(source, paths, index, shape);
			
			for (TreeModelListener listener : _listener) {
				listener.treeNodesRemoved(treeEvent);
			}
			
		}		
	}
}