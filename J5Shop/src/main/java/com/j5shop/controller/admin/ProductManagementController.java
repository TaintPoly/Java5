package com.j5shop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.j5shop.model.Category;
import com.j5shop.model.Product;
import com.j5shop.repositoy.CategoryRepository;
import com.j5shop.repositoy.ProductRepository;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProductManagementController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ServletContext servletContext;
	
	@GetMapping("/admin/products")
	public String index(Model model, @RequestParam("keyword") Optional<String> keyword) {
		String kw = keyword.orElse(null);
		List<Product> list = productRepository.findAll();
		if (kw != null) {
			list = productRepository.findByNameContaining(kw);
		}
		
		model.addAttribute("active", "products");
		model.addAttribute("list", list);
		return "admin/products/list";
	}
	@GetMapping("/admin/products/add")
	public String addProduct(Model model, @ModelAttribute Product product) {
		List<Category> categories = categoryRepository.findByActive(true);
		model.addAttribute("active", "products");
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "admin/products/add";
	}
	
	
	
	@PostMapping("/admin/products/add")
	public String addProduct(Model model,
							@RequestParam("categoryId") Integer categoryId,
							@RequestPart("imageProduct")MultipartFile image,
							@ModelAttribute @Valid Product product, 
							Errors erros
							 ) {
		if(erros.hasErrors()) {
			model.addAttribute("error", "Có lỗi xảy ra");
		}else {
			if(image.isEmpty()) {
				System.out.println("chưa chọn file");
			}else {
				try {
				System.out.println("filename: " + image.getOriginalFilename() +" -type:" +  image.getContentType());
				String path = servletContext.getRealPath("/images/" + image.getOriginalFilename());
				if(!Files.exists(Path.of(path))) {
					Files.createDirectories(Path.of(path));
				}
				File file = new File(path);
				
					image.transferTo(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				product.setImage(image.getOriginalFilename());
			}
			
			Category category = categoryRepository.findById(categoryId).orElse(null);
			product.setCategory(category);
			product.setActive(true);
			productRepository.save(product);
			model.addAttribute("success", "Thêm thành công!");
		}
		return "admin/products/add";
	}
	
	@GetMapping("/admin/products/edit/{id}")
	public String editProduct(Model model,@PathVariable("id")Integer id) {
		Product product = productRepository.findById(id).orElse(null);
		List<Category> categories = categoryRepository.findByActive(true);
		model.addAttribute("active", "products");
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "admin/products/edit";
	}
	@PostMapping("/admin/products/edit/{id}")
	public String  updateProduct(Model model,
							@PathVariable("id")Integer id,
							@RequestParam("categoryId") Integer categoryId,
							@RequestPart("imageProduct")MultipartFile image,
							@ModelAttribute @Valid Product product, 
							Errors erros) {
		if(erros.hasErrors()) {
			model.addAttribute("error", "Có lỗi xảy ra");
		}else {
			if(image.isEmpty()) {
				System.out.println("chưa chọn file");
			}else {
				try {
				System.out.println("filename: " + image.getOriginalFilename() +" -type:" +  image.getContentType());
				String path = servletContext.getRealPath("/images/" + image.getOriginalFilename());
				if(!Files.exists(Path.of(path))) {
					Files.createDirectories(Path.of(path));
				}
				File file = new File(path);
				
					image.transferTo(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				product.setImage(image.getOriginalFilename());
			}
			
			Category category = categoryRepository.findById(categoryId).orElse(null);
			product.setCategory(category);
			productRepository.save(product);
			model.addAttribute("success", "Cập nhật thành công!");
		}
		return "admin/products/edit";
	}
	
	//Xóa
	@GetMapping("admin/products/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		productRepository.deleteById(id);
		return "redirect:/admin/products";
	}
	
	//add or update
	@GetMapping("admin/products/addOrUpdate")
	public String addOrUpdate(Model model,
			@RequestParam("id") Optional<Integer> id,
			@ModelAttribute Product product) {
		if(!id.isEmpty()) {
			product = productRepository.findById(id.get()).orElse(null);
		}
		List<Category> categories = categoryRepository.findByActive(true);
		model.addAttribute("active", "products");
		model.addAttribute("product", product);
		model.addAttribute("categories", categories);
		return "admin/products/add-or-update";
	}
	@PostMapping("admin/products/addOrUpdate")
	public String addOrUpdate(Model model,
			@RequestParam("categoryId") Integer categoryId,
			@RequestPart("imageProduct")MultipartFile image,
			@ModelAttribute @Valid Product product, 
			Errors erros) {
		if(erros.hasErrors()) {
			model.addAttribute("error", "Có lỗi xảy ra");
		}else {
			if(image.isEmpty()) {
				System.out.println("chưa chọn file");
			}else {
				try {
				System.out.println("filename: " + image.getOriginalFilename() +" -type:" +  image.getContentType());
				String path = servletContext.getRealPath("/images/" + image.getOriginalFilename());
				if(!Files.exists(Path.of(path))) {
					Files.createDirectories(Path.of(path));
				}
				File file = new File(path);
				
					image.transferTo(file);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				product.setImage(image.getOriginalFilename());
			}
			
			Category category = categoryRepository.findById(categoryId).orElse(null);
			product.setCategory(category);
			
			productRepository.save(product);
			model.addAttribute("success", "Cập nhật thành công!");
		}
		return "admin/products/add-or-update";
	}
	
}
