package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {

	private String nickname;
	private int imdbtt;
	private double rating;
	
	public Rating() {
		
	}

	public Rating(String nickname, int imdbtt, double rating) {
		super();
		this.nickname = nickname;
		this.imdbtt = imdbtt;
		this.rating = rating;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getImdbtt() {
		return imdbtt;
	}

	public void setImdbtt(int imdbtt) {
		this.imdbtt = imdbtt;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}
