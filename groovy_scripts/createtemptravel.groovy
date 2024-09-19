//Create Temp Record API - NEW

//NEED TO CREATE IF STATEMENT FOR UPDATE VS INSERT DEPENDING ON EXISTENCE OF CTMTEMPID

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
// String ApplicantFirstName = 'Kendall';
// String ApplicantLastName = 'isGrape100000';
// String ApplicantEmail = 'kendall@isgrape10000';
// String ApplicantZip = '56220';
// String ApplicantCTMCert = 'CNA';
// String ApplicantPhone = '5075303795';
// String ApplicantType = 'Prospect';
// String ApplicantReferral = '33';
// String sSecondaryCert = '';
// String ApplicantPrimaryState = 'IA';
// String sSecondaryState = 'MN';
// String ApplicantTravelState = '';

//Variables - some hard set, other come from Teneo Studio global variables
String sFirstName = ApplicantFirstName;
String sLastName = ApplicantLastName;
String sEmailAddress = ApplicantEmail;
String sZip = ApplicantZip;
String sCTMCert = ApplicantCTMCert;
String sMobilephone = ApplicantPhone;
String sSpecialty = 'Unknown';
String sStatus = ApplicantType;
String sHomeRegion = '63';
String sRcode = ApplicantReferral;
String sSecondaryCTMCert = sSecondaryCert;
String sApplicantExperience = 'applicant'

//builds the "states" not for the HCP Profile
String sStates = 'Primary Sate ' + ApplicantPrimaryState + ', Secondary State ' + sSecondaryState + ', Travel State ' + ApplicantTravelState;

if (sSecondaryCTMCert != null) { sCTMCert = ApplicantCTMCert + ',' + sSecondaryCTMCert} else {sCTMCert = ApplicantCTMCert};


//API Common Variables//
String sCredentials = '';
	String encodeScredentials = sCredentials.bytes.encodeBase64().toString();

  def ActionNode = "<tempRecords><tempRecord><firstName>${sFirstName}</firstName><lastName>${sLastName}</lastName><homeRegion>${sHomeRegion}</homeRegion><status>${sStatus}</status><notes>${sStates}</notes><cell_phone>${sMobilephone}</cell_phone><certification>${sCTMCert}</certification><email>${sEmailAddress}</email><zip>${sZip}</zip><specialty>${sSpecialty}</specialty><referralSourceId>${sRcode}</referralSourceId><applicantExperience>${sApplicantExperience}</applicantExperience></tempRecord></tempRecords>";


// Set sSystem to qa for testing
// sSystem = 'qa';

//determines end point of API based on if it coming from a QA or Production Environment
String sURL = '';
if (sSystem == 'production') {sURL = 'https://ctms.contingenttalentmanagement.com/grapetree/clearConnect/2_0/'} else {sURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/clearConnect/2_0/'};


//builds and sends API using appacheHTTPclientBuilder
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
