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
