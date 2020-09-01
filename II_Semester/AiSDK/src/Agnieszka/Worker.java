package Agnieszka;

public class Worker {
	
	int id;
	String name;
	double workTime;
	
	public Worker(int i, String n, double wt) {
		id=i;
		name=n;
		workTime=wt;
	}

	public void setName(String n) {
		name =n;
	}
	
	public void setId(int i) {
		id =i;
	}
	
	public void setWorkTime(double wt) {
		workTime =wt;
	}
	
	public String toString() {
		return id + "\t" + name + "\t" + workTime;
	}
	
	
}
