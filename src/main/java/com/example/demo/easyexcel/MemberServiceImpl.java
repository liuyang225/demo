package com.example.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {


    @Override
    public List<Member> getAllMember() {
        // 这里构造一些测试数据，具体业务场景可从数据库等其他地方获取
        List<Member> list = new ArrayList<>();
        Member member = new Member();
        member.setUsername("张三");
        member.setBirthday(getDate(1990, 10, 11));
        member.setGender(0);
        list.add(member);

        Member member1 = new Member();
        member1.setUsername("王红");
        member1.setBirthday(getDate(1999, 3, 29));
        member1.setGender(1);
        list.add(member1);

        Member member2 = new Member();
        member2.setUsername("李四");
        member2.setBirthday(getDate(2000, 2, 9));
        member2.setGender(0);
        list.add(member2);

        return list;
    }

    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    public void export1(List<?> list, Class<?> clazz, String fileName, String sheetName, HttpServletResponse response) throws Exception {
        // 设置文本内容
        response.setContentType("application/vnd.ms-excel");
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(list);
    }
}
