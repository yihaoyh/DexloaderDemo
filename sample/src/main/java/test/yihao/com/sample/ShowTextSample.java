package test.yihao.com.sample;

import test.yihao.com.plugin.ShowText;

/**
 * Created by yihao on 15/11/22.
 */
public class ShowTextSample implements ShowText {
    @Override
    public String showText() {
        return "Hello Host, I am plugin";
    }
}
