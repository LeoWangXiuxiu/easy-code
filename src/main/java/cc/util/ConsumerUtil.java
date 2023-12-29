package cc.util;//package cc.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Date: 2023/01/24 11:20
// * @Version: 1.0
// * @Description:
// */
//@Component
//@Slf4j
//public class ConsumerUtil {
//
//    @RabbitListener(queues = "producer")
//    public void rabbitReceive(String message){
//        log.info("开始消费消息：{}", message);
//    }
//}
