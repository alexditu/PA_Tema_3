/* Ditu Alexandru 323 CA Tema 3 PA */

/* folosesc aceasta clasa pentru a desemna tipurile nodurilor din graf:
 * astfel:
 * type = 0, desemneaza noduri de tipul g(i,j)
 * type = 1, desemneaza noduri de tipul i/j
 * explicatii suplimentare in readme.
 */
public class Node {
	/* id1 id2, corespunzator i si j 
	 * pentru type = 1, e folosit doar campul id1
	 */
	int id1, id2;
	int type;
	/* nr nodului in cadrul grafului */
	int graphId;

	public Node (int id1, int id2, int type, int graphId) {
		this.id1 = id1;
		this.id2 = id2;
		this.type = type;
		this.graphId = graphId;
	}
}


