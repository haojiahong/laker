package com.hao.laker.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by YangFalcom on 16/8/3.
 */
@Slf4j
public class ToleranceInterceptor implements MethodInterceptor {

//    @Autowired
//    private ToleranceManager toleranceManager;
//
//    @Override

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return null;
    }
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        try {
//            Object obj = invocation.proceed();
//            handleToleranceSuccess(invocation);
//            return obj;
//        } catch (Exception e) {
//            handleToleranceFail(invocation);
//            return null;
//        }
//    }
//
//    private void handleToleranceSuccess(MethodInvocation invocation) {
//        Object[] args = invocation.getArguments();
//        toleranceManager.handleToleranceSuccess(args[0] instanceof Tolerance ? (Tolerance) args[0] : null);
//    }
//
//    private void handleToleranceFail(MethodInvocation invocation) {
//        Method method = invocation.getMethod();
//
//        String className = method.getDeclaringClass().getSimpleName();
//        className = className.substring(0, 1).toLowerCase() + className.substring(1);
//        String methodName = method.getName();
//
//        log.error("容灾拦截器处理调用失败。class:{}, method:{}, args:{}", className, methodName, JSONArray.toJSONString(invocation.getArguments()));
//
//        Class<?>[] classes = method.getParameterTypes();
//        Object[] args = invocation.getArguments();
//
//        List<ToleranceParameterBO> list = Lists.newArrayList();
//        for (int i = 0; i < classes.length; i++) {
//            if ("com.tqmall.combo.model.Tolerance".equals(classes[i].getName())) {
//                continue;
//            }
//
//            Class<?> cls = classes[i];
//            Object arg = args[i];
//
//            ToleranceParameterBO paramBO = new ToleranceParameterBO();
//            paramBO.setType(cls.getName());
//            paramBO.setValue(arg);
//            list.add(paramBO);
//        }
//
//        toleranceManager.handleToleranceFail(args[0] instanceof Tolerance ? (Tolerance) args[0] : null, className, methodName, ObjectUtil.list2JsonStr(list));
//    }
}
