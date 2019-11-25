let user=JSON.parse(sessionStorage.getItem("user"));
let btnStatus=new Array();
let btn0=`<span class="layui-btn layui-btn-normal layui-btn-mini">待审核</span>`;
let btn1=`<span class="layui-btn layui-btn-normal layui-btn-mini">审核成功</span>`;
let btn2=`<span class="layui-btn layui-btn-normal layui-btn-mini">审核失败</span>`;
btnStatus.push(btn0);
btnStatus.push(btn1);
btnStatus.push(btn2);
console.log(user);

function studentChatPage(){
    parent.studentChat();
}



function createRowData(res) {
    let teacherName='';
    if (res.teacherid!='') {
        $.ajax({
            url:'/pub/teacher/getName',
            type:'post',
            async:false,
            data:{
                id:res.teacherid
            },
            success:function (name) {
                teacherName=name;
            }
        });
    }

    let node=`<div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">课题状态</div>
                        <div class="layui-card-body ">
                            <table class="layui-table" lay-size="lg">
                                <colgroup>
                                    <col width="300">
                                    <col width="200">
                                    <col>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>课题名称</th>
                                    <th>状态</th>
                                    <th>指导老师</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr id="taskitemInfo">
                                    <td>${res.name}</td>
                                    <td>${btnStatus[res.status]}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>`;
    $("#mainDiv").append(node);

    if (teacherName==""){
        let node=`<td>${teacherName}</td>`;
        $("#taskitemInfo").append(node);
    } else{
        parent.connect(user.id);
        let node=`<td><button type="button" class="layui-btn layui-btn-primary" onclick="studentChatPage()">${teacherName}</button></td>`;
        $("#taskitemInfo").append(node);
    }

}


//显示用户名、时间
$(function () {

    $("#username").text(user.name);

    function timereset() {
        let datetime = new Date();
        let hour = datetime.getHours() < 10 ? "0 " + datetime.getHours() : datetime.getHours();
        let min = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
        let sec = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
        let year = datetime.getFullYear();
        let mon = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        let day = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        $("#nowtime").text("！当前时间 : "+year + "-" + mon + "-" + day + " " + hour + ":" + min + ":"+ sec);
    }
    setInterval(timereset, 1000);
});




//学生登录
if (user.roleList[0].name=="student"){
    $.ajax({
        url:'/student/taskitem',
        type:'post',
        data:{
            id:user.id
        },
        success:function (res) {
            createRowData(res);
        }
    })

}




