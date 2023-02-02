package atm;


import atm.controller.MainController;
import atm.dao.AccountDAO;
import atm.dao.MemberDAO;

public class Main {
    public static void main(String[] args) {

        MainController mainController = new MainController();
        MemberDAO memberDAO = new MemberDAO();
        AccountDAO accountDAO = new AccountDAO();

        //dev options
        memberDAO.enableSampleData();
        accountDAO.enableSampleData();


        mainController.init(memberDAO, accountDAO);
        mainController.run();


    }
}
