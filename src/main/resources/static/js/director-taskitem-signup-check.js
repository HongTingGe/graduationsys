
function showStudentSignupInfo() {

    let studentId = $("#studentId").val();
    if (!(/^\d{11}$/.test(studentId))){
        layui.layer.alert('请输入正确的11位学生学号!', {
            icon: 0,
            skin: 'layer-ext-moon',
            yes: function (index, layero) {
                layui.layer.close(index);
            }
        });
        return;
    }

    $.ajax({
        url: '/director/get/taskitem/signup/studentid',
        type: 'post',
        data:{
            studentId : studentId
        },
        dataType: 'json',
        success: function (res) {
            if (res.code == -1){
                layer.alert(res.msg, {
                    icon: 0
                });

            } else{
                console.log(res.data);
                layui.layer.open({
                    type: 1,
                    title: '提示',
                    offset: 'auto',
                    shadeClose: true,
                    shade: false,
                    area: ['450px', '450px'],
                    content:
                        `<div class="layui-row" id="addMember">
                             <div class="layui-col-md10">
                                 <form class="layui-form" id="addEmployeeForm">
                                    <div class="layui-form-item">
                                       <label class="layui-form-label">专业</label>
                                       <div class="layui-input-inline">
                                          <input type="text" class="layui-input" value="${res.data.studentBasicInfo.major}" readonly/>
                                       </div>
                                    </div>
                                    <div class="layui-form-item">
                                       <label class="layui-form-label">班级</label>
                                       <div class="layui-input-inline">
                                          <input type="text" class="layui-input" value="${res.data.studentBasicInfo.clazz}" readonly/>
                                       </div>
                                    </div>
                                    <div class="layui-form-item">
                                       <label class="layui-form-label">学号</label>
                                       <div class="layui-input-inline">
                                          <input type="text" class="layui-input" value="${res.data.studentBasicInfo.id}" readonly/>
                                       </div>
                                    </div>
                                    <div class="layui-form-item">
                                       <label class="layui-form-label">姓名</label>
                                       <div class="layui-input-inline">
                                          <input type="text" class="layui-input" value="${res.data.studentBasicInfo.name}" readonly/>
                                       </div>
                                    </div>
                                    <div class="layui-form-item">
                                       <label class="layui-form-label">课题名称</label>
                                       <div class="layui-input-inline">
                                          <button type="button" class="layui-btn" onclick="showTaskItemDeclare('${res.data.taskItemDeclareBasicInfo.id}')">${res.data.taskItemDeclareBasicInfo.name}</button>
                                          (可点击查看)
                                       </div>
                                    </div>
            
                                    <div class="layui-form-item">
                                       <div class="layui-input-block">
                                         <button type="button" class="layui-btn layui-btn-danger" onclick="deleteSignUp('${res.data.studentBasicInfo.id}')">删除该学生报名信息</button>
                                       </div>
                                    </div>
                                 </form>
                             </div>
                        </div>`
                });
            }
        }
    });

}

function deleteSignUp(id) {
    layui.layer.alert('真的删除么?!', function(index){
        $.ajax({
           url: '/director/delete/taskitem/signup/studentid',
            type: 'post',
            data: {
               studentId: id
            },
            dataType: 'json',
            success: function (res) {
                layer.msg(res.msg, {
                    icon: 1,
                    time: 1500
                }, function(){
                    layui.layer.closeAll();
                });
            }
        });
    });

}

//显示申报课题
function showTaskItemDeclare(id) {
    $.ajax({
        url: '/get/taskitemdeclare',
        data :{
            id: id
        },
        type: 'post',
        dataType: 'json',
        success: function (res) {
            layui.layer.open({
                type:1,
                title:"课题简介",
                area:["60%"],
                content: `<div class="layui-row" id="addMember">
                            <div class="layui-col-md10">
                               <form class="layui-form" id="addEmployeeForm">
                                 <div class="layui-form-item">
                                   <label class="layui-form-label">课题名称：</label>
                                   <div class="layui-input-inline">
                                      <input type="text" class="layui-input"  readonly value="${res.name}"/>
                                   </div>
                                 </div>
                                 <div class="layui-form-item">
                                    <label class="layui-form-label">课题方向：</label>
                                    <div class="layui-input-inline">
                                       <input type="text" class="layui-input" readonly value="${res.task.name}"/>
                                    </div>
                                 </div>
                                 <div class="layui-form-item">
                                    <label class="layui-form-label">课题描述：</label>
                                    <div class="layui-input-block">
                                       <textarea class="layui-textarea" readonly>${res.detail}</textarea>
                                    </div>
                                 </div>
                               </form>
                            </div>
                       </div>`
            });
        }
    });
}

//显示课题成员
function showMembers(declareid) {
    $.ajax({
        url: '/director/get/students/bydeclareid',
        data :{
            declareid: declareid
        },
        type: 'post',
        dataType: 'json',
        success: function (res) {
            let trNode='';
            for (let i in res){
                let tr=`<tr>
                          <td>${res[i].college}</td>
                          <td>${res[i].major}</td>
                          <td>${res[i].clazz}</td>
                          <td>${res[i].id}</td>
                          <td>${res[i].name}</td>
                        </tr>`;
                trNode=trNode+tr;
            }

            layui.layer.open({
                type:1,
                title:"课题成员",
                area:["90%"],
                content: `
                            <div class="layui-form">
                               <table class="layui-table">
                                  <colgroup>
                                     <col width="220">
                                     <col width="220">
                                     <col width="220">
                                     <col width="220">
                                     <col>
                                  </colgroup>
                                  <thead>
                                     <tr>
                                        <th>学院</th>
                                        <th>专业</th>
                                        <th>班级</th>
                                        <th>学号</th>
                                        <th>姓名</th>
                                     </tr> 
                                  </thead>
                                  <tbody id="members">
                                      ${trNode}
                                  </tbody>
                               </table>
                            </div>`
            });
        }
    });

}


//更新表格
function reloadTable() {
    layui.table.reload('test');
}


layui.use(['table'], function(){

    let dataUrl="/director/check/taskitem/signup";

    //按钮状态
    function btnStatus(i){
        let btn;
        if (i==0){
            btn=`<button type="button" class="layui-btn layui-btn-warm layui-btn-xs">未审批</button>`;
        }else if (i==1) {
            btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">报名成功</button>`;
        }else{
            btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">报名失败</button>`;
        }
        return btn;
    }



    var table = layui.table;
    let taskitemdeclareid='';

    table.render({
        elem: '#test'
        ,url: dataUrl
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: []
        ,title: '学生课题报名'
        ,cols: [[
            {field:'id', title:'报名编号',hide:true, templet: function(res){
                    return  res.id;
                }}
            ,{field:'name', title:'课题名称(可点击查看)', templet: function(res){
                    taskitemdeclareid=res.taskItemDeclareBasicInfo.id;
                    return  `<a href="javascript:showTaskItemDeclare('${res.taskItemDeclareBasicInfo.id}')">${res.taskItemDeclareBasicInfo.name}</a>` ;
                }}

            ,{field:'name', title:'课题成员', templet: function(res){
                    return  `<a class="layui-btn layui-btn-normal" href="javascript:showMembers('${res.taskItemDeclareBasicInfo.id}')">查看</a>` ;
                }}

            ,{field:'createtime', title:'报名时间', templet: function(res){
                    return  res.createtime;
                }}
            ,{field:'status', title:'状态', templet: function(res){
                    return  btnStatus(res.status) ;
                }}

            ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]]
        ,page: true
        ,limits: [5,10,15,20]
        ,limit: 5
    });


    //头工具栏事件
    table.on('toolbar(test)', function(obj){

        switch(obj.event){
            case 'checked':
                dataUrl="/director/checked/taskitem/signup";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'unchecked':
                dataUrl="/director/unchecked/taskitem/signup";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'all':
                dataUrl="/director/check/taskitem/signup";
                table.reload('test', {
                    url: dataUrl
                });
                break;
        }
    });


    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;

        layui.layer.open({
            type:1,
            title:"审批操作",
            area:["75%"],
            content:
                `<div class="layui-row">
                            <div class="layui-col-md10">
                               <form class="layui-form" onsubmit="return false">
                                 <div class="layui-form-item">
                                     <label for="college" class="layui-form-label">指导老师: </label>
                                     <div class="layui-input-inline">
                                         <select name="college" id="college" lay-filter="college">
                                             <option value="">请选择</option>
                                         </select>
                                     </div>
                                     <div class="layui-input-inline">
                                         <select name="major" id="major" lay-filter="major">
                                             <option value="">请选择</option>
                                         </select>
                                     </div>
                                     <div class="layui-input-inline">
                                         <select name="teacher" id="teacher">
                                             <option value="">请选择</option>
                                         </select>
                                     </div>
                                 </div>
                                 
                                 <div class="layui-form-item">
                                    <label class="layui-form-label">审批批注: </label>
                                    <div class="layui-input-block">
                                       <textarea id="msg" class="layui-textarea" placeholder="请输入信息.." required  lay-verify="required"></textarea>
                                    </div>
                                 </div>
                                 
                                 <div class="layui-form-item">
                                     <label class="layui-form-label"></label>
                                     <button class="layui-btn" lay-filter="pass" lay-submit>通过</button>
                                     <button class="layui-btn" lay-filter="repulse" lay-submit>不通过</button>
                                 </div>
                               </form>
                            </div>
                       </div>
                       <script>
                       var college = $("#college"),
                major = $("#major"),
                teacher = $("#teacher");

            var collegeList;

            $.ajax({
                url:"/college/list",
                type: 'post',
                async: false,
                success:function (res) {
                    collegeList=res;
                }
            });


            for (let i in collegeList){
                addEle(college, collegeList[i].name);
            }


            //赋予完成 重新渲染select
            layui.form.render('select');

            //向select中 追加内容
            function addEle(ele, value) {
                var optionStr = "";
                optionStr = "<option value=" + value + " >" + value + "</option>";
                ele.append(optionStr);
            }

            //向select中 追加内容
            function addTeacherEle(ele, item) {
                var optionStr = "";
                optionStr = "<option value=" + item.id + " >" + item.name + "</option>";
                ele.append(optionStr);
            }

            //移除select中所有项 赋予初始值
            function removeEle(ele) {
                ele.find("option").remove();
                var optionStar = "<option value=" + "" + ">" + "请选择" + "</option>";
                ele.append(optionStar);
            }

            var collegeText,
                collegeIndex,
                majorText;


            //选定学院后 将该学院的专业系读取追加上
            layui.form.on('select(college)', function(data) {
                collegeText = data.value;
                $.each(collegeList, function(i, item) {
                    if (collegeText == item.name) {
                        collegeIndex = i;
                        return collegeIndex;
                    }
                });
                removeEle(major);
                removeEle(teacher);
                $.each(collegeList[collegeIndex].majorList, function(i, item) {
                    addEle(major, item.name);
                });
                //重新渲染select
                layui.form.render('select');
            });

            //选定专业后 将对应的教师读取追加上
            layui.form.on('select(major)', function(data) {
                majorText = data.value;
                removeEle(teacher);
                $.each(collegeList, function(i, item) {
                    if (collegeText == item.name) {
                        majorItem = i;
                        return majorItem;
                    }
                });
                $.each(collegeList[majorItem].majorList, function(i, item) {
                    if (majorText == item.name) {
                        for (var n = 0; n < item.teacherList.length; n++) {
                            addTeacherEle(teacher, item.teacherList[n]);
                        }
                    }
                })
                //重新渲染select
                layui.form.render('select');
            });
            
            //监听提交
            layui.form.on('submit(pass)',

                function(data) {
                

                let teacher=$("#teacher").val();
                let msg=$("#msg").val();

                if (teacher.trim()==""){
                    layer.msg('请填写指导老师!');
                    return false;
                }

                    if (msg.trim()==""){
                        layer.msg('请填写批注信息!');
                        return false;
                    }

                    layer.alert("确定审批通过么?", {
                            icon: 0
                        },
                        function(index) {
                           $.ajax({
                              url: '/director/success/taskItem/signup/bydeclareid',
                              type: 'post',
                              data: {
                                  declareid : '${taskitemdeclareid}',
                                  msg: msg,
                                  teacherid: teacher
                              },
                              dataType: 'json',
                              success: function(res) {
                                if (res.code==0){
                                    
                                    layer.msg(res.msg, {
                                    icon: 1,
                                    time: 2000
                                }, function(){
                                    layer.closeAll();
                                    reloadTable();
                                });
                                    
                                } else{
                                    layer.msg(res.msg, {
                                    icon: 2,
                                    time: 2000
                                }, function(){
                                    layer.closeAll();
                                    reloadTable();
                                });
                                }
                              }
                           })
                        
                      
                        
                        
                        });
                    return false;

                });


            //监听提交
            layui.form.on('submit(repulse)',

                function(data) {
                
                
                    let msg=$("#msg").val();


                    if (msg.trim()==""){
                        layer.msg('请填写批注信息!');
                        return false;
                    }


                    layer.alert("确定不通过么?", {
                            icon: 0
                        },
                        function(index) {
                        
                        $.ajax({
                           url: '/director/fail/taskItem/signup/bydeclareid',
                           type: 'post',
                           data: {
                               declareid : '${taskitemdeclareid}',
                               msg: msg,
                               teacherid: ''
                           },
                           dataType: 'json',
                           success: function(res) {
                             if (res.code == 0){
                                 layer.msg(res.msg, {
                                    icon: 1,
                                    time: 2000
                                }, function(){
                                    layer.closeAll();
                                    reloadTable();
                                });
                             
                             } else{
                                 layer.msg(res.msg, {
                                    icon: 2,
                                    time: 2000
                                }, function(){
                                    layer.closeAll();
                                    reloadTable();
                                });
                                }
                             }
                 
                           });
                        
          
                        });
                    return false;
                });
            
</script>
`
        });
    });
});