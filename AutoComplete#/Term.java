package AutoComplete;
public class Term implements AutoComplete.ITerm {

    private String term;
    private long weight;

    /**
     * Initialize a Term with a given query String and weight
     */
    public Term(String term, long weight) {
        if (term == null) {
            throw new IllegalArgumentException("The term should not be null");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("The weight " +
                    "should not be negative");
        }
        this.term = term;
        this.weight = weight;
    }


    @Override
    public int compareTo(AutoComplete.ITerm that) {
        return this.term.compareTo(that.getTerm());
    }

    @Override
    public String toString() {
        return String.format("%d\t%s", weight, term);
    }

    @Override
    public long getWeight() {
        return this.weight;
    }

    @Override
    public String getTerm() {
        return this.term;
    }

    @Override
    public void setWeight(long weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("The weight " +
                    "should not be negative");
        }
        this.weight = weight;
    }

    @Override
    public String setTerm(String term) {
        if (term == null) {
            throw new IllegalArgumentException("The term should not be null");
        }
        String old = this.term;
        this.term = term;
        return old;
    }


}
