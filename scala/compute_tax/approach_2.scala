def slabtax(aincome:Double, lowerslab:Double, rate:Double):Tuple2[Double, Double] = {
    var tax:Double = 0
    var income = aincome;
    if(income > lowerslab) {
        tax = (income - lowerslab) * rate;
        income = lowerslab
    }
    return (income, tax)
}

def computeTaxApproach2(_income:Double):Double = {
    var income = _income
    var tax:Double = 0
    var result = slabtax(income, 500000, 0.3)
    income = result._1
    tax = tax + result._2
    result = slabtax(income, 400000, 0.2)
    income = result._1
    tax = tax + result._2
    result = slabtax(income, 300000, 0.1)
    income = result._1
    tax = tax + result._2
    return tax;
}
