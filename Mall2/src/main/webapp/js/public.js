//手机号输入框检测---只能输入数字
function checkNumber() {
    var keyCode = event.keyCode;
    if (keyCode != 8 && (keyCode < 48 || keyCode > 57)) {
        //不让键盘的按键起作用
        event.preventDefault();
    }
}

//判断是否是手机号
function isPhoneNumber(tel) {
    var reg = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/
    ;
    return reg.test(tel);
}