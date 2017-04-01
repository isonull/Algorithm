package binarySearchTree;

import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class BinarySearchTreeNode<T extends Comparable<T>> implements TreeNode {
	private BinarySearchTreeNode<T> leftChild;
	private BinarySearchTreeNode<T> rightChild;
	private BinarySearchTreeNode<T> parent;
	private final T key;
	private final int value = -1;

	public BinarySearchTreeNode(T key, BinarySearchTreeNode<T> parent) {
		this.key = key;
		this.parent = parent;
	}

	public BinarySearchTreeNode(T key) {
		this.key = key;
	}

	public void setParent(BinarySearchTreeNode<T> parent) {
		this.parent = parent;
	}

	public void setLeftChild(BinarySearchTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(BinarySearchTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public T getKey() {
		return key;
	}

	@Override
	public BinarySearchTreeNode<T> getChildAt(int childIndex) {
		switch (childIndex) {
		case 0:
			return leftChild;
		case 1:
			return rightChild;
		default:
			return null;
		}
	}

	@Override
	public int getChildCount() {
		return (leftChild == null ? 0 : 1) + (rightChild == null ? 0 : 1);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
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

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}

	@Override
	public Enumeration children() {
		return null;
	}

}
