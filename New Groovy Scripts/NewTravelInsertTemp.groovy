//Insert Travel Temp w/o recruiter - NEW

//import appacheHTTP Client resources and groovy JSON
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.HttpClients;
import groovy.json.*;

String sFirstName = ApplicantFirstName;
String sLastName = ApplicantLastName;
String sEmailAddress = ApplicantEmail;
String sZip = ApplicantZip;
String sCTMCert = ApplicantCTMCert;
String sMobilephone = ApplicantPhone;
String sSpecialty = sApplicantSpecialty;
String sStatus = ApplicantType;
String sHomeRegion = '63';
String sRcode = ApplicantReferral;
String sRecruiter = sApplicantRecruiter;
String sSecondaryCTMCert = sSecondaryCert;

if (sSecondaryCTMCert != null) {sCTMCert = ApplicantCTMCert + ',' + sSecondaryCTMCert} else {sCTMCert = ApplicantCTMCert};

String sStates = 'Primary state ' + ApplicantPrimaryState + ', Secondary state ' + ApplicantSecondaryState + ', Travel state ' + ApplicantTravelState;

String sURL = '';

String sCredentials = '';
	String encodeScredentials = sCredentials.bytes.encodeBase64().toString();
//if statement for action node depending on nUsersFound
if (nUsersFound == 0) {
    def ActionNode = "<tempRecords><tempRecord><firstName>${sFirstName}</firstName><lastName>${sLastName}</lastName><homeRegion>${sHomeRegion}</homeRegion><status>${sStatus}</status><notes>${sStates}</notes><cell_phone>${sMobilephone}</cell_phone><certification>${sCTMCert}</certification><email>${sEmailAddress}</email><zip>${sZip}</zip><specialty>${sSpecialty}</specialty><referralSourceId>${sRcode}</referralSourceId><applicantExperience>applicant</applicantExperience></tempRecord></tempRecords>";
    if (sSystem == 'production') {sURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearConnect/2_0/'} else {sURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/'};

    try {
        
        def post = new HttpPost(sURL);
            post.setHeader('Authorization', 'Basic ' + encodeScredentials);
            payloadbuilder = MultipartEntityBuilder.create();
                payloadbuilder.addTextBody("action", 'insertTempRecords');
                payloadbuilder.addTextBody("tempRecords", ActionNode);
                payloadbuilder.addTextBody("resultType", 'json');
                post.setEntity(payloadbuilder.build());

        def client = HttpClientBuilder.create().build();
        def response = client.execute(post);
        println response.getStatusLine().getStatusCode();	
        sAppacheResponseCode = response.getStatusLine().getStatusCode();
        sAppacheAPIResponse = response.getStatusLine().getReasonPhrase();
        
        //Get the response from the API
        String responseString = new BasicResponseHandler().handleResponse(response);
        def json = new JsonSlurper().parseText(responseString);

        sAPIResponse = json.message;
        sAPISuccess = json.Success;
        //Specific API Return Value (different for every API)//
        sCTMTempId = json.tempId[0];
        sAPIjson = json;
        
            //Different Error For Different API types//
            sErrorOnCreate = '';
            if (json.success[0] = 0) {sErrorOnCreate = json.message} else {sErrorOnCreate = ''};

    } catch(Exception ex){
        if(ex.printStackTrace() != null){
            println 'Exception :'+ ex;
            sExceptionTrace = ex;
        }
    }
    } else { 
    def ActionNode = "<tempRecords><tempRecord><tempId>${sCTMTempId}</tempId><firstName>${sFirstName}</firstName><lastName>${sLastName}</lastName><homeRegion>${sHomeRegion}</homeRegion><status>${sStatus}</status><notes>${sStates}</notes><cell_phone>${sMobilephone}</cell_phone><certification>${sCTMCert}</certification><email>${sEmailAddress}</email><zip>${sZip}</zip><specialty>${sSpecialty}</specialty><referralSourceId>${sRcode}</referralSourceId><applicantExperience>applicant</applicantExperience></tempRecord></tempRecords>";
    if (sSystem == 'production') {sURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearConnect/2_0/'} else {sURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/'};

    try {
        
        def post = new HttpPost(sURL);
            post.setHeader('Authorization', 'Basic ' + encodeScredentials);
            payloadbuilder = MultipartEntityBuilder.create();
                payloadbuilder.addTextBody("action", 'updateTempRecords');
                payloadbuilder.addTextBody("tempRecords", ActionNode);
                payloadbuilder.addTextBody("resultType", 'json');
                post.setEntity(payloadbuilder.build());

        def client = HttpClientBuilder.create().build();
        def response = client.execute(post);
        println response.getStatusLine().getStatusCode();	
        sAppacheResponseCode = response.getStatusLine().getStatusCode();
        sAppacheAPIResponse = response.getStatusLine().getReasonPhrase();
        
        String responseString = new BasicResponseHandler().handleResponse(response);
        def json = new JsonSlurper().parseText(responseString);

        sAPIResponse = json.message;
        sAPISuccess = json.success;
        //Specific API Return Value (different for every API)//
        sCTMTempId = json.tempId[0];
        sAPIjson = json;
        
            //Different Error For Different API types//
            sErrorOnUpdate = '';
            if (json.success[0] = 0) {sErrorOnUpdate = json.message} else {sErrorOnUpdate = ''};

    } catch(Exception ex){
        if(ex.printStackTrace() != null){
            println 'Exception :'+ ex;
            sExceptionTrace = ex;
        }
    }
}

