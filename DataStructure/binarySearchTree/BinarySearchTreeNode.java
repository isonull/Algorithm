package binarySearchTree;

import javax.swing.tree.TreeNode;

public class BinarySearchTreeNode<T extends Comparable<T>, U> {
	private BinarySearchTreeNode<T, U> leftChild;
	private BinarySearchTreeNode<T, U> rightChild;
	private BinarySearchTreeNode<T, U> parent;
	private final T key;
	private final U value;

	public BinarySearchTreeNode(T key, BinarySearchTreeNode<T, U> parent) {
		this.key = key;
		this.parent = parent;
		this.value = null;
	}

	public BinarySearchTreeNode(T key) {
		this.key = key;
		this.value = null;
	}

	public void setParent(BinarySearchTreeNode<T, U> parent) {
		this.parent = parent;
	}

	public void setLeftChild(BinarySearchTreeNode<T, U> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(BinarySearchTreeNode<T, U> rightChild) {
		this.rightChild = rightChild;
	}

	public T getKey() {
		return key;
	}

	public U getValue() {
		return value;
	}

	public BinarySearchTreeNode<T, U> getChildAt(int childIndex) {
		switch (childIndex) {
		case 0:
			return leftChild;
		case 1:
			return rightChild;
		default:
			return null;
		}
	}

	public int getChildCount() {
		return (leftChild == null ? 0 : 1) + (rightChild == null ? 0 : 1);
	}

	public BinarySearchTreeNode<T, U> getParent() {
		return parent;
	}

	public int getIndex(TreeNode node) {
		if (parent == null) {
			return 0;
		} else {
			if (node == node.getParent().getChildAt(0)) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}

}
