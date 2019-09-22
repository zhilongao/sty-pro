package com.study.zl.pattern.delegate.spring;

import java.util.List;

/**
 * @Author long
 * @Date 2019/3/9 8:27
 */
public class DispatcherServlet {



    public void doDispatch(HttpRequest request, HttpResponse response){
        //first
        HandlerExecutionChain handler = getHandler(request);
        //
    }

    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;

    // 由当前类DispatcherServlet中的HandlerMapping列表中获取到一个HandlerExecutionChain
    // 也就是说一个web请求到达后,悔获取到一个HandlerExecutionChain对象
    protected HandlerExecutionChain getHandler(HttpRequest request){
        HandlerExecutionChain handler = null;
        for (HandlerMapping mapping : handlerMappings) {
            try{
                handler = mapping.getHandler(request);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return handler;
    }

    // 通过Handler获取到HandlerAdapter
    protected HandlerAdapter getHandlerAdapter(Object handler){
        HandlerAdapter adapter = null;
        for (HandlerAdapter handlerAdapter :  handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                adapter = handlerAdapter;
            }
        }
        return adapter;
    }
}
