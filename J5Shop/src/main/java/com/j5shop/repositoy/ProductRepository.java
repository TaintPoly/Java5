package com.j5shop.repositoy;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.j5shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("select p from Product p where p.name like ?1")
	public List<Product> findByKeyword(String keyword);
	
	//viết theo kiểu dsl
	public List<Product> findByNameContaining(String keyword);
	
	//viêt theo kieu sql thuần
	@Query(value = "select * from products where name like ?1", nativeQuery = true)
	public List<Product> findByKeywordSql(String keyword);
	
	//Lấy danh sách theo khoảng giá
	
	//lấy theo active ket hop phân trang
	Page<Product> findByActive(boolean active, Pageable pageable);
	
	//lay theo active và keyword
	
	
	//lay theo slug và active
	Product findBySlugAndActive(String slug, boolean active);
}
