package com.stream.fastdevelop.vo;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/3 15:06
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class ButtonVo {
    private String buttonText;


    private String jumpFragmentName;

    public ButtonVo(){

    }

    public ButtonVo (String text, String jumpName) {
        buttonText = text;
        jumpFragmentName = jumpName;
    }

    public String getJumpFragmentName() {
        return jumpFragmentName;
    }

    public void setJumpFragmentName(String jumpFragmentName) {
        this.jumpFragmentName = jumpFragmentName;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
