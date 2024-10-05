//Set Temp record status to Applied -added 1/6
//add task Need_File_Prep -added 1/20

String sDate = new java.text.SimpleDateFormat('yyyy-MM-dd').format(new Date());

String sUserName = '';
String sPass = '';

String sCTMSystem = '';
String sBaseURL = '';
if (sSystem == 'production') {sCTMSystem = 'grapetree'} else {sCTMSystem = 'grapetree_training'};
if (sSystem == 'production') {sBaseURL = 'https://ctms.contingenttalentmanagement.com/'} else {sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/'};

//set status to Applied
String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=updateTempRecords&resultType=json&tempRecords=<tempRecords><tempRecord><tempId>' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '</tempId><status>Applied</status></tempRecord></tempRecords>';

def json = new groovy.json.JsonSlurper().parse(new URL(sUrl), [connectTimeout: 4000, readTimeout: 4000], 'UTF-8');
println json;
sCTMApplied = json.success[0];
if (json.success[0] != "1") {sErrorOnUpdate = json};

//task is Need_File_Prep for CTM Processing user group
String sUrl4 = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=insertNotes&resultType=json&noteRecords=<noteRecords><noteRecord><tempId>' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '</tempId><noteTypeId>2038</noteTypeId><noteType>Task</noteType><note>Need_File_Prep</note><TaskAssignID>' + URLEncoder.encode(sProcessingGroup, 'UTF-8') + '</TaskAssignID><MakeTaskYN>yes</MakeTaskYN><TaskDueDate>' + URLEncoder.encode(sDate, 'UTF-8') + '</TaskDueDate><TaskPriorityID>1</TaskPriorityID><TaskStatusID>1</TaskStatusID></noteRecord></noteRecords>';

def json4 = new groovy.json.JsonSlurper().parse(new URL(sUrl4), [connectTimeout: 4000, readTimeout: 4000], 'UTF-8');
println json4;
sCTMTaskCreated = json4.success[0];
if (json4.success[0] != "1") {sErrorOnTask = json4};

//task for Illinois CNA for SSN
/*if (sCertification == 'CNA' && (ApplicantPrimaryState == 'IL' || ApplicantTravelState == 'IL')) {
String sUrl5 = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=insertNotes&resultType=json&noteRecords=<noteRecords><noteRecord><tempId>' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '</tempId><noteTypeId>2038</noteTypeId><noteType>Task</noteType><note>IL_CNA_need_SSN</note><TaskAssignID>' + URLEncoder.encode(sProcessingGroup3, 'UTF-8') + '</TaskAssignID><MakeTaskYN>yes</MakeTaskYN><TaskDueDate>' + URLEncoder.encode(sDate, 'UTF-8') + '</TaskDueDate><TaskPriorityID>1</TaskPriorityID><TaskStatusID>1</TaskStatusID></noteRecord></noteRecords>';

def json5 = new groovy.json.JsonSlurper().parse(new URL(sUrl5), [connectTimeout: 4000, readTimeout: 4000], 'UTF-8');
println json5;
sCTMTaskCreated = json5.success[0];
if (json5.success[0] != "1") {sErrorOnTask = json5};
}*/