import processing.core.PApplet;
import processing.core.PVector;

public class Agent {
	
	private Sequence _sequence;
	private GridModel _worldState;
	private State	_initialState;
	private State	_goal;

	public Agent( State anInitialState, State aGoal, GridModel aWorldState ) {
		_initialState = anInitialState;
		_goal = aGoal;
		_worldState = aWorldState;
		
		 _sequence = new Sequence();
		_sequence.pushState( _initialState );
	}

	/**
	 * Advances a single step or like whatever
	 */
	public void advance() {
		// TODO: Implement worlds fastest algorithm ever
		
		State currentNode = _sequence.getLastState();
		
		Action randomAction = new Action(1, 0);
		State aState = randomAction.execute( currentNode, _worldState );
		
		if( aState.getSquare() != null ) {
			aState.getSquare()._color = 255;
			_sequence.pushState( aState );
		}
	}
}
