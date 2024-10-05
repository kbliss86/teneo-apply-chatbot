//Create File Prep GREV Task - Travel - API

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
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.HttpClients;
import groovy.json.*;
import groovy.json.JsonOutput;


//Custom Variable//
String sName = sName_Global; //sName_Global is a global variable in chatbot
String stitle = 'Onboarding File Prep';
String suserId = 640;
String sstatusName = 'Not Started';
String spriorityName = 'Normal';
String sduedate = new java.text.SimpleDateFormat('MM/dd/yyyy').format(new Date());
String sdetails = "A new Travel applicant - ${sName} has completed the application process through chatbot and needs file prep.";
String stesttaskAssignID = "729"; //this is a test ID - Assign to kendall for testing//
String staskAssignToGroupID = "13";
String shcpId = sCTMTempId; //Global Variable//
//ring shcpId = "157430"
String stesthcpId = "157430"; //Nancy Nurse for Testing Integration//
String stags = "Travel File Prep";


String sURL = "https://grapetreefunctiontaskmanager.azurewebsites.net/api/task?code={APIKEY}";
//initializes output variable for if statement
String output = '';

//determines payload of API based on if it coming from a QA or Production Environment
if (sSystem == 'production') {output = JsonOutput.toJson([title: stitle,userId:640,statusName: sstatusName,priorityName: spriorityName,dueDate: sduedate,details: sdetails,taskAssignToGroupID: staskAssignToGroupID,hcpId: shcpId,tags: stags])} else {output = JsonOutput.toJson([title: stitle,userId:640,statusName: sstatusName,priorityName: spriorityName,dueDate: sduedate,details: sdetails,taskAssignID: stesttaskAssignID,hcpId: stesthcpId,tags: stags])};

//changes Output variable from JSON to String for API transport
def entity = new StringEntity(output);

//builds and sends API using appacheHTTPclientBuilder
try {
	
	def post = new HttpPost(sURL);
	   post.setEntity(entity);


	def client = HttpClientBuilder.create().build();
	def response = client.execute(post);
	println response.getStatusLine().getStatusCode();
	//println response;	
	sAppacheResponseCode = response.getStatusLine().getStatusCode();//use this for if clause//
	sAppacheAPIResponse = response.getStatusLine().getReasonPhrase();

//If API for GREV task fails, then it will send an email to document processors//
            if (sAppacheResponseCode == 404) {
            println "Response Code is 404. Performing Email"
            String emailTo = "DocumentProcessors@grapetree.com";
            //DP Email: DocumentProcessors@grapetree.com
            String emailSubject = "Chatbot Applicant - Create Task Failed";
            String emailBody = "the following HCP has applied for travel but a file prep task was not able to be created, please create one manually: HCP ID:";
            def purl = "https://prod-133.westus.logic.azure.com:443/workflows/83eb5f9f84a244a88c071f7f238ba024/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=C7PAkJLiD2oBh9PCbvn9gt0WPUix_aye4W3GokgIvmQ";
            
            def map = [:];
            map["emailTo"] = emailTo;
            map["emailSubject"] = emailSubject;
            map["emailBody"] = emailBody;
            map["sCTMTempID"] = shcpId;
            println map
                try {
                    def jsonBody = new JsonBuilder(map).toString();
                    println jsonBody
                    def post2 = new HttpPost(purl);
                    println post2
                    post2.setEntity(new StringEntity(jsonBody));
                    post2.setHeader("Accept", "application/json");
                    post2.setHeader("Content-type", "application/json");
                    
                    def client2 = HttpClientBuilder.create().build();
                    def response2 = client.execute(post2);
                    sResponseCode = jsonBody; 
                } catch (Exception ex) {
                    if(ex.printStackTrace() != null) {
                        println "Exception :"+ ex;
                           sExceptionTrace = ex;
                    }
                }
            }


	String responseString = new BasicResponseHandler().handleResponse(response);
	def json = new JsonSlurper().parseText(responseString);
       
	//Specific API Return Value (different for every API)//  
	sAPIResponse = json.message;
	sAPISuccess = json.success;
	sAPITaskID = json.taskId;
	
		sErrorOnTask = '';
		if (json.success = true) {sErrorOnTask = json.message} else {sErrorOnTask = ''};

}  catch(Exception ex){
	if(ex.printStackTrace() != null){
		println 'Exception :'+ ex;
		sExceptionTrace = ex;
	}
}