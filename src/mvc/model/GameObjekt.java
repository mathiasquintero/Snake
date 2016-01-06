package mvc.model;

import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;

public abstract class GameObjekt {
	
	private List<GameObjektObserver> observers;
	
	private char displayChar;
	
	public GameObjekt(char displayChar) {
		this.observers = new LinkedList<GameObjektObserver>();
		this.displayChar = displayChar;
	}
	
	public abstract List<Pair<Character,Coordinate>> getChanges();
	
	/**
	 * Notify all observers to update.
	 */
	public void notifyObservers() {
		for (GameObjektObserver observer:observers) {
			observer.update(this);
		}
	}
	
	/**
	 * Subscribe to Changes in the Object
	 * @param o
	 */
	public void addOberserver(GameObjektObserver o) {
		observers.add(o);
	}
	
	/**
	 * Unsubscribe to changes in the Object.
	 * @param o
	 */
	public void removeObserver(GameObjektObserver o) {
		observers.remove(o);
	}

	public char getDisplayChar() {
		return displayChar;
	}

	public void setDisplayChar(char displayChar) {
		this.displayChar = displayChar;
	}

}
