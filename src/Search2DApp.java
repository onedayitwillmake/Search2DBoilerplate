import java.util.ArrayList;
import processing.core.*;

public class Search2DApp extends PApplet {
	private static final long serialVersionUID = -3824555102005090780L;
	private static int RESOLUTION = 10;
	
	private float _elapsedFrames;
	
	private GridModel _gridModel;
	private Agent _agent;
	
	public void setup() {
		_elapsedFrames = 0;

		size(1200, 600);
		frameRate(60);
		background(0);

		_gridModel = new GridModel(width, height, width / RESOLUTION, this);
		
		GridSquare initialState = _gridModel.getSquareAtGridPosition(0, 0);
		initialState._color = 255;
		
		GridSquare goalSquare = _gridModel.getSquareAtPixelPosition( width - 1, height - 1);
		goalSquare._color = 128;
		
		_agent = new Agent( new State(initialState, _gridModel), new State(initialState, _gridModel), _gridModel );
	}

	public void update() {
		++_elapsedFrames;
		_agent.advance();
	}
	
	@SuppressWarnings("unused")
	public void draw() {
		update();
		
		background(255);
		stroke( 128 );
		for (GridSquare square : _gridModel.get_gridSquareList()) {
			square.draw();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#mouseDragged()
	 */
	@Override
	public void mouseDragged() {
		super.mouseDragged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#mouseMoved()
	 */
	@Override
	public void mouseMoved() {
		super.mouseMoved();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#mousePressed()
	 */
	@Override
	public void mousePressed() {
		super.mousePressed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see processing.core.PApplet#mouseReleased()
	 */
	@Override
	public void mouseReleased() {
		super.mouseReleased();
	}

	@Override
	public void keyPressed() {
		super.keyPressed();
	}

	//
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "Search2DApp.Search2DApp" });
	}
}
