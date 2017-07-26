<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/resources/lib/handlebars.min.js"></script>
<script type="text/javascript" src="/resources/lib/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/component.min.js"></script>
</head>
<body>

<script type="text/javascript">
//foo();

var PPP = (function (){

    return function (a,b){
        var w  = a;
        var q = b;

        return {
            cd : function cd(){
                w++;
            },
            cds : function cds(){
                console.log(w);
            }
        }
    }

})();

var Phone = (function (){

    return function(name,num){

        var name = name;
        var num = num;

        return {
            showName : function (){
                console.log(name);
            },
            showNum : function (){
                console.log(num);
            }
        }
    }
})();

var p1 = Phone('sung','124');
var p2 = Phone('Park','333');
p1.showName();
p2.showName();

function awew(){

}
awew.prototype.c =5;
var ddd = new awew();
console.log(ddd.c);


var abab = PPP(23,3);
console.log(abab);
abab.cd();
abab.cd();
abab.cds();


    var foo = (function(){

        var hey = 3;

        function aledo(){
            alert('aldo');
        }

        function clhey(){$(document).on('click','#aa',function(){alert(hey++)});};

        clhey();

        return {
            hi : 'hi',
            addHey : function addhi(){hey++;},
            showHey : function showhi(){alert(hey);},
            aledo : function ale(){aledo();}
        }
    })();

    //foo.aledo();

//    foo.showHey();

    var hoo = (function(){

        var hey = 3;

        function aledo(){
            alert('aldo');
        }

        function clhey(){$(document).on('click','#aa',function(){alert(hey++)});};

        clhey();

        return function(){
            return{
            hi : 'hi',
            addHey : function addhi(){hey++;},
            showHey : function showhi(){alert(hey);},
            aledo : function ale(){aledo();}
            }
        }
    })();

    hoo().addHey();
    hoo().addHey();
    //hoo().showHey();


    var coo = (function(){

        var hey = 3;

        function aledo(){
            alert('aldo');
        }

        function clhey(){$(document).on('click','#aa',function(){alert(hey++)});};

        clhey();

        return function(){
            return{
            hi : 'hi',
            addHey : function addhi(){hey++;},
            showHey : function showhi(){alert(hey);},
            aledo : function ale(){aledo();}
            }
        }
    })();

    var coc = coo();
    // coc.showHey();

    var Person = (function(){
    var a = 1;

	return function(name,age){
        var cd = name;
        var se = age;

		return {
			intro : function() {
				console.log(name,age);
			},
			addage : function(count){
				age += count;
			},
            addA : function(){
                ++cd;
            },
            showA  :function(){
                console.log(cd);
            },
		};
	}

})();


var a = Person(2,4);
a.addA();
a.addA();
var b = Person(4,6);
b.addA();
// a.showA();
// b.showA();



(showName = function (name) {
  console.log(name || "No Name")
  }
); // No Name

showName("Rich"); // Rich

showName(); // No Name

var haha = (function () {
    var firstName = 'Richard';
    var hi = 1;

    function init () {
        //doStuff (firstName);
        alert(firstName);
        // code to start the application
    }

    function doStuff () {
        // Do stuff here
        alert(hi);
    }

    function doMoreStuff () {
        // Do more stuff here
    }
    // Start the applicatio

    return {
        addvar : 3,

    //    addfunc : $(document).on('click','#aa',function(){alert(hi++)}),

        ab : function ab()
        {
            alert('a');
        }
    }
})();

//alert(haha.addvar);
// haha.ab();

//haha.doStuff();
//haha.addfunc();
//alert(haha.addvar);

function Person1() {
  this.eyes = 2;
  this.nose = 1;
  this.a = function (){
      this.eyes += 1;
  };
}
var kim  = new Person1();
var park = new Person1();
kim.a();
kim.a();
console.log(kim.eyes);  // => 2
console.log(kim.nose);  // => 1
console.log(park.eyes); // => 2
console.log(park.nose); // => 1


function Person2() {}
Person2.prototype.eyes = 2;
Person2.prototype.nose = 1;
Person2.prototype.eyes = 4;
var kim  = new Person2();
var park = new Person2();
console.log(kim.eyes); // => 2
console.log(park.eyes); // => 2

var aaa = {
    a : 21,
    bbb : function as(){
        alert(this.a);
    }
}

function wer(){
    // this.a =3;
    // this.cd = function ag(){
    //     console.log(this.a);
    // }
};
 wer.prototype.b =2;
 wer.prototype.c =4;
 wer.prototype.d = function dd(){
     b++;
 }
// wer.prototype.e = function we(){
//     console.log(this.b);
// }
var kim = new wer();
var park = new wer();
console.log(kim);
// kim.d();
// kim.d();
// kim.d();
// console.log(kim.b);
// console.log(park.b);
// wer.prototype.b = 4;
// var cc = new wer();
// var cw = new wer();
// wer.prototype.b = 5;
// cc.b = 10;
// console.log(cc.b);
// wer.prototype.b = 12;
// console.log(cw.b);
// console.log(cc.b);
// var cwe = new wer();
// console.log(cwe.b);
// console.log(wer.prototype);

var rectangle = (function (){

function Rectangle(a){
    this.ddd = a;
    this.ab = 6;
    this.cd = this.ab;
}

Rectangle.prototype = new eg.Component();
Rectangle.prototype.constructor = Rectangle;

Rectangle.prototype.go = function(){
    this.ddd += 55;
 console.log("move!!");
  console.log(this.ddd);
  console.log(this.cd);
}

Rectangle.prototype.init = function(){
    this.on("move",this.go);
}


Rectangle.prototype.move = function(){
    this.trigger("move");
}

Rectangle.prototype.clear = function(){
    this.off("move",this.go);
}


     return Rectangle;
})();

var rt = new rectangle(34);
var rs = new rectangle(24);

rt.init();
rt.move();
rt.move();
rt.move();
rt.clear();

   rs.init();
 rs.move();
rs.move();
rs.move();

// rt.on("click",function(){ // 이벤트 등록
//     console.log("aa");
// });
// rt.on("hi",);
//
// // rt.some();
// // rt.some_2();
//
// rt.trigger("hi");

 // rt.trigger("click");



var rectangle = $.extend(eg.Component,{
        // init : function(){},
        // some : function(){
        //      this.trigger("hi",{"a":1})
        // }
    });

var rt = new rectangle();

function callback(e){
    console.log("aa");
    // console.log("hi", e);
     rt.off(callback);
}

function callback_2(e){
    console.log("ab");
    // console.log("hi", e);
     rt.off(callback);
}

rt.on("hi",callback);


var regExp = /^01[0-1]{1}\d{3,4}\d{4}$/;
 /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

console.log(regExp.exec('010332332323'));

class Some extends eg.Component {
  // var aa = 5;

  hi() {
    alert("hi");
    //  console.log(aa);
 }

  init() {
    //this.once("hi", this.hi);
    this.on("hi",this.hi);
  }

}

var some = new Some();


 some.init();
 some.trigger("hi");
// // fire alert("hi");
// some.trigger("hi");

</script>

</body>
<div id = 'aa'>test</div>
</html>
