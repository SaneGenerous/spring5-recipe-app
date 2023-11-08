package org.tp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tp.commands.IngredientCommand;
import org.tp.commands.UnitOfMeasureCommand;
import org.tp.domain.Ingredient;
import org.tp.domain.Recipe;
import org.tp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {
    public static final Long ID_VALUE = valueOf(1l);
    public static final String DESCRIPTION = "description";
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = valueOf(2L);
    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        //command.setRecipe(RECIPE);
        command.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUom(unitOfMeasureCommand);


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(RECIPE, ingredient.getRecipe());
        assertEquals(UOM_ID, ingredient.getUom());
    }

    @Test
    public void convertWithNullUOM() throws Exception{
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        //command.setRecipe(RECIPE);

        //then
    }
}