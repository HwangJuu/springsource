/**
 * register.jsp
 * modify.jsp
 * read.jsp 파일 업로드 스크립트
 */


$(function(){
	//$("uploadBtn").click(function(){})
	$(":file").change(function(){ //클릭이 아니고, 변화가 일어나면으로 수정
		console.log("ajax 파일 업로드 호출");
		
		//폼 객체 생성
		let formData = new FormData();
		
		//첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		//폼 객체에 첨부파일들 추가
		for(let i=0; i<files.length; i++){
			
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
		
		//enctype : "mulitpart/form-data"와 같은 코드 의미
		//목적은 true 면 기본값으로 가기 때문에 fasle
		//processData:false => 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
		//						기본값은 true(application/x-www-form-urlencoded)
		//contentType:false => 기본값은 true
		
		//폼 객체 ajax 전송
		$.ajax({
			url:'/uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:formData,
			dataType:'json',
			success:function(result){ 
				console.log(result);
				$(":file").val("");
				showUploadFile(result);				
				
			}			
		})
	}) //uploadBtn ==> (":file").change 종료	
	
		
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

//첨부파일 확장자 및 사이즈
function checkExtension(fileName, fileSize){

	//확장자 확인
	let regex = new RegExp("(.*?)\.(png|gif|jpg|txt)$");
	
	//파일크기
	let maxSize = 3145728; //3MB
	
	if(!regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 할 수 없습니다.");
		return false;
	}
	
	if(fileSize > maxSize){
		alert("해당 파일은 사이즈를 초과합니다.");
		return false;
	}
	
	return true;
}

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
				
				
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<a href=\"javascript:showImage(\'"+oriPath+"\')\">";			
				// /display ==> 컨트롤러 접속한다는 의미
				str += "<img src='/display?fileName="+fileCallPath+"'></a>";
				str += "<div>" + obj.fileName;
				str += " <button type ='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='image'>";
				str += " <i class='fa fa-times'></i></button>";
				str += "</div></li>";
				
			}else{ //txt파일
			
				//다운로드 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\" + obj.uuid+"_"+obj.fileName);
			
			
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<a href='/download?fileName="+fileCallPath+"'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "<div>" + obj.fileName;
				str += " <button type ='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='file'>";
				str += " <i class='fa fa-times'></i></button>";
				str += "</div></li>";
				
			}
		});
		console.log("업로드 파일 경로");
		console.log(str);
		
		uploadResult.append(str);
	}//showUploadFile종료




function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	
	//.animate({width:'100%', height:'100%'},1000); ==> 서서히 보여주는 애니메이션 
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'},1000);
}











