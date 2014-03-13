/**
 * Proiectarea Algoritmilor, 2013
 * Lab 10: Flux Maxim
 * Ditu Alexandru Mihai 323 CA
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class MaxFlow {

  /* Fisiere de intrare */
  final static String TASK_1_INPUT_FILE = "GraphBinary.in";
  final static String TASK_2_INPUT_FILE = "GraphDegree.in";

  /* Sursa si destinatia pentru flux */
  static int flowSource;
  static int flowDest;

  final static int NONE = -1;
  
  public void setSource (int s) {
	  flowSource = s;
  }
  
  public void setDestination (int t) {
	  flowDest = t;
  }

  public static ArrayList<Integer> bfs(Graph g, int u, int v) {
    /* Ne vom folosi de vectorul de parinti pentru a spune daca un nod a fost
     * adaugat sau nu in coada. */
    ArrayList<Integer> parent = new ArrayList<Integer>(g.getSize());

    for (int i = 0; i < g.getSize(); ++i) {
      parent.add(NONE);
    }

    LinkedList<Integer> q = new LinkedList<Integer>();
    q.add(u);

    while (parent.get(v) == NONE && q.size() > 0) {
      int node = q.get(0);
      q.remove(0);

      for (int i = 0; i < g.getSize(); ++i) {
        if (g.capacityMatrix[node][i] > 0 && parent.get(i) == NONE) {
          parent.set(i, node);
          q.add(i);
        }
      }
    }

    /* Daca nu s-a atins destinatia, atunci nu mai exista drumuri de crestere. */

    if (parent.get(v) == NONE) {
      return new ArrayList<Integer>(0);
    }

    /* Reconstituim drumul de la destinatie spre sursa. */
    ArrayList<Integer> returnValue = new ArrayList<Integer>();
    for (int node = v; ; node = parent.get(node)) {
      returnValue.add(0, node);

      if (node == u) {
        break;
      }
    }

    return returnValue;
  }

  public static int saturate_path(Graph g, ArrayList<Integer> path) {
    /* Niciodata nu ar trebui sa se intample asta pentru ca sursa si destinatia
     * sunt noduri distincte si cel putin unul dintre ele se afla in path. */
    if (path.size() < 2) {
      return 0;
    }

    /* Determinam fluxul maxim prin drum. */
    int flow = g.capacityMatrix[path.get(0)][path.get(1)];

    
    for (int i = 0; i < path.size() - 1; ++i) {
      int u = path.get(i), v = path.get(i + 1);
      /* TODO - Determinati fluxul in functie de capacitata muchiei (u, v) */
      int crt = g.capacityMatrix[u][v];

      if (crt < flow) {
          flow = crt;
      }

    }

    /* Si il saturam in graf. */
    for (int i = 0; i < path.size() - 1; ++i) {
      int u = path.get(i), v = path.get(i + 1);
      /* TODO - Modificati fluxul in functie de capacitatea muchiei (u, v) */
      g.capacityMatrix[u][v] -= flow; //adaug noul flow
      g.capacityMatrix[v][u] += flow;
    }

    /* Raportam fluxul cu care am saturat graful. */
    return flow;
  }

  public static int maxFlow(Graph g, int u, int v) {
    int maxFlow = 0;

    /* Vom incerca in mod repetat sa determinam drumuri de crestere folosind
     * BFS si sa le saturam pana cand nu mai putem determina un astfel de drum in
     * graf. */
    int crt_flow;
    while (true) {
      /* Determina drum de crestere. */
      ArrayList<Integer> saturation_path = bfs(g, u, v);

      /* TODO - In functie de saturation_path determinati daca fluxul trebuie
       * marit sau trebuie iesit din while */
      crt_flow = saturate_path (g, saturation_path);
      if (crt_flow <= 0)
          break;
      
      maxFlow += crt_flow;
    }

    return maxFlow;
  }

  public static void minCut(Graph g, int u, int v) {
    /* Facem o parcurgere BFS din nodul sursa si punem nodurile atinse de
     * parcurgere in source_set. Toate celelalte noduri se vor afla in
     * sink_set. */

    ArrayList<Boolean> in_queue = new ArrayList<Boolean>();
    for (int i = 0; i < g.getSize(); ++i) {
      in_queue.add(false);
    }
    LinkedList<Integer> q = new LinkedList<Integer>();
    q.add(u);
    in_queue.set(u, true);

    /* Rulam BFS din sursa si marcam nodurile atinse. */
    while (q.size() > 0) {
      int node = q.get(0);
      q.remove(0);

      for (int i = 0; i < g.getSize(); ++i) {
        if (in_queue.get(i) == false && g.capacityMatrix[node][i] > 0) {
          in_queue.set(i, true);
          q.add(i);
        }
      }
    }

    System.out.println("The minimum cut associated with the flow yields:");
    /* TODO - In functie de marcajele obtinute de la BFS-ul anterior determinati
     * muchiile ce fac parte din taietura minima */
    for (int i = 0; i < g.getSize(); i++) {
        for (int j = 0; j < g.getSize(); j++) {
            if ((g.adjMatrix[i][j] == true) &&
                (in_queue.get(i) != in_queue.get(j))) {
                System.out.println ("(" + i + ", " + j + ")");
            }
        }
    }

  }
  
  public void printFlow (Graph g) {
	  int i, j;
	  for (i = 0; i < g.capacityMatrix.length; i++) {
		  for (j = 0; j < g.capacityMatrix[0].length; j++) {
			  if (g.adjMatrix[i][j] == true) {
				  System.out.println("flow[" + i + "][" + j + "]= " + g.capacityMatrix[i][j]);
			  }
		  }
	  }
  }

}
