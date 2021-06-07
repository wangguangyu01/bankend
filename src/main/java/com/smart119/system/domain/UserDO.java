package com.smart119.system.domain;

import com.smart119.jczy.domain.XfjyryDO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    @Length(min= 2, max=50, message = "姓名超出范围限制{min}-{max}")
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newpassword;

    /**
     * 部门
     */
    private Long deptId;
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 邮箱
     */
    private String email;
    // 手机号
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    // 创建用户id
    private Long userIdCreate;
    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtModified;
    //角色
    private List<Long> roleIds;
    //性别
    private Long sex;
    //出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    //图片ID
    private Long picId;
    //现居住地
    private String liveAddress;
    //爱好
    private String hobby;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //消防救援机构_通用唯一识别码
    private String xfjyjgTywysbm;
    /**
     * 密码加密加盐的值
     */
    private String salt;
    private XfjyryDO xfjyryDO;
    @Override
    public String toString() {
        return "UserDO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", roleIds=" + roleIds +
                ", sex=" + sex +
                ", birth=" + birth +
                ", picId=" + picId +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
