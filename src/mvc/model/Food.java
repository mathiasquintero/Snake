package mvc.model;

import java.util.LinkedList;
import java.util.List;

public class Food extends GameObjekt {
	
	private Coordinate coordinate;

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Food() {
		super('\u2615');
		coordinate = Coordinate.getRandomCoordinate();
	}
	
	/**
	 * Eat the food. And put it somewhere else.
	 * @param c Coordinates where the new one can't be.
	 */
	public void eat(List<Coordinate> c) {
		while(c.contains(coordinate)) {
			coordinate = Coordinate.getRandomCoordinate();
		}
		notifyObservers();
	}

	@Override
	public List<Change> getChanges() {
		List<Change> list = new LinkedList<Change>();
		list.add(new Change(this.getDisplayChar(), coordinate));
		return list;
	}

}
