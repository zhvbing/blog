package com.lrm.web.admin;

import com.lrm.po.User;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 进入登录页面
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }//loginPage

    /**
     * 登录操作
     * @param username：用户名
     * @param password：密码
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);

        if(user != null){
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message", "用户名密码错误");
            return "redirect:/admin";
        }
    }//login

    /**
     * 登出操作
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }


}
