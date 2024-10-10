class Client extends Thread {
    private Site siteDepart;
    private Site siteArrivee;

    public Client(Site siteDepart, Site siteArrivee) {
        this.siteDepart = siteDepart;
        this.siteArrivee = siteArrivee;
    }

    @Override
    public void run() {
        try {
            siteDepart.emprunterVelo();
            System.out.println("Client emprunte un Vélo au site " + siteDepart.getId() + "  Stock actuel après emprunt au site " + siteDepart.getId() + ": " +" stock = "+ siteDepart.getStock());
            //System.out.println();
            Thread.sleep(Math.abs(siteDepart.getId() - siteArrivee.getId()) * 1000);
            siteArrivee.restituerVelo();
            System.out.println("Client restitue un Vélo au site " + siteArrivee.getId() + "  Stock actuel après restitution au site " + siteArrivee.getId() + ": " +" stock = "+ siteArrivee.getStock());
            //System.out.println("Stock actuel après restitution au site " + siteArrivee.getId() + ": " +" Stock = "+ siteArrivee.getStock());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
