package helpers;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerate {
    Faker fakerRu = new Faker(new Locale("ru"));

    public String getBookAuthor() {return (fakerRu.book().author());}
    public String getBookName() {return fakerRu.book().title();}
    public int getBookYear() {return fakerRu.random().nextInt(1950,2022);}

}
