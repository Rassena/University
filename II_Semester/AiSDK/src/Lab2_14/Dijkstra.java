package Lab2_14;

import java.util.Comparator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
	int dist[]; 
    private HashSet<Integer> settled; 
    private PriorityQueue<Node> pq; 
    private int v;
    ArrayList<ArrayList<Node> > adj; 
    
    public Dijkstra(int v) {
    	this.v = v; 
        dist = new int[v]; 
        settled = new HashSet<Integer>(); 
        pq = new PriorityQueue<Node>(v, new Node());
        
        adj = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < v; i++) { 
            ArrayList<Node> item = new ArrayList<Node>(); 
            adj.add(item); 
        } 
    } 
    
    public void add(int src, int dest, int cost) {
    	adj.get(src).add(new Node(dest, cost));
    }
    
    private class Node implements Comparator<Node> { 
        public int node; 
        public int cost; 
      
        public Node() {} 
      
        public Node(int node, int cost) { 
            this.node = node; 
            this.cost = cost; 
        } 
      
        @Override
        public int compare(Node node1, Node node2) { 
            if (node1.cost < node2.cost) 
                return -1; 
            if (node1.cost > node2.cost) 
                return 1; 
            return 0; 
        }
    }

    public void dijkstra(int src) { 
  
        for (int i = 0; i < v; i++) 
            dist[i] = Integer.MAX_VALUE; 
  
        pq.add(new Node(src, 0)); 
  
        dist[src] = 0; 
        while (settled.size() != v) { 
        	int u = pq.remove().node;
            settled.add(u); 
            Neighbours(u); 
        } 
    } 
    
    private void Neighbours(int u) { 
        int edgeDistance = -1; 
        int newDistance = -1; 
  
        for (int i = 0; i < adj.get(u).size(); i++) { 
            Node v = adj.get(u).get(i); 
  
            if (!settled.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
  
                if (newDistance < dist[v.node]) 
                    dist[v.node] = newDistance; 
  
                pq.add(new Node(v.node, dist[v.node])); 
            } 
        } 
    }
}
