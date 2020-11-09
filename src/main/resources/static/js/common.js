function layer_show(title,url,w,h){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=800;
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    layer.open({
        type: 2, // 弹框框的类型，iframe层
        area: [w+'px', h +'px'], // 弹出来的层大小
        fix: false, // 意思就是鼠标轮滑下去页面是否一直固定在中间，而不会因为轮滑滑动而改变 false 代表不固定
        maxmin: true, //  最大最小化操作
        shade:0.4, // 遮罩，也就是点击 弹出来后的下面那层页面的灰暗度
        title: title, // 相当于网页的标题
        content: url // 要打开的页面，type为2因此放了一个请求，交给springmvc返回视图
    });
}

/*关闭弹出框口*/
function layer_close(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}