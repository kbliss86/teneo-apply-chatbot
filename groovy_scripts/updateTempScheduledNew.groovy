// CTM update temp record
// set url params
//added 12/28

String sUserName = '';
String sPass = '';
//String sPriority = "1";

String sNotes = 'Primary state ' + ApplicantPrimaryState + ', Secondary state ' + ApplicantSecondaryState + ', Travel state ' + ApplicantTravelState + ' ** Interview date ' + InterviewDate + ' Interview time ' + InterviewTime;

//String sDate = new java.text.SimpleDateFormat('MM/dd/yyyy').format(new Date());
//if (sDate == InterviewDate) {sPriority == "4"}

String sCTMSystem = '';
String sBaseURL = '';
if (sSystem == 'production') {sCTMSystem = 'grapetree'} else {sCTMSystem = 'grapetree_training'};
if (sSystem == 'production') {sBaseURL = 'https://ctms.contingenttalentmanagement.com/'} else {sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/'};

//set status to Scheduled and add interview date and time
String sUrl2 = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=updateTempRecords&resultType=json&tempRecords=<tempRecords><tempRecord><tempId>' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '</tempId><status>Scheduled</status></tempRecord></tempRecords>';

def json2 = new groovy.json.JsonSlurper().parse(new URL(sUrl2), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');
println json2;
sCTMScheduled = json2.success[0];
if (json2.success[0] != "1") {sErrorOnUpdate = json2};

//send email WFP link
String sUrl3 = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=sendWorkforcePortalInvite&resultType=json&tempId=' + URLEncoder.encode(sCTMTempId, 'UTF-8');

def json3 = new groovy.json.JsonSlurper().parse(new URL(sUrl3), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');
println json3;
sCTMEmailSent = json3.success[0];

