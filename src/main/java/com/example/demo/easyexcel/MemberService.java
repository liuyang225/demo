package com.example.demo.easyexcel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MemberService {
    /**
     * 获取所有的成员信息
     * @return 成员信息列表
     */
    List<Member> getAllMember();

    /**
     * 导出
     * @param list 数据
     * @param clazz 导出对象
     * @param fileName 文件名称
     * @param sheetName sheet栏名称
     * @param response
     */
    void export1(List<?> list, Class<?> clazz, String fileName, String sheetName, HttpServletResponse response) throws Exception;
}
