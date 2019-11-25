let user=JSON.parse(sessionStorage.getItem("user"));
let menus=JSON.parse(sessionStorage.getItem("menus"));
$("#username").text(user.name);
for (let key1 in menus){

    let key=JSON.parse(key1);


    let liNode=`<li><a href="javascript:;">
                <i class="iconfont left-nav-li" lay-tips="${key.name}">${key.icon}</i>
                <cite>${key.name}</cite>
                <i class="iconfont nav_right">&#xe697;</i>
            </a>
            <ul id="sub-menu${key.id}" class="sub-menu">
            </ul>
            </li>`;

    $("#nav").append(liNode);

    for (let key2 in menus[key1]){

        let subLiNode=`<li>
                           <a onclick="xadmin.add_tab('${menus[key1][key2].name}','${menus[key1][key2].url}')">
                           <i class="iconfont">&#xe6a7;</i>
                           <cite>${menus[key1][key2].name}</cite>
                           </a>
                       </li>`;
        $("#sub-menu"+key.id).append(subLiNode);
    }



}