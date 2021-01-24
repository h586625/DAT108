
"use strict"

const numberList = ['3', 'a05', 2, '6'];
const calc = new Calculator();
calc.calculate('sum', numberList);
console.log("Svaret er: " + calc.result + "\n" + "Status for utregning: " + calc.status);