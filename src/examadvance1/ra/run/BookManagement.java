package examadvance1.ra.run;

import config.CONSOLECOLORS;
import config.CONSTANT;
import config.InputMethods;
import examadvance1.ra.businessImp.Author;
import examadvance1.ra.businessImp.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BookManagement
{
    public static final List<Author> authorList = new ArrayList<>();
    static final List<Book> bookList = new ArrayList<>();

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("""
                    ****************JAVA-HACKATHON-05-ADVANCE-1-MENU***************
                    1. Nhập số tác giả và nhập thông tin các tác giả
                    2. Nhập số sách và nhập thông tin các sách
                    3. Sắp xếp sách theo giá xuất sách tăng dần (Comparable/Comparator)
                    4. Tìm kiếm sách theo tên tác giả sách
                    5. Thoát""");
            System.out.println(CONSTANT.INPUT_YOUR_CHOICE);
            byte choice = InputMethods.nextByte();
            switch (choice)
            {
                case 1:
                    addAuthor();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    sortByPrice();
                    break;
                case 4:
                    searchByAuthorName();
                    break;
                case 5:
                    return;
                default:
                    System.out.println(CONSOLECOLORS.RED + CONSTANT.CHOICE_NOT_AVAI + CONSOLECOLORS.RESET);
                    break;
            }
        }
    }

    private static void addAuthor()
    {
        System.out.println("Nhập số lượng tác giả muốn thêm mới vào danh sách");
        byte n = InputMethods.nextByte();
        for (int i = 0; i < n; i++)
        {
            System.out.println(CONSOLECOLORS.BLUE + "Nhập thông tin cho tác giả thứ " + (i + 1) + CONSOLECOLORS.RESET);
            Author newAuthor = new Author();
            newAuthor.inputData();
            authorList.add(newAuthor);
            System.out.println(CONSOLECOLORS.GREEN + "Thêm tác giả thành công." + CONSOLECOLORS.RESET);
        }
    }

    private static void addBook()
    {
        if (authorList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện chưa có tác giả nào. " +
                    "Vui lòng nhập danh sách tác giả trước khi thêm sách" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println("Nhập số lượng đầu sách muốn thêm mới vào danh sách: ");
        byte n = InputMethods.nextByte();
        for (int i = 0; i < n; i++)
        {
            System.out.println(CONSOLECOLORS.BLUE + "Nhập thông tin cho đầu sách thứ " + (i + 1) + CONSOLECOLORS.RESET);
            Book newBook = new Book();
            newBook.inputData();
            bookList.add(newBook);
            System.out.println(CONSOLECOLORS.GREEN + "Thêm đầu sách thành công." + CONSOLECOLORS.RESET);
        }
    }

    private static void sortByPrice()
    {
        if (bookList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có đầu sách nào nên không thể sắp xếp" + CONSOLECOLORS.RESET);
            return;
        }
        //Sắp xếp theo comparator
        bookList.sort((book1, book2) -> Float.compare(book1.getExportPrice(), book2.getExportPrice()));
        //Sắp xếp theo comparable
//        Collections.sort(bookList);
        System.out.println(CONSOLECOLORS.YELLOW + "Danh sách sau sắp xếp:" + CONSOLECOLORS.RESET);
        bookList.forEach(Book::displayBasicData);
    }

    private static void searchByAuthorName()
    {
        if (bookList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có đầu sách nào để tìm kiếm" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println("Nhập tên tác giả mà bạn muốn tìm kiếm:");
        String searchName = InputMethods.nextLine();
        //Lọc ra list các tác giả có tên giống với tìm kiếm
        List<Author> authorsFound = authorList.stream().filter(a -> a.getAuthorName().contains(searchName)).toList();
        //Nếu không tìm ra tác giả nào có tên gần giống thì không thể hiển thị sách
        if (authorsFound.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + CONSTANT.ELEMENT_NOT_FOUND + CONSOLECOLORS.RESET);
            return;
        }
        //Nếu tìm được các tác giả có tên gần giống => Hiển thị các đầu sách của mỗi tác giả
        for (Author a : authorsFound)
        {
            System.out.println(CONSOLECOLORS.YELLOW + "Danh sách các tác giả và đầu sách tương ứng như sau:" + CONSOLECOLORS.RESET);
            System.out.println("Tác giả: " + a.getAuthorName());
            //So sánh tên tác giả của sách với tên từng tác giả trong list để tìm ra list book tương ứng
            List<Book> booksFound = bookList.stream().filter(b -> b.getAuthor().getAuthorName().equals(a.getAuthorName())).toList();
            if (booksFound.isEmpty())//Nếu tác giả không có đầu sách nào
            {
                System.out.println(CONSOLECOLORS.RED + "Không có đầu sách nào" + CONSOLECOLORS.RESET);
            } else //Nếu tác giả này có đầu sách
            {
                booksFound.forEach(Book::displayData);
            }
        }
    }
}
