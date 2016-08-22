$(document).ready(function() {
	//店铺二维码分享
	$(".toshare a").click(function() {
		$(".toshare p").toggle();
		$(".toshare a .gt").toggleClass("current");
	});

	$(".c_list dl dt a").click(function() {
		$(this).toggleClass("current");
		$(this).parent().next().toggle();
	})

	$(".c_list dl dd a").click(function() {
		$(this).addClass("current");
	})

	$(".o_list").find("a").click(function(){
	  $(this).parent().parent().find(".focus").removeClass("focus");
	  $(this).addClass("focus");
	});

	//列表页tab
	$(".tab_control").find("a").click(function(){
		$(".tab_control").find(".on").removeClass("on");
		$(this).addClass("on");
	});

    //购物车悬浮
    var f_obj = document.getElementById('p_buy');
    if(f_obj) {
        var f_y = f_obj.offsetTop;
        var f_class = f_obj.className;
        document.onscroll = function(){
        	if(document.body.scrollTop>f_y){
        		f_obj.className += " fixed_t";
        	}else{
        		f_obj.className = f_class;
        	}
        }         
    }   

});

//功能：将浮点数四舍五入，取小数点后2位  
function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
}  