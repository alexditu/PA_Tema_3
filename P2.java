/* Ditu Alexandru 323 CA Tema 3 PA */
import java.io.BufferedWriter;
import java.io.FileWriter;


public class P2 {
	
	public static void main(String[] args) throws Exception {
		
		InputDataP2 data = new InputDataP2 ("zar.in");
		data.readData();
		Dice dice = new Dice (data.value);
		
		Board b = new Board (data.n, data.m, dice, data.startX, data.startY,
							 data.endX, data.endY);
		
		b.Dijkstra();
		int no = b.no[data.endX][data.endY];
		int min = 100000;
		for (int i = 0; i < no; i++) {
			if (b.board[data.endX][data.endY][i].dist < min) {
				min = b.board[data.endX][data.endY][i].dist;
			}
		}
		BufferedWriter bw = new BufferedWriter (new FileWriter ("zar.out"));
		bw.write(min + "");
		bw.newLine();
		bw.close();
		
	}

}
