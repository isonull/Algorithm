package segmentsLinearRegressionGUI;

//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-01> 
//I release it under the Simplified BSD License.
public class Line implements Comparable<Line> {
	private double ma; // y=ax+b
	private double mb;
	private double mr; // Correlation Coefficient r
	private double mr2;
	private int mbeg; // related with dotSet index
	private int mend;

	public Line(double a, double b, double r, int beg, int end) {
		ma = a;
		mb = b;
		mr = r;
		mr2 = r * r;
		mbeg = beg;
		mend = end;
	}

	public double a() {
		return ma;
	}

	public double b() {
		return mb;
	}

	public double r() {
		return mr;
	}

	public double r2() {
		return mr2;
	}

	public int beg() {
		return mbeg;
	}

	public int end() {
		return mend;
	}

	public double calculateYbyX(double x) {
		return ma * x + mb;
	}

	@Override
	public String toString() {
		return ma + "x+" + mb + "r:" + mr + " " + mbeg + "-" + mend;
	}

	@Override
	public int compareTo(Line o) {
		return mbeg - o.mbeg;
	}

}
