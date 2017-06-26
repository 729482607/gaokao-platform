	function page_ul_init(){
		var ul_str="";
		ul_str+="<span>当前页：</span>";
		ul_str+="<input id=\"pageGo_text\" type=\"text\"></input>";
		ul_str+="<li id=\"pageGo\" style=\"margin-right:20px;\"><a>Go</a></li><li class=\"pageleft\"><a> </a></li>";
		if(pageTotal<=4){
			for(var i=0; i< pageTotal; i++){
				ul_str+="<li id=\"p_"+(i+1)+"\"><a>"+(i+1)+"</a></li>";
			}
		}
		else{
			for(var i=0; i< pageTotal; i++){
				if(i<3){
					ul_str+="<li id=\"p_"+(i+1)+"\"><a>"+(i+1)+"</a></li>";
				}
			}
			ul_str+="<li id=\"p_.\"><a>...</a></li>";
			ul_str+="<li id=\"p_"+pageTotal+"\"><a>"+pageTotal+"</a></li>";
			
		}
		ul_str+="<li class=\"pageright\"><a> </a></li>";
		$(".clearFloat").html(ul_str);
		$(".page span").html("当前页："+nowPage);
		$("#p_1").addClass("pagehover");

	}
	
$(".clearFloat li").live('click', function(){
	if($(this).attr('id')=="pageGo"){
			var go_page = $("#pageGo_text").val();
			if(go_page!=null && go_page!="" && nowPage!=parseInt(go_page)){
				$("#p_"+nowPage).removeClass("pagehover");
				if(first_li>parseInt(go_page) || parseInt(go_page)>first_li+2)
				{
					$("#p_"+(first_li+2)).attr('id','p_'+(parseInt(go_page)+2));
					$("#p_"+(parseInt(go_page)+2)).html(parseInt(go_page)+2);
					$("#p_"+(first_li+1)).attr('id','p_'+(parseInt(go_page)+1));
					$("#p_"+(parseInt(go_page)+1)).html(parseInt(go_page)+1);
					$("#p_"+(first_li)).attr('id','p_'+(parseInt(go_page)));
					$("#p_"+parseInt(go_page)).html(parseInt(go_page));
					first_li = parseInt(go_page);
				}
				nowPage = parseInt(go_page);
				$(".page span").html("当前页："+nowPage);
				$("#p_"+nowPage).addClass("pagehover");
				showData(nowPage,pageSize);	
			}
		}
		else if($(this).attr('class')=="pageleft"){
			if(nowPage!=1){
				$("#p_"+nowPage).removeClass("pagehover");
				if(nowPage==first_li){
					first_li = first_li-1;
					$("#p_"+(first_li+1)).attr('id','p_'+(first_li));
					$("#p_"+(first_li)).html(first_li);
					$("#p_"+(first_li+2)).attr('id','p_'+(first_li+1));
					$("#p_"+(first_li+1)).html(first_li+1);
					$("#p_"+(first_li+3)).attr('id','p_'+(first_li+2));
					$("#p_"+(first_li+2)).html(first_li+2);
				}
				nowPage = nowPage-1;
				$(".page span").html("当前页："+nowPage);
				$("#p_"+nowPage).addClass("pagehover");
				showData(nowPage,pageSize);	
			}
		}
		else if($(this).attr('class')=="pageright"){
			if(nowPage!=pageTotal){
				nowPage = nowPage+1;
				if(nowPage>3){
					first_li +=1;
					$("#p_"+(first_li+1)).attr('id','p_'+(first_li+2));
					$("#p_"+(first_li+2)).html(first_li+2);
					$("#p_"+(first_li)).attr('id','p_'+(first_li+1));
					$("#p_"+(first_li+1)).html(first_li+1);
					$("#p_"+(first_li-1)).attr('id','p_'+(first_li));
					$("#p_"+(first_li)).html(first_li);
				}
					$("#p_"+(nowPage-1)).removeClass("pagehover");
					$("#p_"+nowPage).addClass("pagehover");
					showData(nowPage,pageSize);	
					$(".page span").html("当前页："+nowPage);
			}
		}
		else{
			var li_id =  $(this).attr('id');
			if(li_id!="p_."){
			var id_now = li_id.split("_")[1];
			if(id_now!=nowPage){
				$("#p_"+nowPage).removeClass("pagehover");
		    	$(this).addClass("pagehover");
		    	nowPage = parseInt(id_now);
		    	$(".page span").html("当前页："+nowPage);
		    	showData(nowPage,pageSize);	
			}
		}
    	}
    	$("#pageGo_text").val("");
	});