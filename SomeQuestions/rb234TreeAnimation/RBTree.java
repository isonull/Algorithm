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

public class RBTree {
	private RBNode root;
	private RBNode nil;
	private int numberOfPic = 1;

	public RBTree() {
		nil = new RBNode();
		nil.setColor(true);
		root = nil;
	}

	public void insert(char k) throws IOException {
		String labelStr = "Insert " + k;

		RBNode x = root;
		RBNode y = nil;

		while (x != nil) {
			generateJPG(visualize(1024, 668, 100, x, labelStr + " (Search)"));
			y = x;
			if (x.getKey() > k) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}
		}

		RBNode z = new RBNode(k);
		z.setParent(y);
		if (y == nil) {
			root = z;
		} else if (z.getKey() < y.getKey()) {
			y.setLeft(z);
		} else {
			y.setRight(z);
		}
		z.setLeft(nil);
		z.setRight(nil);
		z.setColor(false);

		RBNode insNode = z;

		y = null;

		while (z.getParent().getColor() == false) {
			generateJPG(visualize(1024, 668, 100, z, labelStr + " (Fix)"));
			if (z.getParent() == z.getParent().getParent().getLeft()) {
				y = z.getParent().getParent().getRight();// y is uncle of z
				if (y.getColor() == false) {
					// case 1 z's uncle y is red
					z.getParent().setColor(true);
					y.setColor(true);
					z.getParent().getParent().setColor(false);
					z = z.getParent().getParent();

				} else {
					if (z == z.getParent().getRight()) {
						// case 2 z's uncle y is black and z is a right child
						z = z.getParent();
						leftRotate(z);
					}
					// case 3 z's uncle y is black and z is a left child
					z.getParent().setColor(true);
					z.getParent().getParent().setColor(false);
					rightRotate(z.getParent().getParent());
				}
			} else {
				y = z.getParent().getParent().getLeft();
				if (y.getColor() == false) {

					z.getParent().setColor(true);
					y.setColor(true);
					z.getParent().getParent().setColor(false);
					z = z.getParent().getParent();

				} else {
					if (z == z.getParent().getLeft()) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(true);
					z.getParent().getParent().setColor(false);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor(true);
		generateJPG(visualize(1024, 668, 100, insNode, labelStr + " (Done)"));
	}

	public void leftRotate(RBNode x) {
		RBNode y = x.getRight(); // set y
		x.setRight(y.getLeft()); // turn y's left tree to x's right tree
		if (y.getLeft() != nil) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent()); // link x's parent to y
		if (x.getParent() == nil) {
			root = y;
		} else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		} else {
			x.getParent().setRight(y);
		}
		y.setLeft(x); // put x on y's left
		x.setParent(y);
	}

	public void rightRotate(RBNode x) {
		RBNode y = x.getLeft();
		x.setLeft(y.getRight());
		if (y.getRight() != nil) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == nil) {
			root = y;
		} else if (x == x.getParent().getRight()) {
			x.getParent().setRight(y);
		} else {
			x.getParent().setLeft(y);
		}
		y.setRight(x);
		x.setParent(y);
	}

	public void inOrderWalk() {// not implied visualise.
		inOrderWalkAUX(root);
	}

	public void inOrderWalkAUX(RBNode x) { // not implied visualise.
		if (x != nil) {
			System.out.print("(");
			inOrderWalkAUX(x.getLeft());
			System.out.print(x.getKey());
			inOrderWalkAUX(x.getRight());
			System.out.print(")");
		}
	}

	public RBNode maximum(RBNode x) throws IOException {
		String labelStr = "Find maximum of " + x.getKey();
		while (x.getRight() != nil) {
			generateJPG(visualize(1024, 668, 100, x, labelStr + " (Search)"));
			x = x.getRight();
		}
		generateJPG(visualize(1024, 668, 100, x, labelStr + " (Done)"));
		return x;
	}

	public RBNode predecessor(RBNode x) throws IOException {
		String labelStr = "Find predecessor of " + x.getKey();
		if (x == nil) {
			generateJPG(visualize(1024, 668, 100, x, labelStr + " (Fail)"));
			return nil;
		}

		if (x.getLeft() != nil) { // fix here for search maximum
			return x.getLeft().maximum();
		}
		RBNode y = x.getParent();
		while (y != nil && x == y.getLeft()) {
			generateJPG(visualize(1024, 668, 100, x, labelStr + " (Search left parent)"));
			x = y;
			y = y.getParent();
		}

		if (x != nil) {
			generateJPG(visualize(1024, 668, 100, y, labelStr + " (Done)"));
		} else {
			generateJPG(visualize(1024, 668, 100, y, labelStr + " (Fail)"));
		}
		return y;
	}

	public RBNode predecessor(char k) throws IOException {
		String labelStr = "Find predecessor of " + k;
		generateJPG(visualize(1024, 668, 100, null, labelStr + " (Use search to find " + k + " first"));
		return predecessor(search(k));
	}

	public RBNode search(char k) throws IOException {
		String labelStr = "Search " + k;
		RBNode tempNode = root;
		while (tempNode != nil && tempNode.getKey() != k) {
			generateJPG(visualize(1024, 668, 100, tempNode, labelStr));
			if (k < tempNode.getKey())
				tempNode = tempNode.getLeft();
			else
				tempNode = tempNode.getRight();
		}
		if (tempNode != nil) {
			generateJPG(visualize(1024, 668, 100, tempNode, labelStr + " (Done)"));
		} else {
			generateJPG(visualize(1024, 668, 100, tempNode, labelStr + " (Fail)"));
		}
		return tempNode;
	}

	public BufferedImage visualize(int w, int h, int h2, RBNode node, String str) {
		ArrayList<ArrayList<RBNode>> treeInfo = new ArrayList<ArrayList<RBNode>>();
		treeInfo.add((new ArrayList<RBNode>()));
		treeInfo.get(0).add(root);

		boolean allNil = false;
		ArrayList<RBNode> tempNodeList;
		RBNode tempNode;
		int numberOfLevelNode;

		for (int i = 0; !allNil; ++i) {
			allNil = true;
			tempNodeList = new ArrayList<>();
			numberOfLevelNode = (int) Math.pow(2, i);

			for (int j = 0; j < numberOfLevelNode; ++j) {
				tempNode = treeInfo.get(i).get(j);
				if (tempNode != nil && tempNode != null) {
					allNil = false;
					tempNodeList.add(tempNode.getLeft());
					tempNodeList.add(tempNode.getRight());
				} else {
					tempNodeList.add(null);
					tempNodeList.add(null);
				}
			}
			if (!allNil) {
				treeInfo.add(tempNodeList);
			}
		}

		int LHeight = h / (treeInfo.size() + 1);
		int LWidth = 1;
		int nextLWidth = 1;
		int posX;
		int posY = LHeight;
		int nextPosX;
		int diameter = (int) (1.25 * w / Math.pow(2, treeInfo.size()));
		diameter = Math.max(diameter, 20);
		diameter = Math.min(diameter, 200);
		char[] ch = new char[1];
		BufferedImage image = new BufferedImage(w, h + h2, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graph = (Graphics2D) image.getGraphics();
		graph.setFont(new Font(graph.getFont().getFontName(), graph.getFont().getStyle(), diameter * 4 / 5));
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, w, h + h2);
		for (int i = 0; i < treeInfo.size(); ++i) {
			numberOfLevelNode = (int) Math.pow(2, i);
			LWidth = w / (numberOfLevelNode + 1);
			nextLWidth = w / (numberOfLevelNode * 2 + 1);
			posX = LWidth;
			nextPosX = nextLWidth;
			for (int j = 0; j < Math.pow(2, i); ++j) {
				tempNode = treeInfo.get(i).get(j);
				if (tempNode != null) {
					if (tempNode == nil) {
						graph.setColor(Color.GRAY);
						graph.fillOval(posX - diameter / 2, posY - diameter / 2, diameter, diameter);
					} else {
						graph.setColor(Color.BLACK);
						graph.drawLine(posX, posY, nextPosX, posY + LHeight);
						graph.drawLine(posX, posY, nextPosX + nextLWidth, posY + LHeight);
						if (tempNode == node) {
							graph.setColor(Color.GREEN);
							graph.fillOval((int) (posX - diameter * 0.65), (int) (posY - diameter * 0.65),
									(int) (1.30 * diameter), (int) (1.30 * diameter));
						}
						if (tempNode.getColor() == false) {
							graph.setColor(Color.RED);
						} else {
							graph.setColor(Color.BLACK);
						}
						graph.fillOval(posX - diameter / 2, posY - diameter / 2, diameter, diameter);
						ch[0] = tempNode.getKey();
						graph.setColor(Color.GREEN);
						graph.drawChars(ch, 0, 1, posX - diameter / 4, posY + diameter * 2 / 5);
					}
				}
				posX += LWidth;
				nextPosX += nextLWidth * 2;
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
		File file = new File("RBTreePicture/" + numberOfPic + ".jpg");
		ImageIO.write(image, "jpg", file);
		++numberOfPic;
	}
}
