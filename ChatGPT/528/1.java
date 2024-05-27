import org.apache.ibatis.annotations.*;

@Mapper
public interface FeedbackMapper {
    @Select("SELECT * FROM NC_SMS_FEEDBACK WHERE CURRENT_STATUS = '0'")
    List<NC_SMS_FEEDBACK> findFailedFeedbacks();

    @Insert("INSERT INTO NC_SMS_FEEDBACK (CURRENT_PAGINATE, DELIVERY_START_DATE, DELIVERY_END_DATE, CREATE_TIME, LAST_UPDATE_TIME, RELOAD_COUNT, CURRENT_STATUS, LAST_FAIL_REASON, TOAL_RECORD_CODE, MORE_RECORDS_INDICATOR) VALUES (#{currentPaginate}, #{deliveryStartDate}, #{deliveryEndDate}, #{createTime}, #{lastUpdateTime}, #{reloadCount}, #{currentStatus}, #{lastFailReason}, #{totalRecordCode}, #{moreRecordsIndicator})")
    void insertFeedback(NC_SMS_FEEDBACK feedback);

    @Update("UPDATE NC_SMS_FEEDBACK SET RELOAD_COUNT = #{reloadCount} WHERE CURRENT_PAGINATE = #{currentPaginate} AND DELIVERY_START_DATE = #{deliveryStartDate} AND DELIVERY_END_DATE = #{deliveryEndDate}")
    void updateFeedbackReloadCount(@Param("currentPaginate") Integer currentPaginate, @Param("deliveryStartDate") Date deliveryStartDate, @Param("deliveryEndDate") Date deliveryEndDate, @Param("reloadCount") Integer reloadCount);

    @Update("UPDATE NC_SMS_FEEDBACK SET LAST_UPDATE_TIME = #{lastUpdateTime}, LAST_FAIL_REASON = #{lastFailReason} WHERE CURRENT_PAGINATE = #{currentPaginate} AND DELIVERY_START_DATE = #{deliveryStartDate} AND DELIVERY_END_DATE = #{deliveryEndDate}")
    void updateFeedbackFailure(@Param("currentPaginate") Integer currentPaginate, @Param("deliveryStartDate") Date deliveryStartDate, @Param("deliveryEndDate") Date deliveryEndDate, @Param("lastUpdateTime") Timestamp lastUpdateTime, @Param("lastFailReason") String lastFailReason);
}

@Mapper
public interface MessageDataMapper {
    @Insert("INSERT INTO NC_SMS_MESSAGE_DATA (DELIVERY_DATE_TIME, TEMPLATE_ID, TEMPLATE_IDCONTENT_TEXT, DELIVER_STATUS_CODE, DELIVERY_STATUS_DESCRIPTION, CUSTOMER_IDENTIFICATION_NUMBER, MOBILE_PHONE_NUMBER, SOURCE_SYSTEM_ID, TRACKING_NUMBER) VALUES (#{deliveryDateTime}, #{templateId}, #{templateIdContentText}, #{deliverStatusCode}, #{deliveryStatusDescription}, #{customerIdentificationNumber}, #{mobilePhoneNumber}, #{sourceSystemId}, #{trackingNumber})")
    void insertMessageData(NC_SMS_MESSAGE_DATA messageData);
}
