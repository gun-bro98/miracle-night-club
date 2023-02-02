package atm.dao;

import atm.model.MemoryMemberModel;

public class MemberDAO {

    MemoryMemberModel memberModel = new MemoryMemberModel();
    String[][] memberList = null;
    int memberCount = 0;
    boolean check = false;




    public void enableSampleData() {
        String[][] sampleMember = { //일렬번호, 아이디, 비밀번호, 성함
                {"0", "1001" , "qwer" , "김철수"},
                {"1", "1002" , "mmkk11" , "이영희"},
                {"2", "1003" , "javaking123" , "최민수"},
                {"3", "aaa"  ,  "123" , "김명진"}
        };
        memberList = new String[sampleMember.length][4];
        memberCount = sampleMember.length;
        //Stream Copy
        for (int i = 0; i < sampleMember.length; i++) {
            memberList[i][0] = sampleMember[i][0];
            memberList[i][1] = sampleMember[i][1];
            memberList[i][2] = sampleMember[i][2];
            memberList[i][3] = sampleMember[i][3];
        }
        memberModel.setMemberList(memberList);
        memberModel.setMemberCount(memberCount);

    }


    //회원가입시 중복아이디 확인
    public boolean checkId(String id) {
        check = false;
        memberCount = memberModel.getMemberCount();
        memberList = memberModel.getMemberList();
        for (int i = 0; i < memberCount; i++) {
            if (memberList[i][1].equals(id)) {
                check = true;
                break;
            }
        }
        return check;
    }

    //로그인시 일치회원 찾기
    public boolean checkLogin(String id, String pw) {
        check = false;
        memberCount = memberModel.getMemberCount();
        memberList = memberModel.getMemberList();

        for (int i = 0; i < memberCount; i++) {
            if (memberList[i][1].equals(id) && memberList[i][2].equals(pw)) {
                check = true;
                break;
            }
        }
        return check;
    }

    //회원 가입
    public boolean insertMember(String loginId, String pw, String name) {
        memberList = memberModel.getMemberList();
        memberCount = memberModel.getMemberCount();
        String[][] memberTemp = new String[memberCount+1][4];

        //Stream Copy
        for (int i = 0; i < memberCount; i++) {
            memberTemp[i][0] = memberList[i][0];
            memberTemp[i][1] = memberList[i][1];
            memberTemp[i][2] = memberList[i][2];
            memberTemp[i][3] = memberList[i][3];
        }
        //마지막 행에 데이터 추가
        memberTemp[memberCount][0] = String.valueOf(memberCount);
        memberTemp[memberCount][1] = loginId;
        memberTemp[memberCount][2] = pw;
        memberTemp[memberCount][3] = name;

        memberModel.setMemberList(memberTemp);
        memberModel.setMemberCount(++memberCount);

        return true;
    }

    //아이디 , 비밀번호 , 이름  변경
    public void changeUserData(String Type, String beforeData, String afterData) {
        memberList = memberModel.getMemberList();
        memberCount = memberModel.getMemberCount();
        for (int i = 0; i < memberCount; i++) {
            if (memberList[i][1].equals(beforeData)) {
                if(Type.equals("ID")) memberList[i][1] = afterData;
                else if(Type.equals("PW")) memberList[i][2] = afterData;
                else if(Type.equals("NAME")) memberList[i][3] = afterData;
                break;
            }
        }
    }

    //회원 탈퇴
    public void deleteMember(String sessionId) {
        memberList = memberModel.getMemberList();
        memberCount = memberModel.getMemberCount();
        String[][] memberTemp = new String[memberCount-1][4];

        //Stream Copy
        for (int i = 0; i < memberCount; i++) {
            if (!memberList[i][1].equals(sessionId)) {
                memberTemp[i][0] = memberList[i][0];
                memberTemp[i][1] = memberList[i][1];
                memberTemp[i][2] = memberList[i][2];
                memberTemp[i][3] = memberList[i][3];
            }
        }
        memberModel.setMemberList(memberTemp);
        memberModel.setMemberCount(--memberCount);
    }




    //개인 회원 정보 Export - <유저 사용가능>
    public String[] getMemberData(String sessionId) {
        memberList = memberModel.getMemberList();
        memberCount = memberModel.getMemberCount();
        for (int i = 0; i < memberCount; i++) {
            if (memberList[i][1].equals(sessionId)) {
                return memberList[i];
            }
        }
        return null;
    }


    //회원 정보 Export - <관리자 전용>
    public String[][] selectAllFromMember() {
        memberList = memberModel.getMemberList();
        return memberList;
    }


    //회원 정보 출력 - <관리자 전용>
    public void selectAllFromMemberPrint() {
        memberList = memberModel.getMemberList();
        System.out.println("=== 회원 명단 ===");
        System.out.println("[[" + " 총 인원 : " + memberModel.getMemberCount() + " ]]");
        for (String[] member :memberList) {
            System.out.print("   [0]일렬번호=" + member[0]);
            System.out.print("   [1]아이디=" + member[1]);
            System.out.print("   [2]비밀번호=" + member[2]);
            System.out.print("   [3]성함=" + member[3]);
            System.out.println();
        }
        System.out.println("======");
    }



}