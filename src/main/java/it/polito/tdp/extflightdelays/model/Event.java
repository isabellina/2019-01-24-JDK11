package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event> {
	
	private Integer day;

	public Event(Integer day) {
		super();
		this.day = day;
	}

	public Integer getDay() {
		return day;
	}

	@Override
	public int compareTo(Event o) {
		return this.day-o.day;
	}
	
	

}
