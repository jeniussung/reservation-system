(function () {

var id = 2;

var GetUserComment = (function () {

    var score = 0;
    var per;
    var soruce = $("#comment-template").html();
    var template = Handlebars.compile(soruce);

    var qu = getQueryStringObject("http://localhost/map.html?x=925641&y=1666020&level=10");
    console.log(qu.x);



    getComment(id);

    function getComment(id) {
        $.ajax({
            url: "/api/reviews/" + id,
            type: "GET",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            success: function (data) {

                if (data.length === 0) {
                } else {

                    var date;

                    // $('.graph_value').css('width', per + '%');
                    // $('.text_value span').text(score.toFixed(1));
                    // $('.green').text(data.length + '건');

                    for (var i in data) {
                        console.log(data);
                        date = data[i].createDate.split(' ');
                        addCommentLi(data[i].id, "title", data[i].imgCount, data[i].fileId, data[i].comment.replace(/\n/gi, '<br>'), data[i].score, data[i].nickname, date[0]);
                        if (i == 2)
                            break;
                    } // for
                } // if
            } // success
        }); // ajax
    }

    function addCommentLi(id, title, imgCount, file_id, comment, score, nickname, day) {
        var context = {
            id: id,
            title: title,
            imgCount: imgCount,
            file_id: file_id,
            comment: comment,
            score: score,
            nickname: nickname,
            day: day
        };
        var html = template(context);

        $('.list_short_review').show();
        var $element_ul = parent.$('.list_short_review');

        $(html).appendTo($element_ul);
    }

})();

})();
