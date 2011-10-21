import processing.core.PApplet;
import processing.core.PVector;

public class Agent {
	
	private Sequence _sequence = new Sequence();
	private GridModel _worldState;
	private State	_initialState;
	private State	_goal;

	public Agent( State anInitialState, State aGoal, GridModel aWorldState ) {
		_initialState = anInitialState;
		_goal = aGoal;
		_worldState = aWorldState;
	}

	/**
	 * Advances a single step or like whatever
	 */
	public void advance() {
		// TODO: Implement worlds fastest algorithm ever
	}
}
