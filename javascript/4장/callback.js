/*
콜백 함수 : 다른 코드의 인자로 넘겨주는 함수
-> 인자를 넘겨줌과 동시에 제어권도 함께 위임하는 함수이다.
*/
//예제1
var count = 0;
var cbFunc = function () {
  console.log(count);
  if (++count > 4) clearInterval(timer);
};
var timer = setInterval(cbFunc, 300);

//예제2

var newArr = [10, 20, 30].map(function (currentValue, index) {
  console.log(currentValue, index);
  return currentValue + 5;
});
console.log(newArr);

// 콜백 함수도 함수이기 때문에 this가 전역객체를 참조하지만 제어권을 넘겨받을 코드에서 콜백 함수에 별도로
// this가 될 대상을 지정하는 경우는 그 대상을 참조한다.

Array.prototype.map = function (callback, thisArg) {
  var mappedArr = [];
  for (var i = 0; i < this.length; i++) {
    var mappedValue = callback.call(thisArg || window, this[i], i, this);
    mappedArr[i] = mappedValue;
  }
  return mappedArr;
};

setTimeout(function () {
  console.log(this);
}, 300);

[1, 2, 3, 4, 5].forEach(function (x) {
  console.log(this);
});

// document.body.innerHTML += '<button id="a">클릭</button>';
// document.body.querySelector("#a").addEventListener("click", function (e) {
//   console.log(this, e);
// });

//콜백 함수도 함수이므로 콜백 함수로 어떤 객체의 메서드를 전달하더라도 그 메서드는 메서드가 아난 함수로서 호출된다.
// 예시
var obj = {
  vals: [1, 2, 3],
  logValues: function (v, i) {
    console.log(this, v, i);
  },
};
obj.logValues(1, 2);
[4, 5, 6].forEach(obj.logValues);

// 콜백 지옥, 비동기 제어
/*
콜백 지옥 : 콜백 함수를 익명 함수로 전달하는 과정이 반복되어 코드의 들여쓰기 수준이 감당하기 힘들 정도로 
깊어지는 현상 => 가독성이 떨어지고 코드 수정이 어렵다.

비동기 : 동기의 반댓말 -> 동기적 코드는 현재 살행중인 코드가 완료된 후에 다음 코드르 실행하는 방식
비동기적 코드는 현재 실행 중인 코드의 완료 여부와 무관하게 즉시 다음 코드로 넘어갑니다. 
*/

//callback hell
setTimeout(
  function (name) {
    var coffeeList = name;
    console.log(coffeeList);

    setTimeout(
      function (name) {
        coffeeList += ", " + name;
        console.log(coffeeList);

        setTimeout(
          function (name) {
            coffeeList += ", " + name;
            console.log(coffeeList);

            setTimeout(
              function (name) {
                coffeeList += ", " + name;
                console.log(coffeeList);
              },
              500,
              "카페라떼"
            );
          },
          500,
          "카페모카"
        );
      },
      500,
      "아메리카노"
    );
  },
  500,
  "에스프레소"
);
