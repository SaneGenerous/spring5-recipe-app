package org.tp.converters;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tp.commands.NotesCommand;
import org.tp.domain.Notes;

import static java.lang.Long.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    public static final Long ID_VALUE = valueOf(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesCommandToNotes converter;

    @BeforeEach
    public void setUp() throws Exception{
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullNotes() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception{
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}