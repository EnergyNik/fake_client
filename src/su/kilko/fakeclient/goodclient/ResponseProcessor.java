package su.kilko.fakeclient.goodclient;

/**
 * Created by Kosilov Nikita on 15.09.2015.
 */
public class ResponseProcessor {
    public Object getApplicationsRequest(Object response)throws Exception{
        Object applicationsRequest = response;
        if(applicationsRequest == null){
            applicationsRequest = "Client disconnected";
        }


        return applicationsRequest;
    }


}
