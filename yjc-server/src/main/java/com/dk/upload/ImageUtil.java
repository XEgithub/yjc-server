package com.dk.upload;

import com.dk.utils.DateUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class ImageUtil {
    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = UUID.randomUUID() + ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    public static String getFilePath(String route) {
        StringBuffer stringBuffer = new StringBuffer(File.separator + "img" + File.separator);
        stringBuffer.append(route);
        stringBuffer.append(File.separator);
        stringBuffer.append(DateUtil.getDate());
        return stringBuffer.toString();
    }

    /**
     *
     * 功能描述: 本地图片保存到本地
     *
     * @auther: cjy
     * @Date: 2018/7/16
     * @Param:
     * @return:
     */
    public static String save_sourceImg(File source, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = new FileInputStream(source.getAbsolutePath());
        String fileName = UUID.randomUUID() + ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

}
