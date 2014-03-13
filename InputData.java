/* Ditu Alexandru 323 CA Tema 3 PA */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class InputData {
	int n, p, k, q;
	int score[]; //nr de puncte pe care le are echipa i
	int question[]; 
	int gamesLeft[]; //nr de jocuri pe care le mai are de jucat echipa i
	int games[][];
	String filename;
	
	public InputData (String filename) {
		this.filename = filename;
	}
	
	public void readData () throws IOException {
		BufferedReader br = new BufferedReader (new FileReader (filename));
		String line = br.readLine();
		StringTokenizer stk = new StringTokenizer (line);
		int v[] = new int[4];
		int i = 0;
		while (stk.hasMoreElements()) {
			v[i] = Integer.parseInt(stk.nextToken());
			i++;
		}
		n = v[0];
		p = v[1];
		k = v[2];
		q = v[3];
		
		score		= new int [n];
		question	= new int [q];
		gamesLeft	= new int [n];
		games 		= new int [n][n];
		
		int noGamesToPlay = (n - 1) * k;
		
		for (i = 0; i < n; i++) {
			score[i] = 0;
			gamesLeft[i] = noGamesToPlay;
			for (int j = i + 1; j < n; j++) {
				games [i][j] = k;
				games [j][i] = k;
			}
		}
		
		int t1, t2;
		String result;
		
		/* citesc scorurile meciurilor jucate */
		for (i = 0; i < p; i++) {
			line = br.readLine();
			if (line == null) {
				System.out.println("Error: Bad input!!!");
				return;
			}
			stk = new StringTokenizer (line);
			t1 = Integer.parseInt(stk.nextToken());
			t2 = Integer.parseInt(stk.nextToken());
			result = stk.nextToken();
			if (result.equals("WIN")) {
				score[t1] += 2;
			} else {
				score[t1] += 1;
				score[t2] += 1;
			}
			/* marchez si meciul ca jucat */
			gamesLeft[t1] --;
			gamesLeft[t2] --;
			games[t1][t2] --;
			games[t2][t1] --;
		}
		
		/* citesc intrebarile */
		for (i = 0; i < q; i++) {
			line = br.readLine();
			if (line == null) {
				System.out.println("Error: Bad input!!!");
				return;
			}
			question[i] = Integer.parseInt(line);
		}
	}
	
	public int[] getScores () {
		return score;
	}
	
	public int[] getQuestions () {
		return question;
	}
}
