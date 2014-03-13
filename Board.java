/* Ditu Alexandru 323 CA Tema 3 PA */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;


public class Board {
	
	Dice board[][][];
	int n, m;
	int dir[][];
	int startX, startY, endX, endY;
	int no[][];
	
	public Board (int n, int m, Dice d, int startX, int startY, int endX, int endY) {
		this.n = n;
		this.m = m;
		
		no = new int[n][m];
		
		board = new Dice[n][m][24*4];

		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		
		board[startX][startY][0] = d;
		board[startX][startY][0].x = startX;
		board[startX][startY][0].y = startY;
		board[startX][startY][0].no = 0;
		
		initDirections();
	}
	
	public void initDirections () {
		dir = new int[4][];
		for (int i = 0; i < 4; i++) {
			dir[i] = new int[2];
		}
		dir[0][0] = -1;
		dir[0][1] = 0;
		
		dir[1][0] = 0;
		dir[1][1] = 1;
		
		dir[2][0] = 1;
		dir[2][1] = 0;
		
		dir[3][0] = 0;
		dir[3][1] = -1;
	}
	
	
	public boolean isValidMove (int direction, int x, int y) {
		
		int newX, newY;
		
		newX = x + dir[direction][0];
		newY = y + dir[direction][1];
		
		return ((newX >= 0 && newX < n) && (newY >= 0 && newY < m));		
	}
	
	/* aceasta functie aplica algoritmul lui Dijkstra, insa e putin modificat
	 * deoarece muchiile sunt create pe parcurs, si retin pentru fiecare nod
	 * si starea zarului, iar fiecare stare genereaza o noua muchie (chiar daca
	 * a mai fost procesat nodul respectiv).
	 */
	public void Dijkstra () {
		LinkedList <Dice> q = new LinkedList <Dice> ();
		
		board[startX][startY][0].dist = board[startX][startY][0].getScore(); 
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				no[i][j] = 0;
			}
		}
		
		/* iterez prin toti vecinii nodului sursa (pot fi maxim 4) */
		for (int i = 0; i < 4; i++) {
			if (isValidMove(i, startX, startY)) {
				int x, y;
				x = startX + dir[i][0];
				y = startY + dir[i][1];
				
				board[x][y][no[x][y]] = new Dice (board[startX][startY][0]);
				board[x][y][no[x][y]].x = x;
				board[x][y][no[x][y]].y = y;
				board[x][y][no[x][y]].no = no[x][y];
				
				board[x][y][no[x][y]].makeMove (i);
				
				/* actualizez distanta de la sursa la nod */
				board[x][y][no[x][y]].dist = board[startX][startY][0].dist +
											 board[x][y][no[x][y]].getScore();
				
				/* introduc nodul in coada */
				q.add(board[x][y][no[x][y]]);
				no[x][y] ++;
			}
		}
		
		while (!q.isEmpty()) {
			Dice u = q.remove(0);
		//	selected [u.x][u.y][u.no]= 1; 
			
			/* for-each v in vecinii nodului u
			 * eu trbuie sa "creez" vecinii */
			for (int i = 0; i < 4; i++) {
				if (isValidMove(i, u.x, u.y)) {
					int x, y;
					x = u.x + dir[i][0];
					y = u.y + dir[i][1];
					
					Dice node;
					
					node = new Dice (board[u.x][u.y][u.no]);
					node.x = x;
					node.y = y;
					node.no = no[x][y];
					
					node.makeMove (i);
					node.dist = board[u.x][u.y][u.no].dist + node.getScore();
					
					int update = 0;

					for (int j = 0; j < no[x][y]; j++) {
						if (board[x][y][j].isEqual(node)) {
							update = 1;
							if (board[x][y][j].dist > node.dist) {
								board[x][y][j] = node;
								break;
							}
						}
					}
					
					if (update == 0) {
						board[x][y][no[x][y]] = node;
						no[x][y] ++;
						/* daca am ajuns la destinatie nu-l mai bag in coada */
						if (x != endX || y != endY) {
							q.add(node);
						}
					}
				}
			}
		}
	}
}
