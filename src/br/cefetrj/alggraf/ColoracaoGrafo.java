package br.cefetrj.alggraf;

import java.util.Map;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ChromaticNumber;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class ColoracaoGrafo {

	public static void main(String[] args) {
		UndirectedGraph<Object, DefaultEdge> completeGraph = 
				new SimpleGraph<Object, DefaultEdge>(
				DefaultEdge.class);
		
		CompleteGraphGenerator<Object, DefaultEdge> completeGenerator = 
				new CompleteGraphGenerator<Object, 
											DefaultEdge>(3);
		
		completeGenerator.generateGraph(completeGraph,
				new ClassBasedVertexFactory<Object>(Object.class),
				null);

		int chi = ChromaticNumber.findGreedyChromaticNumber(completeGraph);
		
		System.out.println("Número cromático = " + chi);

		Map<Integer, Set<Object>> coloring = ChromaticNumber
				.findGreedyColoredGroups(completeGraph);

		for (Integer colorId : coloring.keySet()) {
			System.out.println("Vértices de cor " + colorId);
			Set<Object> vertices = coloring.get(colorId);
			for (Object object : vertices) {
				System.out.println(object.toString());
			}
		}
	}
}
