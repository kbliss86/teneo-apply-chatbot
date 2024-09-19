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

//Custom Variables//
String sFreeTextNote = sTravelFreeText;
String sTravelState = ApplicantPrimaryState;
def sTravelNote = "The Applicant is licenesed in ${sTravelState}\r\nApplicant Comments: ${stravelFreeText}"
//Multiple notes can be sent in a single api call
//API Note Variables #1//
String sNoteTypeID1 = '120';
String sNoteTypeName1 = 'Travel';
String sNoteValue1 = sTravelNote;

//API Common Variables//
String sCredentials = '';
	String encodeScredentials = sCredentials.bytes.encodeBase64().toString();

//API Action Variable
def ActionNode = "<noteRecords><noteRecord><tempId>${sCTMTempId}</tempId><noteTypeId>${sNoteTypeID1}</noteTypeId><noteType>${sNoteTypeName1}</noteType><note>${sNoteValue1}</note></noteRecord></noteRecords>";

//determines end point of API based on if it coming from a QA or Production Environment
String sURL = '';
if (sSystem == 'production') {sURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearConnect/2_0/'} else {sURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/'};


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
