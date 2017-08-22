package comvv.example.administrator.viewutil.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/8/22.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface BaseEvent {
    //事件三要素
    //1. 设置事件监听的方法 setOnClickListener
    String listenerSetter();
    //2. 事件执行对象类型 View.OnClickListener
    Class<?> listenerType();
    //3. 事件结果回调 OnClick方法
    String callBackMothed();
}
