package org.tp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tp.services.RecipeService;
@Slf4j
@Controller
public class IndexPageController {
    private final RecipeService recipeService;

    public IndexPageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model){
        log.debug("Getting Index Page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
