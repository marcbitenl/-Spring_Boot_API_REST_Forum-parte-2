package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping("/") //diz qual é a url
	@ResponseBody //para não considerar que é uma página ai responde direto peli navegador
	public String hello() {
		return "Hello World!";
	}
	

}
