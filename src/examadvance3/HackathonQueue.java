package examadvance3;

import config.CONSOLECOLORS;
import config.CONSTANT;
import config.InputMethods;

import java.util.LinkedList;
import java.util.Queue;

public class HackathonQueue
{
    private static final Queue<String> customerQueue = new LinkedList<>();

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("""
                    ****************JAVA-HACKATHON-05-ADVANCE-1-MENU***************
                    1. Nhập tên khách hàng chờ mua vé xem phim
                    2. Khách tiếp theo
                    3. Thoát""");
            System.out.println(CONSOLECOLORS.BLUE + CONSTANT.INPUT_YOUR_CHOICE + CONSOLECOLORS.RESET);
            byte choice = InputMethods.nextByte();
            switch (choice)
            {
                case 1:
                    enterCustomerInfo();
                    break;
                case 2:
                    getNextCustomer();
                    break;
                case 3:
                    return;
                default:
                    System.out.println(CONSOLECOLORS.RED + CONSTANT.CHOICE_NOT_AVAI + CONSOLECOLORS.RESET);
                    break;
            }
        }
    }

    private static void enterCustomerInfo()
    {
        while (true)
        {
            System.out.println("Nhập tên của khách hàng đang đợi mua vé. " +
                    "Nhập chính xác 'esc' để ngừng việc thêm khách hàng.");
            String waitingCustomer = InputMethods.nextLine();
            //Cho phép liên tục thêm khách vào hàng đợi cho đến khi nhập esc
            if (waitingCustomer.equals("esc"))
            {
                return;
            }
            customerQueue.offer(waitingCustomer);
            System.out.println(CONSOLECOLORS.GREEN + "Đã thêm thành công khách hàng vào hàng đợi" + CONSOLECOLORS.RESET);
        }
    }

    private static void getNextCustomer()
    {
        if (customerQueue.peek() == null)
        {
            System.out.println(CONSOLECOLORS.RED + "Không còn khách hàng nào đang đợi mua vé nữa" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println("Đã bán vé thành công cho khách hàng: " + customerQueue.poll());
    }
}
