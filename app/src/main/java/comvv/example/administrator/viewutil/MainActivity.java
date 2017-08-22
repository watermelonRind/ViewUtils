package comvv.example.administrator.viewutil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import comvv.example.administrator.viewutil.inject.ContentView;
import comvv.example.administrator.viewutil.inject.OnClick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    //    // xUtils的view注解要求必须提供id，以使代码混淆不受影响。
//    @ViewInject(R.id.btn)
//    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        Toast.makeText(this, "textview " + b, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn, R.id.btn1})
    public void show(View v) { // 方法签名必须和接口中的要求一致
        Toast.makeText(this, "hello world !!", Toast.LENGTH_SHORT).show();
    }
}
