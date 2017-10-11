package com.pbms.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoAccessory;
import com.pbms.pojo.BoBuilding;
import com.pbms.pojo.BoOwner;
import com.pbms.pojo.BoRoom;
import com.pbms.pojo.BoUser;
import com.pbms.service.BoAccessoryService;
import com.pbms.service.BoUserService;
import com.pbms.util.DateTimeUtils;
import com.pbms.vo.AccessoryVO;
import com.pbms.vo.UserVO;

/**
 * 
 * @copyright：柏年软件
 * @author： 吴樑
 */
@Service("boAccessoryService")
public class BoAccessoryServiceImpl implements BoAccessoryService {
    public static final String SOURCE_DIR = "source/";
    // xml文件中的namespac值
    public static final String NAMESPACE = "boAccessoryMapper";
    @Resource
    private BaseDao<BoAccessory> baseDao;
    private BoUserService boUserService;
    
    public BaseDao<BoAccessory> getBaseDao() {
	return baseDao;
    }
    
    public void setBaseDao(BaseDao<BoAccessory> baseDao) {
	this.baseDao = baseDao;
    }
    
    public BoUserService getBoUserService() {
	return boUserService;
    }
    
    @Resource
    public void setBoUserService(BoUserService boUserService) {
	this.boUserService = boUserService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月10日
     * @function：TODO bo转vo
     * @param boAccessory
     * @return
     */
    @Override
    public AccessoryVO boToVo(BoAccessory boAccessory) {
	AccessoryVO accessoryVO = new AccessoryVO();
	if (boAccessory != null && !"".equals(boAccessory)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(boAccessory, accessoryVO);
	    accessoryVO.setTime(DateTimeUtils.DateToString(boAccessory.getDate()));
	    return accessoryVO;
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月6日
     * @function：TODO 添加、更新附件(多个文件)
     * @param boBidBidding
     * @param loginUser
     * @param request
     * @param multipartFiles
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @Override
    public Integer addAndUpdateAttachmentsForObject(UserVO userVO, String type, Object obj, HttpServletRequest request,
	    Map<String, MultipartFile> fileMap) throws IllegalStateException, IOException {
	Integer result = 0;
	try {
	    if (obj != null && !"".equals(obj) && userVO != null && !"".equals(userVO)) {
		// 附件上传者
		BoUser creator = this.boUserService.findBoUserById(userVO.getUserId());
		
		Iterator<Entry<String, MultipartFile>> fileIterator = fileMap.entrySet().iterator();
		while (fileIterator.hasNext()) { // 循环添加多个附件对象
		    BoAccessory boAccessory = new BoAccessory();
		    boAccessory.setCreator(creator.getUserId());
		    boAccessory.setDate(new Timestamp(new Date().getTime()));
		    // 附件所属对象的标示
		    boAccessory.setType(type);
		    // 附件所属的对象
		    if (BoAccessory.USER_SIGN.equals(type)) {
			boAccessory.setUser((BoUser) obj);
		    } else if (BoAccessory.BUILDING_SIGN.equals(type)) {
			boAccessory.setBuilding((BoBuilding) obj);
		    } else if (BoAccessory.ROOM_SIGN.equals(type)) {
			boAccessory.setRoom((BoRoom) obj);
		    } else if (BoAccessory.OWNER_SIGN.equals(type)) {
			boAccessory.setOwner((BoOwner) obj);
		    }
		    
		    // 文件保存
		    Entry<String, MultipartFile> entry = (Entry<String, MultipartFile>) fileIterator.next();
		    // String fileKey = (String) entry.getKey();
		    MultipartFile fileValue = (MultipartFile) entry.getValue();
		    if (fileValue.getSize() > 0 && !fileValue.isEmpty()) { // 移除空文件的字段
			// 文件上传时的名字
			boAccessory.setName(fileValue.getOriginalFilename());
			boAccessory.setSize(fileValue.getSize());
			// 系统中随机的UUID名字，这是实际存放的文件名
			boAccessory.setSign(UUID.randomUUID().toString().replace("-", "")
				+ boAccessory.getName().substring(boAccessory.getName().lastIndexOf("."),
					boAccessory.getName().length()));
			
			boAccessory.setUrl("/" + SOURCE_DIR + boAccessory.getSign());
			
			// 文件要保存的实际路径
			// /build/saveOrUpdateBuilding.dosource/b48442ad42924f25a8e50e1648796769.jpg
			// String path2 = new String(request.getServletPath() +
			// SOURCE_DIR + boAccessory.getSign());
			// G:\projects\webProjets\.metadata\.me_tcat7\webapps\PBMS\source/b48442ad42924f25a8e50e1648796769.jpg
			String path = new String(request.getSession().getServletContext().getRealPath("/") + SOURCE_DIR
				+ boAccessory.getSign());
			// 保存上传的文件
			fileValue.transferTo(new File(path));
			// 保存数据库对象
			this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
			result = this.baseDao.insertSelective(boAccessory);
		    } else {
			continue;
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 根据竞价id获取所有附件(提取附件id和文件名)
     * @param boBidBidding
     * @return
     */
    @Override
    public List<BoAccessory> getAttachmentsByObject(Object bidObj) {
	if (bidObj != null && !"".equals(bidObj)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.selectFks(bidObj);
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 根据附件id获取附件（下载）
     * @param boAttachmentId
     * @return
     */
    @Override
    public BoAccessory findBoAttachmentById(Integer id) {
	if (id != null) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.get(id);
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月15日
     * @function：TODO 根据id下载竞价、报价文件
     * @param attId
     * @param request
     * @param response
     * @return
     */
    @Override
    public HttpServletResponse uploadAttachmentById(Integer attId, HttpServletRequest request,
	    HttpServletResponse response) {
	BoAccessory boAccessory = null;
	InputStream in = null;
	OutputStream out = null;
	try {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    UserVO loginUser = (UserVO) request.getSession().getAttribute("userVO");
	    if (loginUser != null && !"".equals(loginUser)) {
		if (attId != 0 && !"".equals(attId)) {
		    boAccessory = this.findBoAttachmentById(attId);
		}
		// 得到下载文件的路径。
		String filePath = request.getSession().getServletContext().getRealPath("") + boAccessory.getUrl();
		
		File file = new File(filePath);
		if (!file.exists()) {
		    response.sendError(404, "File not found!");
		    return null;
		}
		
		String filename = boAccessory.getName(); // 文件名
		
		// 以流的形式下载文件。
		in = new BufferedInputStream(new FileInputStream(filePath));
		
		// 清空response
		response.reset();
		// 设置response的头部信息Header
		String filenameString = new String(filename.getBytes("gbk"), "iso-8859-1");
		response.setCharacterEncoding("UTF-8");
		// response.setContentType("multipart/form-data");
		response.addHeader("Content-Disposition", "attachment;filename=" + filenameString);
		response.addHeader("Content-Length", "" + file.length());
		out = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream; charset=utf-8");
		
		// 写入文件流
		byte[] b = new byte[1024];
		int length;
		while ((length = in.read(b)) > 0) {
		    out.write(b, 0, length);
		}
		// byte[] buffer = new byte[in.available()];
		// in.read(buffer);
		// out.write(buffer);
		out.flush();
		return response;
	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	} finally {
	    if (out != null) {
		try {
		    out.close();
		} catch (IOException e) {
		    e.getMessage();
		}
	    }
	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.getMessage();
		}
	    }
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月17日
     * @function：TODO 根据id删除附件
     * @param boBidQuote
     * @return
     */
    @Override
    public Integer deleteAttachmentById(Integer id) {
	int result = 0;
	if (id != null && !"".equals(id)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    result = this.baseDao.delete(id);
	}
	return result;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月17日
     * @function：TODO 根据外键对象，删除其下的所有附件
     * @param boBidQuote
     * @return
     */
    @Override
    public Integer deleteAttachmentByObject(Object bidObj) {
	int result = 0;
	if (bidObj != null && !"".equals(bidObj)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    result = this.baseDao.deleteFK(bidObj);
	}
	return result;
    }
    
}
