package com.dk.data.notes;

/**
 * 角色 controller swagger注释
 *
 * @author ban
 * @date 2018/11/28
 */
public class RoleNotes {

    public static final String LIST_NOTE = "角色列表，入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"pageNo\": \"当前页\",\n" +
            "    \"pageSize\": \"每页记录条数\",\n" +
            "    \"orderColumn\": \"排序列(非必填)\",\n" +
            "    \"orderSort\": \"排序方法[asc:正序，desc:倒序],默认倒序\",\n" +
            "    \"name\": \"名称\",\n" +
            "    \"role\": \"角色标识\",\n" +
            "    \"remark\": \"备注\",\n" +
            "    \"createTime\": \"创建时间\",\n" +
            "    \"updateTime\": \"更新时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";


    public static final String LIST_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"pageNo\": 1,\n" +
            "    \"pageSize\": 10,\n" +
            "    \"name\": \"名称\",\n" +
            "    \"role\": \"角色标识\",\n" +
            "    \"remark\": \"备注\",\n" +
            "    \"createTime\": \"创建时间\",\n" +
            "    \"updateTime\": \"更新时间\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String ADD_NOTE = "角色，入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"name\": \"名称\",\n" +
            "    \"role\": \"角色标识\",\n" +
            "    \"remark\": \"备注\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String ADD_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"name\": \"名称\",\n" +
            "    \"role\": \"角色标识\",\n" +
            "    \"remark\": \"备注\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String EDIT_NOTE = "角色修改，入参如下\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"id\",\n" +
            "    \"name\": \"名称\",\n" +
            "    \"role\": \"角色标识\",\n" +
            "    \"remark\": \"备注\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

    public static final String EDIT_VALUE = "\n" +
            "\n" +
            "```\n" +
            "{\n" +
            "    \"id\": \"id\",\n" +
            "    \"name\": \"名称\",\n" +
            "    \"role\": \"角色标识\",\n" +
            "    \"remark\": \"备注\",\n" +
            "    \"deleted\": \"状态\"\n" +
            "}\n" +
            "```";

}
