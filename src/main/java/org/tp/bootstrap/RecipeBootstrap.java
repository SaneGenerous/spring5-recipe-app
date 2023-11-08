package org.tp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.tp.domain.*;
import org.tp.repositories.CategoryRepository;
import org.tp.repositories.RecipeRepository;
import org.tp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Adding Bootstrap Data");
    }
   private List<Recipe> getRecipes(){
       List<Recipe> recipes = new ArrayList<>(2);
       //get UOM
       Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("Each");
       if(!eachUOMOptional.isPresent()){
        throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> tableSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
       if(!tableSpoonUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> teaSpoonUOMOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
       if(!teaSpoonUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("Dash");
       if(!dashUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> cupsUOMOptional = unitOfMeasureRepository.findByDescription("Cup");
       if(!cupsUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> pinchUOMOptional = unitOfMeasureRepository.findByDescription("Pinch");
       if(!pinchUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> pintUOMOptional = unitOfMeasureRepository.findByDescription("Pint");
       if(!pintUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       Optional<UnitOfMeasure> ounceUOMOptional = unitOfMeasureRepository.findByDescription("Ounce");
       if(!ounceUOMOptional.isPresent()){
           throw new RuntimeException("Expected UOM Not Found");
       }
       //get optionals
       UnitOfMeasure eachUom = eachUOMOptional.get();
       UnitOfMeasure tableSpoonUom = tableSpoonUOMOptional.get();
       UnitOfMeasure teaSpoonUom = teaSpoonUOMOptional.get();
       UnitOfMeasure dashUom = dashUOMOptional.get();
       UnitOfMeasure pintUom = pintUOMOptional.get();
       UnitOfMeasure cupsUom = cupsUOMOptional.get();

       //get categories
       Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
       if(!americanCategoryOptional.isPresent()) {
           throw new RuntimeException("Expected Category Not Found");
       }
       Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
       if(!mexicanCategoryOptional.isPresent()) {
           throw new RuntimeException("Expected Category Not Found");
       }

       Category americanCategory = americanCategoryOptional.get();
       Category mexicanCategory = mexicanCategoryOptional.get();

       //Yummy Guacamole
       Recipe guacRecipe = new Recipe();
       guacRecipe.setDescription("Perfect Guacamole");
       guacRecipe.setPrepTime(10);
       guacRecipe.setCookTime(0);
       guacRecipe.setDifficulty(Difficulty.EASY);
       guacRecipe.setDirections("Cut the avocados:\n" +
               "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
       + "\n" + "Mash the avocado flesh:\n" +
               "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)"
       + "\n" + "Add the remaining ingredients to taste:\n" +
               "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
               "\n" +
               "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
               "\n" +
               "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
               "Start with this recipe and adjust to your taste.");
       Notes guacNotes = new Notes();
       guacNotes.setRecipeNotes("Serve immediately:\n" +
               "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
               "\n" +
               "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
               "\n" +
               "Refrigerate leftover guacamole up to 3 days.\n" +
               "\n" +
               "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, " +
               "add it just before serving.");

        guacRecipe.setNotes(guacNotes);

       guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(5), teaSpoonUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(2), tableSpoonUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), tableSpoonUom, guacRecipe));
       guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(5), eachUom, guacRecipe));

       guacRecipe.getCategories().add(americanCategory);
       guacRecipe.getCategories().add(mexicanCategory);
       guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
       guacRecipe.setServings(4);
       guacRecipe.setSource("Simply recipes");

       recipes.add(guacRecipe);

       //Yummy Tacos
       Recipe tacosRecipe = new Recipe();
       tacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
       tacosRecipe.setCookTime(9);
       tacosRecipe.setPrepTime(20);
       tacosRecipe.setDifficulty(Difficulty.MODERATE);
       tacosRecipe.setDirections("1 Prepare the grill:\n" +
               "Prepare either a gas or charcoal grill for medium-high, direct heat." + "\n" +
               "2 Make the marinade and coat the chicken:\n" +
               "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
               "\n" +
               "Set aside to marinate while the grill heats and you prepare the rest of the toppings."
               + "\n" + "3 Grill the chicken:\n" +
               "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part " +
               "of the meat registers 165°F. Transfer to a plate and rest for 5 minutes."
               + "\n" + "4 Thin the sour cream with milk:\n" +
               "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.");

       Notes tacosNotes = new Notes();
       tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
               "\n" +
               "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
               "\n" +
               "Today's tacos are more purposeful a deliberate meal instead of a secretive midnight snack!");

       tacosRecipe.setNotes(tacosNotes);

       tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tableSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(5), teaSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("Clove of garlic, chopped", new BigDecimal(1), eachUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1), tableSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("fresh squeezed orange juice", new BigDecimal(3), tableSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("Olive oil", new BigDecimal(2), tableSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tableSpoonUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cupsUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados, slice", new BigDecimal(2), eachUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes halved", new BigDecimal(5), pintUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(25), eachUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupsUom, tacosRecipe));
       tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom, tacosRecipe));

       tacosRecipe.getCategories().add(americanCategory);
       tacosRecipe.getCategories().add(mexicanCategory);
       tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/fish_tacos/");
       tacosRecipe.setServings(6);
       tacosRecipe.setSource("Simply recipes");

       recipes.add(tacosRecipe);
       return recipes;
   }
}
