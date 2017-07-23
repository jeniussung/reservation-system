$.fn.goBefore = function(width){
        console.log(width);
        console.log(this);
        this.animate({ "left": "-="+width+"px" }, "slow" );

}

$.fn.addCommentLi =function(source, context)
{
    template = Handlebars.compile(soruce);
    var html    = template(context);

    this.show();
    var $element_ul = parent.this;

    $(html).appendTo($element_ul);
}

var GetProductId = (function (){

    return function (){

        var querystring = new Array;
        var returnValue;

        return {
            getProductId : function getProductId(){
                querystring = String (location).split ('/')
                returnValue = querystring[querystring.length-1];
                return returnValue;
            }
        }
    }
})();

var selectParamAjax = (function (){

    return function (url, wantFunction){
        $.ajax({
          url: url,
          type: "GET",
          contentType:"application/json; charset=UTF-8",
          dataType:"json",
          success: wantFunction
        });
    }
})();

var commaPrice = (function (){

    return function(price){
        var reverse="";
        var commaMoney=""
        var money =price+"";

        if(money.length > 3){

            for(var i = 0 ; i <= money.length ; i++){

                if(i%3==0 && i!=0){
                    reverse += ',';
                }

                reverse += money.charAt(money.length-1-i);
            }

            for(var i = 0 ; i < reverse.length ;i++){
                commaMoney += reverse.charAt(reverse.length-1-i);
            }

            return commaMoney;
        }else{
            return price;
        }
    }
})();
