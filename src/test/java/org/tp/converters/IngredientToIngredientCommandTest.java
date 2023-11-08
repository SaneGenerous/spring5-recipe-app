package org.tp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.SingleRegisteredInvocation;
import org.tp.commands.IngredientCommand;
import org.tp.domain.Ingredient;
import org.tp.domain.Recipe;
import org.tp.domain.UnitOfMeasure;

import java.math.BigDecimal;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "cheeseburger";
    public static final Long ID_VALUE = valueOf(1L);
    public static final Long UOM_ID = valueOf(2L);
    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(null);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }

    @Test
    public void testConvertWithUOM() throws Exception{
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUom());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
    }
}