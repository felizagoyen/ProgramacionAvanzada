package jerarquias;

public class Punto {
	private Double x;
	private Double y;
	private Double z;
	private static Punto punto = new Punto();
	
	private Punto() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public static Punto getPunto() {
		return punto;
	}
	
	public Double getX() {
		return x;
	}
	
	public Double getY() {
		return y;
	}
	
	public Double getZ() {
		return z;
	}
	
	public void setX(Double x) {
		this.x = x;
	}
	
	public void setY(Double y) {
		this.y = y;
	}
	
	public void setZ(Double z) {
		this.z = z;
	}
}
