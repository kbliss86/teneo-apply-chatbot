// CTM GET number of records via email lookup
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

//inputs: sEmail, sPhone
//outputs: nCTMUsersFound, sCTMTempId, sCTMStatus, sHomeRegion

//test values
//sEmail = 'kendall.bliss@api.com';
//sPhone = '55551021432';
//sSystem = 'qa';

//Action Variables
String Email = sEmail;
String Phone = sPhone;

//System Variables
mCTMUsersFound = 0;
String sCredentials = '';
String sBaseURL = '';
String sActionNode1 = "&emailAddress=${Email}&phoneNumber=${Phone}";
String sActionNode2 = "&phoneNumber=${Phone}";



//Set System Variables
if (sSystem == 'production') { sBaseURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearconnect/2_0'} else { sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_test/clearconnect/2_0' };

//Get HCP by Email/Phone
    try {
        def post = new HttpPost(sBaseURL + '/index.cfm/?action=getTempsByPhoneOrEmail&resultType=json' + sActionNode1);
            post.setHeader('Authorization', 'Basic ' + sCredentials.bytes.encodeBase64().toString());
        
        def client = HttpClientBuilder.create().build();
        def response = client.execute(post);
        println response.getStatusLine().getStatusCode();
        sAppacheResponseCode = response.getStatusLine().getStatusCode();
        sAppacheAPIResponse = response.getStatusLine().getReasonPhrase();

        String responseString = new BasicResponseHandler().handleResponse(response);
        println responseString;
        def json = new JsonSlurper().parseText(responseString);

        sAPIResponse = json.message;
        sAPISucess = json.success;

        sCTMTempId = json.TempID[0];
        sMatchedOn = json.MatchedOn[0];
        sAPIjson = json;

        sErrorOnTask = '';
        if (json.success[0] == 0) { sErrorOnTask = 'API Error: ' + json.message[0]} else {sErrorOnTask = ''};

        nCTMUsersFound = 0;
        if (sCTMTempId != null) { nCTMUsersFound = 1; } else { nCTMUsersFound = 0; };

    }
    catch (Exception ex) {
        if(ex.printStackTrace() != null){
            println 'Exception :' + ex;
        sExceptionTrace = ex;
        }
    
    }

    println sCTMTempId;
    println sMatchedOn;
    println nCTMUsersFound;