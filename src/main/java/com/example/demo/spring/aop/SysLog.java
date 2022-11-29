package com.example.demo.spring.aop;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class SysLog  {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 操作用户id
     */
    private Integer optId;
    /**
     * 操作用户名
     */
    private String optName;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 日志信息（具体方法名）
     */
    private String logMessage;
    /**
     * 创建时间
     */
    private Date createTime;

}