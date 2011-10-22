package oneday;
import processing.core.PApplet;
import processing.core.PVector;

public class Action {
	private int directionX;
	private int directionY;

	public Action( int aDirectionX, int aDirectionY ) {
		directionX = aDirectionX;
		directionY = aDirectionY;
	}
	

	public State execute( State initialState, GridModel worldState ) {
		int column = (int)initialState.getSquare()._gridPosition.x + directionX;
		int row = (int)initialState.getSquare()._gridPosition.y + directionY;
		return new State( worldState.getSquareAtGridPosition(column, row), worldState);
	}
}
