package comvv.example.administrator.viewutil.inject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/8/22.
 */

public class InjectInvocationHandler implements InvocationHandler {

    private Object receiver;
    private Method methodName;

    public InjectInvocationHandler(Object receiver, Method methodName) {
        this.receiver = receiver;
        this.methodName = methodName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method 就是代理的方法啊 即  OnClick ()
        String name = method.getName();
        //调用自己的方法
        if (methodName != null) {
            return methodName.invoke(receiver, args);
        }

        return null;
    }
}
