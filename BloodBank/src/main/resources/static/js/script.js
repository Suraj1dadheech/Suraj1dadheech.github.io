var catAndActs = {};
catAndActs['Karnataka'] = ['Bangalore', 'Mysore', 'Belgaum', 'Kolar', 'Tumkur'];
catAndActs['Rajasthan'] = ['Jaipur', 'Sikar', 'Kota', 'Udaipur','Bikaner'];
catAndActs['Maharastra'] = ['Mumbai', 'Nagpur', 'Pune', 'Nashik', 'Kolhapur'];
catAndActs['Tamilnadu'] = ['Chennai', 'Coimbatore', 'Tiruchirappalli', 'Vellore', 'Kanyakumari'];
catAndActs['Madhya Pradesh'] = ['Bhopal','Gwalior', 'Indore', 'Jabalpur', 'Ujjain'];
catAndActs['Uttar Pradesh'] = ['Lucknow','Aligarh', 'Bareilly', 'Allahabad', 'Varanasi'];

function ChangecatList() {
    var catList = document.getElementById("validationCustom03");
    var actList = document.getElementById("validationCustom04");
    console.log(actList);
    var selCat = catList.options[catList.selectedIndex].value;
    while (actList.options.length) {
        actList.remove(0);
    }
    var cats = catAndActs[selCat];
    if (cats) {
        var i;
        for (i = 0; i < cats.length; i++) {
			let temp = cats[i];
            var cat = new Option(cats[i], temp);
            actList.options.add(cat);
        }
    }
} 