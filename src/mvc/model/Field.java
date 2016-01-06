package mvc.model;

import java.util.LinkedList;
import java.util.List;

import mvc.controller.GameObjektObserver;
import javafx.util.Pair;

/**
 * 
 * This Class functions as a dual Delegate for the GameObjekts
 * It puts them together without too many pointers going around.
 * 
 * The Controller has no direct access to the GameObjekts but only the field.
 * 
 * It will group all the changes in the field and group.
 *
 */
public class Field extends GameObjekt implements GameObjektObserver {
	
	private int width;
	
	private int height;
	
	private Snake snake;
	
	private Food food;
	
	private List<Change> changes;
	
	public Field(int width, int height) {
		super(' ');
		changes = new LinkedList<Change>();
		snake = new Snake(this,5);
		snake.addOberserver(this);
		food = new Food();
		food.addOberserver(this);
		this.width = width;
		this.height = height;
	}
	
	
	/**
	 * @param d Direction the player has set.
	 */
	public void setDirection(Direction d) {
		snake.setDirection(d);
	}
	
	public boolean isAlive() {
		return snake.isAlive();
	}
	
	/**
	 * Will return if a Coordinate is Outside the field
	 * @param c Coordinate you want to check
	 * @return boolean value.
	 */
	public boolean coordinateIsOut(Coordinate c) {
		if (c.getX() < 0 || c.getY() < 0)
			return true;
		if (c.getX() >= width || c.getY() >= height)
			return true;
		return false;
	}
	
	
	/**
	 * Return if c is the same coordinate of the eatable item
	 * @param c Coordinate you want to check
	 * @return boolean value
	 */
	public boolean didEat(List<Coordinate> c) {
		Coordinate f = food.getCoordinate();
		if (c.contains(f)) {
			food.eat(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Starts the Thread for the snake
	 */
	public void start() {
		Thread snakeThread = new Thread(snake);
		snakeThread.start();
		food.notifyObservers();
	}
	
	@Override
	public List<Change> getChanges() {
		List<Change> returnable = new LinkedList<Change>();
		returnable.addAll(changes);
		changes.clear();
		return returnable;
	}
	
	@Override
	public void update(GameObjekt o) {
		changes.addAll(o.getChanges());
		notifyObservers();
	}
	
}
