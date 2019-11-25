
layui.use('table', function(){


    var table = layui.table;

    table.render({
        elem: '#test'
        ,url: '/get/workfiles'
        ,title: '工作文件下载'
        ,cols: [[
            {field:'id', title:'文件编号', templet: function(res){
                    return  res.id ;
                }}
            ,{field:'name', title:'文件名', templet: function(res){
                    return  res.name ;
                }}
            ,{field:'path', title:'文件路径',hide:true, templet: function(res){
                    return  res.path ;
                }}
            ,{field:'createtime', title:'上传时间', templet: function(res){
                    return  res.createtime;
                }}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]]
        ,page: true
        ,limits: [5,10,15,20]
        ,limit: 5
    });



    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        var $form = $('<form method="GET"></form>');
        $form.attr('action', data.path);
        $form.appendTo($('body'));
        $form.submit();
    });
});