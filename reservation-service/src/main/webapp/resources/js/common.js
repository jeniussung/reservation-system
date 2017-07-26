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
                querystring = location.toString().split ('/')
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

var AjaxProm = (function (option){

    return $.ajax({
          url: option.url,
          type: option.type,
          contentType:"application/json; charset=UTF-8",
          dataType:"json"
        });
});


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

    function Flicking(ele){
        this.ele = ele;
        this.num = 1;
        this.slide_width = $(this.ele).outerWidth();
        this.slide_count = this.ele.length;
        this.touch_start_y = 0;
        this.touch_start_x = 0;
        this.save_x = 0;
        this.save_y = 0;
        this.move_dx = 0;
        this.cur_dist = this.slide_width;
        this.move_sum = 0;
        this.curLiPosition;
    };

    Flicking.prototype = new eg.Component();
    Flicking.prototype.constructor = Flicking;

    Flicking.prototype.flickingStart = function(e){
        console.log("start");
        if ( e.e.type === 'touchstart' && e.e.touches.length === 1 ) {
            this.touch_start_x = e.e.touches[ 0 ].pageX;
            this.touch_start_y = e.e.touches[ 0 ].pageY;
        }
    }

    Flicking.prototype.flickingMove = function(e){
        console.log("start");
        var drag_dist = 0;
        var scroll_dist = 0;
        this.curLiPosition = this.ele.closest("ul").position().left;

        if ( e.e.type === 'touchmove' && e.e.touches.length === 1 ) {
            drag_dist = e.e.touches[ 0 ].pageX - this.touch_start_x;
            scroll_dist = e.e.touches[ 0 ].pageY - this.touch_start_y;
            this.move_dx = ( drag_dist / this.cur_dist ) * 100;
            this.move_sum+=this.move_dx;

            if(this.ele.closest("ul").is(":animated")){
                this.save_x = 0;
                this.touch_start_y = 0;
                this.touch_start_x = 0;
                this.move_dx = 0;
                this.move_sum = 0;

                return false;
            }

            if ( Math.abs(drag_dist ) > Math.abs( scroll_dist )) {
                  if(this.curLiPosition > 0){
                     this.save_x =  1;
                  } else {
                    if(Math.abs(this.move_sum) < this.cur_dist){
                        this.ele.closest("ul").css({ "left": "+="+this.move_dx+"px" });
                    }else{
                        this.save_x =  1;
                    }
                }
                e.e.preventDefault( );
            }
        }

    }

    Flicking.prototype.flickingEnd = function(e){
        if ( e.e.type === 'touchend' && e.e.touches.length === 0 ) {
            if ( Math.abs( this.move_dx ) > 8) {

                if(this.save_x > 0){
                     this.curLiPosition = this.ele.closest("ul").position().left;
                     this.ele.closest("ul").animate({ "left": "-="+(this.curLiPosition+((this.num-1)*this.cur_dist))+"px" }, "fast" );

                    this.save_x = 0;
                    this.touch_start_y = 0;
                    this.touch_start_x = 0;
                    this.move_dx = 0;
                    this.move_sum = 0;

                    return false;
                }

                if (this.move_sum > 0){

                    if(this.ele.closest("ul").is(":animated")){
                        return false;
                    }

                    if (this.num!=1){
                        //$('.figure_pagination > span:first').text(--curImgnum);
                        this.ele.closest("ul").animate({ "left": "+="+(this.cur_dist-this.move_sum)+"px" }, "slow" );
                        this.num--;
                    }
                } else{
                    if (this.ele.closest("ul").is(":animated")){
                        return false;
                    }

                    if (this.num != this.slide_count){
                        // $('.figure_pagination > span:first').text(++curImgnum);
                        this.ele.closest("ul").animate({ "left": "-="+(this.cur_dist+this.move_sum)+"px" }, "slow" );
                        this.num++;
                    }else{
                        this.curLiPosition = this.ele.closest("ul").position().left;
                        this.ele.closest("ul").animate({ "left": "-="+(this.curLiPosition+((this.num-1)*this.cur_dist))+"px" }, "fast" );
                    }
                }
            } else {
                if(this.ele.closest("ul").is(":animated")){
                    return false;
                }

                this.curLiPosition = this.ele.closest("ul").position().left;

                this.ele.closest("ul").animate({ "left": "-="+(this.curLiPosition+((this.num-1)*this.cur_dist))+"px" }, "fast" );
            }

            this.touch_start_y = 0;
            this.touch_start_x = 0;
            this.move_dx = 0;
            this.move_sum = 0;

            e.e.preventDefault( );
        }

    }

    Flicking.prototype.init = function(el){
        // this.on('flickingStart',this.flickingStart);
        // this.on('flickingMove',this.flickingMove);
        // this.on('flickingEnd',this.flickingEnd);

        el.addEventListener('touchstart', function(e) {
            // this.startFlicking(e);
            console.log(this);
            this.trigger("flickingStart", {e : e});
        }.bind(this), false );

        el.addEventListener('touchmove', function( e ) {
            // this.moveFlicking(e);
            console.log(this);
            Flicking.prototype.trigger("flickingMove", {ev : e});
        }.bind(this), false );

        el.addEventListener('touchend', function( e ) {
            // this.endFlicking(e);
            console.log(this);
            Flicking.prototype.trigger("flickingEnd", {e : e});
        }.bind(this), false );
    };

    Flicking.prototype.off = function(){
        this.off('flickingStart',flickingStart(e));
        this.off('flickingMove',flickingMove(e));
        this.off('flickingEnd',flickingEnd(e));
    }

    Flicking.prototype.startFlicking = function(e){
        this.trigger("flickingStart", {e : e});
    }

    Flicking.prototype.moveFlicking = function(e){
        this.trigger("flickingMove", {e : e});
    }

    Flicking.prototype.endFlicking = function(e){
        this.trigger("flickingEnd", {e : e});
    }

    return Flicking;
})();
