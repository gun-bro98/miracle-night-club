/* this -> 클래스로 생성한 인스턴스 객체
자바스크립트에서 this는 실행컨텍스트가 생성될 때 함께 결정된다.
*/

//전역공간에서의 this
console.log("전역공간의 this");
console.log(this);
console.log(global);
console.log(this === global);
// 본래 결과는 true

// 전역 변수와 전역객체
console.log("전역변수와 전역객체");
var a = 1;
console.log(a);
console.log(global.a);
console.log(this.a);

// 함수와 메서드 -> 둘의 차이는 독립성 함수는 독립적인 기능을 수행하지만 메서드는 자신을 호출한 대상 객체에 관한 동작 수행
console.log("함수와 메서드");
var func = function (x) {
  console.log(this, x);
};

var obj = {
  method: func,
};
obj.method(2);

obj.method(1);
obj["method"](2);

//method 내부에서의 this
// this에는 호출한 주체에 대한 정보가 담긴다.
var obj2 = {
  methodA: function () {
    console.log(this);
  },
  inner: {
    methodB: function () {
      console.log(this);
    },
  },
};

obj2.methodA();
obj2["methodA"]();
obj2.inner.methodB();
obj2.inner["methodB"]();
obj2["inner"].methodB();
obj2["inner"]["methodB"]();

//내부함수에서의 this
console.log("내부함수에서의 this");
var obj3 = {
  outer: function () {
    console.log(this);
    var innerFunc = function () {
      console.log(this);
    };
    innerFunc();

    var obj4 = {
      innerMethod: innerFunc,
    };
    obj4.innerMethod();
  },
};
obj3.outer();

// this를 바인딩 하지 않는 함수 (화살표 함수)
// 화살표 함수는 실행 컨텍스트를 생성할 떄 this 바인딩 과정 자체가 빠지게되여 상위 스코프의 this를 그대로 활용한다.
var objArrow = {
  outer: function () {
    console.log(this);
    var innerFunc = () => {
      console.log(this);
    };
    innerFunc();
  },
};
objArrow.outer();

// 생성자 함수
var Cat = function (name, age) {
  this.bark = "야옹";
  this.name = name;
  this.age = age;
};

var choco = new Cat("초코", 7);
var nabi = new Cat("나비", 5);
console.log(choco, nabi);

// 명시적을 this를 바인딩하는 방법
/* Call 메서드는 메서드의 호출 주체인 함수를 즉시 실행하도록 하는 명령이고 이때 call메서드의 첫번째 인자를 this로 바인딩
이후의 인자들을 호출할 함수의 매개변수로 합니다 */

//call 메서드
var func = function (a, b, c) {
  console.log(this, a, b, c);
};
func(1, 2, 3);
func.call({ x: 1 }, 4, 5, 6);
// 해당 출력문을 응용하면 임의의 객체를 this로 지정가능

//call 메서드2
var obj5 = {
  a: 1,
  method: function (x, y) {
    console.log(this.a, x, y);
  },
};

obj5.method(2, 3);
obj5.method.call({ a: 4 }, 5, 6);
