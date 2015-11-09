package br.cefetrj.alggraf;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

public class Grafo {
    private UndirectedGraph<Turma, DefaultEdge> g = new SimpleGraph<Turma, DefaultEdge>(DefaultEdge.class);
    static final double DEFAULT_EDGE_WEIGHT=19;
    private DefaultWeightedEdge e1;

    public void addVertex(Turma t) {
        g.addVertex(t);
    }

    public void addEdge(Turma v1,Turma v2) {
        g.addEdge(v1, v2);
    }

    public UndirectedGraph<Turma, DefaultEdge> getGraph() {
        return g;
    }

    
    
}