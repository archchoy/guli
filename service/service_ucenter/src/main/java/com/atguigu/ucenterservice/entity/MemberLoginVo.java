package com.atguigu.ucenterservice.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class MemberLoginVo implements Serializable {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$",message = "手机号格式有误")
    private String mobile;

    @NotBlank(message="密码不能为空")
    private String password;
}
