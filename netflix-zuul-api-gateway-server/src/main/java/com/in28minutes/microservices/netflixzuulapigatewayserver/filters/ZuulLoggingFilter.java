package com.in28minutes.microservices.netflixzuulapigatewayserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre"; // when the filter needed to be execute. before or after. "pre"  "post"
    }

    @Override
    public int filterOrder() {
        return 1;       // in case of multiple filter, this is used for ordering the filter.
    }

    @Override
    public boolean shouldFilter() {
        return true; //weather the filter needed to be executed or not.
    }

    @Override
    public Object run() throws ZuulException {// request processing
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}",request,request.getRequestURI());
        return null;
    }
}
