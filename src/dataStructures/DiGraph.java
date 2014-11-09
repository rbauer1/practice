package dataStructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DiGraph<V> {
	private V source;
	private V sink;
	private Map<V, List<Edge<V>>> graph = new HashMap<V, List<Edge<V>>>();
	
	public DiGraph(V vertex){
		insertVertex(vertex);
	}
	
	/**
     * String representation of graph.
     */
    public String toString () {
        StringBuffer s = new StringBuffer();
        for (V v: graph.keySet()){
        	s.append(v + " -> {");
        	for(Edge<V> e : graph.get(v)){
        		s.append(e.flow + " / " + e.capacity + " " + e.to + ", ");
        	}
        	s.append("} \n");
        }
        return s.toString();                
    }
	
	public void insertVertex(V vertex){
		if(graph.containsKey(vertex)){
			return;
		}
		graph.put(vertex, new ArrayList<Edge<V>>());
	}
	
	/**
	 * Adds vertices if they don't already exist
	 */
	public void addEdge(V from, V to, int capacity){
		Edge<V> e = new Edge<V>(from, to, capacity);
		addEdge(e);
	}
	
	public void addEdge(Edge<V> e){
		insertVertex(e.from);
		insertVertex(e.to);	
		if(graph.get(e.from).contains(e)){
			return;
		}
		graph.get(e.from).add(e);
		if(e.returnEdge == null){
			for(Edge<V> re : graph.get(e.to)){
				if(re.to.equals(e.from)){
					e.returnEdge = re;
					re.returnEdge = e;
				}
			}
		}
	}
	
	public LinkedList<Edge<V>> findPath(){
		return findPath(source, sink, new LinkedList<Edge<V>>());
	}
	
	private LinkedList<Edge<V>> findPath(V source, V sink, LinkedList<Edge<V>> path){
		if(source.equals(sink)){
			return path;
		}
		for(Edge<V> e : graph.get(source)){
			if(e.capacity - e.flow > 0 && ! path.contains(e)){
				LinkedList<Edge<V>> newPath = (LinkedList<Edge<V>>) path.clone();
				newPath.add(e);
				newPath = findPath(e.to, sink, newPath);
				if(newPath != null){
					return newPath;
				}
			}
		}
		return null;
	}
	
	public int findMaxFlow(){
		LinkedList<Edge<V>> path = findPath();
		while(path != null){
			final int minCapacity = findMinCapacity(path);
			for(Edge<V> e : path){
				e.flow += minCapacity;
				e.returnEdge.flow -= minCapacity;
			}
			path = findPath();
		}
		int maxFlow = 0;
		for(Edge<V> e : graph.get(source)){
			maxFlow += e.flow;
		}
		return maxFlow;
	}
	
	private int findMinCapacity(LinkedList<Edge<V>> path){
		int min = Integer.MAX_VALUE;
		for(Edge<V> e : path){
			final int cf = e.capacity - e.flow;  
			if(cf < min){
				min = cf;
			}
		}
		return min;
	}
	
	public V getSource() {
		return source;
	}

	public void setSource(V source) {
		this.source = source;
	}

	public V getSink() {
		return sink;
	}

	public void setSink(V sink) {
		this.sink = sink;
	}

	public LinkedList<V> topoSortDFS(){
		LinkedList<V> queue = new LinkedList<V>();
		Map<V, boolean[]> markMap = new HashMap<V, boolean[]>();
		Stack<V> unmarked = new Stack<V>();
		for(V v: graph.keySet()){
			unmarked.push(v);
			markMap.put(v, new boolean[]{false, false});
		}
		while(! unmarked.isEmpty()){
			V current = unmarked.pop();
			try {
				visit(current, queue, markMap);
			} catch (Exception e) {
				System.out.println("ERROR: This graph is not a DAG. Aborting topoSort");
				return null;
			}
		}
		return queue;
	}
	
	private void visit(V current, LinkedList<V> queue, Map<V, boolean[]> markMap) throws Exception{
		if(markMap.get(current)[0]){
			throw new Exception();
		}
		if(! markMap.get(current)[1]){
			markMap.get(current)[0] = true;
			for(Edge<V> e : graph.get(current)){
				V next = e.to;
				visit(next, queue, markMap);
			}
			markMap.get(current)[1] = true;
			markMap.get(current)[0] = false;
			queue.offerFirst(current);
		}
	}
	
	private static class Edge<V>{
		V from, to;
		int capacity, flow;
		Edge<V> returnEdge;
		
		public Edge(V from, V to, int capacity){
			this.from = from;
			this.to = to;
			this.capacity = capacity;
			flow = 0;
		}
		
		public String toString(){
			return from + " " + flow + " / " + capacity + " " + to;
		}
		
		@Override
		public boolean equals(Object other){
			if(this.from.equals(((Edge<V>)other).from)	&& this.to.equals(((Edge<V>)other).to)){
				return true;
			}
			return false;
		}
	}
}
