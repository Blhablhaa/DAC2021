package com.app.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *   userName: '',
            password: '',
            firstName: '',
            lastName: '',
            age: '',
            salary: '',
 */
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@ToString
public class User extends BaseEntity{
	@Column(length = 20,unique = true)
	@NotBlank(message = "User Name is required")
	private String userName;
	@Column(length = 20,nullable = false)
	@NotBlank(message = "User pwd is required")
	@Length(min = 4,max=19,message = "Invalid pwd length")
	private String password;
	@Column(length = 20)
	@JsonProperty("firstName") //appears in marshalled json data
	@NotBlank(message = "User first Name is required")
	private String fName;
	//uni dir one to one relationship between entities.
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private Address adr;
	
	@Column(length = 20)
	@NotBlank(message = "User last Name is required")
	private String lastName;
	@Min(value=20,message = "Invalid age")
	@Max(value=60,message = "Invalid age")
	private int age;
	@NotNull
	private double salary;
	public User(String userName, String password, String fName, String lastName, int age, double salary) {
		super();
		this.userName = userName;
		this.password = password;
		this.fName = fName;
		this.lastName = lastName;
		this.age = age;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", fName=" + fName + ", lastName=" + lastName
				+ ", age=" + age + ", salary=" + salary + "]";
	}
	
	
}
