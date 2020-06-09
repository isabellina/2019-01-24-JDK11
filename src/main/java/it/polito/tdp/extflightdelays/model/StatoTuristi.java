package it.polito.tdp.extflightdelays.model;

public class StatoTuristi implements Comparable<StatoTuristi> {
	
	private String state;
	private Integer turisti;
	
	public StatoTuristi(String state, Integer turisti) {
		super();
		this.state = state;
		this.turisti = turisti;
	}

	public Integer getTuristi() {
		return turisti;
	}

	public void setTuristi(Integer turisti) {
		this.turisti = turisti;
	}

	public String getState() {
		return state;
	}
	
	public void decreaseTuristi() {
		this.turisti--;
	}
	
	public void increaseTuristi() {
		this.turisti++;
	}
	
	public void decreaseTuristi(Integer turisti) {
		this.turisti -= turisti;
	}
	
	public void increaseTuristi(Integer turisti) {
		this.turisti += turisti;
	}

	@Override
	public int compareTo(StatoTuristi o) {
		return this.state.compareTo(o.state);
	}
	
	public String toString() {
		return state+" | "+turisti;
	}

}
