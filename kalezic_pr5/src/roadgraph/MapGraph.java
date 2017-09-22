package roadgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;
import geography.RoadSegment;

/**
 * @author UCSD MOOC development team, Monika Sobolewska, and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections
 *
 */
public class MapGraph {
	// Maintain both vertices and edges
	
	// Vertices that map lat/lon  to the list of edges from that location
	private HashMap<GeographicPoint, List<Integer>> pointNodeMap;
	// List of edges / roads
	private List<MapEdge> edges;

	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		pointNodeMap = new HashMap<GeographicPoint, List<Integer>>();
		edges = new ArrayList<MapEdge>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return pointNodeMap.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as a set of GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		return pointNodeMap.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return edges.size();
	}	
	
	/** Add a vertex to the graph (location and an empty list)
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{	
		if(pointNodeMap.containsKey(location) || location == null){
			return false;
		}
		List<Integer> list = new ArrayList<Integer>();
		
		pointNodeMap.put(location, list);
		return true;
		
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
		String roadType, double length) throws IllegalArgumentException {

		// check nodes are valid
		if (!pointNodeMap.containsKey(from))
			throw new NullPointerException("addEdge: pt1:"+from+"is not in graph");
		if (!pointNodeMap.containsKey(to))
			throw new NullPointerException("addEdge: pt2:"+to+"is not in graph");

		// create and add a new edge to the list of edges 
		// add the index of the edge to the list of edges associated with the "from" vertex
		MapEdge edge = new MapEdge(roadName, roadType, from, to);
		edges.add(edge);
		int index = edges.indexOf(edge);
		
		pointNodeMap.get(from).add(index);
	
	}
		
	/** 
	 * Get a set of neighbor nodes from a GeographicPoint
	 * @param node  The location to get the neighbors from
	 * @return A set containing the GeographicPoint objects that are the neighbors 
	 * 	of node
	 */
	private Set<GeographicPoint> getNeighbors(GeographicPoint node) {
		Set<GeographicPoint> set = new HashSet<GeographicPoint>();
		List<Integer> list = pointNodeMap.get(node);
		
		for(int i = 0; i < list.size(); i++){
			int index = list.get(i);
			MapEdge edge = edges.get(index);
			set.add(edge.getOtherNode(node));
		}
		
		return set;
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, 
			 					     Consumer<GeographicPoint> nodeSearched)
	{
			
		
		// Setup - check validity of inputs
		if (start == null || goal == null)
			throw new NullPointerException("Cannot find route from or to null node");
		
		
		// setup to begin BFS
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		Queue<GeographicPoint> toExplore = new LinkedList<GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		toExplore.add(start);
		GeographicPoint next = null;
		

		while (!toExplore.isEmpty()) {
			next = toExplore.remove();
			Set<GeographicPoint> set = getNeighbors(next);
			if(next == goal){
				break;
			}
			for(GeographicPoint p: set){
				if(!visited.contains(p)){
					visited.add(p);
					parentMap.put(p, next);
					toExplore.add(p);
				}
			}
			
			 // hook for visualization
			nodeSearched.accept(next);
			
			
		}
		if (!next.equals(goal)) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		// Reconstruct the parent path
		List<GeographicPoint> path = 
				reconstructPath(parentMap, start, goal);

		return path;
	
	}
	


	/** Reconstruct a path from start to goal using the parentMap
	 *
	 * @param parentMap the HashNode map of children and their parents
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from
	 *   start to goal (including both start and goal).
	 */
	private List<GeographicPoint>
	reconstructPath(HashMap<GeographicPoint, GeographicPoint> parentMap,
					GeographicPoint start, GeographicPoint goal)
	{
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		GeographicPoint current = goal;

		while (!current.equals(start)) {
			path.addFirst(current);
			current = parentMap.get(current);
		}

		path.addFirst(start);
		return path;
	}


	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};

        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		// Setup - check validity of inputs
		if (start == null || goal == null)
			throw new NullPointerException("Cannot find route from or to null node");
		
		
		
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		PriorityQueue<GeographicPoint> toExplore = new PriorityQueue<GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		

		
		GeographicPoint next = null;
		toExplore.add(start);
		
		
		// hook for visualization
		nodeSearched.accept(next);

		while (!toExplore.isEmpty()) {
			next = toExplore.remove();

			
			if(next.equals(goal)){
				break;
				
			}
			
			Set<GeographicPoint> neighborSet = getNeighbors(next);
			double min = Double.POSITIVE_INFINITY;
			if(!visited.contains(next)){
				visited.add(next);
				for(GeographicPoint n : neighborSet){
					if(!visited.contains(n)){
						
						if(next.distance(n) < min){
							min = next.distance(n);
							parentMap.put(n, next);
							
							toExplore.add(n);							
						}
					}			
				}				
			}	
		}
		if (!next.equals(goal)) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		// Reconstruct the parent path
		List<GeographicPoint> path = 
		reconstructPath(parentMap, start, goal);	
		
		return path;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{

		
		// Setup - check validity of inputs
		if (start == null || goal == null)
			throw new NullPointerException("Cannot find route from or to null node");
		
		
		
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		PriorityQueue<GeographicPoint> toExplore = new PriorityQueue<GeographicPoint>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		

		
		GeographicPoint next = null;
		toExplore.add(start);
		
		
		// hook for visualization
		nodeSearched.accept(next);

		while (!toExplore.isEmpty()) {
			next = toExplore.remove();

			
			if(next.equals(goal)){
				break;
				
			}
			
			Set<GeographicPoint> neighborSet = getNeighbors(next);
			double min = Double.POSITIVE_INFINITY;
			if(!visited.contains(next)){
				visited.add(next);
				for(GeographicPoint n : neighborSet){
					if(!visited.contains(n)){
						
						if(next.distance(n) < min){
							min = next.distance(n);
							parentMap.put(n, next);
							
							toExplore.add(n);							
						}
					}			
				}				
			}	
		}
		if (!next.equals(goal)) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		// Reconstruct the parent path
		List<GeographicPoint> path = 
		reconstructPath(parentMap, start, goal);	
		
		return path;		

	}
	
}
