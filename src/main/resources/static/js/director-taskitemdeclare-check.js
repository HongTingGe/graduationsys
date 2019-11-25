
function reloadTable() {
    layui.table.reload('test');
}


layui.use('table', function(){

    let dataUrl="/director/check/taskitem/declare";

    //按钮状态
    function btnStatus(i){
        let btn;
        if (i==0){
            btn=`<button type="button" class="layui-btn layui-btn-warm layui-btn-xs">未审核</button>`;
        }else if (i==1) {
            btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">已审核</button>`;
        }else{
            btn=`<button type="button" class="layui-btn layui-btn-normal layui-btn-xs">申报失败</button>`;
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
            {field:'id', title:'课题编号',hide:true, templet: function(res){
                    return  res.id;
                }}
            ,{field:'name', title:'课题名称', templet: function(res){
                    return  res.name ;
                }}
            ,{field:'taskname', title:'课题方向', templet: function(res){
                    return  res.task.name ;
                }}
            ,{field:'student', title:'申报人', templet: function(res){
                    return  res.student.college+" "+res.student.major+" "+res.student.clazz+" "+res.student.name ;
                }}

            ,{field:'createtime', title:'申报时间', templet: function(res){
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
                dataUrl="/director/checked/taskitem/declare";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'unchecked':
                dataUrl="/director/unchecked/taskitem/declare";
                table.reload('test', {
                    url: dataUrl
                });
                break;
            case 'all':
                dataUrl="/director/check/taskitem/declare";
                table.reload('test', {
                    url: dataUrl
                });
                break;
        }
    });


    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        layer.open({
            type: 2
            ,title: '课题申报详情'
            ,area: ['800px', '600px']
            ,content: '/director/taskitemdeclare/info/check/page?id='+data.id
        });
    });
});