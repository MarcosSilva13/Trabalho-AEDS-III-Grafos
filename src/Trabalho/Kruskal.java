package Trabalho;

public class Kruskal {
    int[] pai;

    public Kruskal(int numVertices) {
        pai = new int[numVertices];
        for (int i = 0; i < numVertices; i++) { //inicializando o vetor de pais com os vertices
            pai[i] = i;
        }
    }

    public int encontraPai(int vertice) { //busca pelo pai de um vertice ou antecessor
        //System.out.println("V: " + vertice + " pai: " + pai[vertice]);
        if (pai[vertice] == vertice) {
            return vertice;
        }
        return encontraPai(pai[vertice]);
    }

    public void uniao(int ori, int dest) {
        int paiOri = encontraPai(ori); //encontra o pai da origem
        int paiDest = encontraPai(dest); //encontra pai do destino
        pai[paiOri] = paiDest; //faz o vertice da origem ter como pai o vertice destino, assim os vertices ficam conectados
    }
}
