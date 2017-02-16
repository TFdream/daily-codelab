package com.bytebeats.code.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BTNodeIteration {

	/**
	 * 前序遍历
	 * 先访问根结点，再访问左子结点，最后访问右子结点
	 * @param root
	 */
	public void preorder(BTNode root){
		//先访问根结点
		System.out.print(root.val+" ");
		if(root.left!=null){
			preorder(root.left);
		}
		if(root.right!=null){
			preorder(root.right);
		}
	}
	
	/**
	 * 中序遍历
	 * 先访问左子结点，再访问根结点，最后访问右子结点
	 * @param root
	 */
	public void middleorder(BTNode root){
		//先访问左子结点
		if(root.left!=null){
			middleorder(root.left);
		}
		System.out.print(root.val+" ");
		if(root.right!=null){
			middleorder(root.right);
		}
	}
	
	/**
	 * 后序遍历
	 * 先访问左子结点，再访问右子结点，最后访问根结点
	 * @param root
	 */
	public void lastorder(BTNode root){
		
		if(root.left!=null){
			lastorder(root.left);
		}
		if(root.right!=null){
			lastorder(root.right);
		}
		System.out.print(root.val+" ");
	}
	
	/**
	 * 层次遍历
	 * @param root
	 */
	public void layerorder(BTNode root){
		if(root==null){
			return;
		}
		//队列,先进先出
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			
			BTNode node = queue.poll();
			
			System.out.print(node.val+" ");
			
			if(node.left!=null){
				queue.add(node.left);
			}
			if(node.right!=null){
				queue.add(node.right);
			}
		}
	}
	
	/**
	 * 获取二叉树的高度
	 * @param root
	 * @return
	 */
	public int getHeight(BTNode root){
		if(root==null){
			return 0;
		}
		int left = getHeight(root.left);  
		int right = getHeight(root.right);
	    
		int h = (left>right?left:right)+1;
	    
	    return h;  
	}
	
	/**
	 * 获取二叉树结点的数量
	 * @param root
	 * @return
	 */
	public int getCount(BTNode root){
		if(root==null){
			return 0;
		}
		return getCount(root.left)+getCount(root.right)+1;
	}
	
	/**
	 * 判断两棵 二叉树 是否相等
	 * @param root1
	 * @param root2
	 * @return
	 */
	public boolean equals(BTNode root1, BTNode root2){
		if((root1==null && root2==null) ||root1.val == root2.val){
			return true;
		}
		
		if(equals(root1.left, root2.left) && equals(root1.right, root2.right)){
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		
		BTNode node8 = new BTNode(2);
		
		BTNode node4 = new BTNode(4, node8, null);
		BTNode node5 = new BTNode(8);
		
		BTNode node2 = new BTNode(6, node4, node5);
		
		BTNode node6 = new BTNode(12);
		BTNode node7 = new BTNode(16);
		
		BTNode node3 = new BTNode(14, node6, node7);
		
		BTNode root = new BTNode(10, node2, node3);
		
		BTNodeIteration it = new BTNodeIteration();
		System.out.println("前序遍历:");
		it.preorder(root);
		
		System.out.println("--------------");
		
		System.out.println("中序遍历:");
		it.middleorder(root);
		
		System.out.println("--------------");
		
		System.out.println("后序遍历:");
		it.lastorder(root);
		
		System.out.println("--------------");
		
		System.out.println("层次遍历:");
		it.layerorder(root);
		
		System.out.println("--------------");
		System.out.println("结点数量："+it.getCount(root));
		
		System.out.println("--------------");
		System.out.println("高度："+it.getHeight(root));
	}

}
