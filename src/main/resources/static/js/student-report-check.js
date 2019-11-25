var myUrl;
function checkReportContent(url) {
    //iframe窗
    myUrl=url;

    //弹出即全屏

    var index = layer.open({
        type: 2,
        offset: 'auto',
        content: ['/student/preview', 'yes'],
        area: ['600px', '600px'],
        maxmin: true
    });
    layer.full(index);


}


layui.use('table', function(){

    let dataUrl="/student/get/Reports";

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
        ,title: '我的报告'
        ,cols: [[
            {field:'id', title:'报告编号',width:320, templet: function(res){
                    return  res.id;
                }}
            ,{field:'report', title:'报告(可点击查看)',width:180, templet: function(res){
                    return  `<a onclick="checkReportContent('`+res.previewpath+`')">`+res.name+"</a>";
                }}
            ,{field:'createtime', title:'上传时间',width:180, templet: function(res){
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
        ]]
        ,page: true
        ,limits: [5,10,15,20]
        ,limit: 5
    });


    //头工具栏事件
    table.on('toolbar(test)', function(obj){

        switch(obj.event){
            case 'checked':
                dataUrl="/student/get/Checked/Reports";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'unchecked':
                dataUrl="/student/get/Unchecked/Reports";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'all':
                dataUrl="/student/get/Reports";
                table.reload('test', {
                    url: dataUrl
                });
                break;
        }
    });

});