import processing.core.PApplet;
import processing.core.PVector;

public class Goal {
	private int _gridX;
	private int _gridY;

	public Goal( int gridX, int gridY ) {
		_gridX = gridX;
		_gridY = gridY;
	}
	public int get_gridY() {
		return _gridY;
	}
	public int get_gridX() {
		return _gridX;
	}
}
