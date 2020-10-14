package com.tugos.dst.admin.controller;

import com.tugos.dst.admin.common.ResultVO;
import com.tugos.dst.admin.entity.Menu;
import com.tugos.dst.admin.entity.User;
import com.tugos.dst.admin.utils.URL;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class MainController {

    /**
     * 后台主体内容
     */
    @GetMapping("/")
    @RequiresPermissions("index")
    public String main(Model model) {
        User user = new User();
        user.setNickname("管理员");
        model.addAttribute("user", user);
        Menu menu = Menu.builder().id(2L).icon("layui-icon layui-icon-home").sort(0).
                children(new HashMap<>()).title("控制台").type(1).url("/home/index").build();

        Menu menu2 = Menu.builder().id(2L).icon("layui-icon layui-icon-set").sort(1).
                children(new HashMap<>()).title("房间设置").type(1).url("/setting/index").build();

        Menu menu3 = Menu.builder().id(2L).icon("layui-icon layui-icon-log").sort(2).
                children(new HashMap<>()).title("备份管理").type(1).url("/backup/index").build();

        Map<String, Menu> treeMenu = new HashMap<>(16);
        treeMenu.put("0", menu);
        treeMenu.put("1", menu2);
        treeMenu.put("2", menu3);
        model.addAttribute("treeMenu", treeMenu);
        return "main";
    }


    /**
     * 跳转到个人信息页面
     */
    @GetMapping("/userInfo")
    @RequiresPermissions("index")
    public String toUserInfo(Model model) {
        return "system/main/userInfo";
    }


    /**
     * 保存修改个人信息
     */
    @PostMapping("/userInfo")
    @RequiresPermissions("index")
    @ResponseBody
    public ResultVO userInfo(User user) {

        // 复制保留无需修改的数据
        ResultVO<URL> data = ResultVO.data(new URL("/userInfo"));
        data.setMessage("保存成功");
        return data;
    }

    /**
     * 跳转到修改密码页面
     */
    @GetMapping("/editPwd")
    @RequiresPermissions("index")
    public String toEditPwd() {
        return "system/main/editPwd";
    }

    /**
     * 保存修改密码
     */
    @PostMapping("/editPwd")
    @RequiresPermissions("index")
    @ResponseBody
    public ResultVO editPwd(String original, String password, String confirm) {
        // 判断原来密码是否有误

        return ResultVO.success();
    }
}