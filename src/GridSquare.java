import processing.core.PApplet;
import processing.core.PVector;

public class GridSquare {
	
	// Square Info
	public PVector			_position;
	public PVector 			_gridPosition;
	public int				_size;
	public PVector			_center;
	
	// Color
	public int				 __color;
	
	// Reference to Processing
	private PApplet			app;
	
	public GridSquare(float xpos, float ypos, int row, int column, int size, PApplet appRef) {
		app = appRef;
		_position = new PVector(xpos, ypos);
		_gridPosition = new PVector(row, column);
		_size = size;
		_center = new PVector(_position.x + _size/2, _position.y + _size/2);
		__color = (int)appRef.random(255);
	}	
	
	public void draw() {
		// Set color based on value - our values start at zero, so we say (white) - value = color
		app.fill( app.random( 255 ) );
		app.rect(_position.x, _position.y, _position.x+_size, _position.y+_size);
//		app.draw();
	}
}
