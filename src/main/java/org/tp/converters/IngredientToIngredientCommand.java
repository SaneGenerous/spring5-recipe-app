package org.tp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.tp.commands.IngredientCommand;
import org.tp.domain.Ingredient;
import org.tp.repositories.RecipeRepository;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;


    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null){
            return null;}
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if (ingredient.getRecipe() != null) {
        ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
