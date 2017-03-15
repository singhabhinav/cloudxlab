def computeTaxApproach1(aincome:Double):Double = {
    var income = aincome;
    var tax:Double = 0;
    if(income > 500000) {
        tax = tax + (income - 500000) * 0.3;
        income = 500000
    }
    if(income > 400000) {
        tax = tax + (income - 400000) * 0.2;
        income = 400000
    }
    if(income > 300000) {
        tax = tax + (income - 300000) * 0.1;
    }
    return tax;
}
