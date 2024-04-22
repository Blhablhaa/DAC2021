package pojos;

public class User {
private String name;
private String email;
private String pass;
private int age;
public User(String name, String email, String pass,int age) {
	super();
	this.name = name;
	this.email = email;
	this.pass = pass;
	this.age = age;
}

public User(String name, String pass, int age) {
	super();
	this.name = name;
	this.pass = pass;
	this.age = age;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
@Override
public String toString() {
	return "User [name=" + name + ", age=" + age + "]";
}

}
