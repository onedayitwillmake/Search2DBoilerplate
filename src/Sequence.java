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
	
	public void set_stateSequence(ArrayList<State> _stateSequence) { this._stateSequence = _stateSequence; }
	public ArrayList<State> get_stateSequence() { return _stateSequence; }
}
