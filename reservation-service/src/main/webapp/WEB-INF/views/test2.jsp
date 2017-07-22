<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/resources/lib/handlebars.min.js"></script>
<script type="text/javascript" src="/resources/lib/jquery.min.js"></script>
</head>
<body>

<script type="text/javascript">
//foo();


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



    </script>

</body>
<div id = 'aa'>test</div>
</html>
