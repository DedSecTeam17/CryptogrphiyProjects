package sample.PolyAlphabitics;

class  Rule {

    private String type;
    private int amount;
    //        0 for left and 1 for right

    private boolean direction;
    private int id;


    public String getType() {
        return type;
    }


    public Rule(String type, int amount, boolean direction) {
        this.type = type;
        this.amount = amount;
        this.direction = direction;
    }

    public void setType(String type) {

        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }



    public Rule() {
    }

    public Rule(String type, int amount, boolean direction, int id) {
        this.type = type;
        this.amount = amount;
        this.direction = direction;
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}