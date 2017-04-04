package segmentsLinearRegressionGUI;

//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-01> 
//I release it under the Simplified BSD License.
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Data mdata = null;

	public DisplayPanel(Data d) {
		mdata = d;
	}

	protected void setData(Data d) {
		mdata = d;
	}

	@Override
	protected void paintComponent(java.awt.Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (mdata == null)
			return;
		g.setColor(Color.BLACK);
		draw(g);
	}

	private void draw(java.awt.Graphics g) {

		ArrayList<Dot> dotSet = mdata.dotSet();
		ArrayList<Line> lineSet = mdata.lineSet();
		for (Dot d : dotSet) {
			g.drawOval((int) (d.x() * 10), (int) (d.y() * 10), 5, 5);
		}
		for (Line l : lineSet) {
			double begX = dotSet.get(l.beg()).x();
			double endX = dotSet.get(l.end()).x();
			g.drawLine((int) ((begX - 1) * 10), (int) (l.calculateYbyX(begX - 1) * 10), (int) ((endX + 1) * 10),
					(int) (l.calculateYbyX(endX + 1) * 10));
		}
	}

	public void display() {
		repaint();
	}
}
