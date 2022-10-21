// 실행 컨텍스트 -> 실행할 코드에 제공할 환경 정보들을 모아놓은 객체
//---------------- (1)
var a = 1;
function outer() {
  function inner() {
    console.log(a); // undefined
    var a = 3;
  }
  inner(); // --------- (2)
  console.log(a); // 1
}
outer(); // ---------- (3)
console.log(a); // 1
// 해당 코드를 통해 콜 스택에 실행 컨텍스트가 쌓이는 순서와 실행 순서를 알 수 있다.
// 스택은 전역 컨텍스트 -> outer -> inner 순으로 쌓이고 inner -> outer -> 전역 컨텍스트순으로 실행
