package comvv.example.administrator.viewutil;

import android.app.Activity;
import android.os.Bundle;

import comvv.example.administrator.viewutil.inject.InjectUtil;

/**
 * Created by Administrator on 2017/8/22.
 */

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtil.inject(this);
    }
}
