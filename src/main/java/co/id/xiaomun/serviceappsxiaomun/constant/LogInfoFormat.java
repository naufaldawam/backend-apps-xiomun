package co.id.xiaomun.serviceappsxiaomun.constant;


import org.springframework.stereotype.Component;

@Component
public class LogInfoFormat {
    public String setLogInfo(String nameService, String stringReq, String stringRes, String stringUrl, String stringHeader, String stringQueryParam ){
      
      String log = "\n=========================================================\n\n" +
                    "Service Name \t :"  + nameService + "\n" +
                    "url \t \t :" + stringUrl  + "\n" +
                    "header \t \t :" + stringHeader  + "\n" +
                    "query param \t :" + stringQueryParam  + "\n" +
                    "request \t :"  + stringReq + "\n" +
                    "response \t :" + stringRes  + "\n" +
                    "===============================================================";

        return log;
    }
}