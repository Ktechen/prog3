package view;

import view.cli.Console;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestConsole {


    @Test
    public void ConsoleGetNullParamter(){
        Assertions.assertThrows(NullPointerException.class, ()->{
           new Console(null);
        });
    }

    @Test
    public void Console(){
        try{
            new Console(new BufferedReader(new InputStreamReader(System.in)));
        }catch (Exception e){
            Assertions.fail();
        }
    }
}
