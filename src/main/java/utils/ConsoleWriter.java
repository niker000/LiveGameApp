package utils;

import interfaces.IWrite;

public class ConsoleWriter implements IWrite {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
