requirejs.config( {
    baseUrl: '/resources'
});

(function () {
    var GetUserComment = (function () {
        var soruce = $("#comment-template").html();
        var template = Handlebars.compile(soruce);
        var GetId = GetProductId();
        var id= GetId.getQueryString("id",document.location);

        var i = 0;
        var curLiNum = 0

        getCommentLi(id, curLiNum);

        function getCommentLi(){
            var ajaxCallback = AjaxProm({url : "/api/reviews/" + id +'/'+ curLiNum, type : "GET"});
            ajaxCallback.then(function(data){
                if (data.length === 0) {
                } else {
                    var date;

                    $('.review_header .title').text(data.CommentCountInfo.name);
                    $('.grade_area .text_value span').text(data.CommentCountInfo.avg.toFixed(1));
                    $('.green').text(data.CommentCountInfo.count+'건');

                    data.CommentInfo.forEach(function(item){
                        if(item.createDate !== null){
                            date = item.createDate.split(' ');
                        }

                        addCommentLi(item);

                        curLiNum++;
                    })
                }
            });
        }

        function addCommentLi(option) {
            var context = {
                id: option.id,
                title: option.title,
                imgCount: option.imgCount,
                file_id: option.fileId,
                comment: option.comment,
                score: option.score,
                nickname: option.nickname,
                day: option.day
            };

            var html = template(context);
            $('.list_short_review').show();
            var $element_ul = $('.list_short_review');
            $(html).appendTo($element_ul);

            $('.review').eq(i).html(option.comment.replace(/\n/gi, '<br>'));
            i++
        }

        $(window).scroll(function(){
            if ($(window).scrollTop() === $(document).height() - $(window).height()) {
                getCommentLi(id, 10);
            }
        });
    })();

    var ShowDetailImage = (function () {
        var soruce = $("#commentImage-template").html();
        var template = Handlebars.compile(soruce);
        var cur_num = 1;
        var slide_width;
        var slide_count;
        var isOpen = 0;
        var ee;

        $(document).on('click', '.thumb_area', function () {
            var commentId = $(this).closest('li').data('comment');
            addDetailImageAjax(commentId);

        })

        $(document).on('click', '.btn-r .cbtn', function () {
            isOpen = 0;
            cur_num = 1;
            ee.flush();
            $('.detail_img li').remove();
            $('#photoviwer').fadeOut();
        })

        $(document).on('click', '.pbtn', function () {
            if (cur_num != 1) {
                $(".detail_img").animate({"left": "+=" + slide_width + "px"}, "slow");
                cur_num--;
            }
        });

        $(document).on('click', '.nbtn', function () {
            if (cur_num != slide_count) {
                $(".detail_img").animate({"left": "-=" + slide_width + "px"}, "slow");
                cur_num++;
            }
        });

        function addDetailImageAjax(commentId) {
            $.ajax({
                url: "./reviews/image/" + commentId,
                type: "GET",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    if (isOpen === 0) {
                        addCommentLi(data);
                    }

                    layerOpen('photoviwer');

                    addFlickingComponent();
                }
            });
        }

        function addCommentLi(data) {
            var context = {fileData: data};
            var html = template(context);

            $('.detail_img').show();

            var $element_ul = $('.detail_img');

            $(html).appendTo($element_ul);
        }

        function layerOpen(el) {
            isOpen = 1;
            slide_width = $('.detail_img > li').outerWidth();
            slide_count = $('.detail_img > li').length;
            var temp = $('#' + el);

            temp.fadeIn();

            if (temp.outerHeight() < $(document).height()) {
                temp.css('margin-top', '-' + temp.outerHeight() / 2 + 'px');
            }
            else {
                temp.css('top', '0px');
            }

            if (temp.outerWidth() < $(document).width()) {
                temp.css('margin-left', '-' + temp.outerWidth() / 2 + 'px');
            }
            else {
                temp.css('left', '0px');
            }
        }

        /*
            플리킹 컴포넌트 구현 적용
        */
        function addFlickingComponent() {
            require([
                'js/requireTest'
            ], function (requireTest) {
                var Fliking = requireTest.flicking();
                var ele = $('.detail_img').get(0);

                ee = new Fliking($('.detail_img'));

                ee.init(ele);
            });
        }
    })();
})();