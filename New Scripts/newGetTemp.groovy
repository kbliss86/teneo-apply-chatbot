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

//global inputs sSystem sCTMTempId
//outputs sCTMStatus, sHomeRegion

//Test Values
// sSystem = 'qa';
// sCTMTempId = '123456';


//Action Variables
String TempId = sCTMTempId;

//System Variables
String sCredentials = '';
String sBaseURL = '';
String sActionNode = "&tempIdIn=${TempId}";

//Set System Variables
if (sSystem == 'production') { sBaseURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearconnect/2_0'} else { sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_test/clearconnect/2_0' };

//Get Temp Info
    try {
        def post = new HttpPost(sBaseURL + '/index.cfm/?action=getTemps&resultType=json' + sActionNode);
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

        sCTMTempId = json.TempId[0];
        sHomeRegion = json.homeRegion[0];
        sCTMStatus = json.status[0];
        sAPIjson = json;

        sErrorOnTask = '';
        if (json.success[0] == 0) { sErrorOnTask = 'API Error: ' + json.message[0]} else {sErrorOnTask = ''};

    }
    catch (Exception ex) {
        if(ex.printStackTrace() != null){
            println 'Exception :' + ex;
        sExceptionTrace = ex;
        }
    
    }
    println sAPISucess;
    println sCTMTempId;
    println sHomeRegion;
    println sCTMStatus;