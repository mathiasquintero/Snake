package mvc.model;

public class Coordinate {
	
	private int y;
	private int x;
	
	public Coordinate(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public static Coordinate getRandomCoordinate() {
		int x = (int) (Math.random() * 10) + 4;
		int y = (int) (Math.random() * 10) + 4;
		return new Coordinate(y,x);
	}
	
	@Override
	public boolean equals(Object obj) {
		try {
			Coordinate other = (Coordinate) obj;
			return other.getX() == x && other.getY() == y;
		} catch (Exception e) {
			return false;
		}
	}
	
}
