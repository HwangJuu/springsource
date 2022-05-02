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
})