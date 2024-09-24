//Script to Parse values from the form and place them in a comma deliminated string

def input = sJsonString;

//parse the json
def jsonMap = new groovy.json.JsonSlurper().parseText(input);

//filter out the keys and get values
def inputValues = jsonMap.findAll { key, _ -> key.startsWith("input_checkbox_") }.values();

//join values with comma delimination
{sSpecialties = inputValues.join(',');}
//Check to see if it is a null value
if (sSpecialties == null || sSpecialties.trim().isEmpty()) {
    sSpecialties = "None Chosen"
}

println sSpecialties