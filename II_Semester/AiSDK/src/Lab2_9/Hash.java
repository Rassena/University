package Lab2_9;

public class Hash {
	private Element[] array;
	private int amountOfElements;
	int trafione;
	int probyTrafionych;
	int probyChybionych;
	
	public Hash(int size) {
		array = new Element[size]; 
		amountOfElements=0;
	}
	
	private class Element{
		int value;
		int key;
		boolean deleted=false;
		public Element(int key,int value) {
			this.value=value;
			this.key=key;
		}
		
		public String toString() {
			if(!deleted)
				return ("Key: " + key + " Value: " + value);
			return null;
		}
	}
	
	public int h(int key) {
		return key*key+15;
	}
	
	public int h2(int key) {
		return (int) Math.sqrt(key)*7;
	}
	
	public void put(int key, int value) {
		boolean putted = false;
		int index = h(key)%size();
		for(int i=0;i<size()&&!putted;i++) {
			if(array[(index+i)%size()]==null) {
				array[(index+i)%size()]= new Element(key,value);
				putted=true;
			}
		}
		amountOfElements++;
//		resize();
	}
	
	public void put2(int key, int value) {
		boolean putted = false;
		int index = h(key)%size();
		for(int i=0;!putted;i++) {
			if(array[(index+i*h2(key))%size()]==null) {
				array[(index+i*h2(key))%size()]= new Element(key,value);
				putted=true;
			}
		}
		amountOfElements++;
//		resize2();
	}
	
	public int remove(int key) {
		if(containsKey(key)) {
			int value = 0;
			boolean found=false;
			int index=h(key);
			for(int i=0;i<size()&&!found;i++)
				if(array[(index+i)%size()].key==key) {
					found=true;
					value=array[(index+i)%size()].value;
					array[(index+i)%size()].deleted=true;
				}
		amountOfElements--;
//		resize();
		return value;
		}
		System.out.print("Nie znaleziono o kluczu: " + key + " ");
		return 0;
	}
	
	public int remove2(int key) {
		if(containsKey(key)) {
			int value = 0;
			boolean found=false;
			int index=h(key);
			for(int i=0;i<size()&&!found;i++)
				if(array[(index+i*h2(key))%size()].key==key) {
					found=true;
					value=array[(index+i*h2(key))%size()].value;
					array[(index+i*h2(key))%size()].deleted=true;
				}
		amountOfElements--;
//		resize2();
		return value;
		}
		System.out.print("Nie znaleziono o kluczu: " + key + " ");
		return 0;
	}
	
	public int get(int key) {
		if(containsKey(key)) {
			int value = 0;
			boolean found=false;
			int index=h(key);
			for(int i=0;i<size()&&!found;i++) {
				probyTrafionych++;
				if(array[(index+i)%size()].key==key) {
					found=true;
					value=array[(index+i)%size()].value;
				}
			}
			trafione++;
			return value;
		}
//		System.out.print("Nie znaleziono elementu o kluczu: " + key + " ");
		return 0;
	}
	
	public int get2(int key) {
		if(containsKey2(key)) {
			int value = 0;
			boolean found=false;
			int index=h(key);
			for(int i=0;i<size()&&!found;i++) {
				probyTrafionych++;
				if(array[(index+i*h2(key))%size()].key==key) {
					found=true;
					value=array[(index+i*h2(key))%size()].value;
				}
			}
			trafione++;
			return value;
		}
//		System.out.print("Nie znaleziono elementu o kluczu: " + key + " ");
		return 0;
	}
	
	public int size() {
		return array.length;
	}
	
	public void resize() {
		Element[] temp = new Element[size()];
		
		if((double)amountOfElements/size()>=0.8||(double)amountOfElements/size()<=0.2) {
			int tempAmount = amountOfElements;
			for(int i=0;i<size();i++)
				if(array[i]!=null&&!array[i].deleted)
					temp[i]=array[i];
			if((double)amountOfElements/size()>=0.8) {
				array = new Element[size()*2+1];
				for(int i=0;i<temp.length;i++)
					if(temp[i]!=null)
						put(temp[i].key,temp[i].value);
			}
			if((double)amountOfElements/size()<=0.2) {
				array = new Element[size()/2];
				for(int i=0;i<temp.length;i++)
					if(temp[i]!=null)
						put(temp[i].key,temp[i].value);
			}
			amountOfElements=tempAmount;
		}
	}
	
	public void resize2() {
		Element[] temp = new Element[size()];
		
		if((double)amountOfElements/size()>=0.8||(double)amountOfElements/size()<=0.2) {
			int tempAmount = amountOfElements;
			for(int i=0;i<size();i++)
				if(array[i]!=null&&!array[i].deleted)
					temp[i]=array[i];
			if((double)amountOfElements/size()>=0.8) {
				array = new Element[size()*2+1];
				for(int i=0;i<temp.length;i++)
					if(temp[i]!=null)
						put2(temp[i].key,temp[i].value);
			}
			if((double)amountOfElements/size()<=0.2) {
				array = new Element[size()/2];
				for(int i=0;i<temp.length;i++)
					if(temp[i]!=null)
						put2(temp[i].key,temp[i].value);
			}
			amountOfElements=tempAmount;
		}
	}
	
	public boolean isEmpty() {
		if(amountOfElements==0)
			return true;
		return false;
	}
	
	public void dump() {
		for(int i=0;i<size();i++) {
			System.out.println(array[i]);
		}
		System.out.println();
	}
	
	public boolean containsKey(int key) {
		int index=h(key);
		int proby =0;
		for(int i=0;i<amountOfElements;i++) {
			proby++;
			if(array[(index+i)%size()]==null) {
				probyChybionych+=proby;
				return false;
			}
			if(array[(index+i)%size()]!=null&&!array[(index+i)%size()].deleted&&array[(index+i)%size()].key==key)
				return true;
		}
		probyChybionych+=proby;
		return false;
	}
	
	public boolean containsKey2(int key) {
		int index=h(key);
		int proby=0;
		for(int i=0;i<amountOfElements;i++) {
			proby++;
			if(array[(index+i*h2(key))%size()]==null) {
				probyChybionych+=proby;
				return false;
			}
			if(array[(index+i*h2(key))%size()]!=null&&!array[(index+i*h2(key))%size()].deleted&&array[(index+i*h2(key))%size()].key==key)
				return true;
		}
		probyChybionych+=proby;
		return false;
	}
	
	public int getAmountOfElements() {
		return amountOfElements;
	}
}
