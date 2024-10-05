import groovy.json.*

def form = [
  "type": "form",
  "elements": [
    [
      "type": "title",
      "text": "Form Title"
    ],
    [
      "type": "subtitle",
      "text": "Form Subtitle"
    ],
    [
      "type": "label",
      "text": "This is an additional label to let people know * means required"
    ],
    [
      "type": "label",
      "text": "These inputs are checkboxes"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Ortho"
      ],
      "label": "Ortho"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "LTC"
      ],
      "label": "LTC"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Asst. Living"
      ],
      "label": "Assisted Living"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Ambulatory Care"
      ],
      "label": "Ambulatory Care"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Cardiac Cath Lab"
      ],
      "label": "Cardiace Cath Lab"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Case Manager"
      ],
      "label": "Case Manager"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Clinic"
      ],
      "label": "Clinic"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Corrections"
      ],
      "label": "Corrections"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "CVOR"
      ],
      "label": "CVOR"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Dialysis"
      ],
      "label": "Dialysis"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "DON"
      ],
      "label": "DON"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Endo"
      ],
      "label": "Endo"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "ER2"
      ],
      "label": "ER2"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Home Health"
      ],
      "label": "Home Health"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Hospice"
      ],
      "label": "Hospice"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "ICU-2"
      ],
      "label": "ICU-2"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "L&D"
      ],
      "label": "L&D"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "LTAC"
      ],
      "label": "LTAC"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "M/S"
      ],
      "label": "M/S"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "MS Telemetry"
      ],
      "label": "MS Telemetry"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Med/Surge ER"
      ],
      "label": "Med/Surge ER"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "NICU"
      ],
      "label": "NICU"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Oncology"
      ],
      "label": "Oncology"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "OR"
      ],
      "label": "OR"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "PACU"
      ],
      "label": "PACU"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "PCU"
      ],
      "label": "PCU"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Peds"
      ],
      "label": "Peds"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "PICU"
      ],
      "label": "PICU"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Postpartum"
      ],
      "label": "Postpartum"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Psych"
      ],
      "label": "Psych"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Radiology"
      ],
      "label": "Radiology"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Rehab"
      ],
      "label": "Rehab"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Step Down"
      ],
      "label": "Step Down"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Supervisor"
      ],
      "label": "Supervisor"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Telemetry"
      ],
      "label": "Telemetry"
    ],
    [
      "type": "input",
      "attributes": [
        "type": "checkbox",
        "value": "Wound Ostomy"
      ],
      "label": "Wound Ostomy"
    ],
    [
      "type": "control",
      "text": "Submit",
      "action": "submit"
    ],
    
  ]
]

JSON = new JsonOutput().toJson(form)
