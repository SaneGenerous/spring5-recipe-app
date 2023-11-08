package org.tp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private UnitOfMeasureCommand uom;
    private BigDecimal amount;
}
