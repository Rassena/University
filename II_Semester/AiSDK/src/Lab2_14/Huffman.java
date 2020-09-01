package Lab2_14;

import java.util.PriorityQueue; 
import java.util.Comparator; 


public class Huffman {
	private char[] charArray;
	int[] charFreq;
	
	private class HComparator implements Comparator<HuffmanNode> { 
		public int compare(HuffmanNode x, HuffmanNode y) { 
			return x.data - y.data; 
		} 
	}
	
	private class HuffmanNode { 
		int data; 
		char c; 
		HuffmanNode left; 
		HuffmanNode right; 
	} 
	
	public Huffman(char[] charArray,int[] charFreq) {
		this.charArray=charArray;
		this.charFreq=charFreq;
	}
	
	public void run() {

		PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(charArray.length, new HComparator()); 

		for (int i = 0; i < charArray.length; i++) { 
			HuffmanNode hn = new HuffmanNode(); 

			hn.c = charArray[i]; 
			hn.data = charFreq[i]; 
			hn.left = null; 
			hn.right = null; 

			q.add(hn); 
		}
		
		HuffmanNode root = null; 

		while (q.size() > 1) { 
			HuffmanNode x = q.remove(); 
			HuffmanNode y = q.remove(); 

			HuffmanNode f = new HuffmanNode(); 

			f.data = x.data + y.data; 
			f.c = '-'; 

			f.left = x; 
			f.right = y; 
			root = f; 
			q.add(f); 
		} 
		printCode(root, ""); 
	}

	private void printCode(HuffmanNode root, String s) { 

		if (root.left == null && root.right == null && Character.isLetter(root.c)) { 
			System.out.println(root.c + ":" + s); 
			return; 
		} 
		printCode(root.left, s + "0"); 
		printCode(root.right, s + "1"); 
	} 

	
}
