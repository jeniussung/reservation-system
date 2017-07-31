//별점 컴포넌트
var Rating = extend(eg.Component,{
    ratingScore : 0,
	totalStarCount : 0,
    $eleRoot : '',
    $eleStar : '',

    "init" : function($eleRoot){
	            this.$eleRoot = $eleRoot;
				this.$eleStar = $eleRoot.find(".rating_rdo").not(".first_star");
				this.totalStarCount = this.$eleStar.length;

				var curThis = this;

	            this.$eleRoot.on('click','.rating_rdo',function(e){
					curThis.ratingScore = this.value;
					curThis.$eleStar.prop("checked", false);
					curThis.$eleStar.slice(0,curThis.ratingScore).prop("checked", true);
	                curThis.trigger("change",{ratingScore : curThis.ratingScore});
	        	});
        	},

    "score" : function(){
		return this.currentScore;
    }
});

//review 입력
(function controlReviewTxt(){
	$(".review_contents").on("click", function(){
		$(this).find(".review_write_info").hide();
		$("textarea").show().focus();
	});
	$("textarea").on("focusout", function(){
		if($(this).val() === ""){
			$(".review_contents .review_write_info").show();
		}
	});
})();

//upload 리뷰 사진 미리보기
(function previewReviewImg(){
	var $eleFile = $("#reviewImageFileOpenInput");

	$(".lst_thumb").on("click",".ico_del",function(){
		this.closest(".item").remove();
	});

	$eleFile.change(function(e){
        var fileList = this.files;
        var numFiles = fileList.length;
        var curNumFiles = $('.item').length;
        var formData;

        if(numFiles > 5 || curNumFiles === 6){
            alert("이미지는 최대 5장까지 업로드할 수 있습니다.");
        }
        var formData = new FormData();

        for (var i = 0; i < numFiles; i++) {

            if( curNumFiles === 6){
                return;
            }

	        var file = this.files[i];
	        var imageTypeJpeg = /^image\/jpeg/;
            var imageTypePng = /^image\/png/;

            if (!imageTypeJpeg.test(file.type) && !imageTypePng.test(file.type)) {
                alert("이미지 확장자는 .png, .jpeg만 가능합니다.")
	            continue;
        	}

            if (file.size > 1048576) {
                alert("파일 사이즈는 1mb보다 작아야 합니다.")
	            continue;
        	}

            // temp.push(this.files[i]);
            formData.append("title","aaa");
            formData.append("file",this.files[i]);

	        var img = new Image();
	        img.width = 130;
	        img.alt = "";
	        img.file = file;
	        $(img).addClass("item_thumb");

	        var reader = new FileReader();

	        reader.onload = (function(aImg) { return function(e) {
	            aImg.src = e.target.result;
	            var html = $(".item").clone();
	            html.find("a").after(aImg);
	            $(html.get(0)).appendTo(".lst_thumb");
	        };})(img);

	        reader.readAsDataURL(file);

            curNumFiles++;
      	}

        // var request = new XMLHttpRequest();
        // request.open("POST", "/files");
        // request.send(formData);
        // console.log("aa");
	});
})();

$(function(){
    var rating = new Rating($(".rating"));

	rating.on('change',function(e){
		$(".rating").find(".star_rank").text(e.ratingScore);
		$(".rating").find(".first_star").val(e.ratingScore);
	});
});
