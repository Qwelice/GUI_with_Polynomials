package polynomials.pair;

public class Pair<T1, T2>{
    T1 first;
    T2 second;

    public Pair(T1 x1, T2 x2) {
        first = x1;
        second = x2;
    }

    @Override
    public boolean equals(Object other) {
        if (getClass() != other.getClass())
            return false;
        else {
            return ((Pair)other).first == first && ((Pair)other).second == second;
        }
    }

    @Override
    public int hashCode(){
        return first.hashCode() + second.hashCode();
    }

    public T1 getFirst(){
        return first;
    }

    public T2 getSecond(){
        return second;
    }

    public void setFirst(T1 first){
        this.first = first;
    }
    public void setSecond(T2 second){
        this.second = second;
    }
}
