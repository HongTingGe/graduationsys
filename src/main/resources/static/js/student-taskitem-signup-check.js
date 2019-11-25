let members=new Array();

//添加小组成员
function addMember(){

    let studentid=$("#studentid").val();
    if (!(/^\d{11}$/.test(studentid))){
        layui.layer.alert('请输入正确的11位学生学号!', {
            icon: 0,
            skin: 'layer-ext-moon',
            yes: function (index, layero) {
                layui.layer.close(index);
            }
        });
        return;
    }

    if (members.length>1){
        layui.layer.alert('小组成员最多只能两个!', {
            icon: 0,
            closeBtn: 0,
            skin: 'layer-ext-moon',
            yes: function (index, layero) {
                layui.layer.closeAll();
            }
        });
        return;
    }

    let member={
        id: $("#studentid").val()
    };
    members.push(member);
    layui.table.reload("test",{
        data:members  // 将新数据重新载入表格
    });
    layui.layer.closeAll();
}

//按钮状态
function btnStatus(code){
    if (code==0){
        $("#btnStatus").html(`<button type="button" class="layui-btn layui-btn-normal">审批中</button>`);
    }else if (code==1){
        $("#btnStatus").html(`<button type="button" class="layui-btn layui-btn-warm">报名成功</button>`);
    }else{
        $("#btnStatus").html(`<button type="button" class="layui-btn layui-btn-danger">报名失败</button>`);
    }
}


$.ajax({
    url:'/student/taskitem/signup/check',
    type: 'post',
    dataType: 'json',
    success: function (res) {

        if (res.code==0){
            $("#taskitemName").val(res.data[0].taskItemDeclareBasicInfo.name);
            $("#detail").val(res.data[0].detail);
            if (res.data[0].teacherBasicInfo!=null){
                $("#teacher").val(res.data[0].teacherBasicInfo.name);
            }else{
                $("#teacher").val("暂无");
            }

            if (res.data[0].msg==null){
                $("#msg").val("暂无审批信息");
            } else{
                $("#msg").val(res.data[0].msg);
            }

            btnStatus(res.data[0].status);
            if (res.data[0].isprincipal == 1){
                if (res.data[0].status == -1){
                    $("#form1").hide();
                    $("#form2").show();
                    $("#againSignUpBtn").show();
                } else{
                    $("#detail").attr("readonly","readonly");
                    $("#form1").show();
                    $("#form2").hide();
                }
            } else {
                $("#detail").attr("readonly","readonly");
                $("#form1").show();
                $("#form2").hide();
            }



            $("#createtime").val(res.data[0].createtime);
            
            //显示报名成员
            if (res.data[1]!=null){
                if (res.data[0].isprincipal == 1){
                    if (res.data[0].status == -1){
                        members=res.data[1];//数据表格显示成员
                    } else{
                        for (let i in res.data[1]){
                            let trNode=`<tr>
                                  <td>${res.data[1][i].college}</td>
                                  <td>${res.data[1][i].major}</td>
                                  <td>${res.data[1][i].clazz}</td>
                                  <td>${res.data[1][i].id}</td>
                                  <td>${res.data[1][i].name}</td>
                                </tr>`;
                            $("#members").append(trNode);
                        }
                    }
                } else{
                    for (let i in res.data[1]){
                        let trNode=`<tr>
                                  <td>${res.data[1][i].college}</td>
                                  <td>${res.data[1][i].major}</td>
                                  <td>${res.data[1][i].clazz}</td>
                                  <td>${res.data[1][i].id}</td>
                                  <td>${res.data[1][i].name}</td>
                                </tr>`;
                        $("#members").append(trNode);
                    }
                }
            }


            layui.use(['form', 'table','layer'], function() {

                var form = layui.form,
                    layer = layui.layer,
                    table = layui.table;

                var flag=false;

                let msg='';




                table.render({
                    elem: '#test'
                    ,toolbar: '#toolbarDemo'
                    ,defaultToolbar: []
                    ,title: '添加成员'
                    ,cols: [[
                        {type:'numbers', hide:true}
                        ,{type: 'checkbox', fixed: 'left', }
                        ,{field:'id', title:'学号',  fixed: 'left'}
                        ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
                    ]]
                    ,data: members
                });

                //头工具栏事件
                table.on('toolbar(test)', function(obj){
                    var checkStatus = table.checkStatus(obj.config.id);
                    switch(obj.event){
                        case 'addMember':
                            layer.open({
                                type:1,
                                title:"添加成员",
                                area:["50%"],
                                content: `<div class="layui-row" id="addMember">
    <div class="layui-col-md10">
        <form class="layui-form" id="addEmployeeForm">
            <div class="layui-form-item">
                <label class="layui-form-label">学号：</label>
                <div class="layui-input-inline">
                    <input type="text" id="studentid" class="layui-input" placeholder="请输入学生学号"/>
                </div>
            </div>
            
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-normal" onclick="addMember()">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>`
                            });
                            break;
                        case 'deleteMember':
                            if (checkStatus.isAll){

                                layer.alert('确定删除么?', {
                                    icon: 1,
                                    skin: 'layer-ext-moon',
                                    yes: function(index, layero){
                                        members=[];
                                        table.reload("test",{
                                            data:members  // 将新数据重新载入表格
                                        });
                                        layer.close(index);
                                    }
                                });
                            }
                            break;
                    }
                });

                //监听行工具事件
                table.on('tool(test)', function(obj){
                    var data = obj.data;

                    if(obj.event === 'del'){
                        layer.confirm('真的删除行么', function(index){

                            for (let i=0;i<members.length;i++){
                                if (members[i].id==data.id){
                                    members.splice(i,1);
                                }
                            }
                            obj.del();

                            table.reload("test",{
                                data:members  // 将新数据重新载入表格
                            });
                            layer.close(index);
                        });
                    }
                });


                //监听提交
                form.on('submit(againSignUp)', function(data){

                    let params= {};
                    params.declareid = res.data[0].taskItemDeclareBasicInfo.id;
                    params.members = members;
                    params.detail = data.field.detail;

                    $.ajax({
                        url:'/student/taskitem/againSignup',
                        data:JSON.stringify(params),
                        type: 'post',
                        contentType:"application/json",
                        dataType: 'json',
                        success: function (res) {
                            if (res.code==-2){
                                let tip='学号: ';
                                for (let i=0;i<res.data.length;i++){
                                    tip=tip+res.data[i];
                                    if (i==res.data.length-1){
                                        tip=tip+"不存在!请重新检查,再次输入!";
                                    } else{
                                        tip=tip+","
                                    }
                                }
                                layer.alert(tip, {
                                    icon: 0,
                                })

                            }else if (res.code==-1) {
                                layer.alert(res.msg, {
                                    icon: 0,
                                    skin: 'layer-ext-moon'
                                })
                            }else if (res.code==-3){

                                let tip='学号: ';
                                for (let i=0;i<res.data.length;i++){
                                    tip=tip+res.data[i];
                                    if (i==res.data.length-1){
                                        tip=tip+"已有课题报名!不能再次报名!如有疑问,请联系系主任!";
                                    } else{
                                        tip=tip+","
                                    }
                                }
                                layer.alert(tip, {
                                    icon: 0,
                                });

                            }else if (res.code==-4){

                                let tip='你与学号: ';
                                for (let i=0;i<res.data.length;i++){
                                    tip=tip+res.data[i];
                                    if (i==res.data.length-1){
                                        tip=tip+"不是同个专业!不能进行报名!";
                                    } else{
                                        tip=tip+","
                                    }
                                }
                                layer.alert(tip, {
                                    icon: 0,
                                });

                            }else{

                                layer.alert(res.msg, {
                                    icon: 1,
                                    skin: 'layer-ext-moon',

                                });

                                //显示重新报名后的结果
                                $.ajax({
                                    url: '/student/taskitem/signup/check',
                                    type: 'post',
                                    dataType: 'json',
                                    success: function (res) {
                                        if (res.code == 0) {
                                            $("#taskitemName").val(res.data[0].taskItemDeclareBasicInfo.name);
                                            $("#detail").val(res.data[0].detail);
                                            if (res.data[0].teacherBasicInfo != null) {
                                                $("#teacher").val(res.data[0].teacherBasicInfo.name);
                                            } else {
                                                $("#teacher").val("暂无");
                                            }

                                            if (res.data[0].msg == null) {
                                                $("#msg").val("暂无审批信息");
                                            } else {
                                                $("#msg").val(res.data[0].msg);
                                            }

                                            btnStatus(res.data[0].status);
                                            if (res.data[0].status == -1) {
                                                $("#form1").hide();
                                                $("#form2").show();
                                            } else {
                                                $("#detail").attr("readonly", "readonly");
                                                $("#form1").show();
                                                $("#form2").hide();
                                            }


                                            $("#createtime").val(res.data[0].createtime);

                                            let members;
                                            //显示报名成员
                                            if (res.data[1] != null) {
                                                if (res.data[0].status != -1) {
                                                    for (let i in res.data[1]) {
                                                        let trNode = `<tr>
                                  <td>${res.data[1][i].college}</td>
                                  <td>${res.data[1][i].major}</td>
                                  <td>${res.data[1][i].clazz}</td>
                                  <td>${res.data[1][i].id}</td>
                                  <td>${res.data[1][i].name}</td>
                                </tr>`;
                                                        $("#members").append(trNode);
                                                    }

                                                } else {
                                                    members = res.data[1];
                                                }
                                            }

                                            $("#againSignUpBtn").hide();
                                        }

                                    }
                                });


                            }

                        }
                    });
                    return false;
                });

            });

        }
    }
});

