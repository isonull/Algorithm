//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-10>
package rb234TreeAnimation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TTFNode {
	private boolean leaf = false;
	private int n = 0;
	private char[] key = { Character.MAX_VALUE, Character.MAX_VALUE, Character.MAX_VALUE };
	private TTFNode[] child = new TTFNode[4];
	private TTFNode parent;

	public TTFNode() {
		leaf = false;
		n = 0;
	}

	public TTFNode(boolean l, char k, TTFNode child1, TTFNode child2) {
		leaf = l;
		n = 1;
		key[0] = k;
		child[0] = child1;
		child[1] = child2;
	}

	public void getPush(char k, TTFNode child1, TTFNode child2) {
		assert (n < 3);
		if (k < key[0]) {
			key[2] = key[1];
			key[1] = key[0];
			key[0] = k;
			child[3] = child[2];
			child[2] = child[1];
			child[0] = child1;
			child[1] = child2;
		} else if (k < key[1]) {
			key[2] = key[1];
			key[1] = k;
			child[3] = child[2];
			child[1] = child1;
			child[2] = child2;
		} else {
			key[2] = k;
			child[2] = child1;
			child[3] = child2;
		}
		++n;
	}

	public void getInsert(char k) {
		assert (this.isLeaf() && n < 3);
		if (k < key[0]) {
			key[2] = key[1];
			key[1] = key[0];
			key[0] = k;
		} else if (k < key[1]) {
			key[2] = key[1];
			key[1] = k;
		} else {
			key[2] = k;
		}
		++n;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public int getN() {
		return n;
	}

	public char getKey(int i) {
		return key[i];
	}

	public TTFNode getChild(int i) {
		return child[i];
	}

	public TTFNode getParent() {
		return parent;
	}

	public void setLeaf(boolean l) {
		leaf = l;
	}

	public void setN(int i) {
		n = i;
	}

	public void setParent(TTFNode p) {
		parent = p;
	}

	public BufferedImage generateImage(int w, int h, boolean s) throws IOException {
		int ow = w / 3;
		int bw = (w - n * ow) / 2;
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graph = (Graphics2D) image.getGraphics();
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, w, h);
		if (n == 0) {
			if (s) {
				graph.setColor(Color.GREEN);
				graph.fillOval(ow, 0, w - 2 * ow, h);
				graph.setColor(Color.RED);
				graph.fillOval(ow * 11 / 10, h / 10, w - 2 * ow * 11 / 10, h * 8 / 10);
			} else {
				graph.setColor(Color.RED);
				graph.fillOval(ow, 0, w - ow, h);
			}
			return image;
		}

		Font font = graph.getFont();
		char[] ch = new char[1];
		graph.setFont(new Font(font.getFontName(), font.getStyle(), w / 3));
		if (s) {
			graph.setColor(Color.GREEN);
			graph.fillOval(bw, 0, w - 2 * bw, h);
			graph.setColor(Color.RED);
			graph.fillOval(bw * 11 / 10, h / 10, w - 2 * bw * 11 / 10, h * 8 / 10);
		} else {
			graph.setColor(Color.RED);
			graph.fillOval(bw, 0, w - 2 * bw, h);
		}
		graph.setColor(Color.GREEN);
		for (int i = 0; i < n; ++i) {
			ch[0] = key[i];
			graph.drawChars(ch, 0, 1, (bw + i * ow) + w / 20, h * 9 / 10);
		}
		graph.dispose();
		return image;
	}
}
