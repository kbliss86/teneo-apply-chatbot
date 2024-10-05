//Insert URL Flag Notes API Travel - NEW

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
// String sURLFlagT = 'TEST';
// String sSessionIDT = 'ABC123';
// String sCTMTempId = '244161';

//Custom Variables//
String sURLFlag = sURLFlag;
String sSessionID = sSessionID;
//Multiple notes can be sent in a single api call
//API Note Variables #1//
String sNoteTypeID1 = '5201';
String sNoteTypeName1 = 'Marketing String';
String sNoteValue1 = sURLFlag;
//API Note Variables #2//
String sNoteTypeID2 = '5195';
String sNoteTypeName2 = 'Chatbot Session ID';
String sNoteValue2 = sSessionID;
//API Note Variables #3//
String sNoteTypeID3 = '5192';
String sNoteTypeName3 = 'AppMethod';
String sNoteValue3 = 'Chatbot';
//API Note Variables #4//
String sNoteTypeID4 = '5193';
String sNoteTypeName4 = 'AppChannel';
String sNoteValue4 = 'Travel';


//API Common Variables//
String sCredentials = '';
	String encodeScredentials = sCredentials.bytes.encodeBase64().toString();

//API Action Variable
def ActionNode = "<noteRecords><noteRecord><tempId>${sCTMTempId}</tempId><noteTypeId>${sNoteTypeID1}</noteTypeId><noteType>${sNoteTypeName1}</noteType><note>${sNoteValue1}</note></noteRecord><noteRecord><tempId>${sCTMTempId}</tempId><noteTypeId>${sNoteTypeID2}</noteTypeId><noteType>${sNoteTypeName2}</noteType><note>${sNoteValue2}</note></noteRecord><noteRecord><tempId>${sCTMTempId}</tempId><noteTypeId>${sNoteTypeID3}</noteTypeId><noteType>${sNoteTypeName3}</noteType><note>${sNoteValue3}</note></noteRecord><noteRecord><tempId>${sCTMTempId}</tempId><noteTypeId>${sNoteTypeID4}</noteTypeId><noteType>${sNoteTypeName4}</noteType><note>${sNoteValue4}</note></noteRecord></noteRecords>";

//Get UTMs API URL
String getUTMsURL = "https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/?action=getUsers&userIdIn=729&resultType=json"
String sStatus = '';

//Set sSystem to qa for testing
// sSystem = 'qa';

//determines end point of API based on if it coming from a QA or Production Environment
String sURL = '';
if (sSystem == 'production') {sURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearConnect/2_0/'} else {sURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/'};

//Get UTMs
	try{
		def get = new HttpGet(getUTMsURL);
		get.setHeader('Authorization', 'Basic ' + encodeScredentials);

		def client = HttpClientBuilder.create().build();
		def response = client.execute(get);
		println response.getStatusLine().getStatusCode();

		String responseString = new BasicResponseHandler().handleResponse(response);
		def json = new JsonSlurper().parseText(responseString);
		println json;

		//UTM status
		sStatus = json.status[0];

	}
	catch(Exception ex){
			if(ex.printStackTrace() != null){
				println 'Exception :'+ ex;
				sExceptionTrace = ex;
			}
		}

//Evaluate UTM Status
if (sStatus != null) {
	if (sStatus == 'active') {
		println 'UTMs Success';

		//builds and sends API using appacheHTTPclientBuilder
		try {
			
			def post = new HttpPost(sURL);
				post.setHeader('Authorization', 'Basic ' + encodeScredentials);
				payloadbuilder = MultipartEntityBuilder.create();
					payloadbuilder.addTextBody("action", 'insertNotes');
					payloadbuilder.addTextBody("noteRecords", ActionNode);
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
			sAPINoteId = json.noteId;
			sAPIjson = json;
			
				//Different Error For Different API types//
				sErrorOnTask = '';
				if (json.success[0] = 0) {sErrorOnTask = json.message} else {sErrorOnTask = ''};

		} catch(Exception ex){
			if(ex.printStackTrace() != null){
				println 'Exception :'+ ex;
				sExceptionTrace = ex;
			}
		}
	} else {
		println 'There was en error retreiving the UTMs Status Code ID:10:T';
	}
} else {
	println 'UTMs did not return a value Err Status Code M0:0Re:On';
}
// println sStatus;
// println sAPIResponse;