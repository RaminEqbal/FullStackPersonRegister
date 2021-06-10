export function isValidSimpleDate(date) {
    const re = /[0-9]{2}[.][0-9]{2}[.][0-9]{4}$/;
    return re.test(String(date));
}