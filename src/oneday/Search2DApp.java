package oneday;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import oneday.Agent;
import oneday.GridModel;
import oneday.GridSquare;
import oneday.State;
import processing.core.*;


public class Search2DApp extends PApplet {
	private static final long serialVersionUID = -3824555102005090780L;
	private static int RESOLUTION = 60;
	
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
		
		


        int[][] board = {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				};
        


        int squareSize = 30;
        _gridModel = new GridModel(board[0].length, board.length, squareSize, this);
        
        // Use board[][] array to create pacman level
        for(int x = 0; x < board.length; ++x){
        	for(int y = 0; y < board[x].length; ++y) {
        		GridSquare square = _gridModel.getSquareAtGridPosition(y, x);
        		

    			square.setPermutable( true );
    			square._color = 128;
    			
    			
    			if( board[x][y] == 1) {
    				square.setPermutable( false );
    				square._color = 0;
    			}
        	}
        }
        
        // Resize the app to the size of the pacman board
        // The delay is due to incorrect re-sizing by eclipse on app start
        delay(4000);
        size( board[0].length * squareSize, board.length * squareSize );
        
        
        GridSquare initialState = _gridModel.getSquareAtGridPosition(14, 25);
		initialState._color = 255;
		
		GridSquare goalState = _gridModel.getSquareAtGridPosition(14, 15);		
		_agent = new Agent( new State(initialState, _gridModel), new State(goalState, _gridModel), _gridModel );
	}

	public void update() {
		++_elapsedFrames;
		
		if( _agent != null ) {
			for(int i = 0; i < 1; i++ ) {
				_agent.advance();		
			}
		}
		
		println("Agent reached goal:" + _agent.isAtGoal() );
	}
	
	@SuppressWarnings("unused")
	public void draw() {
		update();
			
		background(255);
		stroke( 225 );
		rectMode(CORNERS);
		for (GridSquare square : _gridModel.get_gridSquareList()) {
			square.draw(); 
		}
		
		GridSquare currentSquare = _agent.getCurrentSquare();
		if( currentSquare != null ) {
			PVector pos = currentSquare._position;
			fill(255, 0, 0);
			ellipse(currentSquare._center.x, currentSquare._center.y, 5, 5);
		}
		
		if( _agent.isAtGoal() ) {

			// Find a new square
			int x; 
			int y;
			GridSquare square;

			
			do {
				x = (int)random(width);
				y = (int)random(height);
				square = _gridModel.getSquareAtPixelPosition(x,y);
			} while ( !square.isPermutable() );
			
			delay(1000);
			resetAndSetNewGoal(x, y);
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
		resetAndSetNewGoal(mouseX, mouseY);
	}
	
	public void resetAndSetNewGoal(int x, int y) {
		for (GridSquare square : _gridModel.get_gridSquareList()) {
			if( square.isPermutable() ) {
				square._color = 90;
			}
		}
		
		GridSquare square = _gridModel.getSquareAtPixelPosition( x, y );
		if( !square.isPermutable() ) return; // Not reachable
		
		_agent.setGoal( new State( square, _gridModel) );
	}

	@Override
	public void keyPressed() {
		super.keyPressed();
	}

	private static PApplet INSTANCE;
	public static PApplet getInstance() {
		return Search2DApp.INSTANCE;
	} 
}
