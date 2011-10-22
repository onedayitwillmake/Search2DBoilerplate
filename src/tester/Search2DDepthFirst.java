package tester;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import oneday.Agent;
import oneday.GridModel;
import oneday.GridSquare;
import oneday.Search2DApp;
import oneday.State;
import processing.core.*;


public class Search2DDepthFirst extends PApplet {
	private static final long serialVersionUID = -3824555102005090780L;
	private static int RESOLUTION = 50;
	
	private float _elapsedFrames;
	
	private GridModel _gridModel;
	private Agent _agent;
	public void setup() {
		INSTANCE = this;
		_elapsedFrames = 0;

		size(1200, 600);
		frameRate(60);
		background(0);
		noStroke();
		noSmooth();

		_gridModel = new GridModel(width, height, width / RESOLUTION, this);
		
		GridSquare initialState = _gridModel.getSquareAtGridPosition(0, 0);
		initialState._color = 255;
		
		GridSquare goalSquare = _gridModel.getSquareAtPixelPosition( width / 2, height / 2 );
		goalSquare._color = 128;
		
		// Randomly make some not passable
		for (GridSquare square : _gridModel.get_gridSquareList()) {
			if( random(1) < 0.25 && square != goalSquare && square != initialState ) {
				square.setPermutable( false );
				square._color = 0;
			}
		}
		
		_agent = new Agent( new State(initialState, _gridModel), new State(goalSquare, _gridModel), _gridModel );
	}

	public void update() {
		++_elapsedFrames;
		
		for(int i = 0; i < 10; i++ ) {
			_agent.advance();
		}
	}
	
	@SuppressWarnings("unused")
	public void draw() {
		update();
			
		background(255);
		stroke( 128 );
		for (GridSquare square : _gridModel.get_gridSquareList()) {
			square.draw();
		}
		
		GridSquare currentSquare = _agent.getCurrentSquare();
		if( currentSquare != null ) {
			PVector pos = currentSquare._position;
			fill(255, 0, 0);
			ellipse(currentSquare._center.x, currentSquare._center.y, 5, 5);
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
		
		for (GridSquare square : _gridModel.get_gridSquareList()) {
			if( square.isPermutable() ) {
				square._color = 90;
			}
		}
		
		_agent.setGoal( new State( _gridModel.getSquareAtPixelPosition( mouseX, mouseY), _gridModel) );
	}

	@Override
	public void keyPressed() {
		super.keyPressed();
	}

	private static PApplet INSTANCE;
	public static PApplet getInstance() {
		return Search2DDepthFirst.INSTANCE;
	} 
}
