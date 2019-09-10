/*
获取评论列表
 */
function postcomment() {
    var questionId=$("#question_id").val();
    var connect=$("#comment_connect").val();
    comment2target(questionId,1,connect);
}

function comment2target(targetId,type,connect) {
    if (!connect){
        alert("回复内容不能为空！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        data: JSON.stringify({
            "parentId":targetId,
            "comment":connect,
            "type":type
        }),
        success: function (res) {
            if (res.code==200){
                //$("#comment").hide();
                window.location.reload();
            }else {
                // if (res.code==3) {
                //     var isaccpet=confirm(res.message);
                //     if (isaccpet){
                //         window.open("http://localhost:8080/login");
                //         //window.location.href ='target url';
                //         window.localStorage.setItem("closable",true);
                //     }
                // }
                alert(res.message)
            }
            console.log(res)
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentid=e.getAttribute("data-id");
    var connect=$("#replay-"+commentid).val();
    comment2target(commentid,2,connect);
}

/*
展开二级评论
 */
function twocomment(e) {
    var id=e.getAttribute("data-id");
    var commentsid=$("#comment-"+id);
    var collapse=e.getAttribute("data-collapse");
    if (collapse){
        //折叠二级评论
        commentsid.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else {
        var subcomment=$('#comment-'+id);
        if (subcomment.children().length!=1){
            //展开二级评论
            commentsid.addClass('in');
            //记录二级评论展开样式
            e.setAttribute("data-collapse","in");
            e.classList.add("active");
        }else {
            $.getJSON( "/comment/"+id, function( data ) {

                $.each( data.data.reverse(), function( index, comment ) {

                    var medialeftElement=$("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-rounded faceUrl",
                        "src":comment.user.avatarurl}));

                    var mediabodyElement=$("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>",{
                        "class":"media-heading media-h5",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.comment
                    })).append($("<div/>",{
                        "class":"meun"
                    })).append($("<span/>",{
                        "class":"pull-right",
                        "html":moment(comment.gmtCreate).format('YYYY-MM-DD            ')
                    }));

                    var mediaElement=$("<div/>",{
                        "class":"media"}).append(medialeftElement).append(mediabodyElement);

                    var commentElement=$("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);
                    subcomment.prepend(commentElement)
                });

            });
            //展开二级评论
            commentsid.addClass('in');
            //记录二级评论展开样式
            e.setAttribute("data-collapse","in");
            e.classList.add("active");
        }
        }
}

/*
选取标签
 */
function selectTag(e){
    var value=e.getAttribute("data-tag");
    var previous=$("#tag").val();
    if (previous.indexOf(value)==-1){
        if (previous){
            $("#tag").val(previous+','+value);
        }else {
            $("#tag").val(value);
        }
    }

}

function showTags() {
    $("#show-tags").show();
}