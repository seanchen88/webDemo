package iot.ah.demo.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletResponse;

/**
 * @date 2018/8/27
 */
@Controller
public class MainController {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/", method = {RequestMethod.POST,RequestMethod.GET})
    public void rootHome(HttpServletResponse response) throws Exception {
        response.sendRedirect("/home.html");
    }

    @RequestMapping(value = "/getConfig")
    public void getConfig(@RequestParam(name = "key") String key,
                          HttpServletResponse response) throws IOException {
        response.getWriter().print(env.getProperty(key));
    }
}
