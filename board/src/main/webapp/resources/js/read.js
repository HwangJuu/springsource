/**
 * read.jsp 스크립트
 */
$(function(){
	
	//operForm 가져오기
	let operForm = $("#operForm");
	
	//list 버튼 클릭시 /board/list로 이동
	//:.btn-info == :reset
	$(".btn-info").click(function(){
		location.href="/board/list";
	})
	
	//modify 버튼 클릭시 operForm 이동
	$(".btn-default").click(function(){
		operForm.submit();
	})
})