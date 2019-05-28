package com.dk.data.notes;

/**
 * 用户 controller swagger注释
 *
 * @author ban
 * @date 2018/11/26
 */
public class UserNotes {

    public static final String LIST_NOTE = "用户列表，入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"pageNo\": \"当前页\",\n" +
            "    \"pageSize\": \"每页记录条数\",\n" +
            "    \"orderColumn\": \"排序列(非必填)\",\n" +
            "    \"orderSort\": \"排序方法[asc:正序，desc:倒序],默认倒序\",\n" +
            "    \"nickname\": \"昵称\",\n" +
            "    \"username\": \"用户名\",\n" +
            "    \"sex\": \"性别：0:男 1:女 2:未知\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"avatar\": \"头像\",\n" +
            "    \"phone\": \"手机\",\n" +
            "    \"tel\": \"电话\",\n" +
            "    \"email\": \"邮箱\",\n" +
            "    \"idcard\": \"身份证\",\n" +
            "    \"birthday\": \"生日\",\n" +
            "    \"area\": \"地区\",\n" +
            "    \"autograph\": \"签名\",\n" +
            "    \"openid\": \"微信openid\",\n" +
            "    \"active\": \"状态：0:未激活 1:激活\",\n" +
            "    \"referee\": \"推荐人\",\n" +
            "    \"createTime\": \"创建时间\",\n" +
            "    \"updateTime\": \"更新时间\",\n" +
            "    \"lastLoginTime\": \"最后登录时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";


    public static final String LIST_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"pageNo\": 1,\n" +
            "    \"pageSize\": 10,\n" +
            "    \"nickname\": \"昵称\",\n" +
            "    \"username\": \"用户名\",\n" +
            "    \"sex\": \"性别：0:男 1:女 2:未知\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"avatar\": \"头像\",\n" +
            "    \"phone\": \"手机\",\n" +
            "    \"tel\": \"电话\",\n" +
            "    \"email\": \"邮箱\",\n" +
            "    \"idcard\": \"身份证\",\n" +
            "    \"birthday\": \"生日\",\n" +
            "    \"area\": \"地区\",\n" +
            "    \"autograph\": \"签名\",\n" +
            "    \"openid\": \"微信openid\",\n" +
            "    \"active\": \"状态：0:未激活 1:激活\",\n" +
            "    \"referee\": \"推荐人\",\n" +
            "    \"createTime\": \"创建时间\",\n" +
            "    \"updateTime\": \"更新时间\",\n" +
            "    \"lastLoginTime\": \"最后登录时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String ADD_NOTE = "用户，入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"nickname\": \"昵称\",\n" +
            "    \"username\": \"用户名\",\n" +
            "    \"sex\": \"性别：0:男 1:女 2:未知\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"avatar\": \"头像\",\n" +
            "    \"phone\": \"手机\",\n" +
            "    \"tel\": \"电话\",\n" +
            "    \"email\": \"邮箱\",\n" +
            "    \"idcard\": \"身份证\",\n" +
            "    \"birthday\": \"生日\",\n" +
            "    \"area\": \"地区\",\n" +
            "    \"autograph\": \"签名\",\n" +
            "    \"openid\": \"微信openid\",\n" +
            "    \"active\": \"状态：0:未激活 1:激活\",\n" +
            "    \"referee\": \"推荐人\",\n" +
            "    \"lastLoginTime\": \"最后登录时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String ADD_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"nickname\": \"昵称\",\n" +
            "    \"username\": \"用户名\",\n" +
            "    \"sex\": \"性别：0:男 1:女 2:未知\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"avatar\": \"头像\",\n" +
            "    \"phone\": \"手机\",\n" +
            "    \"tel\": \"电话\",\n" +
            "    \"email\": \"邮箱\",\n" +
            "    \"idcard\": \"身份证\",\n" +
            "    \"birthday\": \"生日\",\n" +
            "    \"area\": \"地区\",\n" +
            "    \"autograph\": \"签名\",\n" +
            "    \"openid\": \"微信openid\",\n" +
            "    \"active\": \"状态：0:未激活 1:激活\",\n" +
            "    \"referee\": \"推荐人\",\n" +
            "    \"lastLoginTime\": \"最后登录时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String EDIT_NOTE = "用户修改，入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"id\",\n" +
            "    \"nickname\": \"昵称\",\n" +
            "    \"username\": \"用户名\",\n" +
            "    \"sex\": \"性别：0:男 1:女 2:未知\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"avatar\": \"头像\",\n" +
            "    \"phone\": \"手机\",\n" +
            "    \"tel\": \"电话\",\n" +
            "    \"email\": \"邮箱\",\n" +
            "    \"idcard\": \"身份证\",\n" +
            "    \"birthday\": \"生日\",\n" +
            "    \"area\": \"地区\",\n" +
            "    \"autograph\": \"签名\",\n" +
            "    \"openid\": \"微信openid\",\n" +
            "    \"active\": \"状态：0:未激活 1:激活\",\n" +
            "    \"referee\": \"推荐人\",\n" +
            "    \"lastLoginTime\": \"最后登录时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String EDIT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"id\",\n" +
            "    \"nickname\": \"昵称\",\n" +
            "    \"username\": \"用户名\",\n" +
            "    \"sex\": \"性别：0:男 1:女 2:未知\",\n" +
            "    \"password\": \"密码\",\n" +
            "    \"avatar\": \"头像\",\n" +
            "    \"phone\": \"手机\",\n" +
            "    \"tel\": \"电话\",\n" +
            "    \"email\": \"邮箱\",\n" +
            "    \"idcard\": \"身份证\",\n" +
            "    \"birthday\": \"生日\",\n" +
            "    \"area\": \"地区\",\n" +
            "    \"autograph\": \"签名\",\n" +
            "    \"openid\": \"微信openid\",\n" +
            "    \"active\": \"状态：0:未激活 1:激活\",\n" +
            "    \"referee\": \"推荐人\",\n" +
            "    \"lastLoginTime\": \"最后登录时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

}
