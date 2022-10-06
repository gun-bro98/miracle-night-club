// class Connection{
//     private static Connection _inst = null;
//     private int count = 0;
//     public static Connection get(){
//         if(_inst == null){
//             _inst = new Connection();
//             return _inst;
//         }
//         return _inst;
//     }

//     public void count() {count++;}
//     public int getCount() { return count; };
// }

// public class javastudy {
//     public static void main(String[] args){
//         Connection conn1 = Connection.get();
//         conn1.count();
//         Connection conn2 = Connection.get();
//         conn2.count();
//         Connection conn3 = Connection.get();
//         conn3.count();
//         System.out.print(conn1.getCount());
//     }
// }

// 싱글톤 문제 답 : 3 -> 다른 변수 함수를 호출해도 공통으로 사용하는 count의 값은 중첩됨
// print문으로 conn2, conn3으로 해도 같은 결과가 나옴

class Parent {
    int compute(int num){
        if(num <= 1) return num;
        return compute(num - 1) + compute(num - 2);
    }
}

class Child extends Parent {
    int compute(int num){
        if(num <= 1) return num;
        return compute(num - 1) + compute(num - 3);
    }
}

public class javastudy {
    public static void main(String[] args){
        Parent obj = new Child();
        System.out.println(obj.compute(4));
    }
}

// 상속과 재귀방식의 문제
// 답 : 1