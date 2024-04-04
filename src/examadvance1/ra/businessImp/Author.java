package examadvance1.ra.businessImp;

import config.CONSOLECOLORS;
import config.InputMethods;
import examadvance1.ra.business.IShop;

public class Author implements IShop
{
    private int authorId;
    private String authorName;
    private Boolean sex;
    private int year;

    public Author()
    {
    }

    public Author(int authorId, String authorName, Boolean sex, int year)
    {
        this.authorId = authorId;
        this.authorName = authorName;
        this.sex = sex;
        this.year = year;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(int authorId)
    {
        this.authorId = authorId;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public Boolean getSex()
    {
        return sex;
    }

    public void setSex(Boolean sex)
    {
        this.sex = sex;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    @Override
    public void inputData()
    {
        inputId();
        inputName();
        inputSex();
        inputYear();
    }

    @Override
    public void displayData()
    {
        System.out.printf("Mã tác giả: %-5d | Tên giác giả: %-20s\n", this.authorId, this.authorName);
    }

    private void inputId()
    {
        System.out.print("Nhập mã định danh tác giả này: ");
        this.authorId = InputMethods.nextInt();
    }

    private void inputName()
    {
        System.out.print("Nhập tên tác giả: ");
        this.authorName = InputMethods.nextLine();
    }

    private void inputSex()
    {
        System.out.print("Nhập giới tính của tác giả: true-Nam, false-Nữ ");
        this.sex = InputMethods.nextBoolean();
    }

    private void inputYear()
    {
        System.out.print("Nhập năm sinh của tác giả: ");
        this.year = InputMethods.nextInt();
    }
}
