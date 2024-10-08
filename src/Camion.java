class Camion extends Thread {
    private Site[] sites;
    private boolean running;

    public Camion(Site[] sites) { 
        this.sites = sites;
        this.running = true;
    }
    
    @Override
    public void run() {
        while (running) {
            for (Site site : sites) {
                try {
                    site.camionArrive();
                    int stock = site.getStock();
                    if (stock > Site.BORNE_SUP) {
                        site.setStock(Site.STOCK_INIT);
                        System.out.println("Camion enlève des vélos au site " + site.getId());
                    } else if (stock < Site.BORNE_INF) {
                        site.setStock(Site.STOCK_INIT);
                        System.out.println("Camion ajoute des vélos au site " + site.getId());
                    }
                    site.camionPart();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}
