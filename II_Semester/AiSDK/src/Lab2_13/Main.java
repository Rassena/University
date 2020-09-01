package Lab2_13;

import Lab2_12.Graph;

public class Main {

	public static void main(String[] args) {
		int v = 8; 
        Graph graph = new Graph(v); 
        GraphAlg gA = new GraphAlg(graph); 

        graph.addEdge(graph, 0, 1); 
        graph.addEdge(graph, 0, 4); 
        graph.addEdge(graph, 1, 2); 
        graph.addEdge(graph, 1, 6); 
        graph.addEdge(graph, 1, 4); 
        graph.addEdge(graph, 2, 3); 
        graph.addEdge(graph, 3, 4); 
        graph.addEdge(graph, 3, 7); 
        graph.addEdge(graph, 4, 5); 
        graph.addEdge(graph, 5, 6); 
        graph.addEdge(graph, 6, 7); 
       

        graph.printGraph(graph);
        graph.printMother(graph);
        
        gA.BFS(3);    
        gA.DFS(3);
        
        graph.addVertex(graph);
        
		System.out.println("Graf po dodaniu wierzcholka i kilku wezlow: \n");

        graph.addEdge(graph, 0, 8);
        graph.addEdge(graph, 0, 5);
        graph.addEdge(graph, 0, 7); 
        graph.addEdge(graph, 3, 5); 
        graph.addEdge(graph, 3, 6); 
        graph.addEdge(graph, 3, 8); 
        graph.addEdge(graph, 5, 8);

        //graph.printGraph(graph);
        graph.printMother(graph);
        
        gA = new GraphAlg(graph); 
        
        gA.BFS(3);    
        gA.DFS(3);
        
        
	}

}
