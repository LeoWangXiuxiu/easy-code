package cc.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class Base64CaptchaGenerator {
    public static String generateBase64CaptchaImage(String captchaText) {
        int width = 200;
        int height = 50;

        // 创建图像对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics2D g2d = image.createGraphics();

        // 设置背景颜色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 生成随机验证码

        // 将验证码绘制到图像上
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString(captchaText, 50, 35);

        // 释放图形上下文资源
        g2d.dispose();

        try {
            // 将图像转换为字节数组输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);

            // 将字节数组转换为 Base64 编码字符串
            byte[] bytes = baos.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(bytes);

            return base64Image;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateRandomCaptcha() {
        // 生成随机验证码的逻辑，这里只是一个示例
        // 您可能需要使用自己的逻辑生成验证码

        String captcha = "";

        for (int i = 0; i < 6; i++) {
            int randomDigit = (int) (Math.random() * 10);
            captcha += randomDigit;
        }

        return captcha;
    }


}
