package helpers;

public class DataForTests {
    static DataGenerate generate = new DataGenerate();

    public static final String bookName = generate.getBookName(),
            bookAuthor = generate.getBookAuthor();
    public static final int bookYear = generate.getBookYear();

}
