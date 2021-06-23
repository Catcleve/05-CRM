package com.Hwang.crm.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
public class locationController {


    @RequestMapping({"/toView/{first}/{second}",
            "/toView/{first}/{second}/{third}",
                    "/toView/{first}/{second}/{third}/{fourth}"})
    public String toView(@PathVariable(value = "first", required = false) String first,
                         @PathVariable(value = "second", required = false) String second,
                         @PathVariable(value = "third", required = false) String third,
                         @PathVariable(value = "fourth", required = false) String four) {

        if (third == null) {
            return first + File.separator + second;
        } else if (four == null) {
            return first + File.separator + second + File.separator + third;
        }
        return first + File.separator + second + File.separator + third + File.separator + four;
    }
}
