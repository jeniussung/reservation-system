    (function () {
        var num = 2;
        var slide_width = $('.visual_img > li').outerWidth();
        var slide_count = $('.visual_img > li').length;
        var auto = true;
        var event_num = 0;
        var cur_display_num = 0;
        var cat_num = 0;
        var set_time_out;
        var set_interval;
        var source;
        var template;

        if(auto) set_interval = setInterval(autoRightMove, 2000);

        $( ".visual_img" ).css({ "left": "-="+slide_width+"px" });

        getList();
        getMorelistAll(0);

            $(document).on('click',".nxt_e" ,function() {

              if($( ".visual_img").is(":animated"))
              return false;

              clearInterval(set_interval);
              clearTimeout(set_time_out);
              set_interval = null;
              set_time_out = null;

              set_time_out = setTimeout(goInterval,4000);

                 if(num === slide_count-1)
                 {
                        $( ".visual_img" ).css({ "left": "+="+(slide_count-2)*slide_width+"px" });
                        num=1;
                 }

                 if(num!=slide_count)
                 {
                   $( ".visual_img" ).animate({ "left": "-="+slide_width+"px" }, "normal" );
                  num++;

                 }

              });

            $(document).on('click', ".prev_e" ,function() {

              if($( ".visual_img").is(":animated"))
              return false;

              clearInterval(set_interval);
              clearTimeout(set_time_out);
              set_interval = null;
              set_time_out = null;

              set_time_out = setTimeout(goInterval,4000);

                if(num === 1)
                {
                     $( ".visual_img" ).css({ "left": "-="+(slide_count-2)*slide_width+"px" });
                     num=slide_count-1;
                }

                if(num!=1)
                {
                     $( ".visual_img" ).animate({ "left": "+="+slide_width+"px" }, "normal" );

                     num--;
                }
              });

             $(document).on('mouseenter', ".movebtn" ,function() {

                   if(!auto) return false;

                   clearInterval(set_interval);
                   set_interval = null;
                   auto = false;

                    if(set_time_out === null){
                     set_time_out = setTimeout(goInterval,4000);
                    }

               });

              function goInterval(){

                clearTimeout(set_time_out);
                set_time_out = null;
                set_interval = setInterval(autoRightMove, 2000);
                auto = true;
              }

              function autoRightMove(){

                if($( ".visual_img").is(":animated"))
                    return false;

                  if(num === slide_count-1)
                  {
                         $( ".visual_img" ).css({ "left": "+="+(slide_count-2)*slide_width+"px" });
                         num=1;
                  }

                  if(num!=slide_count)
                  {
                    $( ".visual_img" ).animate({ "left": "-="+slide_width+"px" }, "slow" );
                   num++;

                  }
              }

               $(document).on('click','.more',function(){

                 if(cat_num === 0){

                   getMorelistAll(cur_display_num);
                 }else{

                   getMorelistCategory(cur_display_num,cat_num);
                 }
               });

               $(function(){
                   $(window).scroll(function(){
                       if ($(window).scrollTop() === $(document).height() - $(window).height()) {

                           if(cat_num === 0){

                             getMorelistAll(cur_display_num);
                           }else{

                             getMorelistCategory(cur_display_num,cat_num);
                           }
                       }
                   });
               });


        var goDetail = (function (){  // 디테일 페이지로 넘어가는 기능 모듈화

            var curpage
            var url = './details/';

            $(document).on('click','.lst_event_box li',function(){
                //curpage = $(this).find('.event_txt_tit > span').text();
                curpage = $(this).data('product');
                document.location.href = url+curpage;
            });

        })();


        $(document).on('click', ".section_event_tab ul li" ,function(){

          $('.section_event_tab ul li').children().removeClass('active'); //

          $(this).children().addClass('anchor active'); //

          $('.lst_event_box li').remove();

          cur_display_num = 0;

          cat_num = $(this).data('category');

          if(cat_num === 0){
            getMorelistAll(cur_display_num);
          }else{
            getMorelistCategory(cur_display_num,cat_num);
          }
        });

        function getList(){

          $.ajax({
           url: "./categories",
           type: "GET",
           contentType:"application/json; charset=UTF-8",
           dataType:"json",
           success: function(data) {

               soruce = $("#category-template").html();
               template = Handlebars.compile(soruce);

              for(var i = 0 ; i < data.length-1 ; i++)
                addElementLi(data[i].name,data[i].id);

               soruce = $("#category-last-template").html();
               template = Handlebars.compile(soruce);

                addElementLiLast(data[data.length-1].name,data[data.length-1].id);
           }
          });
         }

         function addElementLi(name,id) {

            var context = {name: name , id: id};
            var html    = template(context);

            $(".section_event_tab ul").show();
            var $element_ul = $(".section_event_tab ul");

            $(html).appendTo($element_ul);

            }

          function addElementLiLast(name,id) {

            var context = {name: name , id: id};
            var html    = template(context);

            $(".section_event_tab ul").show();
            var $element_ul = $(".section_event_tab ul");

            $(html).appendTo($element_ul);

            }

          function getMorelistCategory(start,id){

            $.ajax({
             url: "./products/"+start+"/"+id,
             type: "GET",
             contentType:"application/json; charset=UTF-8",
             dataType:"json",
             success: function(data){

               source = $("#entry-template").html();
               template = Handlebars.compile(source);

               for(var i in data.product){
                 if(i%2 === 0){
                  leftUlAddLi(data.product[i]);
                 }
                 else {
                  rightUlAddLi(data.product[i]);
                 }
               }

               cur_display_num+= 10;
               event_num = data.count;
               showProductNum(event_num);
             }
            });
          }

          function getMorelistAll(start){

            $.ajax({
             url: "./products/"+start,
             type: "GET",
             contentType:"application/json; charset=UTF-8",
             dataType:"json",
             success: function(data){

               source = $("#entry-template").html();
               template = Handlebars.compile(source);

               for(var i in data.product){

                 if(i%2 === 0){
                     leftUlAddLi(data.product[i]);
                 }else {
                     rightUlAddLi(data.product[i]);
                 }
               }

               cur_display_num+= 10;
               event_num = data.count;
               showProductNum(event_num);

             }
            });
          }

          function showProductNum(num){

            $('.pink').text(num+'개');
          }

          function rightUlAddLi(data){
            var context = {name: data.name , place: data.place, desc: data.desc, id: data.id, fileId: data.fileId};
            var html    = template(context);

            $("#right_ul").show();
            var $element_ul = $("#right_ul");

            $(html).appendTo($element_ul);
          }

          function leftUlAddLi(data){
            var context = {name: data.name , place: data.place, desc: data.desc, id: data.id, fileId: data.fileId};
            var html    = template(context);

            $("#left_ul").show();
            var $element_ul = $("#left_ul");

            $(html).appendTo($element_ul);
          }
      })();
