package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MovieRate {

	private Movie movie;
	private double averageRating;
	
	public MovieRate() {
		
	}

	public MovieRate(Movie movie, double averageRating) {
		super();
		this.movie = movie;
		this.averageRating = averageRating;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
	
}
