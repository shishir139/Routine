class PriceProgram {
    static void main(String [] args) {

        def products = [
                ["A", "G1", 20.1],
                ["B", "G2", 98.4],
                ["C", "G1", 49.7],
                ["D", "G3", 35.8],
                ["E", "G3", 105.5],
                ["F", "G1", 55.2],
                ["G", "G1", 12.7],
                ["H", "G3", 88.6],
                ["I", "G1", 5.2],
                ["J", "G2", 72.4]]

        def category = [
                ["C3", 50, 75],
                ["C4", 75, 100],
                ["C2", 25, 50],
                ["C5", 100, null],
                ["C1", 0, 25]]

        def margins = [
                "C1" : "20%",
                "C2" : "30%",
                "C3" : "0.4",
                "C4" : "50%",
                "C5" : "0.6"]
        def categoryFound = "";
        def margin = "";
        def marginValue = 0;
        def checkMarginValue = 0;
        def productPrice = 0;
        def results = [
                "G1" : 0.0,
                "G2" : 0.0,
                "G3" : 0.0];
        def count = [0,0,0];


        products.inject(["", "", 0.0]) { result, i ->
           // println "$result + ${i[2]} = ${result + i[2]}"



           for(it in category) {


                if (((i[2] >= it[1]) && (i[2] < it[2])) || ((i[2] >= it[1]) && (it[2] == null))) {
                    categoryFound = it[0];
                    //println(categoryFound)
                    margin = margins[categoryFound];
                    checkMarginValue = margin.indexOf('%');
                    if(checkMarginValue > -1) {
                        marginValue = (margin.minus("%").toInteger() / 100);
                    }
                    else
                        marginValue = margin.toFloat();
                    productPrice = i[2].multiply((1.plus(marginValue)));
                   // println(productPrice);


                    i[2] = productPrice;
                    break;
                }


           }





       }

       // println(products);

        products.each {
              if(it[1].equals("G1")) {

                  results["G1"] = results["G1"].plus(it[2]);
                  count[0] += 1;

              }

            else if(it[1].equals("G2")) {

                  results["G2"] = results["G2"].plus(it[2]);
                  count[1] += 1;

              }
              else if(it[1].equals("G3")) {
                  results["G3"] = results["G3"].plus(it[2]);
                  count[2] += 1;

              }
            else {
                  println("Invalid input")
              }

        }
       // println(count)
        def j = 0
       results.each {
           it.value = (it.value / count[j]).round(1);

           j++;
       }
       // println(results)

        assert results == [
                "G1" : 37.5,
                "G2" : 124.5,
                "G3" : 116.1
        ] : "It doesn't work"

        println "It works!"


    }
}
