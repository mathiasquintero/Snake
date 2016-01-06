package mvc.model;

import java.util.LinkedList;
import java.util.List;

public class Snake extends GameObjekt implements Runnable {
	
	//Path the Snake is following
	
	private List<Coordinate> path;
	
	//Changes since the last update
	
	private List<Change> changes;
	
	private Direction direction;
	
	private Field field;
	
	private boolean alive = true;

	public Snake(Field field, int size) {
		super('\u2588');
		path = new LinkedList<Coordinate>();
		changes = new LinkedList<Change>();
		direction = Direction.Right;
		this.field = field;
		
		//Create Body
		
		Coordinate start = Coordinate.getRandomCoordinate();
		for (int i = 0; i<size; i++) {
			Coordinate c = new Coordinate(start.getY() + i, start.getX());
			path.add(c);
			changes.add(new Change(this.getDisplayChar(), c));
		}
	}
	
	/**
	 * Will determine the next item and notify all observers about the changes
	 */
	public void move() {
		Coordinate last = path.get(path.size() - 1);
		Coordinate next = new Coordinate(last.getY() + direction.y, last.getX() + direction.x);
		if (path.contains(next) || field.coordinateIsOut(next)) {
			alive = false;
		}
		changes.add(new Change(this.getDisplayChar(), next));
		changes.add(new Change(' ', path.get(0)));
		path.add(next);
		if (!field.didEat(path)) {
			path.remove(0);
		}
		notifyObservers();
	}
	
	/**
	 * Set new Direction. 
	 * The opposite direction of the moment is not allowed. 
	 * That would be suicide.
	 * @param direction New Direction
	 */
	public void setDirection(Direction direction) {
		if (this.direction.x == (-1) * direction.x || this.direction.y == (-1) * direction.y) {
			return;
		}
		this.direction = direction;
	}

	@Override
	public List<Change> getChanges() {
		List<Change> returnable = new LinkedList<Change>();
		returnable.addAll(changes);
		changes.clear();
		return returnable;
	}

	@Override
	public void run() {
		while(alive) {
			move();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isAlive() {
		return alive;
	}

}
