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

            for(var i = 0; i < data.length ; i++){

                if(data[i].priceType === 1){
                    price += "성인(만 19 ~ 64세)"+commaPrice(data[i].price)+"원";
                    addPrcieInfo("성인", commaPrice(data[i].price), commaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }else if(data[i].priceType === 2){
                    price += "/ 청소년(만 13 ~ 18세)"+commaPrice(data[i].price)+"원";
                    addPrcieInfo("청소년", commaPrice(data[i].price), commaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }else if(data[i].priceType === 3){
                    price += "/ 어린인(만 4 ~ 12세)"+commaPrice(data[i].price)+"원";
                    addPrcieInfo("어린이", commaPrice(data[i].price), commaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }else if(data[i].priceType === 4){
                    price += "/ 국가유공자, 장애인, 65세 이상"+commaPrice(data[i].price)+"원";
                    addPrcieInfo("국가유공자, 장애인, 65세 이상",commaMoney(data[i].price), commaPrice(data[i].price*(1-data[i].discountRate)), data[i].discountRate*100);
                }
            }

            $('.dsc').eq(2).text(price);
        }

        function addPrcieInfo(priceType, price, discountPrice, discountRate){

            var context = {priceType : priceType, price : price, discountPrice : discountPrice, discountRate : discountRate};
            var html = template(context);

            $('.ticket_body').show();
            var $element_ul = parent.$('.ticket_body');

            $(html).appendTo($element_ul);
        }
    })();

})();
