import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Sequence {
	private ArrayList<State> _stateSequence;
	
	public Sequence() {
		_stateSequence = new ArrayList<State>();
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
}
