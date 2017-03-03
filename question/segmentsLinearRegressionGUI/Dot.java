package segmentsLinearRegressionGUI;

//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-01> 
//I release it under the Simplified BSD License.
public class Dot implements Comparable<Dot> {
	private double mx;
	private double my;

	public Dot(double x, double y) {
		mx = x;
		my = y;
	}

	public double x() {
		return mx;
	}

	public double y() {
		return my;
	}

	@Override
	public int compareTo(Dot o) {
		double d = mx - o.mx;
		if (d == 0)
			return 0;
		if (d > 0)
			return 1;
		else
			return -1;
	}
}
