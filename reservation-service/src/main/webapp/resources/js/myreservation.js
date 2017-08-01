(function (){

    var MyReservation = (function (){

        var id = $(".section_my").data("userid");

        var cardSoruce = $("#card-template").html();
        var cardTemplate = Handlebars.compile(cardSoruce);

        var usedCardSource = $("#usedCard-template").html();
        var usedCardTemplate = Handlebars.compile(usedCardSource);

        var canceledCardSource = $("#canceledCard-template").html();
        var canceledCardTemplate = Handlebars.compile(canceledCardSource);

        var cancelJsonData;
        var cancelTargetDiv;
        var tapCount = [,0,0,0,0]
        var cancelTap

        var TYPE_NUM = 4;
        var CANCEL_BTN_TXT = '취소';
        var TAP_TYPE = {
            al : '전체',
            exp : '이용예정',
            fin : "이용완료",
            cancel : "취소·환불"};
        var TAP_ROOT = [
            $('.card').eq(0),
            $('.card').eq(1),
            $('.card').eq(2),
            $('.card').eq(3)];
        var CARD_TYPE = {
            reserveCancel : '예약 신청중',
            reservedCancel : '예약 확정',
            writeReview : '이용 완료',
            canceldReservation : "취소된 예약"};
        var TYPE_ROOT = [,
            $('.card').eq(0).find('.link_booking_details').eq(0),
            $('.card').eq(1).find('.link_booking_details').eq(0),
            $('.card').eq(2).find('.link_booking_details').eq(0),
            $('.card').eq(3).find('.link_booking_details').eq(0)];
        var PRICE_TYPE = [
            '일반 : ',
            '청소년 : ',
            '어린이 : ',
            "합계 : "];
        var TEMPLATE_TYPE = [,
            cardTemplate,
            cardTemplate,
            usedCardTemplate,
            canceledCardTemplate];

        var getCardCallback = AjaxProm({url : '/api/myreservations/'+id, type : "GET"});
        getCardCallback.then(function(data){
            data.forEach(function(item){
            	var reservationType = item.reservationType;
                var start = item.displayStart.split(' ');
                var end = item.displayEnd.split(' ');
                var date = start[0] + ' ~ ' + end[0];
                var generalTicketCount = item.generalTicketCount;
                var youthTicketCount = item.youthTicketCount;
                var childTicketCount = item.childTicketCount;
                var totalCount = generalTicketCount + youthTicketCount + childTicketCount;

            	addArticle(TYPE_ROOT[reservationType],TEMPLATE_TYPE[reservationType],
                    {
                        no : item.id,
                        name : item.name,
                        date : date,
                        content : PRICE_TYPE[0] + generalTicketCount + ' ' + PRICE_TYPE[1] + youthTicketCount + ' ' + PRICE_TYPE[2] + childTicketCount + ' ' + PRICE_TYPE[3] + totalCount,
                        product : item.name,
                        company : item.placeName,
                        price : item.totalPrice
                    });

                tapCount[reservationType]++;
            });

            changeTabNum({
                all : tapCount[1]+tapCount[2]+tapCount[3]+tapCount[4],
                expe: tapCount[1]+tapCount[2],
                fin :tapCount[3],
                cancel : tapCount[4]});

            clickCancelBtn();
        });

        bindClickingTab();

        bindLayerBtn('.popup_booking_wrapper');

        function bindClickingTab(){
            $('.summary_board li').on('click',function(){
                $('.summary_board li').find('.link_summary_board').removeClass('on');
                $(this).find('.link_summary_board').addClass('on');

                var type = $(this).find('.tit').text();

                if (type === TAP_TYPE.al){
                    TAP_ROOT[0].css("display","");
                    TAP_ROOT[1].css("display","");
                    TAP_ROOT[2].css("display","");
                    TAP_ROOT[3].css("display","");
                } else if ( type === TAP_TYPE.exp){
                    TAP_ROOT[0].css("display","");
                    TAP_ROOT[1].css("display","");
                    TAP_ROOT[2].css("display","none");
                    TAP_ROOT[3].css("display","none");
                } else if ( type === TAP_TYPE.fin){
                    TAP_ROOT[0].css("display","none");
                    TAP_ROOT[1].css("display","none");
                    TAP_ROOT[2].css("display","");
                    TAP_ROOT[3].css("display","none");
                } else if ( type === TAP_TYPE.cancel){
                    TAP_ROOT[0].css("display","none");
                    TAP_ROOT[1].css("display","none");
                    TAP_ROOT[2].css("display","none");
                    TAP_ROOT[3].css("display","");
                }
            });
        }

        function changeTabNum(option){
            $('.summary_board .figure').eq(0).text(option.all);
            $('.summary_board .figure').eq(1).text(option.expe);
            $('.summary_board .figure').eq(2).text(option.fin);
            $('.summary_board .figure').eq(3).text(option.cancel);
        }

        function removeCard(type)
        {
            $('.card').eq(0).find('.card_item').remove();
            $('.card').eq(0).remove();
        }

        function addArticle(root,template,option){
            // console.log(root);
            var context = {
                no : option.no,
                name : option.name,
                date : option.date,
                content : option.content,
                product : option.product,
                company : option.company,
                price :option.price};

            var html = template(context);

            root.show();

            var $element_ul = parent.root;

            root.after($(html));
        }

        function clickCancelBtn(){
            $('.card_item .booking_cancel').on('click',function(){
                var btnTxt = $(this).find('button').text();
                var id = $(this).closest('.card_detail').find('.booking_number span').text();
                var name = $(this).closest('.card_detail').find('.tit').text();
                var date = $(this).closest('.card_detail').find('ul li .item_dsc').eq(0).text();
                var type = $(this).closest('.card').find('.link_booking_details .tit').eq(0).text();
                cancelTargetDiv = $(this).closest('.card_item')

                if (type === CARD_TYPE.reserveCancel){
                    cancelJsonData = JSON.stringify({'id' : id, 'reservationType' : TYPE_NUM});
                    cancelTap = 1;
                    // $('.popup_booking_wrapper').data('id',id)
                    layerOpen('.popup_booking_wrapper',name,date);
                }else if(type === CARD_TYPE.reservedCancel){
                    cancelJsonData = JSON.stringify({'id' : id, 'reservationType' : TYPE_NUM});
                    cancelTap = 2;
                    layerOpen('.popup_booking_wrapper',name,date);
                }else{

                }
            });
        }

        function layerOpen(el,name,date){
            var temp = $(el);

            temp.find('.pop_tit span').text(name);
            temp.find('.pop_tit .sm').text(date);

            temp.fadeIn();
        }

        function bindLayerBtn(layer){
            $(layer).find('.btn_gray').on('click',function(){
                $(layer).fadeOut();
            })

            $(layer).find('.popup_btn_close').on('click',function(){
                $(layer).fadeOut();
            })

            $(layer).find('.popup_btn_close').on('click',function(){
                $(layer).fadeOut();
            })

            $(layer).find('.btn_green').on('click',function(){
                var ajaxCallback = AjaxProm({url : './api/myreservations', type : "PUT", data : cancelJsonData});
                ajaxCallback.then(function(data){
                    var clone = cancelTargetDiv.clone();
                    cancelTargetDiv.remove();
                    $('.card').eq(3).find('.link_booking_details').eq(0).after(clone);

                    tapCount[cancelTap]--;
                    tapCount[4]++;
                    changeTabNum({
                        all : tapCount[1]+tapCount[2]+tapCount[3]+tapCount[4],
                        expe: tapCount[1]+tapCount[2],
                        fin :tapCount[3],
                        cancel : tapCount[4]});

                    $(layer).fadeOut();
                })
            })
        }

    })();

})();
