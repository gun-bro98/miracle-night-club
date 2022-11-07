var b;
// 변수 선언
b = "abc";
var a = "abc"; //다른 문법과 비슷하게 사용가능

console.log("a=", b);

// 가변값
var obj1 = {
  a: 1,
  b: "bbb",
};
console.log(obj1);

obj1.a = 2; // 새로운 객체 생성이 아닌 기존 객체의 내부 값이 변경
console.log(obj1);

// 변수 복제 및 복제 후 값 비교
var q = 10;
var w = q;
console.log(q, w);

var obj2 = { c: 10, d: "ddd" };
var obj3 = obj2;

w = 15;
obj2.c = 20;
console.log(w, obj2.c);
// 변수 복제를 하더라도 기본형 데이터와 참조형 데이터에 따라 차이가 있다
// 대부분 자바스크립트는 '기본형은 값을 복제하고 참조형은 주솟값을 복사한다'라고 하지만 어떤 데이터 타입이던
// 변수를 할당하기 위해선 주솟값을 복사해야하기 때문에 모든 데이터 타입은 참조형이다.

//얕은 복사, 깊은 복사

//얕은 복사 함수 => 얕은 복사는 바로 아래 단계의 값만 복사하는 방법이다.
var copyObject = function (target) {
  var result = {};
  for (var prop in target) {
    result[prop] = target[prop];
  }
  return result;
};

var user = {
  name: "jaenam",
  urls: {
    portfolio: "http://github.com/abc",
    blog: "http://blog.com/abc",
    facebook: "http://fackbook.com/abc",
  },
};

var user2 = copyObject(user);
console.log(user2); // 얕은 복사

// 깊은 복사 => 깊은 복사는 내부의 모든 값들을 하나하나 찾아서 전부 복사하는 방법이다.
var copyObjectDeep = function (target) {
  var result = {};
  if (typeof target == "object" && target !== null) {
    for (var prop in target) {
      result[prop] = copyObjectDeep(target[prop]);
    }
  } else {
    result = target;
  }
  return result;
};

var obj4 = {
  a1: 1,
  b1: {
    c1: null,
    d1: [1, 2],
  },
};

var obj5 = copyObjectDeep(obj4);

obj5.a1 = 3;
obj5.b1.c1 = 4;
obj5.b1.d1[1] = 3;

console.log(obj4);
console.log(obj5);
// 이것이 깊은 복사다
