package comvv.example.administrator.viewutil.inject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/8/22.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@BaseEvent(listenerSetter = "setOnClickListener", listenerType = View.OnClickListener.class, callBackMothed = "onClick")
public @interface OnClick {
    int[] value();
}
