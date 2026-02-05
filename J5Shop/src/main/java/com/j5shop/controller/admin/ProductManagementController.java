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

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProductManagementController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	HttpSession session;
	List<Product> list = new ArrayList<Product>();
	@GetMapping("/admin/products")
	public String index(Model model) {
		list = (List<Product>) session.getAttribute("list");
		if(list == null) {
			list = new ArrayList<Product>();
		}
		model.addAttribute("active", "products");
		model.addAttribute("list", list);
		return "admin/products/list";
	}
	@GetMapping("/admin/products/add")
	public String addProduct(Model model, @ModelAttribute Product product) {
		
		model.addAttribute("active", "products");
		model.addAttribute("product", product);
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
			
			product.setId(list.size()+1);
			//Category category = new Category(categoryId, "Tên loại " + categoryId, "", true);
			//product.setCategory(category);
			//list.add(product);
			session.setAttribute("list", list);
			model.addAttribute("success", "Thêm thành công!");
		}
		return "admin/products/add";
	}
	
	@GetMapping("/admin/products/edit/{id}")
	public String editProduct(@PathVariable("id")Integer id) {
		System.out.println("id = " + id);
		return "admin/products/add";
	}
}
