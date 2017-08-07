	//별점 컴포넌트
	var Rating = extend(eg.Component,{
		ratingScore : 0,
		totalStarCount : 0,
		$eleRoot : '',
		$eleStar : '',

		init : function($eleRoot){
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

		score : function(){
			return this.ratingScore;
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
			var curNumFiles = $('.item').length;

			if(numFiles > 5 || curNumFiles === 6){
				alert("이미지는 최대 5장까지 업로드할 수 있습니다.");
			}

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

				uploadFileList.push(file);

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

		var userId = $(".write_act").data("userid");

		if(userId === ""){
			alert("로그인 후에 리뷰를 등록 하실 수 있습니다.");
			return;
		}

		var review = JSON.stringify({
			"productId" : $(location).attr('pathname').slice(-1),
			"userId" : userId,
			"score" : score,
			"comment" : comment
		});

		formData.append("review", review);
		for(var i = 0; i<uploadFileList.length; i++){
			formData.append("files", uploadFileList[i]);
		}

		var request = new XMLHttpRequest();
		request.open("POST", "/api/reviews");

		request.onload = function(){
			if(request.readyState === 4){
				if(request.status === 200){
					console.log("insert success");
				} else{
					console.log("insert review fail");
					location.href="/reviewWrite/"+$(location).attr('pathname').slice(-1);
				}
			}
		}
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
