package com.bysj.stsys.config;




public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000117638572";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC9X9XIANwovY+uwYRhxg8tiBzYQQxRYn5xXu6uzjaFxw3yRt8UqSeBZ/rWEtoI/gHeQwoPJ3a5uQzxAG61o6HfaKQqbuL1ZuFQ0Zeisq8Y2mpezrb6zCzBItfgyJ/kX6I8Qw1kUVfieckiT1Ok/aVMEMFcmpZSvDGivJFR/hZrs4+ItIcyVZ74p70SKKUBq/D6kY2DFCvRT99dlRpnTuyA8ER/Oa6y2JmVVCOmc4CNKk3NbfCV0WrAsgo5XbbLBhL6FjhPkAqGM6OSmybCbE2kTLsCmqxY9RtPX9+blRh04hGryd5bsY6Vnj14ZdB0pew1FofFGZSOhMGQPC/E5ojhAgMBAAECggEAPkgwlTDDN5rwkHcQxN9QypbDddQe6v1LvJmOQmuLvv+ydIYehDsFxSefdZyJnP99Oc4ulsFR6M8VlIWh45dHe6R8qYACoNcPL3dfOaEuuSvMZBX0VBOmfHarLUCOZZuWTd93nhCqqM+I+QHB7reFD7lK/tvQqLFAWddOq/q3AQBiVcTLdQxwF6DMLW2uNEDhhHXkn2iZMZUWLNo/t0zncNdGoJUKV/qyHUDMxoSbcL7hwG3hbz7uoYPWe2OeNRSBDr5Zuuzl78DrCqPhDLutGc0iolLYVEv7+7tDj2qjMwFICo8fAKIxkgN0G9Pp6JnWS2GJg8lB/g2mNVhenRQtgQKBgQDdAZzw+Z81JLkotxtb+0ly+LUJVwZlkA51RSuDyTQ2sZYql3ZeP5JvB6ksyIER78lQnDzHfcZ3u41rtD0iuEvitVOrWbcpTmRS6ULupNMJW+GClFU89cX0yOBBEpfFKkTFG6H05asu0ICu/qK7ON9aREkncI5Qg5HYdukrJ/DS2QKBgQDbXAkzbeZP71V2L16TqWXF5USqfv1CD2RWDCZztEJ9ZYSgsFkfH9AbNW78AYnLgHE7JsrB5FNIVxb7+oUQWOmr91P4AXq1H08ajfHpxEYg8VUx7bIIL2t7qgUmi0qXO6S+2M51YHEZhq1Q6b1QU/v7m+gKrijm4vmC2UCJpMoRSQKBgQDDRBmWD/dKzReLkbzqmCtdGcFabzmHorAjjOnVloA7ksxK9Wfv/dsznmEq4DypzGLihihtWgNw5pUdBDK/6nWh6PP0AWCDbm/VkaNBlv+e1T96T/S/16b/kDwTQoo1+hKo1LFT31hQFIieFP1Szf0JlCvOXPRgzt6dkvKQ3sk4gQKBgQDG4OjOogM/0cPwBP6AbgMBqt3Yno3LCN11TkrGeFYYDqRyc7A5kcRCBu36uQJPaMJAWOlXZUVEvwDtr7JYoeQpr3GH1v+Dm0FC2BLOWM8kKumM/O5+kU+nrPxCd+NGviKdjT+SF8Ay3nHgfCrR23MIKc/1bvVSb9K8GXBz2t4BUQKBgCJLHKJRU4QiPWvqu9sK5FaemnLHzWGMILsU87rEHB1xPHLThIdVX3RmO8iKBMHA+g1xTeqLGORYuL+nxgQTGndKSdoybhYcymNdoTq4t1rcCvFbhD/mCl1J4Uwlfp9iDoSvx3rNOlC1eIrddO2rsmkBMQJU0npMvXReVgaLmHwY";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvuJQG9w8oOrNAzh3ZzVtzR5CQoxCUh0Cm7SHRAUY0XhNblYBGwRSLdAqQeyP5XvY57l+ROZyr4dE9xmEmBi49uyA85hQ1sY0K8V+eAOC7MMKyl4RbztCpAGfVRXi0RhEnjhVzI8x3rvqKiKgeyKrrDGhDEbn6xNTt4FB3SjJC9XNXDnauT3GOLrXKo3NHS6vOaJVfeg+39m1KE9ms13wG/A2LIiIHD+5WMHec4e2Dfj/mfp6ekmP679s6EgXu+n6bRP+l+mPnPuC4xxQhFN+VhVDlH2ZubnAskftmarQOna5ni+8prnLBTkKyAaOS1Lqbez89LJoafRZ1mSgIsDb2wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://www.zhihu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";
    // 字符编码格式
    public static String FORMAT = "json";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";




//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */

}

