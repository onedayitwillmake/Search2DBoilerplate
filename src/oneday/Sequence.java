package oneday;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import processing.core.PApplet;
import processing.core.PVector;
import strategies.comparators.ManhattanDistanceComparator;

public class Sequence {
	private ArrayList<State> _stateSequence;
	
	public Sequence() {
		_stateSequence = new ArrayList<State>();
	};
	
	public void insertAtHead( State aState ) {
		_stateSequence.add(0, aState);
	};
	
	public void pushState( State aState ) { _stateSequence.add( aState ); };
	public State popState() { 
		if ( !_stateSequence.isEmpty() ) {
			return _stateSequence.remove( _stateSequence.size() - 1 );
		}
		return null;
	}
	
	///// ACCESSORS
	public State getLastState() {
		if ( !_stateSequence.isEmpty() ) {
			return _stateSequence.get( _stateSequence.size() - 1 );
		}
		return null;
	}
	
	public Boolean containsState( State stateToCompare ) {
		for (State aState : _stateSequence) {
			if( aState.isEqual( stateToCompare ) ) {
				return true;
			}
		}
		
		return false;
	}

	

	public void sort( Comparator<State> aComparator) {
		Collections.sort( _stateSequence, aComparator );
	}
	
	public void clear() { _stateSequence.clear(); }
}
