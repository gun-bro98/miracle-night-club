package atm.model;

public class MemoryMemberModel {
    private int memberCount = 0;

    //[][0]번호 [][1]아이디(PrimaryKey) [][2]비밀번호 [][3]이름
    private String[][] memberList = new String[memberCount][4];



    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String[][] getMemberList() {
        return memberList;
    }

    public void setMemberList(String[][] memberList) {
        this.memberList = memberList;
    }



}
