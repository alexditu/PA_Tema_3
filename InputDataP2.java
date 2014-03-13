/* Ditu Alexandru 323 CA Tema 3 PA */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;


public class InputDataP2 {
	
	int n, m;
	int value[];
	int startX, startY, endX, endY;
	String filename;
	
	public InputDataP2 (String filename) {
		this.filename = filename;
		value = new int[6];
	}
	
	/* functie ce parseaza datele de intrare */
	public void readData () throws Exception {
		BufferedReader br = new BufferedReader (new FileReader (filename));
		String line = br.readLine();
		StringTokenizer stk = new StringTokenizer (line);
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		
		line = br.readLine();
		stk = new StringTokenizer (line);
		
		for (int i = 0; i < 6; i++) {
			value[i] = Integer.parseInt(stk.nextToken());
		}
		
		line = br.readLine();
		stk = new StringTokenizer (line);
		startX = Integer.parseInt(stk.nextToken());
		startY = Integer.parseInt(stk.nextToken());
		
		line = br.readLine();
		stk = new StringTokenizer (line);
		endX = Integer.parseInt(stk.nextToken());
		endY = Integer.parseInt(stk.nextToken());
		
		br.close();
		
	}
}
