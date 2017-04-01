package binarySearchTree;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {
	public static void main(String args[]) throws IOException {
		BinarySearchTreeVisualisable<Integer> tree = new BinarySearchTreeVisualisable<>();
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.insert(new BinarySearchTreeNode<Integer>((int) Math.floor(Math.random() * 100)));
		tree.inorderTreeWalk();
		System.out.println("");
		ImageIO.write((RenderedImage) tree.visualise(1000, 1000), "png", new File("/Users/zijunyan/Desktop/1.png"));
	}
}
