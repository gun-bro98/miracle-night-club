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

/*
environmentRecord와 호이스팅 -> 변수 정보를 수집하는 과정을 더욱 이해하기 쉬운 방법으로 대체한 가상의 개념
environmentRecord에는 현재 컨텍스트와 관련된 코당 식별자 정보들이 저장됨. 
*/

// 매개변수와 변수에 대한 호이스팅
console.log("변수 호이스팅");
function a1(x) {
  console.log(x);
  var x;
  console.log(x);
  var x = 2;
  console.log(x);
}
a1(1);
// --> var x직후 console.log에서 값이 undefined로 예상이 되지만 실제로는 1이 출력됨
function a2(x) {
  //함수 a의 호이스팅과정을 보기 위한 변형 코드 (실제 동작과는 다름)
  var x;
  var x;
  var x;

  x = 1;
  console.log(x);
  console.log(x);
  x = 2;
  console.log(x);
}
// 함수 선언의 호이스팅
console.log("함수 호이스팅");

function a3() {
  console.log(b);
  var b = "bbb";
  console.log(b);
  function b() {}
  console.log(b);
}
a3();

// b함수의 호이스팅을 마친 결과
function a4() {
  var b; // 수집 대상 1 : 변수는 선언부만 끌어올린다.
  function b() {} // 수집 대상 2 : 함수 선언은 전체를 끌어올린다.

  console.log(b);
  b = "bbb"; // 변수할당부는 원래 자리에 남겨둔다.
  console.log(b);
  console.log(b);
}
a4();

// 스코프 체인
/* 
스코프는 식별자에 대한 유효범위 -> 어떤 경계 A의 외부에서 선언한 변수는 A의 외부뿐 아니라 
A의 내부에서도 접근이 가능하지만 A의 내부에서 선언한 변수는 오직 A의 내부에서만 접근가능 (지역변수 전역변수와 비슷)
'식별자의 유효범위'를 안에서 부서 바깐으로 차례로 검색해 나가는 것을 '스코프 체인' 이라고한다.

*/
// 스코프 체인의 코드
console.log("스코프 체인");
var a = 1;
var outer = function () {
  var inner = function () {
    console.log(a);
    var a = 3;
  };
  inner();
  console.log(a);
};
outer();
console.log(a);
