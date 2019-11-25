
$("#downloadBtn").click(function(){
    location.href="/task/download";
});

layui.use(['upload','table','layer'], function(){
    var $ = layui.jquery
        ,upload = layui.upload
        ,table = layui.table
        ,layer=layui.layer;


    //选完文件后不自动上传
    upload.render({
        elem: '#selectTypeBtn'
        ,url: '/task/upload'
        ,auto: false
        ,accept: 'file'
        ,acceptMime: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel'
        ,field: 'file'
        ,bindAction: '#uploadAction'
        ,done: function(res){
            if (res.code==0){
                layer.msg(res.msg);
            } else{
                layer.alert(res.msg, {
                    icon: 0,
                    skin: 'layer-ext-moon'
                })
            }
        }
    });





    table.render({
        elem: '#test'
        ,url:'/task/all'
        ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: []
        ,title: '课题方向'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID', fixed: 'left', hide:true}
            ,{field:'name', title:'课题方向',width:300,edit: 'text'}
            ,{field:'major', title:'所属专业',width:300}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo'}
        ]]
        ,page: true
    });


    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                $.ajax({
                    url:'/task/delete',
                    data:{
                        id: data.id
                    },
                    type: 'post',
                    success: function () {
                        obj.del();
                        layer.close(index);
                    }
                });
            });
        }
    });


    //监听单元格编辑
    table.on('edit(test)', function(obj){
        var value = obj.value //得到修改后的值
            ,id = obj.data.id;
        $.ajax({
            url: '/task/update',
            data:{
                id: id,
                name: value
            },
            type: 'post',
            dataType: 'json',
            success: function (res) {
                layer.msg(res.msg);
            }
        })


    });




    //多文件列表示例
    var demoListView = $('#studentFilesList')
        ,uploadListIns = upload.render({
        elem: '#selectList1'
        ,url: '/director/workfiles/upload'
        ,accept: 'file'
        ,field: 'files'
        ,multiple: true
        ,auto: false
        ,bindAction: '#listAction1'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                demoListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }else{
                layer.alert(res.msg, {
                    icon: 0
                })
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });


});