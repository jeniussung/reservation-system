(function (){

    var GetId = GetProductId(document.location);
    var id = GetId.getProductId();

    var ReserveInfo = (function (){
        var price="";
        var soruce = $("#price-template").html();
        var template = Handlebars.compile(soruce);

        selectParamAjax('./top/'+id,getInfo);

        function getInfo(data){
            $('.img_thumb').attr('src',"/files/"+data[0].fileId);
            $('.top_title span').text(data[0].name);
            $('.preview_txt_tit').text(data[0].name);
            $('.preview_txt_dsc').eq(0).text("₩"+data[0].price+"~");

            var disStart = data[0].displayStart.split(' ');
            var disEnd = data[0].displayEnd.split(' ');

            $('.preview_txt_dsc').eq(1).text(disStart[0]+' ~ '+disEnd[0]+','+' 잔여티켓 4000매');
            $('.in_tit').eq(0).text(data[0].name);
            $('.dsc').eq(0).html("장소 : "+data[0].placeName+'<br>'+"기간 : "+disStart[0]+" ~ "+disEnd[0]);
            $('.dsc').eq(1).html(data[0].observationTime);
            $('.inline_txt .display_term').text(disStart[0]+"~"+disEnd[0]+" ");

            for(var i = 0; i < data.length ; i++){
                if(data[i].priceType === 1){
                    price += "성인(만 19 ~ 64세)"+addCommaPrice(data[i].price)+"원";
                    addPrcieInfo("성인", addCommaPrice(data[i].price), addCommaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }else if(data[i].priceType === 2){
                    price += "/ 청소년(만 13 ~ 18세)"+addCommaPrice(data[i].price)+"원";
                    addPrcieInfo("청소년", addCommaPrice(data[i].price), addCommaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }else if(data[i].priceType === 3){
                    price += "/ 어린인(만 4 ~ 12세)"+addCommaPrice(data[i].price)+"원";
                    addPrcieInfo("어린이", addCommaPrice(data[i].price), addCommaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }else if(data[i].priceType === 4){
                    price += "/ 국가유공자, 장애인, 65세 이상"+addCommaPrice(data[i].price)+"원";
                    addPrcieInfo("국가유공자, 장애인, 65세 이상",addCommaPrice(data[i].price), addCommaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }
            }

            $('.dsc').eq(2).text(price);

            bindPriceTab();
        }

        function addPrcieInfo(priceType, price, discountPrice, discountRate){
            var context = {priceType : priceType, price : price, discountPrice : discountPrice, discountRate : discountRate};
            var html = template(context);

            $('.ticket_body').show();

            var $element_ul = parent.$('.ticket_body');

            $(html).appendTo($element_ul);
        }

        function bindPriceTab()
        {
            var ee = new Observer();

            ee.on("pricePlus",function(e){
                $(e.ele).closest('.clearfix').find('.ico_minus3').removeClass("disabled");
                $(e.ele).closest('.clearfix').find('.count_control_input').removeClass("disabled");

                var price = removeCommaPrice($(e.ele).closest('.qty').find('.dsc_price').text());
                var curPrice = removeCommaPrice($(e.ele).closest('.count_control').find('.total_price').text());
                var sum = price+curPrice;
                $(e.ele).closest('.count_control').find('.total_price').text(addCommaPrice(sum));
            })

            ee.on("priceMinus",function(e){
                var price = removeCommaPrice($(e.ele).closest('.qty').find('.dsc_price').text());
                var curPrice = removeCommaPrice($(e.ele).closest('.count_control').find('.total_price').text());
                var sum = curPrice - price;

                if(sum >= 0){
                    $(e.ele).closest('.count_control').find('.total_price').text(addCommaPrice(sum));

                    if(sum === 0){
                        $(e.ele).closest('.clearfix').find('.ico_minus3').addClass("disabled");
                        $(e.ele).closest('.clearfix').find('.count_control_input').addClass("disabled");
                    }
                }
            })

            ee.on("ticketPlus",function(e){
                var sumTicketCount = $('.inline_txt .count').text()*1;
                sumTicketCount += 1;
                $('.inline_txt .count').text(sumTicketCount);

                var curTicektCount = $(e.ele).closest('.clearfix').find('.count_control_input').val()*1;
                $(e.ele).closest('.clearfix').find('.count_control_input').val(curTicektCount+1);
            })

            ee.on("ticketMinus",function(e){
                var curTicektCount = $(e.ele).closest('.clearfix').find('.count_control_input').val()*1;
                var sumTicketCount = $('.inline_txt .count').text()*1;

                if(curTicektCount != 0){
                    $(e.ele).closest('.clearfix').find('.count_control_input').val(curTicektCount-1);

                    if(sumTicketCount !== 0 ){
                        sumTicketCount -= 1;
                        $('.inline_txt .count').text(sumTicketCount);
                    }
                }
            })

            $(document).on('click','.ico_plus3',function (){
                ee.pricePlus(this);
                ee.ticketPlus(this);
            })

            $(document).on('click','.ico_minus3',function (){
                ee.priceMinus(this);
                ee.ticketMinus(this);
            })
        }
    })();

    var ReserverInfo = (function (){

        var ee = new Observer();

        ee.on("validate",function(){
             var name = isName();
             var tel = isTel()
             var email = isEmail();
             var chk = isChk();

             sumValid(name, tel, email, chk);
        });

        $(document).on('keyup','.text',function(){
            ee.validate();
        });

        $(document).on('keyup','.tel',function(){
            ee.validate();
        });

        $(document).on('keyup','.email',function(){
            ee.validate();
        });

        $(document).on( "click", "#chk3", function(){
            ee.validate();
        });

        function isName(){
            if ($('.text').val()===""){
                return false;
            } else {
                return true;
            }
        }

        function isTel(){
            var telPattern = /^01[0-1]{1}\d{3,4}\d{4}$/;

            if (telPattern.exec($('.tel').val()) !== null){
                return true;
            } else {
                return false;
            }
        }

        function isEmail(){
            var emailPattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

            if ($('.email').val() === ""){
                return true;
            } else{

                if (emailPattern.exec($('.email').val()) !== null){
                    return true;
                } else{
                    return false;
                }
            }
        }

        function isChk(){
            if ($('input:checkbox[id="chk3"]').is(":checked") === true){
                return true;
            } else{
                return false;
            }

        }

        function sumValid(isName, isTel, isEmail, isChk){
            if (isName === true && isTel === true && isEmail === true && isChk === true){
                $('.bk_btn_wrap').removeClass("disable");
            } else{
                $('.bk_btn_wrap').removeClass("disable");
                $('.bk_btn_wrap').addClass("disable");
            }
        }
    })();

    var ShowContract = (function (){
        $(document).on('click','.btn_text',function(){
            if ($(this).closest('.agreement').hasClass("open") === true){
                $(this).closest('.agreement').removeClass("open");
            } else {
                $(this).closest('.agreement').removeClass("open");
                $(this).closest('.agreement').addClass("open");
            }
        });
    })();

    var GetReserverInfo = (function (){
        selectParamAjax('./middle',getInfo);

        function getInfo(data){
            $('#name').val(data.name);
            $('#email').val(data.email);
        }
    })();

    var GoPage = (function (){
        $(document).on('click','.bk_btn',function(){
            if ($(this).closest('.bk_btn_wrap').hasClass("disable") === true) {
                console.log("cat't go")
            } else {
                console.log("can go")
            }
        });
    })();

})();
