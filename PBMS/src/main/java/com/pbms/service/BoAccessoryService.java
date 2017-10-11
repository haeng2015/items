package com.pbms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.pbms.pojo.BoAccessory;
import com.pbms.vo.AccessoryVO;
import com.pbms.vo.UserVO;

/**
 * 
 * @copyright：柏年软件
 * @author： 吴樑
 */
public interface BoAccessoryService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月10日
     * @function：TODO bo转vo
     * @param boAccessory
     * @return
     */
    public AccessoryVO boToVo(BoAccessory boAccessory);
    
    /**
     * @author：He.hp
     * @date：2017年3月7日
     * @function：TODO 为竞价表/报价表添加更新附件(多个)
     * @param boBidBidding
     * @param boAttachments
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public Integer addAndUpdateAttachmentsForObject(UserVO userVO, String sign, Object bidObj,
	    HttpServletRequest request, Map<String, MultipartFile> fileMap) throws IllegalStateException, IOException;
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 根据竞价获取所有附件
     * @param boBidBidding
     * @return
     */
    public List<BoAccessory> getAttachmentsByObject(Object bidObj);
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 根据附件id获取附件
     * @param boAttachmentId
     * @return
     */
    public BoAccessory findBoAttachmentById(Integer id);
    
    /**
     * @author：He.hp
     * @date：2017年3月15日
     * @function：TODO 根据id下载竞价、报价文件
     * @param attId
     * @param request
     * @param response
     * @return
     */
    public HttpServletResponse uploadAttachmentById(Integer id, HttpServletRequest request,
	    HttpServletResponse response);
    
    /**
     * @author：He.hp
     * @date：2017年3月17日
     * @function：TODO 根据id删除附件
     * @param boBidQuote
     * @return
     */
    public Integer deleteAttachmentById(Integer id);
    
    /**
     * @author：He.hp
     * @date：2017年3月17日
     * @function：TODO 根据对象BoBidQuote，删除其下的所有附件附件
     * @param boBidQuote
     * @return
     */
    public Integer deleteAttachmentByObject(Object bidObj);
    
}
