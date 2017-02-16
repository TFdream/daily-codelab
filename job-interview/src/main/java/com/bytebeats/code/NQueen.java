package com.bytebeats.code;

/**
 * 8皇后问题
 * @author Ricky
 *
 */
public class NQueen {
	private int solveNum; // 方案总数
	private int queenNum;// 皇后个数，同时也是棋盘行列总数
	private int[] cols; // 定义cols数组，表示N列棋子摆放情况

	public NQueen(int queenNum) {
		this.queenNum = queenNum;
		this.cols = new int[queenNum];
	}

	public void solve() {
		
		getSolution(0);
		
		System.out.println(queenNum + "皇后问题有" + solveNum + "种摆放方法。");
	}

	// 核心函数
	private void getSolution(int n) {
		// 遍历该列所有不合法的行，并用rows数组记录，不合法即rows[i]=true
		boolean[] rows = new boolean[queenNum];
		for (int i = 0; i < n; i++) {
			rows[cols[i]] = true;
			int d = n - i;
			if (cols[i] - d >= 0)
				rows[cols[i] - d] = true;
			if (cols[i] + d <= queenNum - 1)
				rows[cols[i] + d] = true;

		}
		for (int i = 0; i < queenNum; i++) {
			// 判断该行是否合法
			if (rows[i])
				continue;
			// 设置当前列合法棋子所在行数
			cols[n] = i;
			// 当前列不为最后一列时
			if (n < queenNum - 1) {
				getSolution(n + 1);
			} else {
				solveNum++;
				// 打印棋盘信息
				printChessBoard();
			}
		}
	}

	private void printChessBoard() {

		System.out.println("第" + solveNum + "种走法:");

		for (int i = 0; i < queenNum; i++) {
			for (int j = 0; j < queenNum; j++) {
				if (i == cols[j]) {
					System.out.print("&");
				} else{
					System.out.print("+");
				}
				if(j!=queenNum-1){
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String args[]) {

		NQueen queen = new NQueen(5);
		queen.solve();
	}

}