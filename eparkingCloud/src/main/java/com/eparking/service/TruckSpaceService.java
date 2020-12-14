package com.eparking.service;

import com.common.entity.eparkingCloud.TTruckSpace;
import com.common.entity.PageBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 17:562018-9-14
 * @Modified By:
 */
public interface TruckSpaceService {
    PageBean<TTruckSpace> getTruckSpacebyPage(TTruckSpace tTruckSpace, Integer page, Integer limit);
    List<TTruckSpace> getTruckSpace(TTruckSpace tTruckSpace);
    String UpdateTruckSpace(TTruckSpace tTruckSpace);
    String DeleteTruckSpace(TTruckSpace tTruckSpace);
    String ImportTruckSpace(String fileName, MultipartFile file, Integer parkId, Integer companyId) throws IOException;
    void exportList(TTruckSpace tTruckSpace, String title, HttpServletResponse response);
    TTruckSpace selectByPrimaryKey(Integer id);

}
