package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.di.DaoModule;
import org.example.di.MainModule;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DaoModule(), new MainModule());
    }
}