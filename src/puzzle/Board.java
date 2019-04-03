package puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board
{
	private int N;
	private int[] board;

	public Board(int[][] blocks)
	{
		this.N = blocks.length;
		board = new int[N*N];
		int row = 0;
		for(int i = 0;i<N;i++) {
			for(int j = 0; j<N;j++) {
				board[row+j]=blocks[i][j];
			}
			row = row+N;
		}
	}
	public Board(int[] board) {
		this.board = board;
		this.N = (int) Math.sqrt(board.length);
	}

	public int size() {
		return N;
	}
	
	public int hamming() {
		int count = 0;
		for(int i=0;i<this.size()*this.size()-1;i++) {
			if(this.board[i] != i+1) {
				count++;
			}
		}
		return count;//the number of blocks out of place not counting the zero
	}
	
	public int manhattan()
	{
		//sum of Manhattan distances between blocks and goal
		int sum=0;
		for(int i=0; i<board.length;i++) {
			sum += distance(board[i]);
		}
		return sum;
	}

	public boolean isSolvable()
	{ 
		if(this.isGoal())
			return true;
		boolean solvable = false;
		if(this.N%2==1) {
			//implement if board size is odd inversions
			solvable = (inversions()%2 != 1);
		}else {
			//implement if board size is even blank row plus inversions, goal board is 3
			if(N==2) {
				solvable = ((find(0)/N+inversions())%3==1);
			}else solvable = ((find(0)/N+inversions())%3 == 0);
		}
		return solvable;
	}
	private int inversions() {
		int count = 0;
		for(int i = 0; i<board.length-1; i++) {
			if(board[i]==0)
				continue;
			
			for(int j=i+1; j<board.length;j++) {
				if(board[j]==0)
					continue;
				if(board[i]>board[j])
					count++;
			}
		}
		
		return count;
	}
	
	public Iterable<Board> neighbors(){
		
		Queue<Board> queue = new Queue<Board>();
		
		int space = find(0);
		int row = space/N;
		int col = space%N;
		int[] theNeighbors = hasNeighbors(space);
		if(theNeighbors[0]==1) {
			queue.enqueue(swap(row,col,row-1,col));
		}
		if(theNeighbors[1]==1) {
			queue.enqueue(swap(row,col-1,row,col));
		}
		if(theNeighbors[2]==1) {
			queue.enqueue(swap(row,col,row,col+1));
		}
		if(N<3)
			return queue;
		if(theNeighbors[3]==1) {
			queue.enqueue(swap(row,col,row+1,col));
		}
		
		return queue;
	}


	private Board swap(int row1, int col1, int row2, int col2)
	{
		//swap a number with the zero
		Board board2 = new Board(board.clone());
		board2.setTile(row1, col1, board[row2*N+col2]);
		board2.setTile(row2, col2, board[row1*N+col1]);
		
		return board2;
	}
	public Board twin() {
		int space = find(0);
		int row = space/N;
		int col = space%N;
		int[] theNeighbors = hasNeighbors(space);
		 if(theNeighbors[0]==1) {
				return (swap(row,col,row-1,col));
			}
			if(theNeighbors[1]==1) {
				return (swap(row,col-1,row,col));
			}
			if(theNeighbors[2]==1) {
				return (swap(row,col,row,col+1));
			}
			if(theNeighbors[3]==1) {
				return (swap(row,col,row+1,col));
			}
			return null;
	}
		


	private int[] hasNeighbors(int space) {
		int[] theNeighbors = {1,1,1,1};
		if(space<N)
			theNeighbors[0]=0;
		if(space>N*N-N)
			theNeighbors[3]=0;
		if(space%N==0)
			theNeighbors[1]=0;
		if(space%N==N-1)
			theNeighbors[2]=0;
		
		return theNeighbors;
	}
	
	public boolean isGoal() {
		for(int i=0;i<this.size()*this.size();i++) {
			if(board[i]!=0) {
			if(board[i]!=i+1) {
				return false;
			}
			}
		}
		return true;
	}
	
	public boolean equals(Object b) {
		if(b==this)
			return true;
		if(this.size()!=((Board) b).size()||!(b instanceof Board))
			return false;
		int[] thatBoard = ((Board) b).getBoard();
		for(int i= 0;i<board.length;i++) {
			if(this.board[i] != thatBoard[i]) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * finds the distance a number needs to move to be in place.
	 */
	private int distance(int number) {
		//get the distance a number must move.
		if(number==0)
			return 0;
		
		int row = find(number)/N;
		int col = find(number)%N;
	
		return Math.abs(row-(number-1)/N)+Math.abs(col-(number-1)%N);
		
	}
	
	/*
	 * finds the row and column of an integer
	 */
	private int find(int n) {
		for(int i = 0;i<board.length;i++) {
			if(n==board[i]) {
				return i;
			}
		}
		return -1;
	}


	public int tileAt(int row, int col)
	{
		return board[row*N + col];
	}
	private void setTile(int row, int col, int number) {
		
		this.board[row*N+col]=number;
	}
	
	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
		
		for(int i = 0;i<this.size()*this.size();i++) {
			s.append(""+this.getBoard()[i]+" ");
			if((i+1)%N==0) {
				s.append("\n");
			}
		}	    
	    return s.toString();
	}
	
	private int[] getBoard() {
		return this.board;
	}


	
	
	// =============================================================
	// ++++++++++++++++     TEST CLIENT     ++++++++++++++++++++++++
	// =============================================================
	public static void main(String[] args) {
		System.out.println("---------- BEGIN TESTS ----------");
		int[][] array = {{1,2,3},{4,5,6},{7,8,0}};
		Board board = new Board(array);
		assert(board.tileAt(0, 0)==1);
		assert(board.tileAt(1,1)==5);
		assert(board.tileAt(2, 2)==0);
		
		testPrint(board);
		int[][] array2 = {{1,12,3,4},{5,6,7,8},{9,10,11,2},{14,13,15,0}};
		Board board2 = new Board(array2);
		testPrint(board2);
		assert(board2.hamming()==4);
		assert(board2.distance(14)==1);
		assert(board2.distance(12)==4);
		assert(board2.manhattan()==10);
		int[][] array3 = {{1,2,3},{4,5,6},{7,0,8}};
		Board board3 = new Board(array3);
		Board board4 = board3.swap(2, 2, 2, 1);
		
		testPrint(board4);
		System.out.println("----------TEST EQUALS----------");
		assert(board4.equals(board));
		System.out.println(board4.equals(board));
		
		System.out.println("-----------------TEST NEIGHBORS------------------");
		Board board5 = board3.swap(1, 1, 2, 1);
		testPrint(board5);
		for(Board b : board5.neighbors()) {
			testPrint(b);
		}
		
		System.out.println("---------- TEST INVERSIONS/ISSOLVABLE ----------");
		
		System.out.println("Goal Board " + board.inversions());
		assert(board.inversions()==0);
		
		Board board6 = new Board(new int[][] {{1,2,3},{4,5,6},{8,7,0}});
		System.out.println("board6 inversions;" + board6.inversions());
		System.out.println("board6 isSolvable: "+board6.isSolvable());
		Board board7 = new Board(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,0,15}});
		assert(board7.isSolvable()==true);
		
		Board board2x2 = new Board(new int[][] {{1,2},{0,3}});
		
		testPrint(board2x2.twin());
		testPrint(board2x2);
		System.out.println("----- TEST COMPLETE -----");
		Board board80 = new Board(new int[][] {{0,12,9,13},{15,11,10,14},{3,7,5,6},{4,8,2,1}});
		testPrint(board80);
	}
	private static void testPrint(Board b) {
		System.out.print(b.toString());
		System.out.println("Hamming = "+b.hamming());
		System.out.println("Manhattan = "+b.manhattan());
		System.out.println("Is it a goal board? "+b.isGoal());
		System.out.println("isSolvable: "+ b.isSolvable()+"\n");
	}
}
