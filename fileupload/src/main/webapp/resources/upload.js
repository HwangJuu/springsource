/**
 * uploadform_ajax 스크립트
 */
$(function(){
	$("#uploadBtn").click(function(){
		console.log("ajax 파일 업로드 호출");
		
		//폼 객체 생성
		let formData = new FormData();
		
		//첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		//폼 객체에 첨부파일들 추가
		for(let i=0; i<files.length; i++){
			formData.append("uploadFile",files[i]);
		}
		
		//enctype : "mulitpart/form-data"와 같은 코드 의미
		//목적은 true 면 기본값으로 가기 때문에 fasle
		//processData:false => 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
		//						기본값은 true(application/x-www-form-urlencoded)
		//contentType:false => 기본값은 true
		
		//폼 객체 ajax 전송
		$.ajax({
			url:'uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			data:formData,
			dataType:'json',
			success:function(result){ 
				//console.log(result);
				showUploadFile(result);				
				
			}			
		})
	}) //uploadBtn 종료
	
	function showUploadFile(result){
		//업로드 결과 영역 가져오기
		let uploadResult = $(".uploadResult ul");
		
		let str = "";
		
		$(result).each(function(idx,obj){
			
			
			//이미지 파일 썸네일 보여주기
			if(obj.fileType){ // 이미지파일
			
				//썸네일 이미지 보여주기
				//썸네일 이미지 경로
				//encodeURIComponent 자바스크립트 내장 함수
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_" + obj.uuid+"_"+obj.fileName);
				
				//이미지 다운로드 경로
				//str += "<li><a href='/download?fileName="+fileCallPath+"'>"; 이미지도 똑같이 다운 가능
			
				//원본파일 이미지 경로. 다운로드가 아닌 보여주기
				let oriPath = obj.uploadPath+"\\"+obj.uuid+"_" + obj.fileName;
				oriPath = oriPath.replace(new RegExp(/\\/g),"/");
				
				
				str += "<li><a href=\"javascript:showImage(\'"+oriPath+"\')\">";			
				// /display ==> 컨트롤러 접속한다는 의미
				str += "<img src='/display?fileName="+fileCallPath+"'></a>";
				str += "<div>" + obj.fileName+"<span data-file='"+fileCallPath+"' data-type='image'> x </span>";
				str += "</div></li>";
				
			}else{ //txt파일
			
				//다운로드 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\" + obj.uuid+"_"+obj.fileName);
			
			
				str += "<li><a href='/download?fileName="+fileCallPath+"'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "<div>" + obj.fileName +"<span data-file='"+fileCallPath+"' data-type='file'> x </span>";
				str += "</div></li>";
				
			}
		});
		
		uploadResult.append(str);
	}//showUploadFile종료
	
	//첨부파일 삭제(x 동작)
	//$(".uploadResult") ==> 있는 영역
	//.on("click","span",function() ==> 나중에 가지고 올영역
	$(".uploadResult").on("click","span",function(){
		//span 태그의 data- 속성 가져오기
		//data-는 http로 가지고 오지 않고 직접 가지고 옴.
		let targetFile = $(this).data("file"); //파일 경로 : fileCallPath
		let type = $(this).data("type"); //file or image
		
		//span 태그가 속해 있는 li 태그 가져오기
		let targetLi = $(this).closest("li");
		
		//서버로 들어가서 삭제. 화면이 움직이면 안되니 ajax
		$.ajax({
			url:'/deleteFile', //컨트롤러에 추가하기
			data:{
				fileName:targetFile,
				type:type
			},
			type:'post',
			success:function(result){
				console.log(result);
				//li 태그 제거
				targetLi.remove();
			}
		})
	})
	
	
	
	
	//이미지 종료
	$(".bigPictureWrapper").on("click", function(){
		$(".bigPicture").animate({width:'0', height:'0'},1000);
		
		//배경 감추는 부분. 브라우저가 기억하고 있다가 시간이 지나면 알아서 닫힘.
		//callback 함수.
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	})
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	
	//.animate({width:'100%', height:'100%'},1000); ==> 서서히 보여주는 애니메이션 
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'},1000);
}











