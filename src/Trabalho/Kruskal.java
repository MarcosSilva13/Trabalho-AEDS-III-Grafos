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
        if (pai[vertice] == vertice) {
            return vertice;
        }
        return encontraPai(pai[vertice]);
    }

    public void uniao(int ori, int dest) {
        int paiOri = encontraPai(ori); //encontra o pai da origem
        int paiDest = encontraPai(dest); //encontra pai do destino
        pai[paiOri] = paiDest; //faz o pai da origem ser o pai do destino, assim os vertices ficam conectados
    }
}
