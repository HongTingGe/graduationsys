
var myUrl;
function checkReportContent(url) {
    //iframe窗
    myUrl=url;

    //弹出即全屏

    var index = layer.open({
        type: 2,
        offset: 'auto',
        content: ['/teacher/preview', 'yes'],
        area: ['600px', '600px'],
        maxmin: true
    });
    layer.full(index);


}


layui.use('table', function(){

    let dataUrl="/teacher/get/Reports";

    //按钮状态
    function btnStatus(i){
        let btn;
        if (i==0){
            btn=`<button type="button" class="layui-btn layui-btn-warm layui-btn-xs">已查阅</button>`;
        }else{
            btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">未查阅</button>`;
        }
        return btn;
    }



    var table = layui.table;

    table.render({
        elem: '#test'
        ,url: dataUrl
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: []
        ,title: '学生报告'
        ,cols: [[
            {field:'id', title:'报告编号',hide:true, templet: function(res){
                    return  res.id;
                }}
            ,{field:'college', title:'学院',width:180, templet: function(res){
                    return  res.studentBasicInfo.college ;
                }}
            ,{field:'major', title:'专业',width:120, templet: function(res){
                    return  res.studentBasicInfo.major ;
                }}
            ,{field:'clazz', title:'班级',width:80, templet: function(res){
                    return  res.studentBasicInfo.clazz ;
                }}
            ,{field:'id', title:'学号',width:120, templet: function(res){
                    return  res.studentBasicInfo.id ;
                }}
            ,{field:'name', title:'姓名',width:80, templet: function(res){
                    return  res.studentBasicInfo.name ;
                }}
            ,{field:'report', title:'报告(可点击查看)',width:180, templet: function(res){
                    return  `<a onclick="checkReportContent('`+res.previewpath+`')">`+res.name+"</a>";
                }}
            ,{field:'createtime', title:'上传时间',width:120, templet: function(res){
                    return  res.createtime;
                }}
            ,{field:'status', title:'状态',width:88, templet: function(res){
                    return  btnStatus(res.status) ;
                }}
            ,{field:'msg', title:'批注',  templet: function(res){
                    if (res.msg==null||res.msg=='') {
                        return '';
                    }else{
                        return  res.msg ;
                    }
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
                dataUrl="/teacher/get/Checked/Reports";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'unchecked':
                dataUrl="/teacher/get/Unchecked/Reports";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'all':
                dataUrl="/teacher/get/Reports";
                table.reload('test', {
                    url: dataUrl
                });
                break;
        }
    });


    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        layer.prompt({
            formType: 2
        }, function(value, index){
            $.ajax({
                url:'/teacher/update/report',
                type:'post',
                data:{
                    id: data.id,
                    msg: value
                },
                success: function () {

                    table.reload('test',{  });

                    layer.close(index);
                }
            });
        });
    });
});