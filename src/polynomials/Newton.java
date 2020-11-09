package polynomials;

import polynomials.pair.Pair;

import java.util.LinkedHashMap;

public class Newton extends Polynomial{
    private LinkedHashMap<Double, Double> dots;
    private LinkedHashMap<Pair, Double> p_difference;
    private Polynomial cash_for_elems;
    private Double[] keys;

    public Newton(LinkedHashMap<Double, Double> dots) {
        p_difference = new LinkedHashMap<>();
        keys = new Double[0];
        cash_for_elems = new Polynomial(new double[]{1.});
        this.dots = (LinkedHashMap<Double, Double>) dots.clone();
        keys = this.dots.keySet().toArray(keys);
        createPolynomial();
    }

    private double partDifference(int index_1, int index_2) {
        Pair couple = new Pair(index_1, index_2);
        if (p_difference.keySet().contains(couple))
            return p_difference.get(couple);
        else if (!p_difference.containsKey(couple) && Math.abs(index_1 - index_2) == 1) {
            p_difference.put(couple, (dots.get(keys[index_2]) - dots.get(keys[index_1])) / (keys[index_2] - keys[index_1]));
            return p_difference.get(couple);
        }
        double result = (partDifference(index_1 + 1, index_2) - partDifference(index_1, index_2 - 1)) / (keys[index_2] - keys[index_1]);
        p_difference.put(couple, result);
        return result;
    }

    private Polynomial elemOfNewton(int k) {
        var t = k;
        cash_for_elems.coef.clear();
        Polynomial temp = new Polynomial(2);
        cash_for_elems.coef.add(0.);
        cash_for_elems.set(0, 1.);
        cash_for_elems.timesAssign(partDifference(0, k));
        while (t > 0) {
            temp.set(0, -keys[t - 1]);
            temp.set(1, 1);
            cash_for_elems.timesAssign(temp);
            t--;
        }
        return cash_for_elems;
    }

    public void add(double x, double y){
        dots.put(x, y);
        keys = dots.keySet().toArray(keys);
        if(dots.size() >= 2)
            this.plusAssign(elemOfNewton(keys.length-1));
        else
            this.plusAssign(dots.get(x));
    }

    public void removeLast(double x){
        if(dots.containsKey(x)){
            if(dots.size() >= 2){
                Double[] nodes = new Double[0];
                nodes = dots.keySet().toArray(nodes);
                for(int i = 0; i < nodes.length; i++){
                    if(nodes[i] == x){
                        this.minusAssign(elemOfNewton(i));
                        dots.remove(nodes[i]);
                        break;
                    }
                }
            }
            else{
                dots.remove(x);
            }
        }
    }

    private void createPolynomial() {
        if(dots.size() >= 2) {
            this.plusAssign(dots.get(keys[0]));
            for (int i = 1; i < dots.keySet().size(); i++)
                this.plusAssign(elemOfNewton(i));
        }
        else if(dots.size() == 1){
            this.plusAssign(dots.get(keys[0]));
        }
    }
}
