import processing.core.PApplet;
import processing.core.PVector;

public class Agent {
	
	private Sequence _sequence;
	private GridModel _worldState;
	private State	_initialState;
	private State	_goal;

	private PApplet _app;
	public Agent( State anInitialState, State aGoal, GridModel aWorldState ) {
		
		_app = Search2DApp.getInstance();
		
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
		if( goalCheck( currentNode ) ) { 
			return;
		}
		
		// DOWN, RIGHT, UP, LEFT
		Action[] frontier = { new Action(0, 1), new Action(1, 0), new Action(0, -1), new Action(-1, 0) };
		
		// Picks first action from above that is valid
		for (int i = 0; i < frontier.length; i++) {
			State aState = frontier[i].execute( currentNode, _worldState );
			
			if( aState.getSquare() == null ) continue; // Invalid state - 
			if( _sequence.containsState( aState ) ) continue; // Already in sequence;
			
			aState.getSquare()._color = 255;
			_sequence.pushState( aState );
			break;
		}
	}

	private boolean goalCheck(State aNode) {
		return _goal.isEqual( aNode );
	}
}
