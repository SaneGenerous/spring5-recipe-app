package org.tp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tp.commands.UnitOfMeasureCommand;
import org.tp.domain.UnitOfMeasure;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {
    public static final Long ID_VALUE = valueOf(1L);
    public static final String DESCRIPTION = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception{
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        //when
        UnitOfMeasureCommand uomc = converter.convert(uom);

        //then
        assertEquals(ID_VALUE, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getDescription());
    }
}