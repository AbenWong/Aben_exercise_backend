// NC_SMS_FEEDBACKMapper.java
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NC_SMS_FEEDBACKMapper {
    @Select("SELECT * FROM NC_SMS_FEEDBACK WHERE CURRENT_STATUS = '0' AND RELOAD_COUNT < 30 ORDER BY DELIVERY_START_DATE DESC")
    List<NC_SMS_FEEDBACK> selectPendingRecords();

    @Insert("INSERT INTO NC_SMS_FEEDBACK (CURRENT_PAGINATE, DELIVERY_START_DATE, DELIVERY_END_DATE, CREATE_TIME, LAST_UPDATE_TIME, RELOAD_COUNT, CURRENT_STATUS, LAST_FAIL_REASON, TOAL_RECORD_CODE, MORE_RECORDS_INDICATOR) " +
            "VALUES (#{currentPaginate}, #{deliveryStartDate}, #{deliveryEndDate}, #{createTime}, #{lastUpdateTime}, #{reloadCount}, #{currentStatus}, #{lastFailReason}, #{totalRecordCode}, #{moreRecordsIndicator})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertFeedback(NC_SMS_FEEDBACK feedback);

    @Update("UPDATE NC_SMS_FEEDBACK SET RELOAD_COUNT = #{reloadCount}, LAST_FAIL_REASON = #{lastFailReason}, LAST_UPDATE_TIME = #{lastUpdateTime}, CURRENT_STATUS = #{currentStatus}, TOAL_RECORD_CODE = #{totalRecordCode}, MORE_RECORDS_INDICATOR = #{moreRecordsIndicator} " +
            "WHERE id = #{id}")
    void updateFeedback(NC_SMS_FEEDBACK feedback);
}
