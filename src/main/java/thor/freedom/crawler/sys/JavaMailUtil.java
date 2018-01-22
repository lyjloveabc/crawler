package thor.freedom.crawler.sys;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by Thor on 2018/1/19.
 */
@Component
public class JavaMailUtil {
    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender javaMailSender;

    private final static List<String> TO_USERS = Lists.newArrayList(
            "luoyanjiewade@163.com",
            "546223592@qq.com"
    );

    @Async
    public void send(String subject, String text) {
        if (!ObjectUtils.isEmpty(TO_USERS)) {
            for (String toUser : TO_USERS) {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

                simpleMailMessage.setFrom(username);
                simpleMailMessage.setTo(toUser);
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(text);

                javaMailSender.send(simpleMailMessage);
            }
        }
    }
}
