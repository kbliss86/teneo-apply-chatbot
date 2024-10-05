
//update WorkerUrlTags

String sUserName = '';
String sPass = '';
String sURLFLAG = sURLFLAG;
String sUTMID = sUTMID;
String sUTMMEDIUM = sUTMMEDIUM;
String sUTMCAMPAIGN = sUTMCAMPAIGN;
String sUTMSOURCE = sUTMSOURCE;
String sUTMCONTENT = sUTMCONTENT;
String sUTMTERM = sUTMTERM;
String sUTMCHANNEL = sUTMCHANNEL;

String sCTMSystem = '';
String sBaseURL = '';
if (sSystem == 'production') {sCTMSystem = 'grapetree'} else {sCTMSystem = 'grapetree_training'};
if (sSystem == 'production') {sBaseURL = 'https://ctms.contingenttalentmanagement.com/'} else {sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/'};

//send update UTM information
String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=updateWorkerUrlTags&resultType=json&tempId=' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '&tags=<tags><AppMethod>Chatbot</AppMethod><AppChannel>PerDiem</AppChannel><Source>' + URLEncoder.encode(sUTMSOURCE,'UTF-8') + '</Source><Medium>' + URLEncoder.encode(sUTMMEDIUM,'UTF-8') + '</Medium><Campaign>' + URLEncoder.encode(sUTMCAMPAIGN,'UTF-8') + '</Campaign><ID>' + URLEncoder.encode(sUTMID,'UTF-8') + '</ID><Content>' + URLEncoder.encode(sUTMCONTENT,'UTF-8') + '</Content><Term>' + URLEncoder.encode(sUTMTERM,'UTF-8') + '</Term><Channel>' + URLEncoder.encode(sUTMCHANNEL,'UTF-8') + '</Channel></tags>';

def json = new groovy.json.JsonSlurper().parse(new URL(sUrl), [connectTimeout: 4000, readTimeout: 4000], 'UTF-8');
println json;
sURLFlagUpdated = json.success[0];
if (json.success[0] != "1") {sErrorOnURLFlagUpdate = json};


