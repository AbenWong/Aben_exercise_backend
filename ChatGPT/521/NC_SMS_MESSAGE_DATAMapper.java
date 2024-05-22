// NC_SMS_MESSAGE_DATAMapper.java
import org.apache.ibatis.annotations.Insert;

public interface NC_SMS_MESSAGE_DATAMapper {
    @Insert("INSERT INTO NC_SMS_MESSAGE_DATA (DELIVERY_DATE_TIME, TEMPLATE_ID, CONTENT_TEXT, DELIVERY_STATUS_CODE, DELIVERY_STATUS_DESCRIPTION, CUSTOMER_IDENTIFICATION_NUMBER, MOBILE_PHONE_NUMBER, SOURCE_SYSTEM_ID, TRACKING_NUMBER) " +
            "VALUES (#{deliveryDateTime}, #{templateId}, #{contentText}, #{deliveryStatusCode}, #{deliveryStatusDescription}, #{customerIdentificationNumber}, #{mobilePhoneNumber}, #{sourceSystemId}, #{trackingNumber})")
    void insertMessageData(NC_SMS_MESSAGE_DATA messageData);
}
