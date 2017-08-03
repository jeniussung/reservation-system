(function () {

    var id;

    var BOTTOM_TAP = ['상세정보','예매하기']

    var GetProductId = (function () {
        var querystring = [];
        var qS;
        var returnValue;
        var id;
        
        return {
            getProductId: function getProductId() {
                querystring = document.location.toString().split('/')
                returnValue = querystring[querystring.length - 1];
                id = returnValue.split('#');
                return id[0];
            }
        }
    })();

    id = GetProductId.getProductId();

    var SlideImage = (function () {
        var slide_width = $('.visual_img > li').outerWidth();
        var slide_count = $('.visual_img > li').length;
        var cur_dist = slide_width;
        var el = $('.visual_img').get(0);
        var curImgnum = 1;
        var num = 1;
        var touch_start_y = 0;
        var touch_start_x = 0;
        var save_x = 0;
        var save_y = 0;
        var move_dx = 0;
        var move_sum = 0;
        var curLiPosition;

        /*
            플리킹 컴포넌트 구현 적용
        */
        var ee = new Flicking($('.visual_img'));

        ee.init($('.visual_img').get(0), 'topFlickingEnd');

        ee.on('topFlickingEnd', function (curNum) {
            $('.figure_pagination > span:first').text(curNum.curDisplayNum);

            if (curNum.curDisplayNum === 1) {
                $('.visual_txt_inn').eq(0).css('display', '')
            } else {
                $('.visual_txt_inn').css('display', 'none');
            }
        });

        return {
            setCount: function () {
                slide_count = $('.visual_img > li').length;
            },
            getCount: function () {
                return slide_count;
            }
        }
    });

    var getCurrentDate = (function () {

        var now = new Date();
        var year = now.getFullYear();
        var mon = (now.getMonth() + 1) > 9 ? '' + (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
        var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();
        var chan_val = year + '-' + mon + '-' + day;
        var currentHours = now.getHours() > 9 ? now.getHours() : '0' + now.getHours();
        var currentMinute = now.getMinutes() > 9 ? now.getMinutes() : '0' + now.getMinutes();
        var currentSeconds = now.getSeconds() > 9 ? now.getSeconds() : '0' + now.getSeconds();
        var cur_time = chan_val + ' ' + currentHours + ':' + currentMinute + ':' + currentSeconds;

        return {

            getDate: function getDate() {
                return cur_time;
            }
        }
    })();

    var GetTopInformation = (function () {

        var source;
        var template;
        var title;


        getTopAjax(id);

        function getTopAjax(id) {
            $.ajax({
                url: "./top/" + id,
                type: "GET",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                success: function (data) {

                    addImageLi(data); // 이미지 삽입

                    var slideImage = SlideImage();

                    title = data[0].name;

                    slideImage.setCount(); // 이미지 갯수 갱신

                    $('.figure_pagination .off > span').text(slideImage.getCount()); // 이미지 갯수 반영

                    $('.dsc').html(data[0].description + '<br>' + "- 공연 장소 : " + data[0].placeName + '<br>' + "- 관람 시간 : " + data[0].observationTime + '<br>'); // 공연 설명 삽입

                    addGroupBtn(data[0].homepage, data[0].tel, data[0].email, data[0].placeStreet); // 그룹 버튼 추가

                    changeBkBtn(data[0].saleFlag, data[0].salesEnd); // 예매 가능 여부 체크 후 예매하기 버튼 변경

                    addEvent(data[0].event.replace(/\n/gi, '<br>')); // 이벤트 정보 삽입

                    GetMap.showMap(data[0].placeStreet);
                    GetMap.setPlace(data[0].name, data[0].placeStreet, data[0].placeLot, data[0].placeName, data[0].tel);
                }
            });
        }

        function addImageLi(data) {
            soruce = $("#image-template").html();
            template = Handlebars.compile(soruce);

            var context = {'imgda': data};
            var html = template(context);

            $('.visual_img').show();
            var $element_ul = $('.visual_img');

            $(html).appendTo($element_ul);
        }

        function addGroupBtn(homepage, tel, email, placeStreet) {
            soruce = $("#groupbtn-template").html();
            template = Handlebars.compile(soruce);

            var context = {homepage: homepage, tel: tel, email: email, placeStreet: placeStreet};
            var html = template(context);

            $('.group_btn_goto').show();
            var $element_ul = $('.group_btn_goto');

            $(html).appendTo($element_ul);
        }

        function changeBkBtn(sale_flag, slae_end) {
            var toDay = getCurrentDate.getDate().split(' ');
            var sale = slae_end.split(' ');

            if (toDay[0] > sale[0]) {
                $('.bk_btn > span').text('판매기간 종료');
            }
            else {
                if (toDay[1] > sale[1]) {
                    $('.bk_btn > span').text('판매기간 종료');
                }
                else {
                }
            }

            if (sale_flag === 1) {
                $('.bk_btn > span').text('매진');
            }
        }

        function addEvent(event_info) {

            $('.event_info .in_dsc').html(event_info);

        }

        return {
            getTitle: function getTitle() {
                return title;
            }
        }

    })();

    var GetUserComment = (function () {

        var score = 0;
        var per;
        var source;
        var template;
        var commentLiNum  = 0;

        getComment(id);

        function getComment(id) {
            $.ajax({
                url: "./comment/" + id,
                type: "GET",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                success: function (data) {

                    if (data.length === 0) {
                    } else {

                        if (data.length > 3)
                            $('.btn_review_more').removeClass('hide');

                        for (var i in data)
                            score += data[i].score;

                        score = score / data.length;
                        per = (score / 5) * 100;
                        ;

                        var date;

                        $('.graph_value').css('width', per + '%');
                        $('.text_value span').text(score.toFixed(1));
                        $('.green').text(data.length + '건');

                        for (var i in data) {
                            date = data[i].createDate.split(' ');
                            addCommentLi(data[i].id, GetTopInformation.getTitle(), data[i].imgCount, data[i].fileId, data[i].comment, data[i].score, data[i].nickname, date[0]);

                            if (i == 2){
                                break;
                            }
                        } // for
                    } // if
                } // success
            }); // ajax
        }

        function addCommentLi(id, title, imgCount, file_id, comment, score, nickname, day) {
            soruce = $("#comment-template").html();
            template = Handlebars.compile(soruce);

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
            var $element_ul = $('.list_short_review');

            $(html).appendTo($element_ul);

            $('.review').eq(commentLiNum).html(comment.replace(/\n/gi, '<br>'));

            commentLiNum++
        }

    })();

    var Toggle = (function () {

        var open = 1;

        $(document).on('click', '.bk_more_txt', function () {
            if (open === 1) {
                $('.store_details').removeClass('close3');
                $('._open').css('display', 'none');
                $('._close').css('display', 'block');
                open = 0;
            } else {

                $('.store_details').addClass('close3');
                $('._open').css('display', 'block');
                $('._close').css('display', 'none');
                open = 1;
            }
        })

        $(document).on('click', '.info_tab_lst li', function () {
            $('.info_tab_lst li a').removeClass('active');
            $('.info_tab_lst li a').removeClass('active');
            $(this).find('a').addClass('active');

            if (BOTTOM_TAP[0] === $('.info_tab_lst').find('.active span').text()) {
                $('.detail_area_wrap').removeClass('hide');
                $('.detail_location').addClass('hide');
            } else {
                $('.detail_location').removeClass('hide');
                $('.detail_area_wrap').addClass('hide');
            }
        })

    })();


    var GetMap = (function () {

        var map = new naver.maps.Map('map');
        var myaddress;// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)

        return {

            showMap: function showMap(place) {
                myaddress = place;

                naver.maps.Service.geocode({address: myaddress}, function (status, response) {
                    if (status !== naver.maps.Service.Status.OK) {
                        return alert(myaddress + '의 검색 결과가 없거나 기타 네트워크 에러');
                    }
                    var result = response.result;
                    // 검색 결과 갯수: result.total
                    // 첫번째 결과 결과 주소: result.items[0].address
                    // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
                    var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
                    map.setCenter(myaddr); // 검색된 좌표로 지도 이동
                    // 마커 표시
                    var marker = new naver.maps.Marker({
                        position: myaddr,
                        map: map
                    });
                    // 마커 클릭 이벤트 처리
                    naver.maps.Event.addListener(marker, "click", function (e) {
                        if (infowindow.getMap()) {
                            infowindow.close();
                        } else {
                            infowindow.open(map, marker);
                        }
                    });
                    // 마크 클릭시 인포윈도우 오픈
                    var infowindow = new naver.maps.InfoWindow({});
                });
            },

            setPlace: function setPlace(store_name, store_addr_bold, addr_old_detail, addr_detail, store_tel) {
                $('.store_name').text(store_name);
                $('.store_addr_bold').text(store_addr_bold);
                $('.addr_old_detail').text(addr_old_detail);
                $('.addr_detail').text(addr_detail);
                $('.store_tel').text(store_tel);
                $('.store_tel').attr("href", 'tel:' + store_tel);
                $('.btn_path').attr("href", "http://map.naver.com?query=" + store_addr_bold);
                $('.store_location').attr("href", "http://map.naver.com?query=" + store_addr_bold);
            }
        }

    })();

    var ShowDetailImage = (function () {

        var commentId = $(this).closest('li').data('comment');
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
                url: "./image/" + commentId,
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
            var ele = $('.detail_img').get(0);
            ee = new Flicking($('.detail_img'));

            ee.init(ele);
        }

    })();

    var DetailBottomContent = (function () {

        addBottomContent(id);

        function addBottomContent(productId) {

            $.ajax({
                url: "./content/" + productId,
                type: "GET",
                contentType: "application/json; charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    $('.detail_info_group > li > .in_dsc').text(data.content.replace(/\n/gi, '<br>'));
                }
            });
        }

    })();

    var LazyLoad = (function () {

        var src_image = $('.detail_info_lst').eq(2).find('.in_img_group .img_thumb').data('lazy-image');

        var lazyTarget = $('.detail_info_lst').get(2);


        $(window).scroll(function () {

            if (isInViewport(lazyTarget)) {
                $('.detail_info_lst').eq(2).find('.in_img_group .img_thumb').attr('src', src_image);
            }

        })

        function isInViewport(el) {
            var rect = el.getBoundingClientRect();

            return (
                rect.bottom >= 0 &&
                rect.right >= 0 &&
                rect.top <= (window.innerHeight || document.documentElement.clientHeight) &&
                rect.left <= (window.innerWidth || document.documentElement.clientWidth)
            );
        }
    })();

    var GoReserve = (function () {
        var cur;
        var url = '/reserve/';

        $(document).on('click', '.bk_btn', function () {

            cur = $('.bk_btn > span').text();

            if (cur === BOTTOM_TAP[1]) {
                console.log(cur);

                document.location.href = url + id;
            } else {
                console.log(cur);
            }
        });

    })();

    var GoReview = (function(){
        var url = '/api/reviews?'
        $('.btn_review_more').on('click',function(){
            document.location.href = url+'id='+id;
        })
    })();

})();
