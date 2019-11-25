var stompClient = null;
var layer=null;
var flag=false;
var res;

layui.use(['layer'], function() {

    layer = layui.layer;
});

//建立连接
function connect(from) {


	var socket = new SockJS('/endpoint-websocket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/chat/single/'+from, function (result) {
            


            layer.msg('收到新消息', {
                offset: 'auto',
                time: 0,
                anim: 6,
                time: 3000,
                zIndex: layer.zIndex

            });

            if (maxminStatus == 'min'){
                layer.restore(chatBoxIndex);
            }

            setTimeout(function () {
                flag=true;
                res=result;
                if (subIframeWin==null){
                    studentChat();
                }else{
                    //显示回复内容
                    console.log("显示回复内容");
                    subIframeWin.showContent(result);
                }
            },500);



        });
    });
}

//断开连接
function disconnect() {

    if (stompClient !== null) {
        stompClient.disconnect();
    }
}


//发送信息
function sendName(from,to,content,date) {
    if(from == '16115071033'){
        to='16115071056';
    }else{
        to='16115071033';
    }

    stompClient.send("/app/single/chat", {}, JSON.stringify({'content': content, 'to':to, 'from':from,'createtime':date}));

}

//展示回复信息
function showContent(result) {

    let res=JSON.parse(result.body);
    let message=`<div class="clearfloat">
                            <div class="author-name">
                                <small class="chat-date">`+dateFormat("YYYY年mm月dd日 HH:MM", new Date(res.createtime))+`</small>
                            </div>
                            <div class="left">
                                <div class="chat-avatars"><img src="/img/icon01.png" alt="头像"/></div>
                                <div class="chat-message">
                                    ${res.content}
                                </div>
                            </div>
                        </div>`;
    $("#chatBox-content-demo").append(message);

    //聊天框默认最底部
    $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);

}


//展示回复信息(left)
function showLeftContent(date,content) {
    let message=`<div class="clearfloat">
                            <div class="author-name">
                                <small class="chat-date">`+dateFormat("YYYY年mm月dd日 HH:MM", date)+`</small>
                            </div>
                            <div class="left">
                                <div class="chat-avatars"><img src="/img/icon01.png" alt="头像"/></div>
                                <div class="chat-message">
                                    ${content}
                                </div>
                            </div>
                        </div>`;
    $("#chatBox-content-demo").append(message);
}


//展示回复信息(right)
function showRightContent(date,content) {

    let message=`<div class="clearfloat">
                            <div class="author-name">
                                <small class="chat-date">`+dateFormat("YYYY年mm月dd日 HH:MM", date)+`</small>
                            </div>
                            <div class="right">
                                <div class="chat-message">
                                    ${content}
                                </div>
                                <div class="chat-avatars"><img src="/img/icon01.png" alt="头像"/></div>
                            </div>
                        </div>`;
    $("#chatBox-content-demo").append(message);
}


function dateFormat(fmt, date) {
    let ret;
    let opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        };
    };
    return fmt;
}

