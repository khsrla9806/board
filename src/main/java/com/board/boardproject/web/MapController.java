package com.board.boardproject.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MapController {
	
	private final String conformKey;
	
	public MapController(@Value("${map-api.conformKey}") String conformKey) {
		this.conformKey = conformKey;
	}
	
	@GetMapping("/map/popup")
	public String getMapPopup(Model model) {
		
		model.addAttribute("conformKey", conformKey);
		
		return "pages/map/juso-popup";
	}
	
	@PostMapping("/map/popup")
	public String setMapPop() {
		return "pages/map/juso-popup";
	}
}
