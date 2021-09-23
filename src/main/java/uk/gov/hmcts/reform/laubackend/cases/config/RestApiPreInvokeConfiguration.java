package uk.gov.hmcts.reform.laubackend.cases.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uk.gov.hmcts.reform.laubackend.cases.authorization.RestApiPreInvokeInterceptor;

@Configuration
public class RestApiPreInvokeConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(restApiPreInvokeInterceptor()).addPathPatterns("/audit/caseView");
    }

    @Bean
    public RestApiPreInvokeInterceptor restApiPreInvokeInterceptor() {
        return new RestApiPreInvokeInterceptor();
    }
}
