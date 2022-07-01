package com.atguigu.ucenterservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class MemberRegVo implements Serializable {

    @NotBlank(message = "手机号不能为空")
    private String mobile;  // 手机号

    @NotBlank(message = "密码不能为空")
    @Length(min = 6,message = "密码长度需要大于6个字符")
    private String password;    //密码

    @NotBlank(message = "昵称不能为空")
    private String nickname;    // 昵称

    @NotBlank(message = "验证码不能为空")
    private String code;    //验证码
}
