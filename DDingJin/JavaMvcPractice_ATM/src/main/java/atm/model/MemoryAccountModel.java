package atm.model;

public class MemoryAccountModel {
    private int accountCount = 0;

    //[][0]계좌번호(PK), [][1]???? , [][2]잔액, [][3]아이디(ForeignKey = Member.아이디)
    private String[][] accountList = new String[accountCount][4];

    boolean accountValidationCheck = false;


    public int getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }

    public String[][] getAccountList() {
        return accountList;
    }

    public void setAccountList(String[][] accountList) {
        this.accountList = accountList;
    }
}
