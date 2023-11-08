package org.tp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.tp.commands.CategoryCommand;
import org.tp.commands.IngredientCommand;
import org.tp.commands.RecipeCommand;
import org.tp.domain.Recipe;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    CategoryToCategoryCommand categoryToCategoryCommand;
    NotesToNotesCommand notesToNotesCommand;
    IngredientToIngredientCommand ingredientToIngredientCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand, NotesToNotesCommand notesToNotesCommand, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.notesToNotesCommand = notesToNotesCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null){
        return null;}
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        Set<CategoryCommand> categoryCommandSet = new HashSet<>();
        if(source.getCategories() != null && source.getCategories().size() > 0){
        source.getCategories().forEach( category -> categoryCommandSet.add(categoryToCategoryCommand.convert(category)));}
        recipeCommand.setCategories(categoryCommandSet);
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        Set<IngredientCommand> ingredientCommandSet = new HashSet<>();
        if(source.getIngredients() != null && source.getIngredients().size() > 0){
        source.getIngredients().forEach(ingredient -> ingredientCommandSet.add(ingredientToIngredientCommand.convert(ingredient)));}
        recipeCommand.setIngredients(ingredientCommandSet);

        return recipeCommand;
    }
}

