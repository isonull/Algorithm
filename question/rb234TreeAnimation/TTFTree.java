//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-10>
package rb234TreeAnimation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TTFTree {
	int numberOfPic = 0;
	private TTFNode root;

	public TTFTree() {
		root = new TTFNode();
		root.setLeaf(true);
		root.setN(0);
	}

	public void insert(char k) throws IOException {
		String labelStr = "Insert " + k;
		TTFNode x = root;
		boolean flag = true;

		while (flag) {
			generateJPG(this.visualize(1024, 668, 100, x, labelStr + " (Searching)"));
			if (x.getN() == 3) {
				TTFNode spNode1 = new TTFNode(x.isLeaf(), x.getKey(0), x.getChild(0), x.getChild(1));
				TTFNode spNode2 = new TTFNode(x.isLeaf(), x.getKey(2), x.getChild(2), x.getChild(3));
				if (x.getChild(0) != null)
					x.getChild(0).setParent(spNode1);
				if (x.getChild(0) != null)
					x.getChild(1).setParent(spNode1);
				if (x.getChild(0) != null)
					x.getChild(2).setParent(spNode2);
				if (x.getChild(0) != null)
					x.getChild(3).setParent(spNode2);
				if (x != root) {
					x.getParent().getPush(x.getKey(1), spNode1, spNode2);
					x = x.getParent();
				} else {
					TTFNode newRoot = new TTFNode(false, x.getKey(1), spNode1, spNode2);
					root = newRoot;
					x = root;
				}
				spNode1.setParent(x);
				spNode2.setParent(x);
				generateJPG(this.visualize(1024, 668, 100, x, labelStr + " ( Split and push)"));
			}
			if (!x.isLeaf()) {
				if (k < x.getKey(0)) {
					x = x.getChild(0);
				} else if (k < x.getKey(1)) {
					x = x.getChild(1);
				} else if (k < x.getKey(2)) {
					x = x.getChild(2);
				} else {
					x = x.getChild(3);
				}
			}
			if (x.isLeaf() && x.getN() != 3) {
				x.getInsert(k);
				flag = false;
			}
		}
		generateJPG(this.visualize(1024, 668, 100, x, labelStr + " ( Done)"));
	}

	public TTFNode search(char k) throws IOException {
		String labelStr = "Search " + k;
		TTFNode tempNode = root;
		boolean flag = true;
		int nextChild;
		while (flag) {

			nextChild = 0;
			for (int i = 0; i < tempNode.getN(); ++i) {
				if (k == tempNode.getKey(i)) {
					generateJPG(this.visualize(1024, 668, 100, tempNode, labelStr + " (Done)"));
					return tempNode;
				} else if (k > tempNode.getKey(i)) {
					generateJPG(this.visualize(1024, 668, 100, tempNode, labelStr));
					++nextChild;
				}
			}
			flag = !tempNode.isLeaf();
			tempNode = tempNode.getChild(nextChild);
		}
		generateJPG(this.visualize(1024, 668, 100, tempNode, labelStr + " (Fail)"));
		return null;
	}

	public TTFNode maximum(TTFNode x) throws IOException {
		String labelStr = "Find maximum (in this subtree)";

		while (!x.isLeaf()) {
			generateJPG(this.visualize(1024, 668, 100, x, labelStr + " (Search)"));
			x = x.getChild(x.getN());
		}
		generateJPG(this.visualize(1024, 668, 100, x, labelStr + " (Done)"));
		return x;
	}

	public TTFNode predecessor(char k) throws IOException {
		String labelStr = "Find predecessor of " + k;
		TTFNode x = search(k);
		int ith = 0;
		while (k > x.getKey(ith)) {
			++ith;
		}
		if (!x.isLeaf()) {
			generateJPG(this.visualize(1024, 668, 100, x, labelStr + " (Find max in left label subtree)"));
			TTFNode node = maximum(x.getChild(ith));
			generateJPG(this.visualize(1024, 668, 100, node, labelStr + " (Done)"));
			return node;
		} else if (ith != 0) {
			generateJPG(this.visualize(1024, 668, 100, x, labelStr + " (Done)"));
			return x;
		}
		generateJPG(this.visualize(1024, 668, 100, x, labelStr + " (Fail)"));
		return null;
	}

	public BufferedImage visualize(int w, int h, int h2, TTFNode node, String str) throws IOException {
		ArrayList<ArrayList<TTFNode>> treeInfo = new ArrayList<>();
		ArrayList<TTFNode> tempNodeList;
		TTFNode tempNode;
		treeInfo.add((new ArrayList<TTFNode>()));
		treeInfo.get(0).add(root);
		boolean flag = true;
		for (int i = 0; flag; ++i) {
			tempNodeList = new ArrayList<>();
			for (int j = 0; j < treeInfo.get(i).size(); ++j) {
				tempNode = treeInfo.get(i).get(j);
				if (tempNode != null) {
					if (tempNode.isLeaf()) {
						flag = false;
						break;
					} else {
						if (tempNode.getChild(0) != null)
							tempNodeList.add(tempNode.getChild(0));
						if (tempNode.getChild(1) != null)
							tempNodeList.add(tempNode.getChild(1));
						if (tempNode.getChild(2) != null)
							tempNodeList.add(tempNode.getChild(2));
						if (tempNode.getChild(3) != null)
							tempNodeList.add(tempNode.getChild(3));
					}
				}
			}
			if (flag) {
				treeInfo.add(tempNodeList);
			}
		}

		int LHeight = h / (treeInfo.size() + 1);
		int LWidth = 1;
		int nextLWidth = 1;
		int posX;
		int posY = LHeight / 2;
		int nextPosX;
		int diameter = w / (treeInfo.get(treeInfo.size() - 1).size() + 1);
		;
		BufferedImage image = new BufferedImage(w, h + h2, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graph = (Graphics2D) image.getGraphics();
		BufferedImage tempImage;
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, w, h + h2);

		for (int i = 0; i < treeInfo.size(); ++i) {
			LWidth = w / (treeInfo.get(i).size() + 1);
			if (i < treeInfo.size() - 1) {
				nextLWidth = w / (treeInfo.get(i + 1).size() + 1);
			} else {
				nextLWidth = 0;
			}
			posX = LWidth - diameter / 2;
			nextPosX = nextLWidth - diameter / 2;
			tempNodeList = treeInfo.get(i);
			for (int j = 0; j < tempNodeList.size(); ++j) {
				tempNode = tempNodeList.get(j);
				tempImage = tempNode.generateImage(diameter, diameter / 3, tempNode == node);
				if (!tempNode.isLeaf()) {
					graph.setColor(Color.BLACK);
					for (int k = 0; k <= tempNode.getN(); ++k) {
						graph.drawLine(posX + diameter / 2, posY + diameter / 6, nextPosX + diameter / 2,
								posY + LHeight + diameter / 6);
						nextPosX += nextLWidth;
					}
				}
				graph.drawImage(tempImage, posX, posY, null);
				posX += LWidth;
			}
			posY += LHeight;
		}

		graph.setColor(Color.BLACK);
		graph.setFont(new Font(graph.getFont().getFontName(), graph.getFont().getStyle(), w / 30));
		graph.drawString(str, w / 10, h);
		graph.dispose();

		return image;

	}

	private void generateJPG(BufferedImage image) throws IOException {
		File file = new File("TTFTreePicture/" + numberOfPic + ".jpg");
		ImageIO.write(image, "jpg", file);
		++numberOfPic;
	}
}
