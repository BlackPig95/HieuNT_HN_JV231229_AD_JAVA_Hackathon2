package examadvance1.ra.businessImp;

import config.CONSOLECOLORS;
import config.CONSTANT;
import config.InputMethods;
import examadvance1.ra.business.IShop;

import java.util.List;

import static examadvance1.ra.run.BookManagement.authorList;

public class Book implements IShop, Comparable<Book>
{
    private int bookId;
    private String bookName;
    private String title;
    private int numberOfPages;
    private Author author;
    private float importPrice;
    private float exportPrice;
    private int quantity;
    private Boolean bookStatus;

    public Book()
    {
    }

    public Book(int bookId, String bookName, String title, int numberOfPages, Author author, float importPrice, float exportPrice, int quantity, Boolean bookStatus)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.quantity = quantity;
        this.bookStatus = bookStatus;
    }

    public int getBookId()
    {
        return bookId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages)
    {
        this.numberOfPages = numberOfPages;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public float getImportPrice()
    {
        return importPrice;
    }

    public void setImportPrice(float importPrice)
    {
        this.importPrice = importPrice;
    }

    public float getExportPrice()
    {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice)
    {
        this.exportPrice = exportPrice;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public Boolean getBookStatus()
    {
        return bookStatus;
    }

    public void setBookStatus(Boolean bookStatus)
    {
        this.bookStatus = bookStatus;
    }

    @Override
    public void inputData()
    {
        if (authorList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện chưa có tác giả nào. " +
                    "Vui lòng nhập danh sách tác giả trước khi thêm sách" + CONSOLECOLORS.RESET);
            return;
        }
        inputId();
        inputName();
        inputTitle();
        inputNumOfPages();
        chooseAuthor();
        inputImportPrice();
        this.exportPrice = this.importPrice * RATE;
        inputQuantity();
        inputStatus();
    }

    @Override
    public void displayData()
    {
        System.out.printf("Mã sách: %-5d | Tên sách: %-20s | Tác giả: %-20s\n",
                this.bookId, this.bookName, this.author.getAuthorName());
        System.out.printf("Giá bán: %-5.2f | Số lượng sách: %-15d | Trạng thái: %-20s\n",
                this.exportPrice, this.quantity, this.bookStatus ? "Đang bán" : "Chưa bán");
        System.out.println("---------------------------------------------------------------------------------");
    }

    //Hàm hiển thị thông tin tóm tắt dùng cho việc sắp xếp
    public void displayBasicData()
    {
        System.out.printf("Mã sách: %d | Tên sách: %s | Giá bán: %.2f\n", this.bookId, this.bookName, this.exportPrice);
    }

    private void inputId()
    {
        System.out.print("Nhập mã đầu sách: ");
        this.bookId = InputMethods.nextInt();
    }

    private void inputName()
    {
        System.out.print("Nhập tên sách: ");
        this.bookName = InputMethods.nextLine();
    }

    private void inputTitle()
    {
        System.out.print("Nhập tiêu đề cho đầu sách: ");
        this.title = InputMethods.nextLine();
    }

    private void inputNumOfPages()
    {
        System.out.print("Số trang của mỗi cuốn sách này là: ");
        this.numberOfPages = InputMethods.nextInt();
    }

    private void chooseAuthor()
    {
        if (authorList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện chưa có tác giả nào để chọn" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println(CONSOLECOLORS.YELLOW + "Danh sách các tác giả hiện có: " + CONSOLECOLORS.RESET);
        authorList.forEach(Author::displayData);
        System.out.println(CONSOLECOLORS.YELLOW + "==================================================================================" + CONSOLECOLORS.RESET);
        while (true)
        {
            System.out.print("Chọn tác giả cho đầu sách này bằng mã tác giả: ");
            int authorChoice = InputMethods.nextInt();
            this.author = authorList.stream().filter(a -> a.getAuthorId() == authorChoice).findFirst().orElse(null);
            //Author == null => Không tìm ra phần tử khớp với điều kiên trong hàm filter
            if (this.author == null)
            {
                System.out.println(CONSOLECOLORS.RED + CONSTANT.CHOICE_NOT_AVAI + " "
                        + CONSTANT.INPUT_AGAIN + CONSOLECOLORS.RESET);
            } else break;//Nếu chọn đúng thì tác giả đã được set thành công => break
        }
    }

    private void inputImportPrice()
    {
        System.out.print("Giá nhập vào của đầu sách này là: ");
        this.importPrice = InputMethods.nextFloat();
    }

    private void inputQuantity()
    {
        System.out.print("Số lượng hiện có của đầu sách này là: ");
        this.quantity = InputMethods.nextInt();
    }

    private void inputStatus()
    {
        System.out.println("Nhập trạng thái của đầu sách này: true-Đang bán, false-Chưa bán");
        this.bookStatus = InputMethods.nextBoolean();
    }

    @Override
    public int compareTo(Book o)
    {
        return Float.compare(this.exportPrice, o.exportPrice);
    }
}
