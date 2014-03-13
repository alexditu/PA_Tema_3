/* Ditu Alexandru 323 CA Tema 3 PA */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;


public class P1 {

	/* functie ce creeaza graful specific echipei qTeam */
	public static Graph createGraph (InputData data, int qTeam) {
		
		int graphSize = (((data.n - 1) * (data.n - 2))/ 2) + 2 + (data.n - 1);
		int drain = graphSize - 1;
		Graph g = new Graph (Graph.Type.DIRECTED, graphSize);
		
		int k = 1;
		ArrayList <Node> nodes = new ArrayList <Node> ();
		
		/* adaug acum muchiile de la sursa in nodurile de tipul g(i,j)*/
		for (int i = 0; i < data.n; i++) {
			for (int j = i + 1; j < data.n; j++) {
				if (i != qTeam && j != qTeam) {
					g.addEdge(0, k, 2 * data.games[i][j]);
					Node n = new Node (i, j, 0, k);
					nodes.add(n);
					k ++;
				}
			}
		}
		
		ArrayList <Node> nodes2 = new ArrayList <Node> ();
		/* adaug muchiile de la nodurile de tipul i, i!=x, la drena */
		for (int i = 0; i < data.n; i++) {
			if (i != qTeam) {
				int cost = data.score[qTeam] + 2 * data.gamesLeft[qTeam] - data.score[i];
				if (cost < 0) {
					System.out.println("Cost negativ. FALSE");
					return null;
				}
				g.addEdge(k, drain, cost);
				Node n = new Node (i, i, 1, k);
				nodes2.add(n);
				k++;
			}
		}
		
		
		/* adaug muchiile dintre nodurile g(i,j) si nodurile i si j */
		for (Node crt : nodes) {
			if (crt.type == 0) {
				for (int i = 0; i < nodes2.size(); i++) {
					Node n = nodes2.get(i);
					if (n.id1 == crt.id1) {
						g.addEdge(crt.graphId, n.graphId, Graph.INFINITY);
					}
					if (n.id1 == crt.id2) {
						g.addEdge(crt.graphId, n.graphId, Graph.INFINITY);
					}
				}
			}
		}
		
		return g;
		
	}
	
	/* functie ce verfica daca o echipa poate sa castige */
	public static String winTest (Graph g) {
		
		String answer = "";
		int drain = g.getSize() - 1;
		
		MaxFlow mf = new MaxFlow ();
		mf.setSource(0);
		mf.setDestination(drain);
		
		mf.maxFlow(g, 0, drain);
		int found = 1;
		
		/* testez daca muchiile ce ies din sursa sunt saturate */
		  for (int i = 1; i < g.getSize(); i++) {
			  if (g.capacityMatrix[0][i] != 0) {
				  answer = "FALSE";
				  found = 0;
				  break;
			  }
		  }
		  if (found == 1) {
			  answer = "TRUE";
		  }
		  
		  return answer;
	}
	
	/* functie ce verfica toate intrebarile:
	 * poate echipa questions[i] sa castige? TRUE or FALSE
	 */
	public static void testAllQuestions (InputData data, int[] questions) throws Exception {
		
		BufferedWriter bw = new BufferedWriter (new FileWriter ("fotbal.out"));
		
		for (int i = 0; i < questions.length; i++) {
			String answer;
			
			Graph g = createGraph (data, questions[i]);
			if (g == null) {
				bw.write("FALSE");
				bw.newLine();
				continue;
			}
			answer = winTest (g);
			bw.write(answer);
			bw.newLine();
		}
		bw.close();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		InputData data = new InputData ("fotbal.in");
		data.readData();
		
		int[] questions = data.getQuestions();
		testAllQuestions (data, questions);
	}

}
