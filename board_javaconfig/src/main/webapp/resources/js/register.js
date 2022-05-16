/**
 * register.jsp 스크립트
 */
$(function(){
	$(":submit").click(function(e){
		e.preventDefault();
		
		let str = "";
		
		//li 태그 정보 수집하기
		$(".uploadResult ul li").each(function(idx,obj){
			var job = $(obj);
			
			//value 값은 li 안에 담겨져 있음!
			
			str += "<input type = 'hidden' name='attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>";
			str += "<input type = 'hidden' name='attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>";
			str += "<input type = 'hidden' name='attachList["+idx+"].fileName' value='"+job.data("filename")+"'>";
			str += "<input type = 'hidden' name='attachList["+idx+"].fileType' value='"+job.data("type")+"'>";
		})
		
		console.log("form 태그 삽입 전");
		console.log(str);
		
		//폼 보내기
		$("form").append(str).submit();
		
	})
	
	// x 버튼 클릭시 첨부 파일 삭제
	//나중에 생기는 x이기 때문에 위임을 해야함
	$(".uploadResult").on("click","button", function(){
		//button 태그의 data-속성 가져오기
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
				
				$(":file").val("");
				//li 태그 제거
				targetLi.remove();
			}
		})
	})
	
})



