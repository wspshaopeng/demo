package com.example.demo.ctrl;

//import com.example.demo.utils.QRCodeUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {


    private static final Logger logger = LoggerFactory.getLogger(QrCodeController.class);

    //二维码生成时间map
    private static final String QRCODE_EXPIRES_MAP = "qrcode_expires_map";
    /**
     * 生成二维码
     * */
    @GetMapping(value = "createQrcode")
    public String createQrcode(String url){
        //将二维码追加有效时间
        Long timeStr = new Date().getTime();
        String png_base64 = "";
        BufferedImage image = null;
        try {
//            image = QRCodeUtil.createImage(url, null, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(image, "png", baos);//写入流中
            byte[] bytes = baos.toByteArray();//转换成字节
            png_base64 = Base64.encodeBase64String(bytes).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        } catch (Exception ex) {
            logger.info("生成二维码失败");
        }
        return png_base64;
    }

}
