/* undefined와 null
두가지 모두 없음을 표기하는 문자이지만 차이점이 존재.
undefined -> 사용자가 명시적으로 지정할 수도 있지만 값이 존재하지 않을 때 자바스크립트 엔진이 자동으로 부여함
자바스크립트 엔진이 사용자가 어떤 값을 지정할 것이라고 예상되는 상황임에도 실제로 그렇게 하지 않았을 때 undefined 부여
1. 값을 대입하지 않은 변수, 데이터 영역에 메모리 주소를 지정하지 않은 식별자에 접근할 때
2. 객체 내부의 존재하지 않는 프로퍼티에 접근하려할 때
3. return문이 없거나 호출되지 않는 함수의 실행 결과.
*/

// 자동으로 undefined를 부여하는 경우

var a;
console.log(a); //1번

var obj = { a: 1 };
console.log(obj.a); // 1번
console.log(obj.b); // 2번
//console.log(b); // b는 정의되지 않음 -> 오류

var func = function () {};
var c = func();
console.log(c);

// 비어있는 배열의 경우 undefined조차 할당 되지 않는다

var arr1 = [];
arr1.length = 3;
console.log(arr1); //empty

// null은 비어있음을 명시적으로 나타내고자 할 때 사용
var n = null;
console.log(typeof n);
// null 주의사항 -> typeof null은 object이다.-> 자바스크립트 자체의 버그
// 이로 인해서 null과 undefined를 동등연산자(==)로 비교하면 서로 같다고 판단하지만 일치 연산자(===)로 하면 false가 출력
