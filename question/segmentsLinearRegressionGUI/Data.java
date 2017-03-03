package segmentsLinearRegressionGUI;

//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-01> 
//I release it under the Simplified BSD License.
import java.util.ArrayList;
import java.util.Collections;

public class Data {

	private ArrayList<Dot> dotSet;
	private ArrayList<Line> lineSet;

	public Data() {
		dotSet = new ArrayList<Dot>();
		lineSet = new ArrayList<Line>();
	}

	public void addDot(double x, double y) {
		dotSet.add(new Dot(x, y));
	}

	public void sortDotSet() {
		Collections.sort(dotSet);
	}

	public void generateBestFit(int num) {
		if (dotSet.size() == 0)
			return;
		sortDotSet();
		ArrayList<ArrayList<Line>> memo = new ArrayList<ArrayList<Line>>();
		for (int i = 0; i < dotSet.size(); ++i) {
			memo.add(new ArrayList<Line>(i + 1));
		}
		generateBestFitAux(memo);
		if (num > 0 && num <= dotSet.size())
			lineSet = memo.get(num - 1);
		else if (num <= 0)
			lineSet = new ArrayList<Line>();
		else
			lineSet = memo.get(dotSet.size() - 1);
	}

	private void generateBestFitAux(ArrayList<ArrayList<Line>> memo) {
		for (int i = 0; i < dotSet.size(); ++i) {
			memo.get(dotSet.size() - 1).add(calculateLine(i, i));
		}
		ArrayList<Line> tempLineArray1;
		ArrayList<Line> tempLineArray2;
		double max = -100;
		Line tempLine;
		Line bestLine = null;
		int bestIndex = 0;
		for (int n = dotSet.size() - 1; n > 0; --n) {
			tempLineArray1 = memo.get(n);
			max = -100;
			for (int i = 0; i < n; ++i) {
				tempLine = calculateLine(tempLineArray1.get(i).beg(), tempLineArray1.get(i + 1).end());
				double eval = tempLine.r2() - tempLineArray1.get(i).r2() - tempLineArray1.get(i + 1).r2();
				if (max < eval) {
					max = eval;
					bestLine = tempLine;
					bestIndex = i;
				}
			}
			tempLineArray2 = memo.get(n - 1);
			for (int i = 0; i < bestIndex; ++i) {
				tempLineArray2.add(tempLineArray1.get(i));
			}
			tempLineArray2.add(bestLine);
			for (int i = bestIndex + 2; i < n + 1; ++i) {
				tempLineArray2.add(tempLineArray1.get(i));
			}
		}
	}

	public boolean isSatisfied(ArrayList<Line> lineSet) {
		double sr = 0;
		for (Line l : lineSet) {
			sr += l.r2();
		}
		return sr > 0.9 * lineSet.size();
	}

	public Line calculateLine(int x, int y) {
		if (x == y) {
			return new Line(0, dotSet.get(x).y(), 1, x, y);
		}
		double n = y - x + 1;
		double sx = 0;
		double sy = 0;
		double sxy = 0;
		double sx2 = 0;
		double sy2 = 0;
		for (int i = x; i < y + 1; ++i) {
			Dot dt = dotSet.get(i);
			sx += dt.x();
			sy += dt.y();
			sxy += dt.x() * dt.y();
			sx2 += dt.x() * dt.x();
			sy2 += dt.y() * dt.y();
		}
		double a = (n * sxy - sx * sy) / (n * sx2 - sx * sx);
		Line li = new Line(a, (sy - a * sx) / n,
				(sxy - sx * sy / n) / (Math.sqrt((sx2 - sx * sx / n) * (sy2 - sy * sy / n))), x, y);
		return li;
	}

	public ArrayList<Dot> dotSet() {
		return dotSet;
	}

	public ArrayList<Line> lineSet() {
		return lineSet;
	}

}
