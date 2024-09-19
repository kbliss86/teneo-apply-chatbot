// CTM POST temp record
// set url params
//<referralSourceId>38</referralSourceId> referral source set to Chatbot -added 12/27
//<applicantExperience>applicant</applicantExperience> setting to allow for CTM email link -added 12/28
//sCTMSystem and baseURL variables set depending on global variable sSystem -added 12/28
import groovy.json.*;

String ApplicantFirstName = 'Kendall';
String ApplicantLastName = 'isGrape6022';
String ApplicantEmail = 'kendall@isgrape6022.com';
String ApplicantZip = '65445';
String ApplicantCTMCert = 'CNA';
String ApplicantPhone = '0005556000';
String sApplicantSpecialty = 'LTC';
String ApplicantType = 'Travel Not Reviewed';
String ApplicantReferral = '15';
String sApplicantRecruiter = '';
String sSecondaryCert = '';
String nUsersFound = 0;
String sCTMTempId = '';
String ApplicantPrimaryState = 'IA';
String ApplicantSecondaryState = 'MN';
String ApplicantTravelState = 'IL';

String sUserName = '';
String sPass = '';
String sFirstName = ApplicantFirstName;
String sLastName = ApplicantLastName;
String sEmailAddress = ApplicantEmail;
String sZip = ApplicantZip;
String sCTMCert = ApplicantCTMCert;
String sMobilephone = ApplicantPhone;
String sSpecialty = sApplicantSpecialty;
String sStatus = ApplicantType;
String sHomeRegion = '63';
String sRcode = ApplicantReferral;
String sRecruiter = sApplicantRecruiter;
String sSecondaryCTMCert = sSecondaryCert;


if (sSecondaryCTMCert != null) {sCTMCert = ApplicantCTMCert + ',' + sSecondaryCTMCert} else {sCTMCert = ApplicantCTMCert};


String sStates = 'Primary state ' + ApplicantPrimaryState + ', Secondary state ' + ApplicantSecondaryState + ', Travel state ' + ApplicantTravelState;

String sSystem = 'qa';

String sCTMSystem = '';
String sBaseURL = '';

if (sSystem == 'production') {sCTMSystem = 'grapetree'} else {sCTMSystem = 'grapetree_training'};
if (sSystem == 'production') {sBaseURL = 'https://ctms.contingenttalentmanagement.com/'} else {sBaseURL = 'https://ctmscs.contingenttalentmanagement.com/'};

//determine if this will be an insert or an update
if (nUsersFound == 0) 
{if (sApplicantRecruiter == null){String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=insertTempRecords&resultType=json&tempRecords=<tempRecords><tempRecord><firstName>' + URLEncoder.encode(sFirstName, 'UTF-8') + '</firstName><lastName>' + URLEncoder.encode(sLastName, 'UTF-8') + '</lastName><homeRegion>' + URLEncoder.encode(sHomeRegion, 'UTF-8') + '</homeRegion><status>' + URLEncoder.encode(sStatus, 'UTF-8') + '</status><notes>' + URLEncoder.encode(sStates, 'UTF-8') + '</notes><cell_phone>' + URLEncoder.encode(sMobilephone, 'UTF-8') + '</cell_phone><certification>' + URLEncoder.encode(sCTMCert, 'UTF-8') + '</certification><email>' + URLEncoder.encode(sEmailAddress, 'UTF-8') + '</email><zip>' + sZip + '</zip><specialty>' + URLEncoder.encode(sSpecialty, 'UTF-8') + '</specialty><referralSourceId>' + URLEncoder.encode(sRcode, 'UTF-8') + '</referralSourceId><applicantExperience>applicant</applicantExperience></tempRecord></tempRecords>';} else {String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=insertTempRecords&resultType=json&tempRecords=<tempRecords><tempRecord><firstName>' + URLEncoder.encode(sFirstName, 'UTF-8') + '</firstName><lastName>' + URLEncoder.encode(sLastName, 'UTF-8') + '</lastName><homeRegion>' + URLEncoder.encode(sHomeRegion, 'UTF-8') + '</homeRegion><status>' + URLEncoder.encode(sStatus, 'UTF-8') + '</status><notes>' + URLEncoder.encode(sStates, 'UTF-8') + '</notes><cell_phone>' + URLEncoder.encode(sMobilephone, 'UTF-8') + '</cell_phone><certification>' + URLEncoder.encode(sCTMCert, 'UTF-8') + '</certification><email>' + URLEncoder.encode(sEmailAddress, 'UTF-8') + '</email><zip>' + sZip + '</zip><specialty>' + URLEncoder.encode(sSpecialty, 'UTF-8') + '</specialty><referralSourceId>' + URLEncoder.encode(sRcode, 'UTF-8') + '</referralSourceId><applicantExperience>applicant</applicantExperience><recruiter>' + URLEncoder.encode(sRecruiter, 'UTF-8') + '</recruiter></tempRecord></tempRecords>';};

def json = new groovy.json.JsonSlurper().parse(new URL(sUrl), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');
println json;
sCTMTempId = json.tempId[0];
if (json.success[0] != "1") {sErrorOnCreate = json};
} 
else {if (sApplicantRecruiter == null){String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=updateTempRecords&resultType=json&tempRecords=<tempRecords><tempRecord><tempId>' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '</tempId><firstName>' + URLEncoder.encode(sFirstName, 'UTF-8') + '</firstName><lastName>' + URLEncoder.encode(sLastName, 'UTF-8') + '</lastName><homeRegion>' + URLEncoder.encode(sHomeRegion, 'UTF-8') + '</homeRegion><status>' + URLEncoder.encode(sStatus, 'UTF-8') + '</status><notes>' + URLEncoder.encode(sStates, 'UTF-8') + '</notes><cell_phone>' + URLEncoder.encode(sMobilephone, 'UTF-8') + '</cell_phone><certification>' + URLEncoder.encode(sCTMCert, 'UTF-8') + '</certification><email>' + URLEncoder.encode(sEmailAddress, 'UTF-8') + '</email><zip>' + sZip + '</zip><specialty>' + URLEncoder.encode(sSpecialty, 'UTF-8') + '</specialty><referralSourceId>' + URLEncoder.encode(sRcode, 'UTF-8') + '</referralSourceId><applicantExperience>applicant</applicantExperience></tempRecord></tempRecords>';} else {String sUrl = sBaseURL + URLEncoder.encode(sCTMSystem, 'UTF-8') + '/clearConnect/2_0/index.cfm/?username=' + URLEncoder.encode(sUserName, 'UTF-8') + '&password=' + URLEncoder.encode(sPass, 'UTF-8') + '&action=updateTempRecords&resultType=json&tempRecords=<tempRecords><tempRecord><tempId>' + URLEncoder.encode(sCTMTempId, 'UTF-8') + '</tempId><firstName>' + URLEncoder.encode(sFirstName, 'UTF-8') + '</firstName><lastName>' + URLEncoder.encode(sLastName, 'UTF-8') + '</lastName><homeRegion>' + URLEncoder.encode(sHomeRegion, 'UTF-8') + '</homeRegion><status>' + URLEncoder.encode(sStatus, 'UTF-8') + '</status><notes>' + URLEncoder.encode(sStates, 'UTF-8') + '</notes><cell_phone>' + URLEncoder.encode(sMobilephone, 'UTF-8') + '</cell_phone><certification>' + URLEncoder.encode(sCTMCert, 'UTF-8') + '</certification><email>' + URLEncoder.encode(sEmailAddress, 'UTF-8') + '</email><zip>' + sZip + '</zip><specialty>' + URLEncoder.encode(sSpecialty, 'UTF-8') + '</specialty><referralSourceId>' + URLEncoder.encode(sRcode, 'UTF-8') + '</referralSourceId><applicantExperience>applicant</applicantExperience><recruiter>' + URLEncoder.encode(sRecruiter, 'UTF-8') + '</recruiter></tempRecord></tempRecords>';};

def json = new groovy.json.JsonSlurper().parse(new URL(sUrl), [connectTimeout: 2000, readTimeout: 3000], 'UTF-8');
println json;
sCTMTempId = json.tempId[0];
if (json.success[0] != "1") {sErrorOnUpdate = json};
}