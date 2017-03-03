//I, Zijun Joe Yan zy275, student of Algorithms at the University of Cambridge, am the author of this code and on <2017-02-10>
package rb234TreeAnimation;

public class RBNode {
	private char key;
	private boolean color; // false-red true-black
	private RBNode parent;
	private RBNode left;
	private RBNode right;

	public RBNode(char k, boolean c, RBNode p, RBNode l, RBNode r) {
		key = k;
		color = c;
		parent = p;
		left = l;
		right = r;
	}

	public RBNode(char k) {
		key = k;
	}

	protected RBNode() {// constructor for nil
	}

	public char getKey() {
		return key;
	}

	public boolean getColor() {
		return color;
	}

	public RBNode getParent() {
		return parent;
	}

	public RBNode getLeft() {
		return left;
	}

	public RBNode getRight() {
		return right;
	}

	public void setColor(boolean c) {
		color = c;
	}

	public void setParent(RBNode p) {
		parent = p;
	}

	public void setLeft(RBNode l) {
		left = l;
	}

	public void setRight(RBNode r) {
		right = r;
	}

	public void inOrderWalk() {
		if (this != null) {
			left.inOrderWalk();
			System.out.print(key);
			right.inOrderWalk();
		}
	}

	public RBNode search(char ch) {
		RBNode tempNode = this;
		while (tempNode != null && key != ch) {
			if (ch < key)
				tempNode = tempNode.left;
			else
				tempNode = tempNode.right;
		}
		return tempNode;
	}

	public RBNode minimum() {
		RBNode tempNode = this;
		while (tempNode.left != null) {
			tempNode = tempNode.left;
		}
		return tempNode;
	}

	public RBNode maximum() {
		RBNode tempNode = this;
		while (tempNode.right != null) {
			tempNode = tempNode.right;
		}
		return tempNode;
	}

}
