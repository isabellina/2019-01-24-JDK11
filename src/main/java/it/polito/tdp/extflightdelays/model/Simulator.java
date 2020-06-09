package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {
	
	//Coda eventi
	private PriorityQueue<Event> queue;
	
	//Parametri di simulazione
	private Integer T = 10;
	private Integer G = 10;
	
	private Model model;
	
	//Parametri da stimare
	private Map<String, StatoTuristi> statiTuristi;
	
	
	public void init(Integer T, Integer G, Model model, String source) {
		this.T = T;
		this.G = G;
		this.model = model;
		
		this.queue = new PriorityQueue<Event>();
		this.statiTuristi = new HashMap<String, StatoTuristi>();
		for(String s : this.model.getStati()) {
			if(s.equals(source)) {
				statiTuristi.put(source, new StatoTuristi(source, this.T));
			} else {
				this.statiTuristi.put(s,  new StatoTuristi(s, 0));
			}
		}
		for(int i = 1; i <= this.G; i++)
			this.queue.add(new Event(i));
		
		this.model = model;
		
	}
	
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			this.processEvent(e);
		}
	}


	private void processEvent(Event e) {
		for(StatoTuristi st : this.statiTuristi.values()) {
			if(st.getTuristi() > 0) {
				List<Adiacenza> vicini = this.model.getAdicenze(st.getState());
				Integer somma = 0;
				for(Adiacenza a : vicini)
					somma += a.getPeso();
				
				Random r = new Random();
				Integer numTuristi = st.getTuristi();
				for(int i = 0; i < numTuristi; i++) {
					Double prob = r.nextDouble()*somma;
					Integer cumulata = 0;
					Integer key = -1;
					while(cumulata < prob) {
						key++;
						cumulata += vicini.get(key).getPeso();
					}
					this.statiTuristi.get(vicini.get(key).getState2()).increaseTuristi();
					st.decreaseTuristi();
				}
			}	
		}
	}
	
	
	public List<StatoTuristi> getStatiTuristi() {
		List<StatoTuristi> stati = new ArrayList<StatoTuristi>(this.statiTuristi.values());
		stati.sort(null);
		return stati;
	}
	
		
}
