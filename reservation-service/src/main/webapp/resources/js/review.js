(function () {

var GetUserComment = (function () {

    var score = 0;
    var soruce = $("#comment-template").html();
    var template = Handlebars.compile(soruce);
    var GetId = GetProductId();
    var id = GetId.getQueryString("id",document.location);

    var ajaxCallback = AjaxProm({url : "/api/reviews/" + id, type : "GET"});
    ajaxCallback.then(function(data){
        if (data.length === 0) {
        } else {
            var date;

            for (var i in data.CommentInfo) {
                date = data.CommentInfo[i].createDate.split(' ');
                addCommentLi({
                    id : data.CommentInfo[i].id,
                    title : data.CommentCountInfo.name,
                    imgCount : data.CommentInfo[i].imgCount,
                    file_id : data.CommentInfo[i].fileId,
                    comment : data.CommentInfo[i].comment.replace(/\n/gi, '<br>'),
                    score : data.CommentInfo[i].score,
                    nickname : data.CommentInfo[i].nickname,
                    day : date[0]
                })
            } // for
        } // if
    });

    function addCommentLi(option) {
        var context = {
            id: option.id,
            title: option.title,
            imgCount: option.imgCount,
            file_id: option.file_id,
            comment: option.comment,
            score: option.score,
            nickname: option.nickname,
            day: option.day
        };

        var html = template(context);

        $('.list_short_review').show();
        var $element_ul = parent.$('.list_short_review');

        $(html).appendTo($element_ul);
    }

})();

})();
