package Lab2_11;

public class Main {
	
	// zawsze wstawiamy czerwone wezly
	//przelaczenie kolorow
	//rotacja lewo
	//rotacja prawo
	

	public static void main(String[] args) {
		
		RedBlack rb = new RedBlack();
		
		rb.add(19);
		rb.add(8);
		rb.add(12);
		rb.add(20);
		rb.add(5);
		rb.add(11);
		rb.add(9);
		rb.add(2);
		rb.add(7);

		System.out.println("zawiera 10: " + rb.contains(10));
		System.out.println("zawiera 8: " + rb.contains(8));
		System.out.println("Szukana 8: " + rb.get(8));
		
		System.out.println("Max: " + rb.findMax());
		System.out.println("Min: " + rb.findMin());
		System.out.println("Height: " + rb.height());
		
		System.out.println();
		rb.printByLevels();
		
		rb.addStats();

		System.out.println();
		System.out.println("Height left child " + rb.get(8) + ": " + rb.heightLeftChild(rb.get(8)));
		System.out.println("Height right child " + rb.get(19) + ": " + rb.heightRightChild(rb.get(19)));
		System.out.println("Nodes on left of " + rb.get(12) + ": " + rb.amountOfNodesLeft(rb.get(12)));
		System.out.println("Nodes on right of " + rb.get(12) + ": " + rb.amountOfNodesRight(rb.get(12)));
		
		System.out.println();
		System.out.println(rb.get(5).extended());
		System.out.println(rb.get(12).extended());

/*
		for(int i=9;i>0;i--) {
			rb.add(i);
			rb.printByLevels();
			System.out.println();
			for(int j=9;j>=i;j--) {
				rb.addStats();
				System.out.println(rb.get(j) +": " + " l: " + rb.get(j).left +
						" r: " + rb.get(j).right);
			}
			System.out.println();
			System.out.println();
		}

		System.out.println();
		for(int i=0;i<30;i++) {
			if(rb.contains(i)) {
				System.out.println(rb.get(i) +": " + " l: " + rb.get(i).left +
						" r: " + rb.get(i).right);
			}
		}
*/
	}

}
