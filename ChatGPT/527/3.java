import org.apache.ibatis.annotations.*;

@Mapper
public interface FeedbackMapper {
    @Insert("INSERT INTO NC_SMS_FEEDBACK (...) VALUES (...)")
    void insertFeedback(NC_SMS_FEEDBACK feedback);

    @Update("UPDATE NC_SMS_FEEDBACK SET reloadCount = #{reloadCount} WHERE currentPaginate = #{currentPaginate} AND deliveryStartDate = #{deliveryStartDate} AND deliveryEndDate = #{deliveryEndDate}")
    void updateFeedbackReloadCount(@Param("currentPaginate") Integer currentPaginate, @Param("deliveryStartDate") java.sql.Date deliveryStartDate, @Param("deliveryEndDate") java.sql.Date deliveryEndDate, @Param("reloadCount") Integer reloadCount);

    @Update("UPDATE NC_SMS_FEEDBACK SET lastUpdateTime = #{lastUpdateTime}, lastFailReason = #{lastFailReason} WHERE currentPaginate = #{currentPaginate} AND deliveryStartDate = #{deliveryStartDate} AND deliveryEndDate = #{deliveryEndDate}")
    void updateFeedbackFailure(@Param("currentPaginate") Integer currentPaginate, @Param("deliveryStartDate") java.sql.Date deliveryStartDate, @Param("deliveryEndDate") java.sql.Date deliveryEndDate, @Param("lastUpdateTime") Timestamp lastUpdateTime, @Param("lastFailReason") String lastFailReason);
}

@Mapper
public interface MessageDataMapper {
    @Insert("INSERT INTO NC_SMS_MESSAGE_DATA (...) VALUES (...)")
    void insertMessageData(NC_SMS_MESSAGE_DATA messageData);
}
