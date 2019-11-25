var memberTmp = new Array();//保存添加成员数组

function updateMember(res) {//更新编辑成员信息
    myobj.update({
        college:res.college,
        major:res.major,
        class:res.class,
        sid:res.sid,
        username: res.name,
        phone:res.phone
    });
}

function addMember(res) {//添加成员
    let memberInfo={
        "college" : res.college,
        "major" : res.major,
        "class" : res.class,
        "sid" : res.sid,
        "username" : res.name,
        "phone" : res.phone
    };
    memberTmp.push(memberInfo);
    layui.table.reload("test",{
        data:memberTmp  // 将新数据重新载入表格
    });
}

function a2() {
    if (memberTmp.length>1){
        alert("小组成员最多2人");
        return;
    }
    layer.open({
        type: 2,
        title: '添加成员',
        area: ['750px', '400px'],
        anim: 2,
        content: ['/member-save', 'yes'], //iframe的url，no代表不显示滚动条
        cancel: function(){
            //右上角关闭回调

            return flag;
        }

    });
}