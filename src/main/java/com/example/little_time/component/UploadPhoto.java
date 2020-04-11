package com.example.little_time.component;

//import com.citadel.fingerling.module.common.exception.ServiceException;

import org.springframework.stereotype.Component;


//import static jxl.biff.BaseCellFeatures.logger;

@Component
public class UploadPhoto {
//
//    @Value("${fingerling.upload.image.base.path}")
//    private String uploadPath;
//
//    @Value("${fingerling.upload.image.context.path}")
//    private String contextPath;
//
//    @Value("${upload.image.valid.suffixes}")
//    private String validSuffix;
//
//
//    public String upload(MultipartFile file) throws Exception {
//
//        //获取文件名称；
//        String name = file.getOriginalFilename();
//
//        //获取当前时间作为最终存储文件的上级目录；
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        String today = format.format(new Date());
//
//        //保存的文件名：时间戳+六位随机数+文件后缀；
//        String fileName = RandomUtils.getRandomId() + RandomUtils.generateCode(6) + getFileSuffix(name);
//
//        //文件类型校验；
//        String type = getFileSuffix(name);
//        if (!validSuffix.contains(type)) {
////            logger.error("UploadFile failed for suffix error;error sufiix:" + type);
//            throw new Exception("M2-000001");
//        }
//
//        //文件最终上传地址；
//        String uploadFolder = uploadPath + File.separator + today;
//        //上传文件；
//        File dir = new File(uploadFolder);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        String targetUploadPath = dir + File.separator + fileName;
//        String targetContextPath = contextPath + File.separator + today + File.separator + fileName;
//        try {
//            FileUtils.writeByteArrayToFile(new File(targetUploadPath), file.getBytes());
//        } catch (IOException e) {
////            logger.error("UploadFile failed", e);
//            throw new Exception("M2-000002");
//        }
//        //返回文件存储位置；
//        return targetContextPath;
//    }
//
//    public static String getFileSuffix(String name) {
//        return name.substring(name.lastIndexOf("."), name.length());
//    }
}
