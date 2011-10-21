import java.util.ArrayList;
import processing.core.*;

public class Search2DApp extends PApplet {
	private static final long serialVersionUID = -3824555102005090780L;
	private static int RESOLUTION = 20;

	private float _elapsedFrames;
	private GridModel _gridModel;

	public void setup() {
		_elapsedFrames = 0;

		size(1200, 600);
		frameRate(60);
		background(0);

		_gridModel = new GridModel(width, height, width / RESOLUTION, this);
	}

	@SuppressWarnings("unused")
	public void draw() {
		++_elapsedFrames;
		background(255);
		noStroke();

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
