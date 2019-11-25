let subIframeWin=null;
let maxminStatus;
let chatBoxIndex;

function studentChat(){


    chatBoxIndex=layer.open({
        type: 2 //此处以iframe举例
        ,id: 'LAY_layuipro'//设定一个id，防止重复弹出
        ,title: '即时聊天'
        ,area: ['320px', '500px']
        ,shade: 0
        ,maxmin: true
        ,offset: 'rb'//右下角弹出
        ,content: ['/student/chat/page', 'yes']

        ,zIndex: layer.zIndex //重点1


        ,success: function(layero){
            subIframeWin=window[layero.find('iframe')[0]['name']];
            layer.setTop(layero); //重点2
            layero.find('.layui-layer-max').hide();

            if (flag){
                //显示回复内容
                subIframeWin.showContent(res);
            }

        }
        ,cancel: function(index, layero){

            flag=false;
            subIframeWin=null;
            maxminStatus="";
            return true;
        }
        ,min: function (layero) {
            maxminStatus='min';
            layero.find('.layui-layer-max').show();
        }
        ,restore: function (layero) {
            layero.find('.layui-layer-max').hide();
        }
    });


}
