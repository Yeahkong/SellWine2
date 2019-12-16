package com.hzxy.modules.app.VO;

import com.hzxy.modules.app.entity.Electrics;
import lombok.Data;

import java.util.List;

/**
 * @Auther: 赵晓辉
 * @Date: 2019-10-16 14:47
 * @Description:
 */
@Data
public class FamilyRoomElectricInfoVO {

    private Long familyId;

    private Long roomId;

    private String roomName;

    private String electrics;

    private List<ElectricsVO> electricsVOList;

}
