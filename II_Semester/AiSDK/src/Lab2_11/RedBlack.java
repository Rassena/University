package Lab2_11;


public class RedBlack {
	Node node=null;
	int leafs;
	int nodes;
	
	public class Node {
		int value;
		Node left;
		Node right;
		Node parent;
		boolean red;
		
		private int height;
		private int nodes;
		private int leftNodes;
		private int rightNodes;
		private int leftHeight;
		private int rightHeight;
		
		public Node(int value) {
			this.value=value;
			red=true;
		}
		
		public String toString() {
			String s = Integer.toString(value);
			if(red)
				s+="(RED)";
			else
				s+="(BLACK)";
			return s;
					
		}
		
		public String extended() {
			String s = toString();
			s+="[h: "+ height +",lh: " + leftHeight +", rh: "+ rightHeight+
					", n: " + nodes + ", ln: " + leftNodes + ", rn: " + rightNodes + "]";
			return s;
		}
		

	}
	
	public void add(int value) {
		Node temp;
		if(node==null) {
			temp = new Node(value);
			temp.red=false;
			node=temp;
		}
		else {
			temp = node;
			boolean work=true;
			while(work) {
				if(value<=temp.value) {
					if(temp.left==null) {
						temp.left=new Node(value);
						work=false;
						temp.left.parent=temp;
						temp=temp.left;
					}
					else {
						temp=temp.left;
					}
				}
				else {
					if(temp.right==null) {
						temp.right=new Node(value);
						work=false;
						temp.right.parent=temp;
						temp=temp.right;
					}
					else {
						temp=temp.right;
					}
				}
			}
		}
		fix(temp);
	}
	
	private void fix(Node n) {
		 n.red = true;
		 while (n!=node && n.parent.red) {
			 if (n.parent == n.parent.parent.left) {
				 Node y = n.parent.parent.right;
				 boolean done=false;
				 if (y!=null&&y.red) {					// przypadek 1
					n.parent.red=false;
					y.red=false; 
					n.parent.parent.red=true;
					n=n.parent.parent;
					done=true;
				 }
				 if(!done&&(y==null||!y.red)) {
					if (n==n.parent.right)	{
						n=n.parent; 			// przypadek 2
						rotateLeft(n);
						}
					n.parent.red=false; 
					n.parent.parent.red=true;
					rotateRight(n.parent.parent);
				 }
			 }
			 else { 
				 boolean done=false;
				 Node y = n.parent.parent.left;
				 if (y!=null&&y.red) { 					// przypadek 3
					n.parent.red=false;
					y.red=false; 
					n.parent.parent.red=true;
					n=n.parent.parent;
					done=true;
				 }
				 if(!done&&(y==null||!y.red)) {
					if (n==n.parent.left)	{
						n=n.parent; 			// przypadek 4
						rotateRight(n);
					}
					n.parent.red=false; 	
					n.parent.parent.red=true;	
					rotateLeft(n.parent.parent);
				 }
			}
		 }
		 node.red=false;
	}
	
	private void rotateLeft(Node x) {
	     Node y = x.right;
	     x.right = y.left;
	     if(y.left != null){
	         y.left.parent = x;
	     }
	     y.parent = x.parent;
	     if(x.parent == null)
	         node = y;
	     else if(x == x.parent.left){
	         x.parent.left = y;
	     }
	     else{
	         x.parent.right = y;
	     }
	     y.left = x;
	     x.parent = y;
	    }

	private void rotateRight(Node x) {
       Node y = x.left;
       x.left = y.right;
       if(y.right != null){
           y.right.parent = x;
       }
      y.parent = x.parent;
       if(x.parent == null){
           node = y;
       }
       else if(x == x.parent.right){
           x.parent.right = y;
       }
       else{
           x.parent.left = y;
       }
       y.right = x;
       x.parent = y;
   }
	
	public boolean contains(int value) {
		boolean found=false;
		boolean end=false;
		Node temp=node;
		while(!found&&!end) {
			if (temp.value==value)
				return true;
			if(value<temp.value)
				if(temp.left==null)
					return false;
				else
					temp=temp.left;
			if(value>temp.value)
				if(temp.right==null)
					return false;
				else
					temp=temp.right;
		}
		return false;
	}
	
	public Node get(int value) {
		if(contains(value)) {
			Node temp=node;
			while(value!=temp.value&&temp!=null) {
				if(value<temp.value)
					temp=temp.left;
				else
					temp=temp.right;
			}
			return temp;
		}
		else
			return null;
	}
	
	public Node findMax() {
		Node temp=node;
		while(temp.right!=null)
			temp=temp.right;
		return temp;
	}
	
	public Node findMin() {
		Node temp=node;
		while(temp.left!=null)
			temp=temp.left;
		return temp;
	}
	
	private Node findMin(Node n) {
		Node temp=n;
		while(temp.left!=null)
			temp=temp.left;
		return temp;
	}
	
	private Node findMax(Node n) {
		Node temp=n;
		while(temp.right!=null)
			temp=temp.right;
		return temp;
	}

	public void inOrder() {
		inOrder(node);
		System.out.println();
	}
	
	private void inOrder(Node n) {
		if(n==null)
			return;
		inOrder(n.left);
		System.out.print(n.value + " ");
		inOrder(n.right);
	}
	
	public void preOrder() {
		preOrder(node);
		System.out.println();
	}
	
	private void preOrder(Node n) {
		if(n==null)
			return;
		System.out.print(n.value + " ");
		preOrder(n.left);
		preOrder(n.right);
	}
	
	public void postOrder() {
		postOrder(node);
		System.out.println();
	}
	
	private void postOrder(Node n) {
		if(n==null)
			return;
		postOrder(n.left);
		postOrder(n.right);
		System.out.print(n.value + " ");
	}
	
	public int amountOfLeafs() {
		leafs=0;
		amountOfLeafs(node);
		return leafs;
	}
	
	private void amountOfLeafs(Node n) {
		if(n==null)
			return;
		if(n.left==null&&n.right==null) {
			leafs++;
			return;
		}
		amountOfLeafs(n.left);
		amountOfLeafs(n.right);
	}
	
	public int amounOfNodes() {
		nodes=0;
		amountOfNodes(node);
		return nodes;
	}
	
	public int amountOfNodesLeft(Node node) {
		nodes=0;
		amountOfNodes(node.left);
		return nodes;
	}
	
	public int amountOfNodesRight(Node node) {
		nodes=0;
		amountOfNodes(node.right);
		return nodes;
	}
	
	private void amountOfNodes(Node n) {
		if(n==null)
			return;
		amountOfNodes(n.left);
		amountOfNodes(n.right);
		nodes++;
	}
	
	public int height() {
		return findHeight(node) -1;
	}
	
	public int heightLeftChild (Node node) {
		return findHeight(node.left) -1;
	}
	
	public int heightRightChild (Node node) {
		return findHeight(node.right) -1;
	}
	
	private int findHeight(Node n){
	    int heightLeft = 0;
	    int heightRight = 0;
	    if(n.left!=null)
	        heightLeft = findHeight(n.left);
	    if(n.right!=null)
	        heightRight = findHeight(n.right);
	    if(heightLeft > heightRight){
	        return heightLeft+1;
	    }
	    else{
	        return heightRight+1;
	    }
	}
	
	public Node successor(Node node){
		  
	    Node successor = null;
	    Node current  = this.node;
	    if(node==null)
	        return null;
	  
	    while(current.value!= node.value){
	        if(current.value > node.value){
	            successor = current;
	            current= current.left;
	        }
	        else
	            current = current.right;
	    }
	    if(current!=null && current.right!=null){
	        successor = findMin(current.right);
	    }
	  
	    return successor;
	}
	
	public Node predecessor(Node node){
		  
	    Node predecessor = null;
	    Node current  = this.node;
	    if(node==null)
	        return null;
	  
	    while(current.value!= node.value){
	        if(current.value < node.value){
	            predecessor = current;
	            current= current.right;
	        }
	        else
	            current = current.left;
	    }
	    if(current!=null && current.left!=null){
	        predecessor = findMax(current.left);
	    } 
	    return predecessor;
	}
	
	public void delete(int key) { 
	        node = delete(node, key); 
	    } 

	private Node delete(Node n, int key) { 
		 if (n == null)  return n;
	     if (key < n.value) 
	    	 n.left = delete(n.left, key); 
	     else 
	    	 if (key > n.value) 
	            n.right = delete(n.right, key); 
	        else { 
	            if (n.left == null) 
	                return n.right; 
	            else if (n.right == null) 
	                return n.left; 
	            n.value = findMin(n.right).value; 
	            n.right = delete(n.right, n.value); 
	        } 
	     return n; 
	 }
	 
	public void printLevel(int height) {
		 printLevel(node, height); 
	     System.out.println();       
	 } 
	    	     
	private void printLevel(Node n ,int level) { 
		if (n == null) 
			return; 
	    if (level == 0) 
	        System.out.print(n + " "); 	
	    else 
	    	if (level > 0) { 
	            printLevel(n.left, level-1); 
	            printLevel(n.right, level-1);
	        } 
	    } 
	 
	public void printByLevels() {
		for(int i=0; i<=height();i++) {
			System.out.print("Poziom " + i +": ");
			printLevel(i);
		}
	}

	public void addStats() {
		addStat(node);
	}
	
	private void addStat(Node n) {
		if(n==null)
			return;
		addStat(n.left);
		n.height = findHeight(n)-1;
		if(n.left!=null) {
			n.leftHeight = findHeight(n.left)-1;
			n.leftNodes = amountOfNodesLeft(n);
		}
		if(n.right!=null) {
			n.rightHeight = findHeight(n.right)-1;
			n.rightNodes = amountOfNodesRight(n);
		}
		n.nodes = n.leftNodes + n.rightNodes+1;
		addStat(n.right);
	}

}
