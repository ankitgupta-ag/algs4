import java.util.Arrays;

public class Board {

	private final int N;
	private final int[][] blocks;

	public Board(int[][] blocks) {

		this.N = blocks.length;
		this.blocks = duplicateBoard(blocks);

	}

	public int dimension() {
		return this.N;
	}

	public int hamming() {
		int hammingP = 0;
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				if (this.blocks[i][j] != 0) {

					int rowNum = getRowNum(blocks[i][j]);
					int colNum = getColNum(blocks[i][j]);

					if (rowNum != i || colNum != j)
						hammingP += 1;

				}
			}
		}

		return hammingP;

	}

	private int getRowNum(int i) {
		int rowNum;
		if (i % N == 0) {
			rowNum = (i / this.N) - 1;
		} else {
			rowNum = i / this.N;
		}
		return rowNum;
	}

	private int getColNum(int i) {
		int colNum;
		if (i % N == 0) {
			colNum = this.N - 1;
		} else {
			colNum = (i % this.N) - 1;
		}
		return colNum;
	}

	public int manhattan() {
		int manhattanP = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (this.blocks[i][j] != 0) {
					int rowNum = getRowNum(blocks[i][j]);
					int colNum = getColNum(blocks[i][j]);

					manhattanP += Math.abs(rowNum - i) + Math.abs(colNum - j);
				}
			}
		}
		return manhattanP;

	}

	public boolean isGoal() {
		return hamming() == 0;
	}

	private int[][] duplicateBoard(int[][] blocks) {
		int[][] duplicateBoard = new int[this.N][];

		for (int i = 0; i < this.N; i++) {
			duplicateBoard[i] = Arrays.copyOf(blocks[i], this.N);
		}

		return duplicateBoard;
	}

	public Board twin() {

		int[][] twin = duplicateBoard(this.blocks);

		boolean zeroFound = false;
		int i = 0;

		while (!zeroFound && i < this.N) {
			if (this.blocks[0][i] == 0) {
				zeroFound = true;
			}
			i++;
		}

		i = zeroFound ? 1 : 0;

		int temp = twin[i][0];
		twin[i][0] = twin[i][1];
		twin[i][1] = temp;

		return new Board(twin);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Board other = (Board) obj;
		if (N != other.N) {
			return false;
		}
		if (!Arrays.deepEquals(blocks, other.blocks)) {
			return false;
		}
		return true;
	}

	private boolean validate(int i) {
		return (0 <= i && i < this.N);
	}

	private boolean validate(int i, int j) {
		return validate(i) && validate(j);

	}

	private Queue<Board> getANeighbor(Queue<Board> neighbors, int i, int j,
			int i2, int j2) {

		if (validate(i2, j2)) {

			int[][] neighbor = duplicateBoard(this.blocks);
			int temp = neighbor[i][j];
			neighbor[i][j] = neighbor[i2][j2];
			neighbor[i2][j2] = temp;

			neighbors.enqueue(new Board(neighbor));
		}

		return neighbors;

	}

	public Iterable<Board> neighbors() {
		Queue<Board> neighbors = new Queue<Board>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (blocks[i][j] == 0) {
					// surrounding 4 blocks in the 4 directions of the empty
					// block can move
					getANeighbor(neighbors, i, j, i - 1, j);
					getANeighbor(neighbors, i, j, i + 1, j);
					getANeighbor(neighbors, i, j, i, j - 1);
					getANeighbor(neighbors, i, j, i, j + 1);
					return neighbors;

				}
			}
		}

		return neighbors;

	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();

		int[][] blocks = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();

		Board initial = new Board(blocks);

		StdOut.println(initial.hamming());
		StdOut.println(initial.manhattan());
		StdOut.println("twin" + initial.twin());
		StdOut.println(initial.toString());
		StdOut.println(initial.isGoal());

		Iterable<Board> neighbors = initial.neighbors();
		for (Board neighbor : neighbors)
			StdOut.println(neighbor.toString());
	}

}
