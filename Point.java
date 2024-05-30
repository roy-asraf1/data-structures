package matala2;
public class Point {
	private double x,y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point(Point p) {
		this.x = p.x;              
		this.y = p.y;
	}
	public double distance(Point p) {
		double xDis = x - p.x;
		double yDis = y - p.y;
		return Math.sqrt(xDis*xDis + yDis*yDis);
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
}