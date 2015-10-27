package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class Movie {

	private int movieId;
	private int imdbTT;
	private String title;
	private String dateOfPublication;
	private int lengthInMinutes;
	private String director;
	private String description;
	
	public Movie() {
		
	}

	/**
	 * Constructor
	 * @param movieId				int
	 * @param imdbTT				int
	 * @param title					String
	 * @param dateOfPublication		String
	 * @param lengthInMinutes		int
	 * @param director				String
	 * @param description			String
	 */
	public Movie(int movieId, int imdbTT, String title, String dateOfPublication, int lengthInMinutes, String director, String description) {
		super();
		this.movieId = movieId;
		this.imdbTT = imdbTT;
		this.title = title;
		this.dateOfPublication = dateOfPublication;
		this.lengthInMinutes = lengthInMinutes;
		this.director = director;
		this.description = description;
	}

	/**
	 * Sets the movieid
	 * @param movieId	int
	 */
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	/**
	 * Gets the ImdbTT
	 * @return imdbTT	int
	 */
	public int getImdbTT() {
		return imdbTT;
	}

	/**
	 * Sets the imdbTT
	 * @param imdbTT	int
	 */
	public void setImdbTT(int imdbTT) {
		this.imdbTT = imdbTT;
	}

	/**
	 * Gets the title
	 * @return title	String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title
	 * @param title	String
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the Date of publication
	 * @return dateofPublication	String
	 */
	public String getDateOfPublication() {
		return dateOfPublication;
	}

	/**
	 * Sets the date of publication
	 * @param dateOfPublication String
	 */
	public void setDateOfPublication(String dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	/**
	 * Gets the length in minutes
	 * @return lengthInMinutes	int
	 */
	public int getLengthInMinutes() {
		return lengthInMinutes;
	}

	/**
	 * Sets the length in minutes
	 * @param lengthInMinutes	int
	 */
	public void setLengthInMinutes(int lengthInMinutes) {
		this.lengthInMinutes = lengthInMinutes;
	}

	/**
	 * Gets the director
	 * @return director String
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Sets the director
	 * @param director	String
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Gets the description
	 * @return description String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets th description
	 * @param description	String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the movieid
	 * @return movieId	int
	 */
	@XmlTransient
	@JsonIgnore
	public int getMovieId() {
		return movieId;
	}
}
