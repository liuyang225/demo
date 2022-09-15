package com.example.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.example.demo.easyexcel.handler.CustomCellWriteHandler;
import com.example.demo.easyexcel.listener.MemberExcelListener;
import com.example.demo.easyexcel.strategy.CommonCellStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/easyExcel")
public class easyexcelController {

    @Autowired
    private MemberService memberService;

    /**
     * 普通导出方式
     */
    @RequestMapping("/export1")
    public void exportMembers1(HttpServletResponse response) {
        List<Member> members = memberService.getAllMember();
        try {
            memberService.export1(members, Member.class, "成员列表", "成员列表", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于策略及拦截器导出
     */
    @RequestMapping("/export2")
    public void exportMembers2(HttpServletResponse response) throws IOException {
        List<Member> members = memberService.getAllMember();

        // 设置文本内省
        response.setContentType("application/vnd.ms-excel");
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应头
        response.setHeader("Content-disposition", "attachment;filename=demo.xlsx");
        EasyExcel.write(response.getOutputStream(), Member.class).sheet("成员列表")
                // 注册通用格式策略
                .registerWriteHandler(CommonCellStyleStrategy.getHorizontalCellStyleStrategy())
                // 设置自定义格式策略
                .registerWriteHandler(new CustomCellWriteHandler())
                .doWrite(members);
    }

    /**
     * 从Excel导入会员列表
     */
    @RequestMapping(value = "/import1", method = RequestMethod.POST)
    public void importMemberList(@RequestPart("file") MultipartFile file) throws IOException {
        List<Member> list = EasyExcel.read(file.getInputStream())
                .head(Member.class)
                .sheet()
                .doReadSync();
        for (Member member : list) {
            System.out.println(member);
        }
    }

    /**
     * 基于Listener方式从Excel导入会员列表
     */
    @RequestMapping(value = "/import2", method = RequestMethod.POST)
    public void importMemberList2(@RequestPart("file") MultipartFile file) throws IOException {
        // 方式一：同步读取，将解析结果返回，比如返回List<Member>，业务再进行相应的数据集中处理
        // 方式二：对照doReadSync()方法的是最后调用doRead()方法，不进行结果返回，而是在MemberExcelListener中进行一条条数据的处理；
        // 此处示例为方式二
        EasyExcel.read(file.getInputStream(), Member.class, new MemberExcelListener()).sheet().doRead();
    }
}
