package segmentsLinearRegressionGUI;

//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-01> 
//I release it under the Simplified BSD License.
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Data mdata;
	int numberOfLine;
	JButton grt;

	public GUI() {
		super("Window");
		numberOfLine = 1;
		mdata = new Data();
		setSize(1000, 1000);
		add(creatDisplayPanel(), BorderLayout.CENTER);
		add(creatControlPanel(), BorderLayout.SOUTH);
	}

	public JPanel creatControlPanel() {
		JPanel ctrl = new JPanel();
		ctrl.setLayout(new GridLayout(1, 0));
		JButton clr = new JButton("clear");
		grt = new JButton("generate " + numberOfLine + " line(s)");
		JButton plus = new JButton("+");
		JButton remo = new JButton("-");
		plus.addActionListener(l -> addLine());
		remo.addActionListener(l -> removeLine());
		clr.addActionListener(e -> clear());
		grt.addActionListener(e -> generate());
		ctrl.add(plus);
		ctrl.add(remo);
		ctrl.add(clr);
		ctrl.add(grt);
		return ctrl;
	}

	public JPanel creatDisplayPanel() {
		DisplayPanel disp = new DisplayPanel(mdata);
		disp.addMouseListener(new MouseHandler());
		return disp;
	}

	public void clear() {
		mdata = new Data();
		((DisplayPanel) this.getContentPane().getComponent(0)).setData(mdata);
		display();
	}

	public void generate() {
		mdata.generateBestFit(numberOfLine);
		display();
	}

	public void addLine() {
		mdata.generateBestFit(++numberOfLine);
		grt.setText("generate " + numberOfLine + " line(s)");
		display();
	}

	public void removeLine() {
		mdata.generateBestFit(--numberOfLine);
		grt.setText("generate " + numberOfLine + " line(s)");
		display();
	}

	public void display() {
		((DisplayPanel) this.getContentPane().getComponent(0)).display();
	}

	public static void main(String args[]) {
		GUI g = new GUI();
		g.setVisible(true);
	}

	private class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			mdata.addDot(((double) e.getX()) / 10, ((double) e.getY()) / 10);
			display();
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
}
