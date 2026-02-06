package com.j5shop.repositoy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.j5shop.model.Category;
import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Page<Category> findByNameLikeAndId(String name, Integer id, Pageable pageable);
	
	//lay danh sách con hoạt đông(DSL)
	public List<Category> findByActive(boolean active);
}
