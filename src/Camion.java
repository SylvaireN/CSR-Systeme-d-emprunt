class Camion extends Thread {
    private Site[] sites;
    private boolean running;
    private int stockCamion;

    public Camion(Site[] sites) { 
        this.sites = sites;
        this.running = true;
        this.stockCamion = 0;
    }
    
    @Override
    public void run() {
        while (running) {
            for (Site site : sites) {
                try {
                    site.camionArrive();
                    int stock = site.getStock();
                    if (stock > Site.BORNE_SUP) {
                        int velosEnleves = stock - Site.STOCK_INIT;
                        site.setStock(Site.STOCK_INIT);
                        stockCamion += velosEnleves;
                        System.out.println("Camion enlève "+velosEnleves+" vélos au site " + site.getId() + " Stock actuel après enlèvement du Camion au site " + site.getId() + ": "+ " Stock = " + site.getStock());
                        System.out.println("Stock actuel du camion: " + stockCamion);

                        //System.out.println("Stock actuel après enlèvement au site " + site.getId() + ": " + site.getStock());
                    } else if (stock < Site.BORNE_INF) {
                        int velosAjoutes = Site.STOCK_INIT - stock;
                        if (velosAjoutes <= stockCamion) {
                            site.setStock(Site.STOCK_INIT);
                            stockCamion -= velosAjoutes;
                            System.out.println("Camion ajoute "+velosAjoutes+" vélos au site " + site.getId() + " Stock actuel après ajout du Camion au site " + site.getId() + ": " +" Stock = "+ site.getStock());
                            //System.out.println("Stock actuel après ajout au site " + site.getId() + ": " + site.getStock());
                            System.out.println("Stock actuel du camion: " + stockCamion);
                        } else {
                            System.out.println("Camion n'a pas assez de vélos pour ajouter au site " + site.getId());
                        }
                       // site.setStock(Site.STOCK_INIT);
                        //System.out.println("Camion ajoute "+velosAjoutes+" vélos au site " + site.getId() + " Stock actuel après ajout du Camion au site " + site.getId() + ": " +" Stock = "+ site.getStock());
                       // System.out.println("Stock actuel après ajout au site " + site.getId() + ": " + site.getStock());
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
