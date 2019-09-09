layui.use('element', function(){
    var $ = layui.jquery,
        element = layui.element;
    var token = layui.data("user_token");
    $.ajax({
        type:"GET",
        headers: {
            token:token.token
        },
        url:"sys-menu/list-user-sys-menu",
        dataType: "json",
        success: function (dataRaw) {
            var data = dataRaw.data;
            $.each(data, function (i, obj) {
                loadMenu(i, obj.menuName, obj.childSysMenu);
                element.init();
            })

            $('.site-demo-active').on('click', function () {
                var dataid = $(this);
                if ($(".layui-tab-title li[lay-id]").length <= 0) {
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                } else {
                    var isData = false;
                    $.each($(".layui-tab-title li[lay-id]"), function () {
                        if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                            isData = true;
                        }
                    })
                    if (isData == false) {
                        active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
                    }
                }
                active.tabChange(dataid.attr("data-id"));

            });
        }
    });



    var active = {
        tabAdd: function (url, id, name) {
            element.tabAdd('demo', {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                id: id
            })
            FrameWH();
        },
        tabChange: function (id) {
            element.tabChange('demo', id);
        },
        tabDelete: function (id) {
            element.tabDelete("demo", id);
        }
    };
    function FrameWH() {
        var h = $(window).height();
        $("iframe").css("height",h+"px");
    }




    function loadMenu(i, menuTitle, childSysMenuList){
        var content;
        if (!i) {
            content = "<li class='layui-nav-item layui-nav-itemed'>";
        } else {
            content = "<li class='layui-nav-item'>";
        }

        content += "<a class='' href='javascript:;'>" + menuTitle + "</a>";
        if (childSysMenuList) {
            $.each(childSysMenuList, function (j, obj) {
                content += loadChild(obj);
            })
        }
        content += "</li>";
        $(".layui-nav-tree").append(content);

    }

    function loadChild(obj){
        var content = "<dl class='layui-nav-child'>";
        content += "<dd>";
        content += "<a href='javascript:;' data-id='" +obj.id+ "' data-title='" + obj.menuName + "' data-url='" + obj.menuUrl + "' " +
            "class='site-demo-active' >" + obj.menuName + "</a>";
        content += "</dd>";
        content += "</dl>";
        return content;
    }
});