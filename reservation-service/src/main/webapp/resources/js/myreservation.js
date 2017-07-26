(function (){

    var topTap = (function (){

        // var ajaxCallback = AjaxProm({url : './top/'+id, type : "GET"});
        // /ajaxCallback.then(function(data){

        // });

        $('.summary_board li').on('click',function(){
            $('.summary_board li').find('.link_summary_board').removeClass('on');
            $(this).find('.link_summary_board').addClass('on');
        })

        function changeTabNum(option){
            $('.summary_board .figure').eq(0).text(option.all);
            $('.summary_board .figure').eq(1).text(option.expe);
            $('.summary_board .figure').eq(2).text(option.fin);
            $('.summary_board .figure').eq(3).text(option.cancel);
        }

        changeTabNum({all : 1, expe: 3, fin :5, cancel : 7});

        function removeCard(type)
        {
            $('.card').eq(0).find('.card_item').remove();
            $('.card').eq(0).remove();
        }

        removeCard(4);

    })();

})();
