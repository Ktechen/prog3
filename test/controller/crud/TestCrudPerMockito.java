package controller.crud;

import modell.data.content.Person;
import modell.mediaDB.Uploader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestCrudPerMockito {

    @Test
    public void uploader() {
        Uploader uploader = mock(Uploader.class);
        when(uploader.getName()).thenReturn("Kevin Techen");

        assertEquals(uploader.getName(), "Kevin Techen");
    }

    @Test
    public void PersonNull() {
        try {
            Person p = mock(Person.class);
            when(p.getName()).thenReturn(null);
        } catch (NullPointerException e){
            doThrow(NullPointerException.class);
        }

    }

    @Test
    public void PersonEmpty() {
        try {
            Person p = mock(Person.class);
            when(p.getName()).thenReturn("");
        } catch (NullPointerException e){
            doThrow(NullPointerException.class);
        }

    }


}
