
	package it.polito.tdp.extflightdelays.model;

	import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
	import org.jgrapht.Graphs;
	import org.jgrapht.graph.DefaultWeightedEdge;
	import org.jgrapht.graph.SimpleDirectedWeightedGraph;

	import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

	public class Model {
		
		private Graph<String,DefaultWeightedEdge> grafo;
		private ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		
		public Model() {
			
		}
		
		public void creaGrafo() {
			this.grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
			Graphs.addAllVertices(this.grafo,this.dao.getVertici());
			
			for(Arco a : this.dao.getArchi()) {
				DefaultWeightedEdge edge = this.grafo.getEdge(a.getS1(), a.getS2());
				if(edge==null) {
					this.grafo.addEdge(a.getS1(), a.getS2());
					this.grafo.setEdgeWeight(a.getS1(), a.getS2(), a.getPeso());
				}
				DefaultWeightedEdge edge2 = this.grafo.getEdge( a.getS2(),a.getS1());
				if(edge2==null) {
					Graphs.addEdgeWithVertices(this.grafo, a.getS2(), a.getS1(), a.getPeso());
				}
			}
			System.out.println(this.grafo);
		}
		
		public int nVertici() {
			return this.grafo.vertexSet().size();
		}


		public int nArchi() {
			return this.grafo.edgeSet().size();
		}
		
		public List<String> getVertici(){
			List<String> v = new LinkedList<String>(this.dao.getVertici());
			return v;
		}
		
		public List<StatiPeso> getStatiAdiacenti(String stato){
			int peso=0;
			List<StatiPeso> ltemp = new LinkedList<StatiPeso>();
			for(String s: Graphs.predecessorListOf(this.grafo, stato)) {
				 peso = (int) this.grafo.getEdgeWeight(this.grafo.getEdge(s, stato));
				StatiPeso r = new StatiPeso(s,peso);
				ltemp.add(r);
			}
			Collections.sort(ltemp);
			return ltemp;
		}
		

	}


