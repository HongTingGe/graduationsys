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
    if (i == 0){
        btn=`<button type="button" class="layui-btn layui-btn-warm layui-btn-xs">未审核</button>`;
    }else if (i == 1) {
        btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">申报成功</button>`;
    }else{
        btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">申报失败</button>`;
    }
    return btn;
}



layui.use(['form', 'layer'],
    function() {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

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
                $("#type").val(res.task.name);
                $("#student").val(res.student.college+" "+res.student.major+" "+res.student.clazz+" "+res.student.name);
                $("#status").append(btnStatus(res.status));
                $("#detail").val(res.detail);
                $("#msg").val(res.msg);
                $("#createtime").val(res.createtime);

            }
        });


        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引


        //监听提交,审核通过
        form.on('submit(pass)',

            function(data) {


                let msg=$("#msg").val();


                if (msg.trim()==""){
                    layer.msg('请填写批注信息!');
                    return false;
                }



                layer.alert("确定审核通过么", {
                        icon: 6
                    },
                    function() {

                        $.ajax({
                            url:'/update/taskitemdeclare',
                            type:'post',
                            data:{
                                id : $("#id").val(),
                                status : 1,
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


        //监听提交,审核不通过
        form.on('submit(repulse)',

            function(data) {
                let msg=$("#msg").val();


                if (msg.trim()==""){
                    layer.msg('请填写批注信息!');
                    return false;
                }



                layer.alert("确定打回么", {
                        icon: 6
                    },
                    function() {

                        $.ajax({
                            url:'/update/taskitemdeclare',
                            type:'post',
                            data:{
                                id : $("#id").val(),
                                status : -1,
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
