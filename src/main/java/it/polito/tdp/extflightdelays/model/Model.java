package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> graph;
	private ExtFlightDelaysDAO dao;
	private Simulator sim;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
	}
	
	
	public List<String> buildGrah() {
		this.graph = new DirectedWeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.loadAllStates());
		
		for(Adiacenza a : this.dao.getAdiacenza()) {
			if(this.graph.getEdge(a.getState1(), a.getState2()) == null)
				Graphs.addEdge(this.graph, a.getState1(), a.getState2(), a.getPeso());
		}
		
		List<String> vertex = new ArrayList<String>(this.graph.vertexSet());
		vertex.sort(null);
		return vertex;
	}


	public Integer getNumVertex() {
		return this.graph.vertexSet().size();
	}
	
	public Integer getNumEdge() {
		return this.graph.edgeSet().size();
	}
	
	
	public List<String> getStati() {
		List<String> vertex = new ArrayList<String>(this.graph.vertexSet());
		vertex.sort(null);
		return vertex;
	}


	public List<Adiacenza> getAdicenze(String state) {
		List<Adiacenza>	result = new ArrayList<Adiacenza>();
		for(String vicino : Graphs.successorListOf(this.graph, state)) {
			result.add(new Adiacenza(state, vicino, (int) this.graph.getEdgeWeight(this.graph.getEdge(state, vicino))));
		}
		
		result.sort(null);
		return result;
	}
	
	
	public List<StatoTuristi> simula(Integer T, Integer G, String source) {
		this.sim = new Simulator();
		this.sim.init(T, G, this, source);
		this.sim.run();
		
		return this.sim.getStatiTuristi();
	}

}
