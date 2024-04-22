package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_adr")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Address extends BaseEntity {
	@Column(length = 30)
	private String city;
	@Column(length = 30)
	private String state;
	@Column(length = 30)
	private String country;
	@OneToOne(mappedBy = "adr")
	@JsonIgnoreProperties("adr")
	private User user;
	public Address(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
}
