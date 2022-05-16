/**
 * read.jsp 스크립트
 */
$(function(){
	
	//operForm 가져오기
	let operForm = $("#operForm");
	
	//list 버튼 클릭시 /board/list로 이동
	//:.btn-info == :reset
	$(".btn-info").click(function(){
		//location.href="/board/list";
		
		//operForm bno 태그 제거하기
		//찾아서 제거하기 순서대로 작성
		operForm.find("input[name='bno']").remove();
		
		
		//operForm action 수정
		operForm.attr("action", "/board/list");
		
		//operForm 보내기
		operForm.submit();
	})
	
	//modify 버튼 클릭시 operForm 이동
	$(".btn-default").click(function(){
		operForm.submit();
	})
	
	//첨부파일 -----------------------------------------------------------
	
	//첨부파일 가져오기 - 무조건 실행(있던 없던), 있으면 보여주기
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno
		},
		success:function(data){
			console.log(data);
			showUploadFile(data);
		}
	})
	
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
				//내 글이 아닐 수 있기 때문에 x가 보이면 안됨.
				//str += " <button type ='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='image'>";
				//str += " <i class='fa fa-times'></i></button>";
				str += "</div></li>";
				
			}else{ //txt파일
			
				//다운로드 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\" + obj.uuid+"_"+obj.fileName);
			
			
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<a href='/download?fileName="+fileCallPath+"'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "<div>" + obj.fileName;
				//내 글이 아닐 수 있기 때문에 x가 보이면 안됨.
				//str += " <button type ='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='file'>";
				//str += " <i class='fa fa-times'></i></button>";
				str += "</div></li>";
				
			}
		});
		console.log("업로드 파일 경로");
		console.log(str);
		
		uploadResult.append(str);
	}//showUploadFile종료
	
})