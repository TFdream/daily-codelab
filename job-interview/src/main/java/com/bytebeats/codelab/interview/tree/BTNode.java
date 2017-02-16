package com.bytebeats.codelab.interview.tree;

public class BTNode {
	int val;
	BTNode left;	//左子树
	BTNode right;	//右子树
	public BTNode(){
		
	}
	public BTNode(int val){
		this.val = val;
	}
	public BTNode(int val, BTNode left, BTNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
}
