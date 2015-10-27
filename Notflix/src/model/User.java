package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
public class User {

	private String firstName;
	private String insertion;
	private String lastName;
	private String nickName;
	private String password;

	public User() {
		
	}

	/**
	 * Constructor
	 * @param firstName	String
	 * @param insertion	String
	 * @param lastName	String
	 * @param nickName	String
	 * @param password	String
	 */
	public User(String firstName, String insertion, String lastName, String nickName, String password) {
		super();
		this.firstName = firstName;
		this.insertion = insertion;
		this.lastName = lastName;
		this.nickName = nickName;
		this.password = password;
	}

	/**
	 * Get firstname
	 * @return firstname String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set firstname
	 * @param firstName String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get insertion
	 * @return insertion String
	 */
	public String getInsertion() {
		return insertion;
	}

	/**
	 * Set insertion
	 * @param insertion String
	 */
	public void setInsertion(String insertion) {
		this.insertion = insertion;
	}

	/**
	 * Get lastname
	 * @return lastname STring
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set lastname
	 * @param lastName String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get nickname
	 * @return nickname String
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Set nickname
	 * @param nickName String
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Get password
	 * @return password String
	 */
	@XmlTransient
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
