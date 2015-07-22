public class Solver {

	private final Node solutionNode;

	public Solver(Board initial) {
		if (initial == null)
			throw new java.lang.NullPointerException();

		MinPQ<Node> gameTree = new MinPQ<Node>();
		MinPQ<Node> twinGameTree = new MinPQ<Node>();

		gameTree.insert(new Node(initial, null));
		twinGameTree.insert(new Node(initial.twin(), null));

		Node solutionRoot = null;
		Node twinSolutionRoot = null;

		do {
			solutionRoot = processNode(gameTree);
			twinSolutionRoot = processNode(twinGameTree);
		} while (solutionRoot == null && twinSolutionRoot == null);

		this.solutionNode = solutionRoot;

	}

	private Node processNode(MinPQ<Node> gameTree) {
		if (gameTree.isEmpty()) {
			return null;
		}

		Node node = gameTree.delMin();

		if (node.board.isGoal()) {
			return node;
		}

		for (Board neighbor : node.board.neighbors()) {
			if (node.previous == null || !node.previous.board.equals(neighbor)) {
				gameTree.insert(new Node(neighbor, node));
			}
		}

		return null;
	}

	private class Node implements Comparable<Node> {
		private Board board;
		private Node previous;
		private int moves;

		public Node(Board board, Node previous) {
			this.board = board;
			this.previous = previous;
			this.moves = (this.previous == null) ? 0
					: (this.previous.moves + 1);
		}

		@Override
		public int compareTo(Node that) {

			return (this.board.manhattan() - that.board.manhattan())
					+ (this.moves - that.moves);
		}

	}

	public boolean isSolvable() {
		return (solutionNode != null);

	}

	public int moves() {
		if (solutionNode != null) {
			return solutionNode.moves;
		} else {
			return -1;
		}
	}

	public Iterable<Board> solution() {

		if (!isSolvable())
			return null;

		Stack<Board> solution = new Stack<Board>();
		Node traverser = solutionNode;

		while (traverser != null) {
			solution.push(traverser.board);
			traverser = traverser.previous;
		}

		return solution;
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

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
