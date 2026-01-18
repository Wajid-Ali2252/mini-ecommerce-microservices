package order_service.order__service.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilterConfig {

	@Bean
    public FilterRegistrationBean<InternalSecurityFilter> loggingFilter(InternalSecurityFilter filter){
        FilterRegistrationBean<InternalSecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*"); 
        return registrationBean;
    }
	
}
