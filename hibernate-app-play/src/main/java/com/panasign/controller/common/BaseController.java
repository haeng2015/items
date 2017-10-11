package com.panasign.controller.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.panasign.utils.ControlDataUtils;
import com.panasign.utils.ExcelUtils;
import com.panasign.utils.PanasignUploadImageException;

/**
 * 父类controller，所有子类controller都规定必须继承它，抽离一些大家都要用到的方法。
 * 
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-6-3
 */
public class BaseController {
	private static final String CONTENT_TYPE_JPEG = "\\image\\jpeg";
	/**
	 * apk附件上传路径
	 */
	public final static String APK_DIR = "\\attachment\\apk\\";
	/**
	 * 资源附件上传路径
	 */
	public final static String RESOURCE_DIR = "\\attachment\\resource\\";
	
	
	/**
	 * 把一个object对象以json格式打回客户端，默认格式为application/json
	 * @param object
	 * @param response
	 * @throws IOException
	 * @author Wu.Liang
	 */
	public void renderJSON(Object object, HttpServletResponse response)throws IOException {
		String json = "";
		PrintWriter pw = null;
		try {
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("application/json;charset=UTF-8");
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 *  把一个object对象以json格式打回客户端，contentType格式自己传入
	 * @param object
	 * @param response
	 * @param format
	 * @throws IOException
	 * @author Wu.Liang
	 */
	public void renderJSON(Object object, HttpServletResponse response, String format)throws IOException {
		String json = "";
		PrintWriter pw = null;
		try {
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
			response.setContentType(format);
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	/**
	 * 生成验证码图片
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author Wu.Liang
	 */
	public void renderImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType(CONTENT_TYPE_JPEG);
		response.setDateHeader("Expires", 0);
		//图片宽，高
		int width = 65, height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		OutputStream os = response.getOutputStream();
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Arial", Font.PLAIN, 28));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String sRand = "";
		for (int j = 0; j < 4; j++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			//后面的30是字体底边的高度
			g.drawString(rand, 13 * j + 6, 30);
		}
		//验证码内容放入session
		request.getSession().setAttribute("checkImage", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", os);
		os.flush();
		os.close();
		os = null;
		response.flushBuffer();
	}

	/**
	 * renderImageCode()生成验证码图片专用，用来生成随机颜色
	 * @param fc
	 * @param bc
	 * @return
	 * @author Wu.Liang
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 下载apk附件方法
	 * @param fileName Templates文件夹下存在的的文件名，比如importCustomers.xls
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author: Wu.Liang
	 */
	public void downloadApk(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		downloadFile(APK_DIR, fileName, request, response);
	}

	/**
	 * 下载资源附件方法
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void downloadSource(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		downloadFile(RESOURCE_DIR, fileName, request, response);
	}
	
	
	public HttpServletResponse downloadFileReturn(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return downloadFileReturn(RESOURCE_DIR, fileName, request, response);
	}
	
	/**
	 * 
	 * @param dir
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void downloadFile(String dir, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext().getRealPath("/") + dir;

		String downLoadPath = ctxPath + fileName;
		try {
			// 获取文件长度
			long fileLength = new File(downLoadPath).length();
			response.setCharacterEncoding("UTF-8");

			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			// 2.设置文件头：最后一个参数是设置下载文件名
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			// 3、告诉浏览器，文件有多长
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 下载成功后返回对象
	 * @param dir
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private HttpServletResponse downloadFileReturn(String dir, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + dir;
		
		String downLoadPath = ctxPath + fileName;
		try {
			// 获取文件长度
			long fileLength = new File(downLoadPath).length();
			response.setCharacterEncoding("UTF-8");
			
			// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
			response.setContentType("multipart/form-data");
			// 2.设置文件头：最后一个参数是设置下载文件名
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			// 3、告诉浏览器，文件有多长
			response.setHeader("Content-Length", String.valueOf(fileLength));
			
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
		}
		return response;
	}
	
	
	
	/**
	 * 接收上传的文件，保存后，返回保存后的绝对路径名
	 * 
	 * @param request
	 * @param receiveFile
	 * @return 保存后的绝对路径名
	 * @throws Exception
	 * @author: Wu.Liang
	 */
	public String receiveUploadFile(HttpServletRequest request,
			CommonsMultipartFile receiveFile) throws Exception {
		String fileAbsolutePath = "isNotFile";
		if (!receiveFile.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/uploadFiles/"); // 获取本地存储路径
			String fileName = receiveFile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			// 新建一个文件
			String fileAbsoluteName = path + "/" + UUID.randomUUID().toString()
					+ fileType;
			ExcelUtils.isFilePathExist(fileAbsoluteName);
			File file = new File(fileAbsoluteName);
			// 将上传的文件写入新建的文件中
			receiveFile.getFileItem().write(file);
			fileAbsolutePath = file.getAbsolutePath();
		}
		return fileAbsolutePath;
	}

	
	/**
	 * 接收上传的文件，保存后，返回保存后的相对路径 <br><p>
	 * 如:<code>E:/project/webapps/Panasign-cloud/uploadFiles/***.jpg\/png..</code><p>
	 * 返回： <code>uploadFiles/***.jpg\/png..</code>
	 * @param request
	 * @param receiveFile
	 * @return 
	 * @throws Exception
	 */
	public String receiveImageUploadFile(HttpServletRequest request, CommonsMultipartFile receiveFile) throws Exception {
		String fileAbsolutePath = "isNotFile";
		if (!receiveFile.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/uploadFiles/"); // 获取本地存储路径
			if (receiveFile.getSize() / 1024 >= 5 * 102) {
				throw new PanasignUploadImageException("msg:0000001", "图片不能大于500K");
			} else {
			
				String fileName = receiveFile.getOriginalFilename();
				String fileType = fileName.substring(fileName.lastIndexOf("."));
				// 新建一个文件
				String fileAbsoluteName = path + "/" + ControlDataUtils.getSystemTimeToString() + fileType;
				ExcelUtils.isFilePathExist(fileAbsoluteName);
				File file = new File(fileAbsoluteName);
				// 将上传的文件写入新建的文件中
				receiveFile.getFileItem().write(file);
				fileAbsolutePath = file.getAbsolutePath();
				fileAbsolutePath=fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf(
						request.getContextPath().substring(1))
						+request.getContextPath().length());
			}	
		}
		return fileAbsolutePath;
	}
}
