package it.polito.tdp.extflightdelays.model;

public class StatiPeso implements Comparable<StatiPeso> {

	
	private String stato;
	private int peso;
	
	
	public StatiPeso(String stato, int peso) {
		super();
		this.stato = stato;
		this.peso = peso;
	}


	public String getStato() {
		return stato;
	}


	public void setStato(String stato) {
		this.stato = stato;
	}


	public int getPeso() {
		return peso;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}


	@Override
	public int compareTo(StatiPeso o) {
		// TODO Auto-generated method stub
		return (this.getPeso()-o.getPeso())*-1;
	}


	@Override
	public String toString() {
		return "StatiPeso [stato=" + stato + ", peso=" + peso + "]";
	}
	
	
	
	
	
}
