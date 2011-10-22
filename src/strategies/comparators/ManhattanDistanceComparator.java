/**
 * Used to sort a set of polygonal points based on the barycenter
 * http://gamedev.stackexchange.com/questions/13229/sorting-array-of-points-in-clockwise-order
 */
package strategies.comparators;

import java.util.Comparator;

import oneday.GridSquare;
import oneday.State;

import processing.core.PVector;

public class ManhattanDistanceComparator implements Comparator<State>  {
	private GridSquare _goal; 
	public ManhattanDistanceComparator(GridSquare goal) {
		_goal = goal;
	}
	
	public int compare(State stateA, State stateB) {
		PVector subA = PVector.sub( stateA.getSquare()._center, _goal._center );
		float distASq = subA.dot( subA );
		
		PVector subB = PVector.sub( stateB.getSquare()._center, _goal._center );
		float distBSq = subB.dot( subB );
		
		
		if( distASq < distBSq ) return 1;
		else if ( distBSq < distBSq ) return -1;
		return 0;
	}
	
}