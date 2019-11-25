var myobj;
var rowData;
var flag=true;

layui.use(['form', 'layer','table'], function(){

    var form = layui.form,
        layer = layui.layer,
        table = layui.table;

    table.render({
        elem: '#test'
        ,toolbar: '#toolbarDemo'
        ,title: '用户数据表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'college', title:'学院'}
            ,{field:'major', title:'专业'}
            ,{field:'class', title:'班级'}
            ,{field:'sid', title:'学号'}
            ,{field:'username', title:'姓名'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
        ]]
        ,data: [{
            "college": ""
            ,"major": ""
            ,"class": ""
            ,"sid": ""
            ,"username": ""
        }]
    });

    $("tbody").remove();


    //监听行工具事件
    table.on('tool(test)', function(obj){
        myobj = obj;
        rowData = obj.data;

        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){

            layer.open({
                type: 2,
                title: '编辑成员',
                shade: [0],
                area: ['750px', '500px'],
                anim: 2,
                content: ['/member-edit', 'yes'], //iframe的url，no代表不显示滚动条
                cancel: function(){
                //右上角关闭回调

                return flag;
            }
            });

        }
    });

    //监听提交
    form.on('submit(add)',
        function(data) {

            let user=JSON.parse(sessionStorage.getItem("user"));
            let flag=false;

            $.ajax({
                url: '/student/taskitem',
                async: false,
                type: 'post',
                data:{
                    id:user.id
                },
                dataType: "json",
                success:function (res) {

                    if(JSON.stringify(res) == "{}"){
                        flag=false;
                    }else{
                        flag=true;
                    }
                }

            });

            if (flag == true){
                layer.alert('已申请过课题,不能再次申请!', {
                    icon: 0,
                    skin: 'layer-ext-moon'
                });
                return ;
            }

            let members=new Array();
            let objs=layui.table.cache["test"];

            for (let i in objs) {
                if (objs[i].sid==''){
                    break;
                }
                let obj={
                    id: objs[i].sid,
                    name: objs[i].username,
                    major: objs[i].major,
                    clazz: objs[i].class
                };
                members.push(obj);
            }


            $.ajax({
                url:'/student/taskitem/apply',
                type:'post',
                data:{
                    sid: user.id,
                    sname: user.name,
                    majorClazz: user.majorClazz,
                    name:data.field.taskName,
                    type:data.field.taskType,
                    details:data.field.details,
                    members: JSON.stringify(members)
                },
                success:function (res) {
                    var index=layer.alert("增加成功", {
                            icon: 6
                        },
                        function() {
                            layer.close(index);
                        });
                }
            });
            return false;

        });


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


    //课题方向
    $.ajax({
        url:'/task/list',
        type:'post',
        async: false,
        success:function (res) {
            for(let i in res){
                var optionStr = "";
                optionStr = "<option value=" + res[i].name + " >" + res[i].name + "</option>";
                $("#taskType").append(optionStr);
            }
            form.render('select');
        }
    });


    for (let i in collegeList){
        addEle(college, collegeList[i].name);
    }


    //赋予完成 重新渲染select
    form.render('select');

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
    form.on('select(college)', function(data) {
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
        form.render('select');
    });

    //选定专业后 将对应的教师读取追加上
    form.on('select(major)', function(data) {
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
        });
        //重新渲染select
        form.render('select');
    });

});
