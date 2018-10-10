// JavaScript Document
var s = $(window).scrollTop();
var h = $(window).height();
var w = $(window).width();
var openedModal = "";

$(window).resize(function(){
	w = $(window).width();
	h = $(window).height();
});

$(document).ready(start);

function start(){
	$("select").select2();
	$("select[multiple='multiple']").select2({
		multiple: true,
	});
	$(".datepicker input").datepicker({
		dateFormat: "dd/mm/yy"
	});
	
	$(document).on('click',".btn-modal",function(event){
		openedModal = $(this).attr("data-modal");
		modalOpen(openedModal,$(this));
	});
	$(".modal .btn-close, .modal .btn.cancel").click(modalClose);
	$(".overlay").click(modalClose);
	
	$(document).on('click',".btn-href",function(event){
		window.location.href = $(this).attr("data-href");
	});
}

$(window).on("load", function() {
});

// MODALS
	
/* modal > open */
function modalOpen(id,btn){
	if(openedModal == "user-delete"){ //delete-modal
		$("#delete-name").html(btn.attr("data-name"));
	}
	
	$("#" + id).fadeIn(200);
	$(".overlay").fadeIn(300);
	$("#" + id).addClass("on");
	setTimeout(function(){
		$("body").addClass("modalOpen");
	}, 900);		
}

/* modal > close */
function modalClose(){
	$(".overlay").fadeOut(200);
	$(".modal").fadeOut(50);
	setTimeout(function(){
		$(".modal").removeClass("on");
		$("body").removeClass("modalOpen");
		$(".overlay").removeClass("black");
	}, 300);			
}