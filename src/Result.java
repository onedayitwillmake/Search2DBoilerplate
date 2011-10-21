
public class Result {
	private Action inAction;
	private State inState;
	private GridModel worldState;

	public Result( State aState, Action anInAction,  GridModel aWorldState) {
		inState = aState;
		inAction = anInAction;
		worldState = aWorldState; 
	}
	
	public State execute() {
		return inAction.execute( inState, worldState );
	}
}
