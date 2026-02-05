package com.j5shop.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Length(max = 255)
	@NotBlank(message = "Slug không được để trống!")
	String slug;
	@NotBlank(message = "Tên sản phẩm không được để trống!")
	@Column(columnDefinition = "nvarchar(255)")
	String name;
	@Min(value = 1000, message = "Giá phải >= 1000")
	int price;
	@Min(value = 1, message = "Số lượng phải >= 1")
	int quantity;
	@Column(columnDefinition = "nvarchar(max)")
	String description;
	@Column(columnDefinition = "nvarchar(255)")
	String image;
	boolean active;
	@ManyToOne @JoinColumn(name = "category_id")
	Category category;
	@OneToMany(mappedBy = "product")
	List<CartDetail> cartDetails;
	@OneToMany(mappedBy = "product")
	List<OrderDetail>orderDetails;
}
