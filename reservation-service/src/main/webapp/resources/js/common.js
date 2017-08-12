function extend(superClass, def) {
   var extendClass = function extendClass() {
      // Call a parent constructor
      superClass.apply(this, arguments);

      // Call a child constructor
      if (typeof def.init === "function") {
         def.init.apply(this, arguments);
      }
   };

   var ExtProto = function() {};
   ExtProto.prototype = superClass.prototype;

   var extProto = new ExtProto();
   for (var i in def) {
      extProto[i] = def[i];
   }
   extProto.constructor = extendClass;
   extendClass.prototype = extProto;

   return extendClass;
};

var GetProductId = (function () {

    return function () {

        var querystring = new Array;
        var returnValue;

        return {
            getQueryString : function getQueryString(pName,query)
            {
                var qS = query.toString().split ('?')[1];
                if (!qS) return false;
                qS = qS. split ('&');
                for (var i = 0 ; i < qS.length; i++)
                {
                    var o = qS[i].split('=');
                    if (pName == o[0])
                    {
                        returnValue = o[1]
                    }
                }
                return returnValue;
            },

            getProductId: function getProductId() {
                querystring = location.toString().split('/')
                returnValue = querystring[querystring.length - 1];
                return returnValue;
            }
        }
    }
})();

var selectParamAjax = (function () {

    return function (url, wantFunction) {
        $.ajax({
            url: url,
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: wantFunction
        });
    }
})();

var AjaxProm = (function (option) {

    return $.ajax({
        url: option.url,
        type: option.type,
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        data: option.data
    });
});

// var putParamAjax = (function (optoin) {
//     return $.ajax({
//         url: option.url,
//         type: "PUT",
//         contentType: "application/json; charset=UTF-8",
//         dataType: "json",
//         data: JSON.stringify({"name": name, "id": id})
//     });
// })

var addCommaPrice = (function () {
    return function (price) {

        var reverse = "";
        var commaMoney = ""
        var money = price + "";

        if (money.length > 3) {

            for (var i = 0; i <= money.length; i++) {

                if (i % 3 == 0 && i != 0) {

                    reverse += ',';
                }

                reverse += money.charAt(money.length - 1 - i);
            }

            for (var i = 0; i < reverse.length; i++) {

                commaMoney += reverse.charAt(reverse.length - 1 - i);
            }

            return commaMoney;
        } else {
            return price;
        }
    }
})();

var removeCommaPrice = (function () {

    return function (price) {

        var curPrice = "";

        for (var i = 0; i < price.length; i++) {
            if (price.charAt(i) !== ',')
                curPrice += price.charAt(i);
        }

        curPrice *= 1;

        return curPrice;
    }
})();

var ValidateReserve = (function () {

    function ValidateReserve() {
    };

    ValidateReserve.prototype = new eg.Component();
    ValidateReserve.prototype.constructor = ValidateReserve;

    ValidateReserve.prototype.validate = function () {
        this.trigger("validate");
    }

    return ValidateReserve;
})();


var Flicking = (function () {
    function Flicking(ele) {
        this.root = ele;
        this.ele = ele.find('li');
        this.num = 1;
        this.slide_width = this.ele.outerWidth();
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

    Flicking.prototype.flickingStart = function (e) {
        if (e.type === 'touchstart' && e.touches.length === 1) {
            this.touch_start_x = e.touches[0].pageX;
            this.touch_start_y = e.touches[0].pageY;
        }
        e.preventDefault();
    }

    Flicking.prototype.flickingMove = function (e) {
        var drag_dist = 0;
        var scroll_dist = 0;
        this.curLiPosition = this.ele.closest("ul").position().left;

        if (e.type === 'touchmove' && e.touches.length === 1) {
            drag_dist = e.touches[0].pageX - this.touch_start_x;
            scroll_dist = e.touches[0].pageY - this.touch_start_y;
            this.move_dx = ( drag_dist / this.cur_dist ) * 100;
            this.move_sum += this.move_dx;

            if (this.ele.closest("ul").is(":animated")) {
                this.save_x = 0;
                this.touch_start_y = 0;
                this.touch_start_x = 0;
                this.move_dx = 0;
                this.move_sum = 0;

                return false;
            }

            if (Math.abs(drag_dist) > Math.abs(scroll_dist)) {
                if (this.curLiPosition > 0) {
                    this.save_x = 1;
                } else {
                    if (Math.abs(this.move_sum) < this.cur_dist) {
                        this.ele.closest("ul").css({"left": "+=" + this.move_dx + "px"});
                    } else {
                        this.save_x = 1;
                    }
                }
                e.preventDefault();
            }
        }
    }

    Flicking.prototype.flickingEnd = function (e) {
        if (e.type === 'touchend' && e.touches.length === 0) {
            if (Math.abs(this.move_dx) > 8) {

                if (this.save_x > 0) {
                    this.curLiPosition = this.ele.closest("ul").position().left;
                    this.ele.closest("ul").animate({"left": "-=" + (this.curLiPosition + ((this.num - 1) * this.cur_dist)) + "px"}, "fast");

                    this.save_x = 0;
                    this.touch_start_y = 0;
                    this.touch_start_x = 0;
                    this.move_dx = 0;
                    this.move_sum = 0;

                    return false;
                }

                if (this.move_sum > 0) {

                    if (this.ele.closest("ul").is(":animated")) {
                        return false;
                    }

                    if (this.num != 1) {
                        //$('.figure_pagination > span:first').text(--curImgnum);
                        this.ele.closest("ul").animate({"left": "+=" + (this.cur_dist - this.move_sum) + "px"}, "slow");
                        this.num--;
                    }
                } else {
                    if (this.ele.closest("ul").is(":animated")) {
                        return false;
                    }

                    if (this.num != this.slide_count) {
                        // $('.figure_pagination > span:first').text(++curImgnum);
                        this.ele.closest("ul").animate({"left": "-=" + (this.cur_dist + this.move_sum) + "px"}, "slow");
                        this.num++;
                    } else {
                        this.curLiPosition = this.ele.closest("ul").position().left;
                        this.ele.closest("ul").animate({"left": "-=" + (this.curLiPosition + ((this.num - 1) * this.cur_dist)) + "px"}, "fast");
                    }
                }
            } else {
                if (this.ele.closest("ul").is(":animated")) {
                    return false;
                }

                this.curLiPosition = this.ele.closest("ul").position().left;

                this.ele.closest("ul").animate({"left": "-=" + (this.curLiPosition + ((this.num - 1) * this.cur_dist)) + "px"}, "fast");
            }

            this.touch_start_y = 0;
            this.touch_start_x = 0;
            this.move_dx = 0;
            this.move_sum = 0;

            e.preventDefault();

        }

    }

    Flicking.prototype.init = function (topFlickingEnd) {
        $(document).on('click', '.btn_prev', function () {

            if (this.num !== 1) {
                $(".visual_img").animate({"left": "+=" + this.slide_width + "px"}, "slow");

                if (this.num === 2) {
                    $('.visual_txt_inn').eq(0).css('display', '');
                }

                this.num--;
            }

            this.trigger(topFlickingEnd, {curDisplayNum: this.num});
        }.bind(this))

        $(document).on('click', '.btn_nxt', function () {
            $('.visual_txt_inn').css('display', 'none');

            if (this.num != this.slide_count) {
                $(".visual_img").animate({"left": "-=" + this.slide_width + "px"}, "slow");
                //  $('.figure_pagination > span:first').text(++curImgnum);
                this.num++;
            }

            this.trigger(topFlickingEnd, {curDisplayNum: this.num});
        }.bind(this))

        this.root.get(0).addEventListener('touchstart', function (e) {
            this.flickingStart(e);
        }.bind(this), false);

        this.root.get(0).addEventListener('touchmove', function (e) {
            this.flickingMove(e)
        }.bind(this), false);

        this.root.get(0).addEventListener('touchend', function (e) {
            this.flickingEnd(e);

            this.trigger(topFlickingEnd, {curDisplayNum: this.num});
        }.bind(this), false);
    };

    Flicking.prototype.flush = function(){
        this.ele.closest("ul").animate({"left": "0px"}, "fast");
        this.num = 1;
        this.touch_start_y = 0;
        this.touch_start_x = 0;
        this.save_x = 0;
        this.move_dx = 0;
        this.move_sum = 0;
    }

    return Flicking;
})();
