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

//별점 (.rating)

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

var uploadFileList = [];
//upload 리뷰 사진 미리보기
(function previewReviewImg(){
	var $eleFile = $("#reviewImageFileOpenInput");

	$(".lst_thumb").on("click",".ico_del",function(){
		var deleteFileIndex = $(".ico_del").index(this)-1;
		uploadFileList.splice(deleteFileIndex,1);
		this.closest(".item").remove();
	});

	$eleFile.change(function(e){

        var fileList = this.files;
        var numFiles = fileList.length;

        for (var i = 0; i < numFiles; i++) {
	        var file = this.files[i];
	        if(uploadFileList.length >= 5){
	        	alert("사진은 5장까지 첨부 할 수 있습니다.");
	        	return ;
	        }

	        if(file.size <= 1000000) {
		        uploadFileList.push(file);
		        var imageType = /^image\//;

		        if (!imageType.test(file.type)) {
		          continue;
	        	}

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
	        }
      	}
	});
})();

//리뷰 등록
function writeReview(){
	var formData = new FormData();
	var score = $(".first_star").val();
	var comment = $("textarea").val();

	if(comment === ""){
		alert("리뷰 내용을 확인해 주세요.");
		$(".review_contents").focus();
		return ;
	}

	var reservationUserComment = {
		"productId" : $(location).attr('pathname').slice(-1),
		"userId" : $(".write_act").data("userid"),
		"score" : score,
		"comment" : comment
	};

	formData.append("reservationUserComment", reservationUserComment);
	for(var i = 0; i<uploadFileList.length; i++){
		formData.append("file", uploadFileList[i]);
	}

	var request = new XMLHttpRequest();
	request.open("POST", "/files");
	request.send(formData);
}

$(function(){
    var rating = new Rating($(".rating"));

	rating.on('change',function(e){
		$(".rating").find(".star_rank").text(e.ratingScore);
		$(".rating").find(".first_star").val(e.ratingScore);
	});

	$(".box_bk_btn").on('click', writeReview);
});
