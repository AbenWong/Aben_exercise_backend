// NcSmsFeedbackMapper.java
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NcSmsFeedbackMapper {
    void insert(NcSmsFeedback feedback);
    void updateReloadCount(@Param("reloadCount") int reloadCount, @Param("lastUpdateTime") Timestamp lastUpdateTime, @Param("lastFailReason") String lastFailReason);
}

// NcSmsMessageDataMapper.java
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NcSmsMessageDataMapper {
    void insert(NcSmsMessageData messageData);
}