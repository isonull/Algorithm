package binarySearchTree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class BinarySearchTreeVisualisable<T extends Comparable<T>, U> extends BinarySearchTree<T, U> {

	private class nodeS {
		BinarySearchTreeNode<T, U> node;
		boolean leftDone = false;
		boolean selfDone = false;
		boolean rightDone = false;
		int X = -1;
		int Y = -1;

		public nodeS(BinarySearchTreeNode<T, U> node) {
			this.node = node;
			this.leftDone = node.getChildAt(0) == null;
			this.rightDone = node.getChildAt(1) == null;
		}
	}

	public Image visualise(int width, int height) {

		Image image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D graph = (Graphics2D) image.getGraphics();
		graph.setColor(Color.WHITE);

		// I hate that JAVA DO NOT HAVE THE NESTED METHOD!!!
		Stack<nodeS> stack = new Stack<>();

		int Xstep = width / (size + 1);
		int Ystep = height / (depth + 1);

		int X = Xstep;
		int Y = Ystep;

		int previousX = -1;
		int previousY = -1;

		stack.push(new nodeS(root));

		nodeS tempNodeS;
		while (!stack.isEmpty()) {
			tempNodeS = stack.peek();
			if (tempNodeS.X != -1 && tempNodeS.Y != -1 && previousX != -1 && previousY != -1) {
				graph.drawLine(tempNodeS.X, tempNodeS.Y, previousX, previousY);
				previousX = -1;
				previousY = -1;
			}
			if (!tempNodeS.leftDone) {
				tempNodeS.leftDone = true;
				stack.push(new nodeS(tempNodeS.node.getChildAt(0)));
				Y += Ystep;
				continue;
			}

			if (!tempNodeS.selfDone) {
				tempNodeS.X = X;
				tempNodeS.Y = Y;
				char[] charArray = tempNodeS.node.getKey().toString().toCharArray();
				graph.drawChars(charArray, 0, charArray.length, X, Y);
				tempNodeS.selfDone = true;
				X += Xstep;
				continue;
			}

			if (!tempNodeS.rightDone) {
				tempNodeS.rightDone = true;
				stack.push(new nodeS(tempNodeS.node.getChildAt(1)));
				Y += Ystep;
				continue;
			}

			previousX = tempNodeS.X;
			previousY = tempNodeS.Y;
			stack.pop();
			Y -= Ystep;
		}

		return image;
	}

}
