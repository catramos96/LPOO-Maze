package maze.logi;

public class Point {
	private int x;
	private int y;

	public Point(){
		x = 0;
		y = 0;
	};
	
	public Point(int xi, int yi) {
		x = xi;
		y = yi;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setXY(int new_x,int new_y){
		x = new_x;
		y = new_y;
	}
	
	public void setX(int new_x) {
		x = new_x;
	}

	public void setY(int new_y) {
		y = new_y;
	}
}
