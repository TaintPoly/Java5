package com.j5shop.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart_details")
public class CartDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	int quantity;
	@ManyToOne @JoinColumn(name = "account_id")
	Account account;
	@ManyToOne @JoinColumn(name = "product_id")
	Product product;
}
