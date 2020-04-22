package com.study.controller;

import com.study.dto.AccountDto;
import com.study.dto.Person;
import com.study.result.ResultEnum;
import com.study.result.ResultView;
import com.study.utils.createUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.NameAlreadyBoundException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ldap")
@RestController
public class LDAPController {

    @Autowired
    private LdapTemplate ldapTemplate;

    /**
     * 注册
     *
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public ResultView register(@RequestBody AccountDto dto) {
        String username = dto.getAccount();
        String password = dto.getPassword();
        Person person = new Person(username);
        person.setUserPassword(password);
        person.setCn("cn-" + username);
        person.setSn("sn-" + username);
        person.setGidNumber(Integer.valueOf(createUtils.createCode(6)));
        person.setUidNumber(Integer.valueOf(createUtils.createCode(6)));
        person.setHomeDirectory("/home/user");
        try {
            ldapTemplate.create(person);
        } catch (Exception e) {
            if (e instanceof NameAlreadyBoundException) {
                return ResultView.error("账号已存在");
            }
            e.printStackTrace();
            return ResultView.error(ResultEnum.CODE_666);
        }
        return ResultView.success();
    }

    /**
     * 注销账号（即删除账号）
     *
     * @param account
     * @return
     */
    @DeleteMapping("logout/{account}")
    public ResultView logout(@PathVariable String account) {
        ldapTemplate.delete(new Person(account));
        return ResultView.success();
    }

    /**
     * 更新密码
     *
     * @param dto
     * @return
     */
    @PostMapping("updatePassword")
    public ResultView update(@RequestBody AccountDto dto) {
        String username = dto.getAccount();
        String password = dto.getPassword();
        Person person = new Person(username);
        person.setUserPassword(password);
        person.setCn("cn-" + username);
        person.setSn("sn-" + username);
        person.setGidNumber(Integer.valueOf(createUtils.createCode(6)));
        person.setUidNumber(Integer.valueOf(createUtils.createCode(6)));
        person.setHomeDirectory("/home/user");
        try {
            ldapTemplate.update(person);
        } catch (Exception e) {
            if (e instanceof NameNotFoundException) {
                return ResultView.error("账号不存在");
            }
            e.printStackTrace();
            return ResultView.error(ResultEnum.CODE_666);
        }
        return ResultView.success();
    }

    /**
     * 验证账号和密码
     *
     * @param dto
     * @return
     */
    @PostMapping("/checkAccount")
    public ResultView checkAccount(@RequestBody AccountDto dto) {
        String username = dto.getAccount();
        String password = dto.getPassword();
        EqualsFilter filter = new EqualsFilter("uid", username);
        String s = filter.toString();
        boolean b = ldapTemplate.authenticate("", s, password);
        if (b) {
            return ResultView.success("账号和密码正确！", null);
        } else {
            return ResultView.error("账号或密码错误！");
        }
    }

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping("/getAll")
    public ResultView getAll() {
        List<Person> list = ldapTemplate.findAll(Person.class);
        return ResultView.success(list);
    }
}
