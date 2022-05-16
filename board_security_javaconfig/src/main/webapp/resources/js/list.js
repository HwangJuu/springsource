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
		
		//bno가 있는 경우 제거
		actionForm.find("[name='bno']").remove();
		
		//action 수정
		actionForm.attr("action","/board/list");
			
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
	
	//타이틀 클릭시
	$(".move").click(function(e){
		e.preventDefault(); //a 태그 기능 중지
		
		//a가 가지고 있는 href 가지고 오기
		let href = $(this).attr("href");		
		
		//actionForm 안에 bno 태그를 추가하기(bno 값은 href가 가지고 있는 값으로)
		//내용 보기에 들어갔을 때 사용자가 리스트 버튼이 아닌 뒤로 가기 버튼을 눌러서
		//목록으로 돌아올때 bno가 계속 추가되는 것 방지
		if(actionForm.find("[name='bno']").length != 0){
			
			actionForm.find("[name='bno']").val(href);
		
		}else{
		
			actionForm.append("<input type ='hidden' name ='bno' value='" +href+ "'>");			
		
		}
		
		//actionForm action 변경(/board/read)
		actionForm.attr("action","/board/read");
		
		//actionForm 보내기
		actionForm.submit();
	})
	
	//검색 버튼 클릭시
	$(".btn-default").click(function(e){
	//submit 기능 중지
		e.preventDefault();	
		
		let searchForm = $("#searchForm");
	
	//type 아무것도 선택이 되지 않으면 경고 메세지 주기
	if(searchForm.find("select[name='type']").val()== ''){
		alert('검색 기준을 선택해 주세요');
		return false;
	}	
	//keyword 값이 없으면 경고 메세지 주기
	if(searchForm.find("input[name='keyword']").val()== ''){
		alert('검색어를 입력해 주세요');
		return false;
	}	
	//검색 폼 안에 pageNum은 1로 변경.
	//검색 후 페이지는 첫페이지를 보여줘야함.
	searchForm.find("input[name='pageNum']").val("1");
	
	//검색 폼 전송
	searchForm.submit();
	})
	
	
	

})