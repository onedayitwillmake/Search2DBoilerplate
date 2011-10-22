package oneday;
import processing.core.PApplet;
import processing.core.PVector;
import strategies.DepthFirst;
import strategies.GraphSearch;
import strategies.comparators.ManhattanDistanceComparator;

public class Agent {
	
	private Sequence _sequence;
	private GridModel _worldState;
	private State	_initialState;
	private State	_goal;
	
	private GraphSearch _graph;

	private PApplet _app;
	public Agent( State anInitialState, State aGoal, GridModel aWorldState ) {
		
		_app = Search2DApp.getInstance();
		
		_initialState = anInitialState;
		_goal = aGoal;
		_worldState = aWorldState;
		
		_graph = new GraphSearch();

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
		
		_graph.advance( currentNode, _worldState, _sequence );
		
		ManhattanDistanceComparator manhattanDistanceComparator = new ManhattanDistanceComparator( _goal.getSquare() );
		_graph.getFrontier().sort( manhattanDistanceComparator );
	}

	private boolean goalCheck(State aNode) {
		return _goal.isEqual( aNode );
	}

	public GridSquare getCurrentSquare() {
		if( _sequence.getLastState() != null && _sequence.getLastState().getSquare() != null ) {
			return _sequence.getLastState().getSquare();
		}
		return null;
	}

	/**
	 * Sets the goal ( Causes a restart to the initial state )
	 * @param aGoal
	 */
	public void setGoal(State aGoal) {
		_initialState = _sequence.getLastState();
		
		_sequence = new Sequence();
		_sequence.pushState( _initialState );
		
		_goal = aGoal;
		_graph.clear();
	}
}
