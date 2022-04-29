/**
 * list.jsp 스크립트
 */
$(function(){
	//Register New Board 클릭하면
	//regBtn 클릭시 
	$("#regBtn").click(function(){
		location.href = "/board/register";
	})
	
	
	//게시물 등록 시 모달 창 띄우기
	checkModal(result);
	
	history.replaceState({}, null,null);
	
	function checkModal(result){
		if(result =='' || history.state){
			return;
		}
		
		if(parseInt(result) > 0){
			$(".modal-body").html("게시물 " + result+"번이 등록 되었습니다.");
		}
		
		$("#myModal").modal("show"); //show를 꼭 해줘야 보여줌 'hide'하면 감춰줌

	}//checkModal 종료
	
	//페이지 이동 버튼 클릭
	
	//actionForm 가져오기
	let actionForm = $("#actionForm");
	
	$(".paginate_button a").click(function(e){
		e.preventDefault(); //a속성 중지
		
		//사용자가 선택한 페이지 번호 가져오기
		let pageNum = $(this).attr('href');
		
		//가져온 번호를 actionForm 안의 pageNum 값으로 대체
		actionForm.find("[name='pageNum']").val(pageNum);
		
		//actionForm 보내기
		actionForm.submit();
		
	})//paginate_button종료
	
	//페이지 목록 개수가 클릭
	$(".form-control").change(function(){
		
	//actionForm 안의 amount 값을 변경하기
	actionForm.find("[name='amount']").val($(this).val());	
	

	//actionForm 보내기
	actionForm.submit();
	
	})
	
	
	
	
	

})