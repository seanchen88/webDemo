package iot.ah.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.PropertySource;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @date 2018/8/27
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/iot/ah",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
public class BusinessController {
    @Autowired
    private Environment env;

    @RequestMapping(value = "/getEnvProp")
    public Map getEnvProp(@RequestBody Map<String,Object> params) {
        Map<String, Object> map = new HashMap();
        for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                map.putAll(((MapPropertySource) propertySource).getSource());
            }
        }
        Map<String, String> result = new HashMap();
        result = map.entrySet().parallelStream().filter(entry -> {
            return (entry.getKey().contains("iot") || entry.getKey().contains("link"));
        }).collect(Collectors.toMap(entry -> entry.getKey(), entry -> String.valueOf(entry.getValue())));
        return result;
    }

    @RequestMapping(value = "/getAllEnvProp")
    public Map<String, String> getEnvProp() {
        return System.getenv();
    }
}
