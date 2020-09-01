package Lab2_14;

public class Main {
	
	public static void main(String[] args) {

	int v = 5; 
    int source = 0; 
    Dijkstra d = new Dijkstra(v); 
 
    d.add(0, 1, 9);
    d.add(0, 2, 6);
    d.add(0, 3, 5);
    d.add(0, 4, 3);
    d.add(2, 1, 2);
    d.add(2, 3, 4);
    
    d.dijkstra(source);

    System.out.println("Dijkstra:"); 

    System.out.println("Najkrotsza droga od krawedzi " + source + ":"); 
    for(int i = 0; i < d.dist.length; i++) 
        System.out.println(source + " -> " + i + " = "+ d.dist[i]);
	
	/*
	 * Huffman
	 */
    
    System.out.println(); 
    System.out.println("Huffman:"); 

	
	char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' }; 
	int[] charFreq = { 45, 13, 12, 16, 9, 5 }; 
	
	Huffman h= new Huffman(charArray, charFreq);
	h.run();
	}
}
