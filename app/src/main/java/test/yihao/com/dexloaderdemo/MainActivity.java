package test.yihao.com.dexloaderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;
import test.yihao.com.plugin.ShowText;


/**
 * 本demo参考
 * http://www.alloyteam.com/2014/04/android-cha-jian-yuan-li-pou-xi/
 *
 * http://blog.csdn.net/quaful/article/details/6096951
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassLoader classLoader = getClassLoader();

                /**
                 * 此处写法参考 http://blog.csdn.net/yangjunjiezai/article/details/8668265
                 */
                DexClassLoader localDexClass = new DexClassLoader("mnt/sdcard/sample.apk", getDir("dex", 0).getAbsolutePath(), null, classLoader);

                //load class
                Class localClass = null;
                try {
                    localClass = localDexClass.loadClass("test.yihao.com.sample.ShowTextSample");
                    Constructor localConstructor = localClass.getConstructor(new Class[]{});
                    Object instance = localConstructor.newInstance(new Object[]{});
                    ShowText showText = (ShowText) instance;
                    String str = showText.showText();
                    textView.setText(str);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
