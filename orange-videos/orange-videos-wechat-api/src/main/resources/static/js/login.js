$(document).ready(function(){
	$("#regist").click(function(){
		window.open("http://www.baidu.com");
	});

	$("#login").click(function(){
		var data = {
            username:$("#username").val(),
            password:$("#password").val()
		}
		$.ajax({
			url:'http://localhost:8081/login',
			type:'post',
            contentType: "application/json;charset=utf-8",
			data:JSON.stringify(data),
			success:function(res){
				alert(res);
				var status=res.code;
				if(status==200){
					alert("登录成功");
					alert(res.data.id);
				}else{
					alert(res.msg);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
            },
            complete:function(XMLHttpRequest,textStatus){
            	this;
            }
		});
	});
});