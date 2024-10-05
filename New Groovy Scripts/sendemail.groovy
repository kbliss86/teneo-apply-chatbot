//Send Email API - sends an email from chatbot utilizing Power Automate connector

//import appacheHTTP Client resources and groovy JSON
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.util.EntityUtils;
import groovy.json.*;

//email components variables
def emailTo = sEmailTo;
def emailSubject = sEmailSubject;
def emailBody = sEmailBody;
// + ": " + ApplicantName + ", " +  ApplicantEmail + ", " + ApplicantPhone;


def result;

//url is a gloabl variable inside teneo studio
def url = powerAutomateURL;

//assign fields to the map object
def map = [:];
map["emailTo"] = emailTo;
map["emailSubject"] = emailSubject;
map["emailBody"] = emailBody;
map["tanscript"] = sTranscript;
map["applicantname"] = ApplicantName;
map["applicantemail"] = ApplicantEmail;
map["applicantphone"] = ApplicantPhone;
map["applicantCTMCert"] = ApplicantCTMCert;
map["applicantexperience"] = ApplicantExperience;
map["sCTMTempId"] = sCTMTempId;

//send the email API to power Automate using appacheHTTP client
try {
	def jsonBody = new JsonBuilder(map).toString();
	def post = new HttpPost(url);
	post.setEntity(new StringEntity(jsonBody));
	post.setHeader("Accept", "application/json");
	post.setHeader("Content-type", "application/json");
	
	
	def client = HttpClientBuilder.create().build();
	def response = client.execute(post);
	sResponseCode = jsonBody;

} catch(Exception ex){
	if(ex.printStackTrace() != null){
		println 'Exception : '+ ex;
		sExceptionTrace = ex;
	}
}