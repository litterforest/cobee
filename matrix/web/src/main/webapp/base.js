// layer组件，关闭当前对话框
function goback() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
