function reloadTable() {
    layui.table.reload('test');
}


layui.use('table', function(){


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



    var table = layui.table;



    table.render({
        elem: '#test'
        ,url: '/student/taskitem/declare/check'
        ,title: '我的课题申报'
        ,cols: [[
            {field:'id', title:'课题编号',hide:true, templet: function(res){
                    return  res.id;
                }}
            ,{field:'name', title:'课题名称', templet: function(res){
                    return  res.name;
                }}
            ,{field:'type', title:'课题方向', templet: function(res){
                    return  res.task.name;
                }}
            ,{field:'status', title:'状态', templet: function(res){
                    return  btnStatus(res.status) ;
                }}
            ,{field:'detail', title:'课题描述',  templet: function(res){
                    if (res.detail==null||res.detail=='') {
                        return '';
                    }else{
                        return  res.detail ;
                    }
                }}
            ,{field:'msg', title:'批注',  templet: function(res){
                    if (res.msg==null||res.msg=='') {
                        return '';
                    }else{
                        return  res.msg ;
                    }
                }}
            ,{fixed: 'right', title:'操作',  toolbar: '#barDemo'}
        ]]
    });


    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        layer.open({
            type: 2
            ,title: '课题申报详情'
            ,area: ['800px', '600px']
            ,content: '/student/taskitemdeclare/info/check/page?id='+data.id
        });
    });

});