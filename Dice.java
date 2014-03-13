/* Ditu Alexandru 323 CA Tema 3 PA */
public class Dice {
	int status[];
	int dist;
	/* pozitia zarului in cadrul tablei
	 * imi trebuie aceste date deoarece in coada trebuie sa stiu
	 * din ce casuta provine zarul */
	int x, y, no;
	
	/* copy constructor */
	public Dice (Dice d) {
		status = new int[6];
		for (int i = 0; i < 6; i++) {
			status[i] = d.status[i];
		}
		dist = d.dist;
	}
	
	public Dice (int status[]) {
		this.status = new int[6];
		for (int i = 0; i < 6; i++) {
			this.status[i] = status[i];
		}
		
		dist = 100000;
	}
	
	public void moveUp () {
		int newStatus[] = new int[6];
		newStatus[0] = status[1];
		newStatus[1] = status[5];
		newStatus[2] = status[2];
		newStatus[3] = status[3];
		newStatus[4] = status[0];
		newStatus[5] = status[4];
		
		status = newStatus;		
	}
	
	public void moveDown () {
		int newStatus[] = new int[6];
		newStatus[0] = status[4];
		newStatus[1] = status[0];
		newStatus[2] = status[2];
		newStatus[3] = status[3];
		newStatus[4] = status[5];
		newStatus[5] = status[1];
		
		status = newStatus;		
	}
	
	public void moveLeft () {
		int newStatus[] = new int[6];
		newStatus[0] = status[2];
		newStatus[1] = status[1];
		newStatus[2] = status[5];
		newStatus[3] = status[0];
		newStatus[4] = status[4];
		newStatus[5] = status[3];
		
		status = newStatus;		
	}
	
	public void moveRight () {
		int newStatus[] = new int[6];
		newStatus[0] = status[3];
		newStatus[1] = status[1];
		newStatus[2] = status[0];
		newStatus[3] = status[5];
		newStatus[4] = status[4];
		newStatus[5] = status[2];
		
		status = newStatus;		
	}
	
	public void makeMove (int dir) {
		if (dir == 0) {
			moveUp();
		}
		if (dir == 1) {
			moveRight();
		}
		if (dir == 2) {
			moveDown();
		}
		if (dir == 3) {
			moveLeft();
		}
		
	}
	
	/* intoarce fata de sus a zarului, adica scorul casutei respective */
	public int getScore () {
		return status[0];
	}
	
	public boolean isEqual (Dice d) {
		boolean rezult = true;
		for (int i = 0; i < 6; i++) {
			if (status[i] != d.status[i]) {
				rezult = false;
				break;
			}
		}
		return rezult;
	}
	
	/* seteza distanta de la nodul sursa la nodul curent */
	public void setDistance (int d) {
		dist = d;
	}
	
	public int getDistance () {
		return dist;
	}
}
