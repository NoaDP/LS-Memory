package model;

public class User {
	private String Name;
	private String Surname;
	private String Nickname;
	private String Password;
	private String Date;
	
	public User(String name, String surname, String nickname, String password,
			String date) {
		super();
		this.Name = name;
		this.Surname = surname;
		this.Nickname = nickname;
		this.Password = password;
		this.Date = date;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
	
	
	
	
}
