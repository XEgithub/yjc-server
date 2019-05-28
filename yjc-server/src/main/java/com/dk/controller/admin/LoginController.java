package com.dk.controller.admin;

import com.dk.config.Constant;
import com.dk.data.dto.UserLoginDto;
import com.dk.data.entity.Menu;
import com.dk.data.entity.Role;
import com.dk.data.entity.User;
import com.dk.service.MenuService;
import com.dk.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
//    @Autowired
//    private UserRoleService userRoleService;
//    @Autowired
//    private SmsUtil smsUtil;
//    @Autowired
//    private VerifyCodeService verifyCodeService;

    @GetMapping("/login")
    String login(){
        return "/admin/account/login";
    }

    @GetMapping("/reg")
    String reg(){
        return "/admin/account/reg";
    }

    @GetMapping("/forget")
    String forget(){
        return "/admin/account/forget";
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(@Valid UserLoginDto user, BindingResult bindingResult, HttpSession session,
                        RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/admin/account/login";
        }

        String username = user.getUsername();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            log.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            User admin = userService.findByUsername(username);
            if (admin == null) {
                return "redirect:/403";
            }
            session.setAttribute("user", admin);
            if (admin.getActive().equals(Constant.USER_NO_ACTIVE)) {
                return "redirect:/wechat/goods/list";
            }
            log.info("用户[" + username + "]登录认证通过(加载授权菜单)");
//            List<Role> userRoleList = userService.findRolesByUser(admin);
//            if (CollectionUtils.isNotEmpty(userRoleList)) {
//                List<Menu> menuList = menuService.findMenuByRoles(userRoleList);
//                List<Menu> authenMenuList = new ArrayList<>();
//                if (CollectionUtils.isNotEmpty(menuList)) {
//                    for (Menu menu : menuList) {
//                        if (menu.getLevel() == 1) {
//                            authenMenuList.add(menu);
//                        }
//                    }
//                }
//                if (CollectionUtils.isNotEmpty(authenMenuList)) {
//                    for (Menu menu : authenMenuList) {
//                        for (Menu mc : menuList) {
//                            if (mc.getPid().equals(menu.getId())) {
//                                menu.getChildren().add(mc);
//                            }
//                        }
//                    }
//                    session.setAttribute("menuList", authenMenuList);
//                    return "/admin/index";
//                }
//            }
            return "redirect:/wechat/goods/list";
        }else{
            token.clear();
            return "redirect:/admin/login";
        }
    }

//    @PostMapping("sendCode")
//    @ResponseBody
//    Vo sendCode(String phone){
//        if (StringUtils.isBlank(phone)) {
//            return Result.paramError("请输入手机号码");
//        }
//        boolean isMobile = smsUtil.isMobileNo(phone);
//        if (!isMobile) {
//            return Result.paramError("手机号码格式有误");
//        }
//        User user = userService.findByUsername(phone);
//        if (user != null) {
//            return Result.paramError("手机号码已被注册");
//        }
//        String code = smsUtil.sendSms(phone);
//        VerifyCode oldCode = verifyCodeService.findByPhone(phone);
//        if (oldCode == null) {
//            VerifyCode verifyCode = new VerifyCode();
//            verifyCode.setPhone(phone);
//            verifyCode.setCode(code);
//            verifyCode.setCount(1);
//            verifyCode.setStatus(Constant.YES);
//            verifyCodeService.add(verifyCode);
//        } else {
//            boolean allowSend = verifyCodeService.allowSend(phone);
//            if (!allowSend) {
//                return Result.paramError("发送过于频繁");
//            }
//            oldCode.setCode(code);
//            oldCode.setCount(oldCode.getCount() + 1);
//            oldCode.setStatus(Constant.YES);
//            verifyCodeService.update(oldCode);
//        }
//        return Result.ok();
//    }

//    @PostMapping("reg")
//    @ResponseBody
//    Vo reg(@Validated UserRegDto param, BindingResult result){
//        log.info("注册用户，参数：\n {}", param.toString());
//        if (result.hasErrors()) {
//            FieldError fieldError = result.getFieldError();
//            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
//            log.error("注册用户:{}", errorMsg);
//            return Result.paramError(errorMsg);
//        }
//
//        //两次密码是否一致
//        if (!param.getPassword().equals(param.getRepassword())) {
//            return Result.paramError("两次输入密码不一致");
//        }
//
//        //验证邀请人
//        String invitCode = param.getInvitCode();
//        User parent = userService.findByUsername(invitCode);
//        if (parent == null) {
//            return Result.paramError("邀请码无效");
//        }
//
//        //验证验证码
//        boolean expire = verifyCodeService.expire(param.getPhone());
//        if (expire) {
//            return Result.paramError("验证码已过期");
//        }
//
//        VerifyCode verifyCode = verifyCodeService.findByPhone(param.getPhone());
//        if (!param.getVerifyCode().equals(verifyCode.getCode())) {
//            return Result.paramError("验证码不正确");
//        }
//
//        if (verifyCode.getStatus().equals(Constant.NO)) {
//            return Result.paramError("验证码已被使用");
//        }
//
//        verifyCode.setStatus(Constant.NO);
//        verifyCodeService.update(verifyCode);
//
//        //初始化用户
//        User bean = new User();
//        bean.setNickname(param.getRealname());
//        bean.setUsername(param.getPhone());
//        bean.setRealname(param.getRealname());
//        bean.setPhone(param.getPhone());
//        bean.setActive(Constant.USER_NO_ACTIVE);
//        bean.setPid(parent.getId());
//        User repo = userService.add(bean);
//
//        //授权
//        UserRole ur = new UserRole();
//        ur.setUserId(repo.getId());
//        ur.setRoleId(3L);
//        userRoleService.add(ur);
//
//        return Result.ok();
//    }

    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/admin/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        log.info("------没有权限-------");
        return "403";
    }

    /**
     * 登录认证异常
     */
    @ExceptionHandler({UnauthenticatedException.class,AuthenticationException.class})
    public String authenticationException(){
        return "redirect:/admin/login";
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(){
        return "redirect:/403";
    }

}
