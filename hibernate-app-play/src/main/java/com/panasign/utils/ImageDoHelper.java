package com.panasign.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.IOUtils;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageDoHelper
{

	public File loadLocalFile(String fileName)
	{
		File file = new File(fileName);
		if (file.exists())
		{
			return file;
		} else
		{
			return null;
		}
	}

	/**
	 *将文件转换为字节数组
	 * 
	 * @param File file
	 * @return byte[]
	 */
	public byte[] file2Byte(File file) throws Exception
	{

		FileInputStream is = null;
		ByteArrayOutputStream os = null;
		try
		{
			is = new FileInputStream(file);
			os = new ByteArrayOutputStream();
			int ch;
			while ((ch = is.read()) != -1)
			{
				os.write(ch);
			}
			byte[] imgdata = os.toByteArray();

			return imgdata;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			try
			{
				is.close();
				os.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}

	}

	/**
	 *将文件按照指定的尺寸进行缩放
	 * 
	 * @param Object input(File InputStream), int width, int height
	 * @return InputStream
	 */
	public InputStream imageScaleStream(Object input, int width, int height) throws IOException
	{
		BufferedImage bi;
		if (input instanceof InputStream)
		{
			bi = ImageIO.read((InputStream) input);

		} else
		{
			bi = ImageIO.read((File) input);
		}

		// 图片实际宽
		int oldWidth = bi.getWidth();
		// 图片实际高
		int oldHeight = bi.getHeight();
		// 缩放比例，原图任何一边大于显示区域则进行比例计算，只考虑缩小，不考虑放大
		double ratio = 1;
		if (oldWidth > width || oldHeight > height)
		{
			if (oldWidth > oldHeight)
			{
				ratio = (new Integer(width)).doubleValue() / oldWidth;
			} else
			{
				ratio = (new Integer(height)).doubleValue() / oldHeight;
			}

		}
		// 计算缩放尺寸
		int widthScale = parseDoubleToInt(oldWidth * ratio);
		int heightScale = parseDoubleToInt(oldHeight * ratio);

		java.awt.Image image = bi.getScaledInstance(widthScale, heightScale, java.awt.Image.SCALE_SMOOTH);
		BufferedImage outputImage = new BufferedImage(widthScale, heightScale, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = outputImage.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		// 获取实际文件格式
		String format = getFileFormat(input);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(outputImage, format, out);

		return new ByteArrayInputStream(out.toByteArray());
	}

	/**
	 *将文件按照指定的尺寸进行缩放
	 * 
	 * @param Object input(File InputStream), int width, int height
	 * @return byte[]
	 */
	public byte[] imageScaleByte(Object input, int width, int height) throws IOException
	{
		BufferedImage bi;
		if (input instanceof InputStream)
		{
			bi = ImageIO.read((InputStream) input);

		} else
		{
			bi = ImageIO.read((File) input);
		}

		// 图片实际宽
		int oldWidth = bi.getWidth();
		// 图片实际高
		int oldHeight = bi.getHeight();

		// 计算缩放尺寸
		double ratio = this.getRatio(oldWidth, oldHeight, width, height);
		if (ratio != 1)// 进行比例缩放
		{
			int widthScale = parseDoubleToInt(oldWidth * ratio);
			int heightScale = parseDoubleToInt(oldHeight * ratio);

			java.awt.Image image = bi.getScaledInstance(widthScale, heightScale, java.awt.Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(widthScale, heightScale, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			// 获取实际文件格式
			String format = getFileFormat(input);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(outputImage, format, out);

			return out.toByteArray();
		} else
		// 按原图显示
		{
			if (input instanceof InputStream)
			{
				return IOUtils.toByteArray((InputStream) input);

			} else
			{
				return IOUtils.toByteArray(new FileInputStream((File) input));
			}
		}
	}

	private double getRatio(int oldWidth, int oldHeight, int viewWidth, int viewHeight)
	{

		double ratio = 1;
		if (oldWidth > viewWidth || oldHeight > viewHeight)
		{// 只要宽高有一边比显示宽高要大，就进入
			if (oldWidth > oldHeight)
			{
				ratio = (new Integer(viewWidth)).doubleValue() / oldWidth;
				if (ratio >= 1)
				{// 说明显示长度比实际的要大，改用另一边的
					ratio = (new Integer(viewHeight)).doubleValue() / oldHeight;
				}
			} else
			{
				ratio = (new Integer(viewHeight)).doubleValue() / oldHeight;

				if (ratio >= 1)
				{// 说明显示长度比实际的要大，改用另一边的
					ratio = (new Integer(viewWidth)).doubleValue() / oldWidth;
				}
			}

			// 计算缩放尺寸
			int widthScale = parseDoubleToInt(oldWidth * ratio);
			int heightScale = parseDoubleToInt(oldHeight * ratio);
			// 将计算出来的新显示高宽作为实际高宽再迭代计算，直到最后一次返回1为止
			double subRatio = this.getRatio(widthScale, heightScale, viewWidth, viewHeight);
			return ratio * subRatio;
		} else
		{
			return ratio;
		}

	}

	public String getFileFormat(Object input)
	{
		String format = "jpg";
		ImageInputStream iis = null;
		try
		{
			iis = ImageIO.createImageInputStream(input);

			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (iter.hasNext())
			{
				ImageReader reader = iter.next();
				format = reader.getFormatName();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (iis != null)
				{
					iis.close();
				}
			} catch (IOException e)
			{

			}
		}

		return format;
	}

	/**
	 *输入流保存图片
	 * 
	 * @param InputStream
	 * @return
	 */
	public void saveImageScale(String fileName, InputStream inputStream) throws IOException
	{
		FileOutputStream out = null;
		try
		{
			// 如果目录不存在，创建相应目录
			String path = fileName.substring(0, fileName.lastIndexOf("/"));
			File filePath = new File(path);
			if (!filePath.exists())
			{
				filePath.mkdirs();
			}

			out = new FileOutputStream(new File(fileName));

			byte[] bs = new byte[1024]; // 读取缓冲区
			int len;
			while ((len = inputStream.read(bs)) != -1)
			{ // 循环读取
				out.write(bs, 0, len); // 写入到输出流
			}

		} catch (IOException e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (out != null)
			{
				out.close();
			}
		}
	}

	/**
	 *字节数组保存图片
	 * 
	 * @param InputStream
	 * @return
	 */
	public void saveImageScale(String fileName, byte[] imageArray) throws Exception
	{

		InputStream inputStream = null;
		try
		{

			inputStream = new ByteArrayInputStream(imageArray);
			this.saveImageScale(fileName, inputStream);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			if (inputStream != null)
			{
				inputStream.close();
			}

		}

	}

	/**
	 * 将double类型的数据转换为int，四舍五入原则
	 * 
	 * @param sourceDouble
	 * @return
	 */
	private static int parseDoubleToInt(double sourceDouble)
	{
		int result = 0;
		result = (int) sourceDouble;
		return result;
	}

	/**
	 * 通过 com.sun.image.codec.jpeg.JPEGCodec提供的编码器来实现图像压缩,jpeg格式
	 * 
	 * @des 未正式启用,只作为一种功能实现参考，如有需要稍做修改即可
	 * @param image
	 * @param quality
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused" })
	private byte[] compressJpeg(Object input, float quality) throws IOException
	{

		BufferedImage bi;
		if (input instanceof InputStream)
		{
			bi = ImageIO.read((InputStream) input);

		} else
		{
			bi = ImageIO.read((File) input);
		}
		// 开始开始，写入byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
		
		//以下为jdk6及以下版本的方式
		// 设置压缩参数
//		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(bi);
//		param.setQuality(quality, false);
//		// 设置编码器
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream, param);
//		try
//		{
//			encoder.encode(bi);
//		} catch (Exception ef)
//		{
//			ef.printStackTrace();
//		}
		
		//以下为jdk7以上方式
		ImageIO.write(bi, "jpg", byteArrayOutputStream);
		
		
		return byteArrayOutputStream.toByteArray();

	}

	public static void main(String args[])
	{
		testImageScale();
	}

	private static void testImageScale()
	{
		ImageDoHelper imageDoHelper = new ImageDoHelper();
		File file = imageDoHelper.loadLocalFile("f:/star_level2.gif");
		try
		{

			byte[] bb = imageDoHelper.imageScaleByte(file, 200, 200);
			imageDoHelper.saveImageScale("f:/day.gif", bb);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void testFile()
	{
		File file = new File("f:/ajb/add/kacha");
		System.out.println(file.isDirectory() + " " + file.isFile());
		file.mkdirs();
	}
}
