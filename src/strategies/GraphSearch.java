package strategies;

import oneday.Action;
import oneday.GridModel;
import oneday.Sequence;
import oneday.State;
import strategies.comparators.ManhattanDistanceComparator;

public class GraphSearch {
	private Sequence _frontier;
	public GraphSearch() {
		_frontier = new Sequence();
	}
	
	public void advance( State currentNode, GridModel worldState, Sequence sequence ) {
		// DOWN, RIGHT, UP, LEFT
		Action[] frontier = {new Action(0, -1), new Action(-1, 0),  new Action(1, 0), new Action(0, 1) };
		
		boolean hasValidState = false;
		// Picks first action from above that is valid
		for (int i = 0; i < frontier.length; i++) {
			State aState = frontier[i].execute( currentNode, worldState );
			
			if( aState.getSquare() == null ) continue; // Invalid state - 
			if( _frontier.containsState( aState ) ) continue; // Already in _frontier;
			if( sequence.containsState( aState ) ) continue; // Already in sequence;
			if( !aState.getSquare().isPermutable() ) continue;
						 
			aState.getSquare()._color = 128;
			_frontier.insertAtHead( aState );
		}
		
		if( _frontier.getLastState() != null ) {
			sequence.pushState( _frontier.popState() );
			sequence.getLastState().getSquare()._color = 255;
		}
	}
	
	public Sequence getFrontier() { return _frontier; }

	public void clear() {
		_frontier.clear();
	}
}
