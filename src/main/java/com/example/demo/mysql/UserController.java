package com.example.demo.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUser() {
        List<User> list = new ArrayList<>();
        int count = 0;
        for (int i = 1; i <= 1000000; i++) {
            User user = new User();
            String name = createName();
            user.setName(name);
            user.setAge(new Random().nextInt(100));
            list.add(user);

            if (i % 1000 == 0) {
                System.out.println(list.size());
                count++;
                userService.saveBatch(list);
                list.clear();
            }

        }
        System.out.println("count=" + count);
        return "ok";
    }

    public String createName(){
        String line = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦" +
                "章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞仁袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅" +
                "皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜" +
                "阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡";
        Random random = new Random();
        String name = line.charAt(random.nextInt(line.length()))+"";
        for(int i= 1+random.nextInt(2);i>0;i--){
            name+=line.charAt(random.nextInt(line.length()))+"";
        }
        return name;
    }
}
