package atm.dao;

import atm.model.MemoryAccountModel;

public class AccountDAO {

    MemoryAccountModel accountModel = new MemoryAccountModel();
    String[][] accountList = null;
    int accountCount = 0;
    int temp;
    String[] temp2;
    static String ACCOUNT_NUMBER = "94320200";
    boolean check = false;


    public void enableSampleData() {
        String[][] sampleAccount = { //계좌번호, ??? , 잔액, 아이디(FK = sampleMember 아이디)
                {"943202001" , "1234" ,"100000" , "qwer"},
                {"943202002" , "1234" ,"200000" , "mmkk11"},
                {"943202003" , "1234" ,"300000" , "mmkk11"},
                {"943202004" , "1234" ,"400000" , "javaking123"},
                {"943202005" , "1234" ,"500000" , "qwer"},
                {"943202006" , "1234" ,"600000" , "qwer"},
                {"943202007" , "1234" ,"700000" , "aaa"}
        };
        accountList = new String[sampleAccount.length][4];
        accountCount = sampleAccount.length;
        //Stream Copy
        for (int i = 0; i < sampleAccount.length; i++) {
            accountList[i][0] = sampleAccount[i][0];
            accountList[i][1] = sampleAccount[i][1];
            accountList[i][2] = sampleAccount[i][2];
            accountList[i][3] = sampleAccount[i][3];
        }
        accountModel.setAccountList(accountList);
        accountModel.setAccountCount(accountCount);
    }

    //금액의 유효성 검증
    public String[] checkValidationAmount(String amount) {
        temp2 = new String[2];
        temp2[0] = "validationTrue";

        //입금 금액 유효성 확인
        try{
            temp = Integer.parseInt(amount);
        } catch (Exception e) {
            temp2[0] = "validationFalse";
            temp2[1] = "숫자만 입력해주세요!";
            return temp2;
        }

        //입금 금액 범위 확인
        if(temp <= 0 || temp >= 100000000) {
            temp2[0] = "validationFalse";
            temp2[1] = "잘못된 입금금액 입니다.";
            return temp2;
        }
        return temp2;
    }


    //계좌 조회
    public String[] getAccount(String sessionId) {
        accountList = accountModel.getAccountList();
        accountCount = accountModel.getAccountCount();
        for (int i = 0; i < accountCount; i++) {
            if(accountList[i][3].equals(sessionId))
                return accountList[i];
        }
        return null;
    }

    //계좌 발급
    public void createAccount(String sessionId) {
        accountList = accountModel.getAccountList();
        accountCount = accountModel.getAccountCount();
        String[][] accountTemp = new String[accountCount + 1][4];
        for (int i = 0; i < accountList.length; i++) {
            accountTemp[i][0] = accountList[i][0];
            accountTemp[i][1] = accountList[i][1];
            accountTemp[i][2] = accountList[i][2];
            accountTemp[i][3] = accountList[i][3];
        }
        accountTemp[accountCount][0] = ACCOUNT_NUMBER + accountCount;
        accountTemp[accountCount][1] = "";
        accountTemp[accountCount][2] = "1000";
        accountTemp[accountCount][3] = sessionId;

        accountModel.setAccountList(accountTemp);
        accountModel.setAccountCount(++accountCount);
    }


    //입금
    public void deposit(String sessionId, int amount) {
        temp = 0;
        accountList = accountModel.getAccountList();
        accountCount = accountModel.getAccountCount();
        for (int i = 0; i < accountList.length; i++) {
            if(accountList[i][3].equals(sessionId)) {
                temp = Integer.parseInt(accountList[i][2]);
                temp = temp + amount;
                accountList[i][2] = String.valueOf(temp);
                break;
            }
        }
    }

    //계좌이체 이체 송금
    public String transfer(String sendId, String receiveAccount, int amount) {
        temp = 0;
        String receiveId = "";
        accountList = accountModel.getAccountList();
        accountCount = accountModel.getAccountCount();
        for (int i = 0; i < accountList.length; i++) {
            if(accountList[i][3].equals(sendId)) {
                temp = Integer.parseInt(accountList[i][2]);
                temp -= amount;
                accountList[i][2] = temp2[1];
            }
            if(accountList[i][0].equals(receiveAccount)) {
                temp = Integer.parseInt(accountList[i][2]);
                temp += amount;
                accountList[i][2] = String.valueOf(temp);
                receiveId = accountList[i][3];

            }
        }
        return receiveId;
    }


    //회원 정보 출력 - <관리자 전용>
    public void selectAllFromAccountPrint() {
        accountList = accountModel.getAccountList();
        System.out.println("=== 계좌 명단 ===");
        System.out.println("[[" + " 총 계좌 갯수 : " + accountModel.getAccountCount() + " ]]");
        for (String[] member : accountList) {
            System.out.print("   [0]계좌번호=" + member[0]);
            System.out.print("   [1]????=" + member[1]);
            System.out.print("   [2]잔액=" + member[2]);
            System.out.print("   [3]소유자 아이디=" + member[3]);
            System.out.println();
        }
        System.out.println("======");
    }
}
