
layui.use('form', function(){
    var form = layui.form
        ,layer = layui.layer;




    //自定义验证规则
    form.verify({
        password: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });


    //监听提交
    form.on('submit(userinfoFilter)', function(data){

        if (user.roleList[0].name=="student"){
            $.ajax({
                url: '/student/update/info',
                type: 'post',
                data: {
                    id: data.field.id,
                    name: data.field.name,
                    phone: data.field.phone,
                    email: data.field.email
                },

                success: function (stu) {
                    sessionStorage.removeItem("user");
                    sessionStorage.setItem("user",JSON.stringify(stu));
                    layer.alert('修改成功!');
                }
            });
        }else if (user.roleList[0].name=="teacher"||user.roleList[0].name=="director"){
            $.ajax({
                url: '/teacher/update/info',
                type: 'post',
                data: {
                    id: data.field.id,
                    name: data.field.name,
                    phone: data.field.phone,
                    email: data.field.email
                },

                success: function (stu) {
                    sessionStorage.removeItem("user");
                    sessionStorage.setItem("user",JSON.stringify(stu));
                    layer.alert('修改成功!');
                }
            });
        }



        return false;
    });


    //监听提交
    form.on('submit(passwordFilter)', function(data){
        let nowpass=data.field.nowpass;
        if (user.roleList[0].name=="student"){
            $.ajax({
                url: '/student/check/password',
                data: {
                    id: JSON.parse(sessionStorage.getItem("user")).id,
                    password: nowpass
                },
                type:'post',
                success: function (res) {
                    if (res.code == -1){
                        layer.alert(res.msg, {
                            icon: 0,
                        });
                        return ;
                    }else{
                        $.ajax({
                            url: '/student/update/password',
                            type: 'post',
                            data: {
                                id: JSON.parse(sessionStorage.getItem("user")).id,
                                password: data.field.repass
                            },
                            success: function (res) {
                                if (res.code == 0){
                                    layer.msg(res.msg, {
                                        anim: 6,
                                        time: 2000,
                                        end: function () {
                                            parent.location.href='/logout';
                                        }
                                    });
                                }
                            }
                        })
                    }

                }
            });
        }else if (user.roleList[0].name=="teacher"||user.roleList[0].name=="director") {
            $.ajax({
                url: '/teacher/check/password',
                data: {
                    id: JSON.parse(sessionStorage.getItem("user")).id,
                    password: nowpass
                },
                type:'post',
                success: function (res) {
                    if (res.code == -1){
                        layer.alert(res.msg, {
                            icon: 0,
                        });
                        return ;
                    }else{
                        $.ajax({
                            url: '/teacher/update/password',
                            type: 'post',
                            data: {
                                id: JSON.parse(sessionStorage.getItem("user")).id,
                                password: data.field.repass
                            },
                            success: function (res) {
                                if (res.code == 0){
                                    layer.msg(res.msg, {
                                        anim: 6,
                                        time: 2000,
                                        end: function () {
                                            parent.location.href='/logout';
                                        }
                                    });
                                }
                            }
                        })
                    }

                }
            });
        }

        return false;
    });


    let user=JSON.parse(sessionStorage.getItem("user"));
    if (user.roleList[0].name=="student"){
        $("#id").val(user.id);
        $("#college").val(user.college.name);
        $("#majorClass").val(user.major.name+" "+user.clazz.name);
        $("#name").val(user.name);
        $("#phone").val(user.phone);
        $("#email").val(user.email);
    }else if (user.roleList[0].name=="teacher"||user.roleList[0].name=="director"){
        $("#majorClassItem").hide();
        $("#id").val(user.id);
        $("#college").val(user.college.name+user.major.name);
        $("#name").val(user.name);
        $("#phone").val(user.phone);
        $("#email").val(user.email);
    }else{
        $("#collegeItem").hide();
        $("#majorClassItem").hide();
        $("#id").val(user.id);
        $("#name").val(user.name);
        $("#phone").val(user.phone);
        $("#email").val(user.email);
    }






});