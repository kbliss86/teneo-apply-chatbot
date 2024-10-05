// CTM GET number of records via email lookup
// set url params

nCTMUsersFound = 0;
String sUserName = '';
String sPass = '';
String sCTMSystem = '';
String sBaseURL = '';
if (sSystem == 'production') {sCTMSystem = 'grapetree'} else {sCTMSystem = 'grapetree_training'};
if (sSystem == 'production') {sBaseURL = 'https://ctms.contingenttalentmanagement.com/'} else {sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/'};

String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=getTemps&resultType=json&emailStartsWith=' + URLEncoder.encode(sEmail, 'UTF-8');

def json = new groovy.json.JsonSlurper().parse(new URL(sUrl), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');

nCTMUsersFound = json.size();
println json.size();
sCTMTempId = json.tempId[0];
println json.tempId[0];
sCTMStatus = json.status[0];
println json.status[0];
sHomeRegion = json.homeRegion[0];
println json.homeRegion[0];

// Now check phone number

if (nCTMUsersFound == 0) {
String sUrl2 = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=getTemps&resultType=json&phoneNumber=' + URLEncoder.encode(sPhone, 'UTF-8');

def json2 = new groovy.json.JsonSlurper().parse(new URL(sUrl2), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');

nCTMUsersFound = json2.size();
println json2.size();
sCTMTempId = json2.tempId[0];
println json2.tempId[0];
sCTMStatus = json2.status[0];
println json2.status[0];
sHomeRegion = json.homeRegion[0];
println json.homeRegion[0];
}