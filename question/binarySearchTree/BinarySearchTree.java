package binarySearchTree;

public class BinarySearchTree<T extends Comparable<T>> {
	protected BinarySearchTreeNode<T> root;
	protected int size = 0;

	public boolean insert(BinarySearchTreeNode<T> node) {
		BinarySearchTreeNode<T> y = null;
		BinarySearchTreeNode<T> x = root;
		while (x != null) {
			y = x;
			if (node.getKey().compareTo(x.getKey()) < 0) {
				x = x.getChildAt(0);
			} else if (node.getKey().compareTo(x.getKey()) > 0) {
				x = x.getChildAt(1);
			} else {
				// TODO: replace the node;
				return false;
			}
		}
		node.setParent(y);
		if (y == null) {
			root = node;
			++size;
			return true;
		} else if (node.getKey().compareTo(y.getKey()) < 0) {
			y.setLeftChild(node);
			++size;
			return true;
		} else if (node.getKey().compareTo(y.getKey()) > 0) {
			y.setRightChild(node);
			++size;
			return true;
		} else {
			// TODO: replace the node but we do nothing here, value not
			// implemented;
			return false;
		}
	}

	public BinarySearchTreeNode<T> search(T k) {
		if (root == null) {
			return null;
		}
		BinarySearchTreeNode<T> x = root;
		while (x != null) {
			if (k.compareTo(x.getKey()) < 0) {
				x = x.getChildAt(0);
			} else if (k.compareTo(x.getKey()) > 0) {
				x = x.getChildAt(1);
			} else {
				return x;
			}
		}
		return null;
	}

	public BinarySearchTree<T> maximum(T k) {
		return null;
	}

	public BinarySearchTree<T> minimum(T k) {
		return null;
	}

	public void inorderTreeWalk() {
		inorderTreeWalkAux(root);
	}

	private void inorderTreeWalkAux(BinarySearchTreeNode<T> node) {
		if (node != null) {
			System.out.print("(");
			inorderTreeWalkAux(node.getChildAt(0));
			System.out.print(node.getKey());
			inorderTreeWalkAux(node.getChildAt(1));
			System.out.print(")");
		}
	}

}
