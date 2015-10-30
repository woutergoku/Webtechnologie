package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Model {

	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Rating> ratings = new ArrayList<Rating>();
	private HashMap<String, User> givenTokens = new HashMap<String, User>();
	private ArrayList<MovieRate> movieRates = new ArrayList<MovieRate>();
	
	public Model() {
		initMovies();
		User user = new User("Admin", "", "Inistator", "Admin", "admin123");
		addUser(user);
	}
	
	/**
	 * Returns a list of all users.
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Returns a list of Ratings
	 * @return ArrayList<Rating>
	 */
	public ArrayList<Rating> getRatings(String token) {
		ArrayList<Rating> ownRatings = new ArrayList<Rating>();
		
		String username = getUsernameFromToken(token);
		
		for(Rating rating : ratings) {
			if(rating.getNickname().equals(username)) {
				ownRatings.add(rating);
			}
		}
		return ownRatings;
	}

	/**
	 * Returns a list with all movies.
	 * @return ArrayList<Movie>
	 */
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	/**
	 * Returns a Movie with the given id.
	 * @param id int
	 * @return Movie if found, null when not.
	 */
	public Movie getMovieById(int id) {
		for(Movie mov : movies) {
			if(mov.getImdbTT() == id) {
				return mov;
			}
		}
		return null;
	}
	
	/**
	 * Returns a Movie with the given title.
	 * @param title String
	 * @return Movie if found, null when not.
	 */
	public ArrayList<Movie> getMovieByTitle(String title) {
		ArrayList<Movie> moviesFound = new ArrayList<Movie>();
		for(Movie mov : movies) {
			if(mov.getTitle().equals(title)) {
				moviesFound.add(mov);
			}
		}
		return moviesFound;
	}
	
	/**
	 * Adds an User to the list.
	 * @param user User
	 */
	public void addUser(User user) {
		users.add(user);
	}
	
	/**
	 * Checks if the username and password are correct and then gives a Token back.
	 * @param username String
	 * @param password String
	 * @return Token String
	 */
	public String loginUser(String username, String password) {
		for(User user : users) {
			if(user.getNickName().equals(username) && user.getPassword().equals(password)) {
				String token = randomToken();
				
				if(!checkToken(token)) {
					if(!givenTokens.containsValue(user)) {
						givenTokens.put(token, user);
						return token;
					}
					else {
						for(String key : givenTokens.keySet()) {
							if(givenTokens.get(key).getNickName().equals(username)) {
								return key;
							}
						}
					}
				} else {
					loginUser(username, password);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Initiliazes some Movies into the Model.
	 */
	public void initMovies() {
		Movie mov1 = new Movie(1, 1, "Harry Potter en de Steen der Wijzen", "16-11-2001", 152, "Daniel Radcliffe", "Fantasie");
		Movie mov2 = new Movie(2, 2, "Harry Potter en de Geheime Kamer", "13-11-2002", 161, "Daniel Radcliffe", "Fantasie");
		Movie mov3 = new Movie(3, 3, "Harry Potter en de Gevangene van Azkaban", "31-05-2004", 142, "Daniel Radcliffe", "Fantasie");
		Movie mov4 = new Movie(4, 4, "Harry Potter en de Vuurbeker", "18-11-2005", 157, "Daniel Radcliffe", "Fantasie");
		Movie mov5 = new Movie(5, 5, "Harry Potter en de Orde van de Feniks", "13-07-2007", 138, "Daniel Radcliffe", "Fantasie");
		Movie mov6 = new Movie(6, 6, "Harry Potter en de Halfbloed Prins", "15-07-2009", 153, "Daniel Radcliffe", "Drama / Fantasie / Avontuur");
		Movie mov7 = new Movie(7, 7, "Harry Potter en de Relieken van de Dood deel 1", "17-11-2010", 147, "Daniel Radcliffe", "Fantasie / Avontuur");
		Movie mov8 = new Movie(8, 8, "Harry Potter en de Relieken van de Dood deel 2", "13-07-2011", 130, "Daniel Radcliffe", "Fantasie / Avontuur");
		Movie mov9 = new Movie(9, 9, "Spy", "05-06-2015", 119, "Paul Feig", "Comedie / Actie");
		Movie mov10 = new Movie(10, 10, "Fifty Shades of Grey", "13-02-2015", 125, "Sam Taylor-Johnson", "Drama");
		Movie mov11 = new Movie(11, 11, "Jupiter Ascending", "06-02-2015", 127, "Andy Wachowski, Lana Wachowski", "Science-Fiction");
		
		movies.add(mov1);
		movies.add(mov2);
		movies.add(mov3);
		movies.add(mov4);
		movies.add(mov5);
		movies.add(mov6);
		movies.add(mov7);
		movies.add(mov8);
		movies.add(mov9);
		movies.add(mov10);
		movies.add(mov11);
	}
	
	/**
	 * Creates a random Token.
	 * @return Token String
	 */
	public String randomToken(){
		
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		int len = 15;
		
		StringBuilder sb = new StringBuilder(len);
		for( int i = 0; i < len; i++ ) {
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		}
		
		return sb.toString();
	}
	
	/**
	 * Checks if the token already exists.
	 * @param token String
	 * @return true if exists, false when not.
	 */
	public boolean checkToken(String token) {
		return givenTokens.containsKey(token);
	}
	
	/**
	 * Adds a rating to the list.
	 * @param rating 	Rating
	 */
	public void addRating(Rating rating) {
		if(!doesRatingExists(rating)) {
			if(doesMovieExists(rating.getImdbtt())) {
				if(ratingInBounds(rating.getRating())) {
					ratings.add(rating);
				}
			}
		}
		
	}
	
	/**
	 * Returns the Nickname of the User based on the given Token.
	 * @param token		String
	 * @return Nickname	String
	 */
	public String getUsernameFromToken(String token) {
		
		if(checkToken(token)) {
			for(String searchToken : givenTokens.keySet()) {
				if(searchToken.equals(token)) {
					return givenTokens.get(searchToken).getNickName();
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the User based on the given nickname
	 * @param nickname	String
	 * @return User		User
	 */
	public User getUserByNickname(String nickname) {
		for(User user : users) {
			if(user.getNickName().equals(nickname)) {
				return user;
			}
		}
		
		return null;
	}
	
	/**
	 * Checks if the user already made a Rating on the Movie
	 * @param givenRating	Rating
	 * @return true if there is a Rating, false if not.
	 */
	public boolean doesRatingExists(Rating givenRating) {
		for(Rating rating : ratings) {
			if(rating.getImdbtt() == givenRating.getImdbtt() && rating.getNickname().equals(givenRating.getNickname())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if the given movie Exists
	 * @param movieid	int
	 * @return true if exists, false if not.
	 */
	public boolean doesMovieExists(int movieid) {
		for(Movie mov : movies) {
			if(mov.getImdbTT() == movieid) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the rating based on the given movieid and token.
	 * @param movieid	int
	 * @param token		String
	 * @return Rating	Rating
	 */
	public Rating getRatingFromId(int movieid, String token) {
		for(Rating rating : ratings) {
			if(rating.getImdbtt() == movieid && rating.getNickname().equals(getUsernameFromToken(token))) {
				return rating;
			}
		}
		
		return null;
	}
	
	/**
	 * Updates the Rating based on the given rating, movieid and token
	 * @param rating	double
	 * @param movieid	int
	 * @param token		String
	 * @return The updated Rating.
	 */
	public Rating updateRating(double rating, int movieid, String token) {
		Rating rat = getRatingFromId(movieid, token);
		rat.setRating(rating);
		
		return getRatingFromId(movieid, token);
	}
	
	/**
	 * Checks if the rating is in bounds
	 * @param rating	double
	 * @return true if in bounds, false if not.
	 */
	public boolean ratingInBounds(double rating) {
		if(rating >= 0.5 && rating <= 5) {
			if((rating % 0.5) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Deletes a rating based on given movieid and token
	 * @param movieid	int
	 * @param token		String
	 */
	public void deleteRating(int movieid, String token) {
		Rating rating = getRatingFromId(movieid, token);
		
		ratings.remove(rating);
	}
	
	/**
	 * Gets a list with all movies that have a rating and their average rating.
	 * @return movierates	ArrayList<MovieRate>
	 */
	public ArrayList<MovieRate> getMovieRates() {
		ArrayList<Movie> movies = getMoviesWithRating();
		movieRates.clear();
		
		for(Movie mov : movies) {
			MovieRate movieRate = new MovieRate(mov, getAverageRatingOfMovie(mov));
			movieRates.add(movieRate);
		}
		
		return movieRates;
	}
	
	/**
	 * Returns a list with all movies that have a rating.
	 * @return movies	ArrayList<Movies>
	 */
	public ArrayList<Movie> getMoviesWithRating() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		for(Rating rat : ratings) {
			Movie mov = getMovieById(rat.getImdbtt());
			if(!movies.contains(mov)) {
				movies.add(mov);
			}
		}
		
		return movies;
	}
	
	/**
	 * Gets the average rating of a movie.
	 * @param mov	Movie
	 * @return average	double
	 */
	public double getAverageRatingOfMovie(Movie mov) {
		double total = 0;
		double counter = 0;
		
		for(Rating rat : ratings) {
			if(rat.getImdbtt() == mov.getImdbTT()) {
				total += rat.getRating();
				counter++;
			}
		}
		
		return total / counter;
	}
}
