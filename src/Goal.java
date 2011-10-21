import processing.core.PApplet;
import processing.core.PVector;

public class Goal {
	private GridSquare _square;
	public Goal( GridSquare aSquare ) {
		setSquare(aSquare);
	}
	
	public void setSquare(GridSquare _square) { this._square = _square; }
	public GridSquare getSquare() { return _square; }
}
