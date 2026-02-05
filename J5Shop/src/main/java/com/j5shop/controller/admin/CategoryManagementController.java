package com.j5shop.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.j5shop.model.Category;
import com.j5shop.repositoy.CategoryRepository;

@Controller
public class CategoryManagementController {
	
	@Autowired
	CategoryRepository categoryRepository;
	//load danh sách
	@GetMapping("admin/categories")
	public String index(Model model, 
						@RequestParam("pageNum") Optional<Integer> pageNum,
						@RequestParam("pageSize") Optional<Integer> pageSize) {
		int pageNumber = pageNum.orElse(0);
		int pageSizes = pageSize.orElse(2);
		Pageable pageable = PageRequest.of(pageNumber, pageSizes, Direction.DESC, "id");
		Page<Category> page = categoryRepository.findAll(pageable);
		
		List<Category> categories = page.getContent();
		model.addAttribute("list", categories);
		model.addAttribute("page", page);
		model.addAttribute("active", "categories");
		return "admin/categories/list";
	}
	//Thêm mới
	@GetMapping("admin/categories/add")
	public String add(Model model) {
		return "admin/categories/add";
	}
	@PostMapping("admin/categories/add")
	public String add(Model model, @ModelAttribute Category category) {
		category.setActive(true);
		categoryRepository.save(category);
		model.addAttribute("message", "Thêm mới thành công");
		return "admin/categories/add";
	}
	//sửa
	@GetMapping("admin/categories/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category category = categoryRepository.findById(id).orElse(null);
		model.addAttribute("category", category);
		return "admin/categories/edit";
	}
	
	@PostMapping("admin/categories/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id, @ModelAttribute Category category) {
//		category.setId(id);
		categoryRepository.save(category);
		model.addAttribute("message", "Cập nhật thành công");
		model.addAttribute("category", category);
		return "admin/categories/edit";
	}
	//Xóa
	@GetMapping("admin/categories/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		categoryRepository.deleteById(id);
		return "redirect:/admin/categories";
	}
}
