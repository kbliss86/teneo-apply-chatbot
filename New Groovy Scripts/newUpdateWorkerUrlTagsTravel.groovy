//Update Worker URL Tags

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

//Global Variables (For Testing)
// String sUTMIDT = '244161';
// String sUTMMEDIUMT = 'TestChatbot';
// String sUTMCAMPAIGNT = 'TestPerDiem';
// String sUTMSOURCET = 'TestIndeed';
// String sUTMCONTENTT = 'TestHCPsAreGreate';
// String sUTMTERMT = 'TestJuly24';
// String sUTMCHANNELT = 'TestChatbotPerDiem';
// String sCTMTempIdT = '244161';


//Variables - some hard set, other come from Teneo Studio global variables
String sUTMID = sUTMID
String sUTMMEDIUM = sUTMMEDIUM
String sUTMCAMPAIGN = sUTMCAMPAIGN
String sUTMSOURCE = sUTMSOURCE
String sUTMCONTENT = sUTMCONTENT
String sUTMTERM = sUTMTERM
String sUTMCHANNEL = sUTMCHANNEL
String sCTMTempId = sCTMTempId

//API Common Variables//
String sCredentials = '';
    String encodeScredentials = sCredentials.bytes.encodeBase64().toString();

//Action Node for API Call
def ActionNode = "<tags><AppMethod>Chatbot</AppMethod><AppChannel>Travel</AppChannel><Source>${sUTMSOURCE}</Source><Medium>${sUTMMEDIUM}</Medium><Campaign>${sUTMCAMPAIGN}</Campaign><ID>${sUTMID}</ID><Content>${sUTMCONTENT}</Content><Term>${sUTMTERM}</Term><Channel>${sUTMCHANNEL}</Channel></tags>";

//Set sSystem to qa for testing
// sSystem = 'qa';

//Determin end point for API based on if it is coming from QA or Production Environment
String sURL = '';
if (sSystem == 'production') {sURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearConnect/2_0/'} else {sURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/'};

//Builds and Sends API using appacheHTTPclientBuilder
try {

    def post = new HttpPost(sURL);
        post.setHeader('Authorization', 'Basic ' + encodeScredentials);
        payloadbuilder = MultipartEntityBuilder.create();
            payloadbuilder.addTextBody("action", "updateWorkerUrlTags");
            payloadbuilder.addTextBody("tempId", sCTMTempId);
            payloadbuilder.addTextBody("tags", ActionNode);
            payloadbuilder.addTextBody("resultType", "json");
            post.setEntity(payloadbuilder.build());
        
    def client = HttpClientBuilder.create().build();
    def response = client.execute(post);
    println response.getStatusLine().getStatusCode();
    sAppacheResponseCode = response.getStatusLine().getStatusCode();
    sAppacheAPIResponse = response.getStatusLine().getReasonPhrase();

    //Get the response from the API
    String responseString = new BasicResponseHandler().handleResponse(response);
    def json = new JsonSlurper().parseText(responseString);

    //Set the response variables
    sAPIResponse = json.message;
    sAPISuccess = json.success;
    //Specific API Return Vlue (different for every APU)//
    sAPITempId = json.tempId;
    sAPIjson = json;

        //Different Error For Different API types//
        sErrorOnURLFlagUpdate = '';
        if (json.success[0] = 0) {sErrorOnURLFlagUpdate = json.message} else {sErrorOnURLFlagUpdate = ''};

    } catch(Exception ex){
        if(ex.printStackTrace() != null){
            println 'Exception :'+ ex;
            sExceptionTrace = ex;
        }

    }
