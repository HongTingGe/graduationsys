let user=JSON.parse(sessionStorage.getItem("user"));

layui.use(['form', 'layer'], function() {

    var form = layui.form,
        layer = layui.layer;

    //根据专业id显示课题方向
    $.ajax({
        url:'/task/list/bymarjorid',
        type:'post',
        success:function (res) {
            for(let i in res){
                var optionStr = "";
                optionStr = "<option value=" + res[i].id + " >" + res[i].name + "</option>";
                $("#taskType").append(optionStr);
            }
            form.render('select');
        }
    });

    //监听提交
    form.on('submit(declare)', function(data){

        $.ajax({
            url:'/student/taskitem/declare',
            data:{
                name: data.field.name,
                typeid: data.field.typeid,
                detail: data.field.detail
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                if (res.code == 0){
                    layer.alert(res.msg, {
                        icon: 1,
                        skin: 'layer-ext-moon'
                    });

                    $("#name").val("");
                    $("#detail").val("");
                    $("#taskType option[value='']").attr("selected", true);
                    form.render('select');


                }else{
                    $("#name").val("");
                    $("#detail").val("");
                    $("#taskType option[value='']").attr("selected", true);
                    form.render('select');

                    layer.alert(res.msg, {
                        icon: 0,
                        skin: 'layer-ext-moon'
                    });
                }
            }
        });
        return false;
    });
});
