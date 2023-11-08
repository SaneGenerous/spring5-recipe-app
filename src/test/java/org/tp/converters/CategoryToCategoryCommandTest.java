package org.tp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tp.commands.CategoryCommand;
import org.tp.domain.Category;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {
    public static final Long ID_VALUE = valueOf(1L);
    public static final String DESCRIPTION = "description";
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNotNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}