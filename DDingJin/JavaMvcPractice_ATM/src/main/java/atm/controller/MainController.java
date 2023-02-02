package atm.controller;

import atm.dao.AccountDAO;
import atm.dao.MemberDAO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainController {
    Scanner scan = new Scanner(System.in);
    MemberDAO memberDAO = null;
    AccountDAO accountDAO = null;
    String currentSession = ""; //log

    int sel = 0;
    int exitCnt = 0;
    boolean check = false;
    String editData = "";

    String[] memberData = null;
    String[] accountData = null;

    public void init(MemberDAO memberDAO, AccountDAO accountDao) {
        this.memberDAO = memberDAO;
        this.accountDAO = accountDao;
    }

    public void run() {
        while (true) {
            try {

                System.out.println("=== [메인화면] ===");
                System.out.println("[1]가입 [2]로그인 [0]종료");

                sel = scan.nextInt();
                //[1]가입
                if (sel == 1) {
                    System.out.println("아이디를 입력하세요 : ");
                    String id = scan.next();
                    boolean check = memberDAO.checkId(id);
                    if (check) System.out.println("이미 존재하는 아이디입니다.");
                    else {
                        System.out.println("사용할 수 있는 아이디입니다.");
                        System.out.println("비밀번호를 입력하세요 : ");
                        String pw = scan.next();
                        System.out.println("이름을 입력하세요 : ");
                        String name = scan.next();
                        String result = memberDAO.insertMember(id,pw,name) ?
                                "회원가입에 성공했습니다." :
                                "회원가입에 실패했습니다!";
                        System.out.println(result);
                    }
                }
                //[2]로그인
                else if (sel == 2) {
                    System.out.println("아이디를 입력하세요 : ");
                    String id = scan.next();
                    System.out.println("비밀번호를 입력하세요 : ");
                    String pw = scan.next();
                    check = memberDAO.checkLogin(id, pw);


                    if(check) {
                        currentSession = id;
                        System.out.println("로그인 성공");
                        while(true) {
                            System.out.println("[" + currentSession + "님 로그인중]");
                            System.out.println("[21]회원 테이블 출력    [22]계좌 테이블 출력");
                            System.out.println("[1]로그아웃 [2]정보수정 [3]정보확인 [4]회원탈퇴");
                            System.out.println("[5]입금   [6]이체   [7]잔액조회   [0]종료");
                            sel = scan.nextInt();

                            // [1]로그아웃
                            if (sel == 1) {
                                System.out.println("로그아웃합니다.");
                                currentSession = "";
                                break;
                            }
                            // [2]정보수정
                            else if (sel == 2) {

                                while(true) {
                                    System.out.println("회원정보를 수정합니다.");
                                    System.out.println("[1]아이디 [2]비밀번호 [3]이름 [0]뒤로");
                                    sel = scan.nextInt();

                                    // [1]아이디
                                    if (sel == 1) {
                                        exitCnt = 0;
                                        while(exitCnt <= 3) {
                                            System.out.println("변경하실 아이디를 입력하세요 : ");
                                            editData = scan.next();
                                            check = memberDAO.checkId(editData);
                                            if (check)  {
                                                System.out.println("이미 존재하는 아이디입니다.");
                                                exitCnt++;
                                            } else {
                                                memberDAO.changeUserData("ID",currentSession, editData);
                                                System.out.println("아이디가 변경되었습니다.");
                                                currentSession = editData;
                                                editData = "";
                                                break;
                                            }
                                        }
                                    }
                                    // [2]비밀번호
                                    else if (sel == 2) {
                                        System.out.println("변경하실 아이디를 입력하세요 : ");
                                        editData = scan.next();
                                        memberDAO.changeUserData("PW",currentSession, editData);
                                        System.out.println("비밀번호가 변경되었습니다.");
                                        editData = "";
                                    }
                                    // [3]이름
                                    else if (sel == 3) {
                                        System.out.println("변경하실 이름를 입력하세요 : ");
                                        editData = scan.next();
                                        memberDAO.changeUserData("NAME",currentSession, editData);
                                        System.out.println("이름이 변경되었습니다.");
                                        editData = "";
                                    }
                                    // [0]뒤로
                                    else if (sel == 0) {
                                        break;

                                    } else System.out.println("잘못된 선택지 입니다.");
                                }

                            }
                            // [3]정보확인
                            else if (sel == 3) {
                                memberData = memberDAO.getMemberData(currentSession);
                                accountData = accountDAO.getAccountData(currentSession);
                                if(memberData == null) continue;
                                System.out.println("=== 회원님의 계정 정보 ===");
                                System.out.print("   [0]일렬번호=" + memberData[0]);
                                System.out.print("   [1]아이디=" + memberData[1]);
                                System.out.print("   [2]비밀번호=" + memberData[2]);
                                System.out.print("   [3]성함=" + memberData[3]);
                                System.out.println();

                                if(accountData == null) {
                                    System.out.println("=== 계좌가 아직 개설되지 않았습니다! ===");
                                    continue;
                                }
                                System.out.println("=== 회원님의 계좌 정보 ===");
                                System.out.print("   [0]계좌번호=" + accountData[0]);
                                System.out.print("   [1]????=" + accountData[1]);
                                System.out.print("   [2]잔액=" + accountData[2]);
                                System.out.println();

                            }
                            // [4]회원탈퇴
                            else if (sel == 4) {
                                System.out.println("※※※   경고   ※※※");
                                System.out.println("회원님의 모든 데이터와 계좌가 지워집니다.");
                                System.out.println("지워진 데이터는 복구할 수 없습니다.");
                                System.out.println("계속 진행하시겠습니까 (Y/N) : ");
                                editData = scan.next();
                                if(editData.equals("Y")) {
                                    memberDAO.deleteMember(currentSession);
                                    accountDAO.deleteAccount(currentSession);
                                    currentSession = "";
                                    editData = "";
                                    System.out.println("회원님의 데이터가 삭제되었습니다.");
                                    break;
                                } else if(editData.equals("N")) editData = "";
                                 else {
                                    editData = "";
                                    System.out.println("잘못된 선택지 입니다. 뒤로 돌아갑니다.");
                                 }

                            }
                            // [5]입금
                            else if (sel == 5) {
                                exitCnt = 0;
                                while (exitCnt < 3) {
                                    System.out.println("입금할 금액을 입력하세요.");
                                    editData = scan.next();

                                    //유효성 검증
                                    String[] result = accountDAO.checkValidationAmount(editData);
                                    if(result[0] == "validationFalse") {
                                        System.out.println(result[1]);
                                        exitCnt++;
                                        continue;
                                    }

                                    //계좌가 존재하는지 확인
                                    accountData = accountDAO.getAccount(currentSession);
                                    if (accountData == null) {
                                        System.out.println("계좌가 존재하지 않습니다! 계좌를 자동으로 계설합니다.");
                                        accountDAO.createAccount(currentSession);
                                        System.out.println("계좌 계설 축하혜택으로 1000원이 증정되었습니다.");
                                        accountData = accountDAO.getAccount(currentSession);
                                    }
                                    sel = Integer.parseInt(editData);
                                    accountDAO.deposit(currentSession, sel);
                                    System.out.println();
                                    System.out.println(editData +"원이 입금 되었습니다.");
                                    System.out.println("현재 계좌 잔액은 " + accountData[2] + "원 입니다.");
                                    sel = -1;
                                    break;
                                }
                            }

                            // [6]이체
                            else if (sel == 6) {
                                exitCnt = 0;
                                while (exitCnt < 3) {
                                    System.out.println("입금 대상 계좌번호를 입력하세요.");
                                    editData = scan.next();

                                    //계좌가 존재하는지 확인
                                    accountData = accountDAO.getAccount(currentSession, editData);
                                    if (accountData == null) {
                                        System.out.println("해당 계좌번호는 존재하지 않습니다.");
                                        exitCnt++;
                                        continue;
                                    } else if(accountData[3].equals(currentSession)) {
                                        System.out.println("자기 자신의 계좌로 송금할 수 없습니다.");
                                        exitCnt++;
                                        continue;
                                    }

                                    System.out.println("송금할 금액을 입력하세요.");
                                    sel = scan.nextInt();
                                    //유효성 검증
                                    String[] result = accountDAO.checkValidationAmount(String.valueOf(sel));
                                    if(result[0] == "validationFalse") {
                                        System.out.println(result[1]);
                                        exitCnt++;
                                        continue;
                                    }

                                    String receiveId = accountDAO.transfer(currentSession, editData, sel);
                                    System.out.println(receiveId + "님에게 "+ sel +"원을 송금 하였습니다.");
                                    accountData = accountDAO.getAccount(currentSession);
                                    System.out.println("현재 계좌 잔액은 " + accountData[2] + "원 입니다.");
                                    sel = -1;
                                    break;
                                }
                            }
                            // [7]잔액조회
                            else if (sel == 7) {
                                accountData = accountDAO.getAccount(currentSession);
                                System.out.println("현재 계좌 잔액은 " + accountData[2] + "원 입니다.");
                            }
                            // [0]종료
                            else if (sel == 0) {
                                System.out.println("프로그램을 종료합니다..");
                                System.exit(0);
                            } else if (sel == 21) {
                                memberDAO.selectAllFromMemberPrint();
                            } else if (sel == 22) {
                                accountDAO.selectAllFromAccountPrint();
                            } else System.out.println("잘못된 선택지 입니다.");
                        }
                    }

                    else {
                        System.out.println("로그인 실패. 아이디 또는 비빌번호가 올바르지 않습니다.");
                    }

                } else if (sel == 21) {
                    memberDAO.selectAllFromMemberPrint();
                } else if (sel == 22) {
                    accountDAO.selectAllFromAccountPrint();
                }
                //[0]종료
                else if (sel == 0) {
                    System.out.println("프로그램을 종료합니다..");
                    break;
                } else System.out.println("잘못된 선택지 입니다.");


            } catch (InputMismatchException e) {
                System.out.println("잘못된 선택지 입니다.");
                currentSession = "";
                scan.next();
            }

        }
    }
}


