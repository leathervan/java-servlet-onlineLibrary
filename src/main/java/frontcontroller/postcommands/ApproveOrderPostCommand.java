package frontcontroller.postcommands;

import dao.book.BookDaoImpl;
import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import entity.user.User;
import entity.user.UserRole;
import frontcontroller.ServletCommand;
import service.BookService;
import service.ReceiptService;
import service.SubscriptionService;
import service.UserService;
import util.MappingProperties;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class ApproveOrderPostCommand implements ServletCommand {
    private SubscriptionService subscriptionService;
    private ReceiptService receiptService;
    private BookService bookService;
    private static String approvePage;

    public ApproveOrderPostCommand() {
        subscriptionService = new SubscriptionService(SubscriptionDaoImpl.getInstance());
        receiptService=new ReceiptService(ReceiptDaoImpl.getInstance());
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        approvePage = properties.getProperty("workerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String datetime = req.getParameter("datetime");
        datetime.replace('T',' ');
        Subscription subscription = subscriptionService.getSubscription(Integer.valueOf(req.getParameter("subID")));
        subscriptionService.setEndTime(subscription,datetime);
        Receipt receipt = receiptService.getReceipt(Integer.valueOf(req.getParameter("receiptID")));
        receiptService.changeStatus(receipt, ReceiptStatus.SUBSCRIPTION.ordinal());
        Book book=bookService.getBook(subscription.getBook_id());
        bookService.decreaseBookAmount(book);
        return approvePage;
    }
}