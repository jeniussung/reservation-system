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

var addCommaPrice = (function (){

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

var removeCommaPrice = (function(){

    return function(price){

        var curPrice="";

        for(var i = 0; i < price.length ; i++){
            if(price.charAt(i) !== ',')
                curPrice += price.charAt(i);
        }

         curPrice *= 1;

        return curPrice;
    }
})();

var Observer = (function (){

    function Observer(){};

    Observer.prototype = new eg.Component();
    Observer.prototype.constructor = Observer;

    Observer.prototype.pricePlus = function(ele){
        this.trigger("pricePlus",{ele : ele});
    }

    Observer.prototype.priceMinus = function(ele){
        this.trigger("priceMinus", {ele : ele});
    }

    Observer.prototype.ticketPlus = function(ele){
        this.trigger("ticketPlus",{ele : ele});
    }

    Observer.prototype.ticketMinus = function(ele){
        this.trigger("ticketMinus",{ele : ele});
    }

    Observer.prototype.validate = function(){
        this.trigger("validate");
    }

    return Observer;
})();

var Flicking = (function (){

    function Flicking(){};

    Flicking.prototype = new eg.Component();
    Flicking.prototype.constructor = Flicking;

    Flicking.prototype.flickingStart = function(e){
        this.trigger("flickingStart",{e:e});
    }

    Flicking.prototype.flickingMove = function(e){
        this.trigger("flickingMove",{e:e});
    }

    Flicking.prototype.flickingEnd = function(e){
        this.trigger("flickingEnd",{e:e});
    }

    return Flicking;
})();
