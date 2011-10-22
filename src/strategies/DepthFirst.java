package strategies;

import oneday.Action;
import oneday.GridModel;
import oneday.Sequence;
import oneday.State;

public class DepthFirst {
	public static void ADVANCE( State currentNode, GridModel worldState, Sequence sequence ) {
		// DOWN, RIGHT, UP, LEFT
		Action[] frontier = { new Action(1, 0), new Action(0, 1), new Action(0, -1), new Action(-1, 0) };
		
		boolean hasValidState = false;
		// Picks first action from above that is valid
		for (int i = 0; i < frontier.length; i++) {
			State aState = frontier[i].execute( currentNode, worldState );
			
			if( aState.getSquare() == null ) continue; // Invalid state - 
			if( sequence.containsState( aState ) ) continue; // Already in sequence;
			if( !aState.getSquare().isPermutable() ) continue;
			
			hasValidState = true;
			 
			aState.getSquare()._color = 255;
			sequence.pushState( aState );
			break;
		}
		
		
		if( !hasValidState ) {
			currentNode = sequence.popState();
			sequence.insertAtHead( currentNode );
		}
	}
}
