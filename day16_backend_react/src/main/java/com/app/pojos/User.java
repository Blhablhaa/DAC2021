package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * userName: '',
            password: '',
            firstName: '',
            lastName: '',
            age: '',
            salary: '',
 */

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User extends BaseEntity {
@Column(length = 20,unique = true)
private String userName;
@Column(length = 20,nullable = false)
private String password;
@Column(length = 20)
@JsonProperty("firstName")//appears in marshalled json data
private String fName;

@Column(length = 20)
private String lastName;
private int age;
private double salary;
}
