class Site {
    static final int STOCK_INIT = 5;
    static final int STOCK_MAX = 10;
    static final int BORNE_SUP = 8;
    static final int BORNE_INF = 2;

    private int id;
    private int stock;
    private boolean isServing;

    public Site(int id) {
        this.id = id;
        this.stock = STOCK_INIT;
        this.isServing = false;
    }

    public synchronized void emprunterVelo() throws InterruptedException {
        while (stock == 0 || isServing) {
            wait();
        }
        System.out.println("Avant emprunt au site " + id + ": stock = " + stock);
        stock--;
        isServing = true;
        notifyAll();
        //System.out.println("Après emprunt au site " + id + ": stock = " + stock);
    }

    public synchronized void restituerVelo() throws InterruptedException {
        while (stock == STOCK_MAX || isServing) {
            wait();
        }
        System.out.println("Avant restitution au site " + id + ": stock = " + stock);
        stock++;
        isServing = true;
        notifyAll();
        //System.out.println("Après restitution au site " + id + ": stock = " + stock);
    }

    public synchronized void camionArrive() {
        isServing = true;
    }

    public synchronized void camionPart() {
        isServing = false;
        notifyAll();
    }

    public synchronized int getStock() {
        return stock;
    }

    public synchronized void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }
}
