function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}



//按钮状态
function btnStatus(i){
    let btn;
    if (i==-1){
        btn=`<button type="button" class="layui-btn layui-btn-warm layui-btn-xs">申报失败</button>`;
    }else if(i==0){
        btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">待审核中</button>`;
    }else{
        btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">申报成功</button>`;
    }
    return btn;
}


layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        //根据专业id显示课题方向
        $.ajax({
            url:'/task/list/bymarjorid',
            type:'post',
            async: false,
            success:function (res) {
                for(let i in res){
                    var optionStr = "";
                    optionStr = "<option value=" + res[i].id + " >" + res[i].name + "</option>";
                    $("#taskType").append(optionStr);
                }
                layui.form.render('select');
            }
        });

        $.ajax({
            url:'/get/taskitemdeclare',
            type:'post',
            data:{
                id: getQueryString("id")
            },
            dataType:'json',
            success:function (res) {
                $("#id").val(res.id);
                $("#name").val(res.name);
                $("#taskType option[value="+res.task.id+"]").attr("selected", true);
                layui.form.render('select');
                $("#status").append(btnStatus(res.status));
                $("#detail").val(res.detail);
                $("#msg").val(res.msg);
                $("#createtime").val(res.createtime);

            }
        });


        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引


        //监听提交,审核通过
        form.on('submit(again)',

            function(data) {

                layer.alert("确定重新申报么", {
                        icon: 6
                    },
                    function() {

                        $.ajax({
                            url:'/student/update/taskitemdeclare',
                            type:'post',
                            data:{
                                id : $("#id").val(),
                                name: $("#name").val(),
                                typeid: $("#taskType").val(),
                                status : 0,
                                detail :$("#detail").val(),
                                msg : $("#msg").val()
                            },
                            success:function (res) {

                                layer.msg(res.msg, {
                                    icon: 1,
                                    time: 2000
                                }, function(){
                                    parent.layer.close(index); //再执行关闭
                                    parent.reloadTable();
                                });

                            }
                        })


                    });
                return false;

            });

    });
