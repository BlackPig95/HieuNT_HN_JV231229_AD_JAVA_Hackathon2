package examadvance2;

import config.CONSOLECOLORS;
import config.CONSTANT;
import config.InputMethods;

import java.util.Stack;

public class HackathonStack
{
    private static final Stack<String> urlStack = new Stack<>();

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("""
                    ****************JAVA-HACKATHON-05-ADVANCE-1-MENU***************
                    1. Nhập URL muốn truy cập
                    2. Quay lại
                    3. Thoát""");
            System.out.println(CONSOLECOLORS.BLUE + CONSTANT.INPUT_YOUR_CHOICE + CONSOLECOLORS.RESET);
            byte choice = InputMethods.nextByte();
            switch (choice)
            {
                case 1:
                    accessURL();
                    break;
                case 2:
                    backToPreviousPage();
                    break;
                case 3:
                    return;
                default:
                    System.out.println(CONSOLECOLORS.RED + CONSTANT.CHOICE_NOT_AVAI + CONSOLECOLORS.RESET);
                    break;
            }
        }
    }

    private static void accessURL()
    {
        while (true)
        {
            System.out.println("Nhập đường dẫn của trang web muốn truy cập. Nhập chính xác 'esc' khi muốn quay lại");
            String newUrl = InputMethods.nextLine();
            //Cho phép nhập liên tục các url cho đến khi nhập esc thì ngừng hàm
            if (newUrl.equals("esc"))
            {
                break;
            }
            urlStack.push(newUrl);
            System.out.println(CONSOLECOLORS.GREEN + "Truy cập thành công trang web: " + newUrl + CONSOLECOLORS.RESET);
        }

    }

    private static void backToPreviousPage()
    {
        //Kiểm tra trường hợp cố gắng truy cập khi không còn phần tử nào trong stack
        if (urlStack.empty())
        {
            System.out.println(CONSOLECOLORS.RED + "Đã quay lại hết mức có thể" + CONSOLECOLORS.RESET);
            return;
        }
        //Xóa bỏ phần tử trên cùng của stack, chính là trang web hiện tại
        urlStack.pop();
        //Lúc này phần tử trên cùng của stack chính là url đã truy cập trước đó
        if (urlStack.empty())//Kiểm tra trường hợp phần tử vừa bị pop ở trên chính là phần tử cuối cùng
        {
            System.out.println(CONSOLECOLORS.RED + "Đã quay lại hết mức có thể" + CONSOLECOLORS.RESET);
        } else
        {
            System.out.println("Trang web mà bạn đã truy cập trước đó là: " + urlStack.peek());
        }
    }
}
