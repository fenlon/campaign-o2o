package com.fenlonsky.campaign.qrcode.web.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fenlonsky.campaign.qrcode.entity.QRCode;
import com.fenlonsky.campaign.qrcode.util.QRCodeUtil;

@Controller
@RequestMapping(value = "/qrcode/")
public class QRCodeController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 生成兑换码二维码
	 * 
	 * @param content
	 */
	/*
	 * @RequestMapping(value = "get", method = RequestMethod.GET)
	 * public void generate(String content, Integer width, HttpServletResponse
	 * response) {
	 * byte[] data = QRCodeUtil.getQRCodeBytes(content, width, width);
	 * Map<String, String> map = new HashMap<String, String>();
	 * map.put("Content-Disposition", "filename=" + content + "."
	 * + QRCodeUtil_ERROR.QRCODE_FILETYPE);
	 * writePic(data, response, map);
	 * }
	 */
	
	/**
	 * 生成兑换码二维码
	 * 
	 * @param content
	 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public void generate(QRCode code, HttpServletResponse response) {
		byte[] data = QRCodeUtil.getQRCodeBytes(code);
		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Disposition", "filename=" + code + "."
				+ QRCodeUtil.QRCODE_FILETYPE);
		writePic(data, response, map);
	}
	
	/**
	 * 生成兑换码二维码
	 * 
	 * @param content
	 */
	/*
	 * @RequestMapping(value = "get", method = RequestMethod.GET)
	 * public void generate(String content, HttpServletResponse response) {
	 * byte[] data = QRCodeUtil_ERROR.getQRCodeBytes(content, null, null);
	 * Map<String, String> map = new HashMap<String, String>();
	 * map.put("Content-Disposition", "filename=" + content + "."
	 * + QRCodeUtil_ERROR.QRCODE_FILETYPE);
	 * writePic(data, response, map);
	 * }
	 */
	
	/**
	 * 文件流写出
	 * 
	 * @param data
	 *            图片字节
	 * @param response
	 * @param filename
	 *            图片文件名
	 */
	private void writePic(byte[] data, HttpServletResponse response,
			Map<String, String> headers) {
		try {
			response.setContentType("image/" + QRCodeUtil.QRCODE_FILETYPE);
			response.setCharacterEncoding("UTF-8");
			
			Set<Entry<String, String>> entries = headers.entrySet();
			for (Entry<String, String> entry : entries) {
				response.setHeader(entry.getKey(), entry.getValue());
			}
			OutputStream outputSream = response.getOutputStream();
			InputStream in = new ByteArrayInputStream(data);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = in.read(buf, 0, 1024)) != -1) {
				outputSream.write(buf, 0, len);
			}
			outputSream.flush();
			outputSream.close();
		} catch (IOException ex) {
			this.logger.info("getFile error", ex.fillInStackTrace());
		}
	}
}
