package com.j5shop.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(unique = true)
	String email;
	String password;
	@Column(columnDefinition = "nvarchar(60)")
	String fullName;
	@Column(columnDefinition = "nvarchar(255)")
	String photo;
	boolean active;
	boolean admin;
	@OneToMany(mappedBy = "account")
	List<CartDetail> cartDetails;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
}
