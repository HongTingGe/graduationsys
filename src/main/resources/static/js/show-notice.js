function showNoticeContent(id) {
    $.ajax({
        url: '/notice/get',
        data:{
            id: id
        },
        type: 'post',
        dataType: 'json',
        success: function (res) {
            layui.layer.open({
                type: 1,
                area: ['600px', '600px'], //宽高
                content: res.content
            });
        }
    })
}

$.ajax({
    url: '/getAllNotices',
    type: 'post',
    dataType: 'json',
    success: function (res) {
        for (let i in res){
            let liNode=`<li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">
                                   <a style="text-decoration: none" onclick="showNoticeContent('${res[i].id}')">${res[i].title}</a>
                                </h3>
                            </div>
                        </li>`;
            $("#notices").append(liNode);
        }
    }
});
