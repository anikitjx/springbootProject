$(document).ready(function(){
    var totalPage=1;
    var currentPage=1;
    function getMyDate(str){
        var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ addZero(oMonth) +'-'+ addZero(oDay) +' '+ addZero(oHour) +':'+
            addZero(oMin) +':'+addZero(oSen);
        return oTime;
    }
    function addZero(num){
        if(parseInt(num) < 10){
            num = '0'+num;
        }
        return num;
    }
    function getVideoList(page){
        var data={}
        var videoList=[]
        $.ajax({
            url:'http://localhost:8081/video/showAllVideos?page='+page,
            type:'post',
            contentType:'application/json;charset=utf-8',
            async:false,
            data:JSON.stringify(data),
            success: function(res){
                if(res.code==200){
                    console.log(res.data);
                    totalPage=res.data.pages;
                    videoList=res.data.rows;
                    $("#mytbody").children().remove();
                    $.each(videoList,function(index,item){
                        var $tr = $("<tr></tr>")
                        var date = getMyDate(parseInt(item.createTime))
                        var nicknametd = "<td>"+item.user.nickname+"</td>"
                        var videoDesctd = "<td>"+item.videoDesc+"</td>"
                        var datetd = "<td>"+date+"</td>"
                        $tr.append(nicknametd);
                        $tr.append(videoDesctd);
                        $tr.append(datetd);
                        $("#mytbody").append($tr);
                    })
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
        console.log(totalPage);
    }
    function getpages(){
        var $preli=$("#preli");
        for(var i=1;i<totalPage+1;i++){
            var $current = $("<li class='numli' value='"+i+"'><a href='#'>"+i+"</a></li>");
            $preli.after($current);
            if(i==1){
                $current.addClass("active")
            }
            $preli=$current
        }
    }
    getVideoList(1);
    getpages();

    $(".numli").click(function(){
        $(".numli").removeClass("active");
        $(this).addClass("active");
        var selectpage = $(this).val();
        currentPage = selectpage;
        console.log(currentPage);
        getVideoList(currentPage);
    });

    $("#preli").click(function(){
        if(currentPage==1){
            return;
        }
        currentPage = currentPage-1;
        $(".numli").removeClass("active");
        $(".numli").each(function () {
            if($(this).val() == currentPage){
                $(this).addClass("active")
            }
        });
        getVideoList(currentPage);
    });

    $("#sufli").click(function(){
        if(currentPage==totalPage){
            return;
        }
        currentPage = currentPage+1;
        $(".numli").removeClass("active");
        $(".numli").each(function () {
            if($(this).val() == currentPage){
                $(this).addClass("active")
            }
        });
        getVideoList(currentPage);
    });
});