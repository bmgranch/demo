package demo.tests;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConfigBean {

    @Value("${test.env}")
    private String testEnv;

    @Value("${domain}")
    private String domain;

    @Autowired
    private Environment env;

    public String getAppBaseUrl(){
        return env.getProperty(testEnv + "." + domain + ".web.url");
    }

    public String getCardGameBaseUrl(){
        return env.getProperty(testEnv + "." + "dice" + ".web.url");
    }

}
