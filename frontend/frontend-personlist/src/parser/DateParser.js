


//Simple Date Format: DD.MM.YYYY


//SQL Date Format: yyyy-MM-dd
//Longated: 1997-06-28T22:00:00.000+00:00

export function parseSimpleDateToSQLDate(simpleDate) {
    const values = simpleDate.split(".")
    return values[2] + "-" + values[1] + "-" + values[0];

}

export function parseSQLDateToSimpleDate(sqlDate) {
    const dayInformation =  sqlDate.substring(0,10)
    const values = dayInformation.split("-");
    return values[2] + "." + values[1] + "."+ values[0];
}