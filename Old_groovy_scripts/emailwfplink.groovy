// CTM update temp record
// set url params
//added 12/28

String sUserName = '';
String sPass = '';
//String sPriority = "1";


String sCTMSystem = '';
String sBaseURL = '';
if (sSystem == 'production') {sCTMSystem = 'grapetree'} else {sCTMSystem = 'grapetree_training'};
if (sSystem == 'production') {sBaseURL = 'https://ctms.contingenttalentmanagement.com/'} else {sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/'};



//send email WFP link
String sUrl3 = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=sendWorkforcePortalInvite&resultType=json&tempId=' + URLEncoder.encode(sCTMTempId, 'UTF-8');

def json3 = new groovy.json.JsonSlurper().parse(new URL(sUrl3), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');
println json3;
sCTMEmailSent = json3.success[0];
