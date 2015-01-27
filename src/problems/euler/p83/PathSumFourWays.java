package problems.euler.p83;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Constructs a digraph out of the input matrix and runs Dijkstra's algorithm on
 * it. Run-time could be improved by using a heap implementation that actually
 * has a 'decreaseKey()' method as the standard Java PriorityQueue does not.
 * 
 * @author Riley
 *
 */
public class PathSumFourWays {
	private static final int N_ROWS = 80;
	private static final int N_COLS = 80;
	/**
	 * No path can exceed this as all edges have a max cost of 9999 and there
	 * are 6400 vertices (and repeats are not allowed).
	 */
	private static final int MAX_DISTANCE = N_ROWS * N_COLS * 9999;

	public static void main(String[] args) {
		new PathSumFourWays();
	}

	public PathSumFourWays() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"src/problems/misc/euler/p83/matrix.txt"));
			Node[] graph = new Node[N_ROWS * N_COLS];
			PriorityQueue<Node> unvisited = new PriorityQueue<Node>();
			int[][] matrix = new int[N_ROWS][N_COLS];
			for (int i = 0; i < N_ROWS; i++) {
				String[] nextLine = br.readLine().split(",");
				// System.out.println(Arrays.toString(nextLine));
				for (int j = 0; j < N_COLS; j++) {
					matrix[i][j] = Integer.parseInt(nextLine[j]);
					Node n;
					if ((i == 0 || i == N_ROWS - 1)
							&& (j == 0 || j == N_COLS - 1)) {
						if (i != 0 || j != 0) {
							n = new Node(2, i * N_COLS + j);
						} else {
							n = new Node(2, i * N_COLS + j, true);
						}
					} else if (i == 0 || i == N_ROWS - 1 || j == 0
							|| j == N_COLS - 1) {
						n = new Node(3, i * N_COLS + j);
					} else {
						n = new Node(4, i * N_COLS + j);
					}
					unvisited.add(n);
					graph[i * N_COLS + j] = n;

				}
			}

			for (int i = 0; i < N_ROWS; i++) {
				for (int j = 0; j < N_COLS; j++) {
					Node current = graph[i * N_ROWS + j];
					if (i * N_COLS + j == 23) {
						System.out.println();
					}
					if ((i == 0 || i == N_ROWS - 1)
							&& (j == 0 || j == N_COLS - 1)) {
						if (i == 0 && j == 0) {
							current.edges[0] = new Edge(current, graph[1],
									matrix[i][j]);
							current.edges[1] = new Edge(current, graph[N_ROWS],
									matrix[i][j]);
						} else if (i == N_ROWS - 1 && j == N_COLS - 1) {
							current.edges[0] = new Edge(current, graph[N_ROWS
									* N_COLS - 2], matrix[i][j]);
							current.edges[1] = new Edge(current,
									graph[(N_ROWS - 2) * N_COLS + N_COLS - 1],
									matrix[i][j]);
						} else if (i == 0) {
							current.edges[0] = new Edge(current,
									graph[N_COLS - 2], matrix[i][j]);
							current.edges[1] = new Edge(current,
									graph[2 * N_COLS - 1], matrix[i][j]);
						} else {
							current.edges[0] = new Edge(current,
									graph[(N_ROWS - 2) * N_COLS], matrix[i][j]);
							current.edges[1] = new Edge(current,
									graph[(N_ROWS - 1) * N_COLS + 1],
									matrix[i][j]);
						}
					} else if (i == 0 || i == N_ROWS - 1) {
						if (i == 0) {
							current.edges[0] = new Edge(current, graph[j - 1],
									matrix[i][j]);
							current.edges[1] = new Edge(current, graph[j + 1],
									matrix[i][j]);
							current.edges[2] = new Edge(current, graph[j
									+ N_ROWS], matrix[i][j]);
						}
						if (i == N_ROWS - 1) {
							current.edges[0] = new Edge(current, graph[i
									* N_COLS + (j - 1)], matrix[i][j]);
							current.edges[1] = new Edge(current, graph[i
									* N_COLS + (j + 1)], matrix[i][j]);
							current.edges[2] = new Edge(current, graph[(i - 1)
									* N_COLS + j], matrix[i][j]);
						}
					} else if (j == 0 || j == N_COLS - 1) {
						if (j == 0) {
							current.edges[0] = new Edge(current, graph[i
									* N_COLS + (j + 1)], matrix[i][j]);
							current.edges[1] = new Edge(current, graph[(i - 1)
									* N_COLS], matrix[i][j]);
							current.edges[2] = new Edge(current, graph[(i + 1)
									* N_COLS], matrix[i][j]);
						}
						if (j == N_COLS - 1) {
							current.edges[0] = new Edge(current, graph[i
									* N_COLS + (j - 1)], matrix[i][j]);
							current.edges[1] = new Edge(current, graph[(i - 1)
									* N_COLS + j], matrix[i][j]);
							current.edges[2] = new Edge(current, graph[(i + 1)
									* N_COLS + j], matrix[i][j]);
						}
					} else {
						current.edges[0] = new Edge(current, graph[(i - 1)
								* N_COLS + j], matrix[i][j]);
						current.edges[1] = new Edge(current, graph[(i + 1)
								* N_COLS + j], matrix[i][j]);
						current.edges[2] = new Edge(current, graph[i * N_COLS
								+ (j - 1)], matrix[i][j]);
						current.edges[3] = new Edge(current, graph[i * N_COLS
								+ (j + 1)], matrix[i][j]);
					}
				}
			}
			System.out.println(dijkstra(graph[N_ROWS * N_COLS - 1], unvisited));
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int dijkstra(Node goal, PriorityQueue<Node> unvisited) {
		while (!unvisited.isEmpty()) {
			final Node currentNode = unvisited.poll();
			System.out.println(currentNode);
			if (currentNode.equals(goal)) {
				return goal.distance + goal.edges[0].weight;
			}
			for (final Edge e : currentNode.edges) {
				final int tentativeDistance = currentNode.distance + e.weight;
				if (e.to.distance > tentativeDistance) {
					e.to.distance = tentativeDistance;
					if (!e.to.visited) {
						/*
						 * Note: This should be unvisted.decreaseKey(e.to) But
						 * Java's PriorityQueue does not implement such a method
						 */
						unvisited.remove(e.to);
						unvisited.add(e.to);
					}
				}
				currentNode.visited = true;
			}
		}
		return -1;
	}

	private class Node implements Comparable<Node> {
		Edge[] edges;
		boolean visited;
		int distance;
		int id;

		public Node(int numEdges, int id) {
			this(numEdges, id, false);
		}

		public Node(int numEdges, int id, boolean first) {
			edges = new Edge[numEdges];
			if (first) {
				distance = 0;
			} else {
				distance = MAX_DISTANCE;
			}
			visited = false;
			this.id = id;

		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(id + "\t" + distance + "\t" + visited + "\n");
			for (Edge e : edges) {
				sb.append(e + "\n");
			}
			return sb.toString();
		}

		@Override
		public int compareTo(Node other) {
			int otherDist = other.distance;
			if (this.distance == otherDist) {
				return 0;
			} else if (this.distance < otherDist) {
				return -1;
			}
			return 1;
		}
	}

	private class Edge {
		int weight;
		Node to;
		Node from;

		public Edge(Node from, Node to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public String toString() {
			return "W: " + weight + "\tFrom: " + from.id + "\tTo: " + to.id;
		}
	}
}
