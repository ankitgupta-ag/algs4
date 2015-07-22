public class Percolation {
 private boolean[] grid;
 private WeightedQuickUnionUF uf, backwashUf;
 private int N;

 public Percolation(int N) {
  if (N <= 0)
   throw new IllegalArgumentException();

  this.N = N;
  grid = new boolean[N * N + 2];
  grid[0] = true;
  grid[N * N + 1] = true;

  uf = new WeightedQuickUnionUF(N * N + 2);
  backwashUf = new WeightedQuickUnionUF(N * N + 1);
 }

 private int getIndex(int i, int j) {
  return (i - 1) * N + (j);
 }

 public void open(int i, int j) {
  validateInput(i, j);

  int indexInGrid = getIndex(i, j);

  if (!grid[indexInGrid]) {
   grid[indexInGrid] = true;
   joinWithNeighbors(i, j, indexInGrid);
  }

  if (indexInGrid <= N) {
   uf.union(0, indexInGrid);
   backwashUf.union(0, indexInGrid);
  }

  if (indexInGrid >= N * (N - 1) + 1) {
   uf.union(N * N + 1, indexInGrid);
  }
 }

 private void joinWithNeighbor(int i, int j) {
  if (isOpen(i)) {
   uf.union(i, j);
   backwashUf.union(i, j);
  }

 }

 private void joinWithNeighbors(int i, int j, int index) {
  if (i != 1)
   joinWithNeighbor(getIndex(i - 1, j), index);
  if (j != 1)
   joinWithNeighbor(getIndex(i, j - 1), index);
  if (j != N)
   joinWithNeighbor(getIndex(i, j + 1), index);
  if (i != N)
   joinWithNeighbor(getIndex(i + 1, j), index);
 }

 private boolean isOpen(int index) {
  return grid[index];
 }

 public boolean isOpen(int i, int j) {
  validateInput(i, j);
  return isOpen(getIndex(i, j));
 }

 public boolean isFull(int i, int j) {
  validateInput(i, j);
  return (backwashUf.connected(0, getIndex(i, j)));
 }

 private void validateInput(int i, int j) {
  if (i <= 0 || i > N)
   throw new IndexOutOfBoundsException("row index i out of bounds");
  if (j <= 0 || j > N)
   throw new IndexOutOfBoundsException("column index j out of bounds");
 }

 public boolean percolates() {
  return uf.connected(0, N * N + 1);
 }
}