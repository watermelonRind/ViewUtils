package comvv.example.administrator.viewutil.inject;

import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/8/22.
 */

public class InjectUtil {

    public static void inject(Object activity) {
        //注入布局
        injectLayout(activity);
        //注入控件
        injectView(activity);
        //注入事件
        injectEvent(activity);
    }

    /**
     * 注入事件
     *
     * @param activity
     */
    private static void injectEvent(Object activity) {
        Class<?> aClass = activity.getClass();
        //获取类中的所有方法
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            //获取方法上的注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //获取注解类型
                Class<?> annotationType = annotation.annotationType();
                //获取注解类型中的baseEvent注解类型
                BaseEvent event = annotationType.getAnnotation(BaseEvent.class);
                if (event == null) {
                    continue;
                }

                //获取事件三要素:
                //设置事件监听的方法
                String listenerSetter = event.listenerSetter();
                //事件监听的类型
                Class<?> listenerType = event.listenerType();
                //事件监听的回调
                String callBackMothed = event.callBackMothed();

                //获取调用事件的控件
                try {
                    Method valueMethod = annotationType.getDeclaredMethod("value");
                    int[] viewIds = (int[]) valueMethod.invoke(annotation);
                    for (int viewId : viewIds) {
                        Method findViewById = aClass.getMethod("findViewById", int.class);
                        findViewById.setAccessible(true);
                        View view = (View) findViewById.invoke(activity, viewId);
                        if (view == null) {
                            continue;
                        }
                        //获取控件调用的事件方法
                        Log.i("InjectUtil", listenerSetter);
                        Method listenerMethod = view.getClass().getMethod(listenerSetter, listenerType);
                        //创建一个代理处理方法
                        InvocationHandler handler = new InjectInvocationHandler(activity, method);
                        //生成一个代理类
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[]{listenerType}, handler);
                        listenerMethod.setAccessible(true);
                        listenerMethod.invoke(view, proxy);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注入控件
     *
     * @param activity
     */
    private static void injectView(Object activity) {
        Class<?> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ViewInject view = field.getAnnotation(ViewInject.class);
            if (view == null) {
                continue;
            }
            int viewId = view.value();

            try {
                Method findViewById = clazz.getMethod("findViewById", int.class);
                findViewById.setAccessible(true);
                View invoke = (View) findViewById.invoke(activity, viewId);
                //赋值给activity的变量
                field.setAccessible(true);
                field.set(activity, invoke);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入布局
     *
     * @param activity
     */
    private static void injectLayout(Object activity) {
        Class<?> tClass = activity.getClass();
        ContentView viewContent = tClass.getAnnotation(ContentView.class);
        if (viewContent == null) {
            return;
        }
        int layoutId = viewContent.value();
        try {
            Method setContentView = tClass.getMethod("setContentView", int.class);
            setContentView.setAccessible(true);
            setContentView.invoke(activity, layoutId);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
