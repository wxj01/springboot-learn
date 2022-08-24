package com.wxj.springboot.specificfeature.config;

        import com.netflix.loadbalancer.IRule;
        import com.wxj.springboot.specificfeature.rule.CustomerRoundRobinRule;
        import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${specific.branch.rule}' != null")
public class CustomerConfig {


    @Bean
    @ConditionalOnExpression("'${spring.profiles.active}' =='dev'  or '${spring.profiles.active}'== 'test' ")
    public IRule customerRule() {
        System.out.println("abc");
        return new CustomerRoundRobinRule();
    }
}
