var normalTitle = document.title
window.onfocus = function () {
    document.title = "(/≥▽≤)咦! 你又回来啦";
    setTimeout(function (){
        document.title = normalTitle;
    }, 1500);
};
window.onblur = function () {
    normalTitle = document.title
    document.title = "等等, 你别走啊!!!";
}