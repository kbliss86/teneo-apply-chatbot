//Time Delay:
import java.util.concurrent.TimeUnit
TimeUnit.SECONDS.sleep(15)

//Parse Name to two seperate variables for first and last name
if (sName.indexOf(" ") > -1) {
    sFirstName = Lib_sUserFirstName = sName.substring(0, sName.indexOf(" "));
    sLastName = sName.substring(sName.indexOf(" ") + 1);
} else {
    sFirstName = Lib_sUserFirstName = sName
}

//Parse phone and verifies it is a valid 10 digit number
sPhone_Global = sPhone_Global.replaceAll("\\D","");
nPlen = sPhone_Global.length();

//Provides button for user to to their profile
if (sSystem != 'production') {sTheURL = 'https://ctmscs.contingenttalentmanagement.com/grapetree_training/WorkforcePortal/login.cfm?CFID=14598425&CFTOKEN=f018a81f7e71247b-B0B567E0-5056-A922-232E38E2310032DB'}
else {sTheURL = 'https://ctms.contingenttalentmanagement.com/grapetree/WorkforcePortal/login.cfm?CFID=14598425&CFTOKEN=f018a81f7e71247b-B0B567E0-5056-A922-232E38E2310032DB'};

def buttons = [:]
buttons.type = "linkbuttons"
buttons.linkbutton_items = []

def styles = [
['title':'Workforce Portal', 'url':"${sTheURL}"],
]
styles.each {
	def button = [:]
	button.title = it.title
	button.url= it.url
	if (it.target) {
		button.target= it.target
	}
	buttons.linkbutton_items << button
}

JSON = new groovy.json.JsonBuilder(buttons).toString()

//Checks for special characters in Free text sent by API
def input = sJsonString

// Check for ampersand and replace it with a safe character
if (input.contains('&')) {
    input = input.replaceAll('&', 'and')
}

{sFreeTextResponse = input}

println sFreeTextResponse