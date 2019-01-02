package com.orange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 该Controller主要用于路由网页
 */
@Controller
public class PageController {

    @RequestMapping("/page/login")
    public ModelAndView getLogin() {
        return new ModelAndView("login");
    }


    @RequestMapping("/page/table")
    public ModelAndView getTable() {
        return new ModelAndView("table");
    }


    @RequestMapping("/admin/logout")
    public ModelAndView getLogout() {
        return new ModelAndView("admin/logout");
    }


    @RequestMapping("/admin/club")
    public ModelAndView getClub() {
        return new ModelAndView("admin/club");
    }


    @RequestMapping("/admin/park")
    public ModelAndView getPark() {
        return new ModelAndView("admin/park");
    }


    @RequestMapping("/admin/errorlog")
    public ModelAndView getErrorLog() {
        return new ModelAndView("admin/errorlog");
    }


    //组件


    @RequestMapping("/components/tips")
    public ModelAndView getComponentsTips() {
        return new ModelAndView("components/tips");
    }


    @RequestMapping("/components/bigtips")
    public ModelAndView getComponentsBigTips() {
        return new ModelAndView("components/bigtips");
    }


    @RequestMapping("/components/banner")
    public ModelAndView getComponentsBanner() {
        return new ModelAndView("components/banner");
    }
}
