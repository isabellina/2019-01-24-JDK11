package it.polito.tdp.extflightdelays.model;

public class Adiacenza implements Comparable<Adiacenza> {
	
	private String state1;
	private String state2;
	private Integer peso;
	
	public Adiacenza(String state1, String state2, Integer peso) {
		super();
		this.state1 = state1;
		this.state2 = state2;
		this.peso = peso;
	}

	public String getState1() {
		return state1;
	}

	public String getState2() {
		return state2;
	}

	public Integer getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Adiacenza o) {
		return -this.peso.compareTo(o.peso);
	}
	
	public String toString() {
		return state2+" | "+peso;
	}

}
