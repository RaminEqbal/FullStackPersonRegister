/**
 * Defines the namespace for generally used keys and strings
 */
const namespace = {

    keys: {
        PERSON_ID:"id",
        SURNAME:"surname",
        NAME:"name",
        EMAIL:"emailAddress",
        DATE_OF_BIRTH:"dob",
        ADDRESS:"address",

        ADDRESS_ID:"id",
        STREET_NAME:"streetName",
        STREET_NUMBER:"streetNo",
        POSTAL_CODE:"postalCode",
        COUNTRY_NAME:"countryName"

    },
    api:{
        default: "http://localhost:8080/api/person",
        add: "http://localhost:8080/api/person/add"

    },
    
}

export {namespace};


/**
 * Locale of the displayed String on the Front end
 */
const localeStrings = {
    german : {
        keys : {
            [namespace.keys.PERSON_ID]:"ID",
            [namespace.keys.SURNAME]:"Vorname",
            [namespace.keys.NAME]:"Nachname",
            [namespace.keys.EMAIL]:"E-Mail",
            [namespace.keys.DATE_OF_BIRTH]:"Geburtsdatum",
            [namespace.keys.ADDRESS]:"Addresse",
            
            [namespace.keys.ADDRESS_ID]:"ID",
            [namespace.keys.STREET_NAME]:"Straßename",
            [namespace.keys.STREET_NUMBER]:"Straßennummer",
            [namespace.keys.POSTAL_CODE]:"Postleitzahl",
            [namespace.keys.COUNTRY_NAME]:"Land"
        },
        errorMessages: {
            PARSE_FORM_ERROR:"Beim Parsen des Formulars ist ein Fehler aufgetreten\n",
            PERSON_NOT_ADD:"Person wurde nicht hinzugefügt\n",
            PARSE_EMAIL_ERROR:"Es wurde keine gültige E-Mail angegeben\n",
            PARSE_DATE_ERROR:"Das Geburtsdatum Feld enthält kein gültiges Datum\n",
            API_RETURNS_NOT_OK:"Beim Aufruf der API mit den angegebenen Daten ist ein Fehler aufgetreten\n",
        },
        messages: {
            PERSON_ADD:"Person wurde hinzugefügt",
        },
        html: {
            APPLICATION_TITLE: "Personendatenbank",
            FORM_ADDPERSON_TITLE:"Person Hinzufügen",
        },
        placeholders : {
            [namespace.keys.PERSON_ID]:"",
            [namespace.keys.SURNAME]:"",
            [namespace.keys.NAME]:"",
            [namespace.keys.EMAIL]:"z.B. max.musterman@muster.de",
            [namespace.keys.DATE_OF_BIRTH]:"Format: TT.MM.JJJJ (z.B. 01.01.1990)",
            [namespace.keys.ADDRESS]:"Format: Straßenname, Straßennummer, Postleitzahl, Ländername (Werte mit Komma separiert, mehrere Addressen durch ;) ",
            
        }
    }
}


export {localeStrings};


/**
 * Global Variables that can be imported
 */
const globals = {

    subscribableKeys: [
        // namespace.keys.PERSON_ID,
        namespace.keys.SURNAME,
        namespace.keys.NAME,
        namespace.keys.EMAIL,
        namespace.keys.DATE_OF_BIRTH,
        namespace.keys.ADDRESS,
    ],
    addressKeys : [
        // namespace.keys.ADDRESS_ID,
        namespace.keys.STREET_NAME,
        namespace.keys.STREET_NUMBER,
        namespace.keys.POSTAL_CODE,
        namespace.keys.COUNTRY_NAME
    ],
    


}


export default globals;