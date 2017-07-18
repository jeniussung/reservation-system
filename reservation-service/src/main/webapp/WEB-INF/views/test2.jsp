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
    foo.addHey();
    foo.addHey();
    foo.addHey();
    foo.showHey();

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
haha.ab();

//haha.doStuff();
//haha.addfunc();
//alert(haha.addvar);





    </script>

</body>
<div id = 'aa'>test</div>
</html>
