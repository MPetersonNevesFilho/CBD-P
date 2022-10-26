palindromePhones = function () {
    var phonesArray = db.phones.find({},{"display": 1, _id: 0}).toArray();
    var palindromes = [];
    var palindromeNumber = 0;
    for (var i = 0; i < phonesArray.length; i++) {
        var phone = phonesArray[i].display.replace("+", "").split("-")[1];
        if (phone == phone.split("").reverse().join("")) {
            palindromeNumber++;
            palindromes.push(phonesArray[i].display);
        }
    }
    print("Total palindrome numbers: " + palindromeNumber);
    print("Palindrome numbers: " + palindromes);

}