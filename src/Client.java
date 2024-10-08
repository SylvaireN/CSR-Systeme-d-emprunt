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
            System.out.println("Client emprunte un vélo au site " + siteDepart.getId());
            Thread.sleep(Math.abs(siteDepart.getId() - siteArrivee.getId()) * 1000);
            siteArrivee.restituerVelo();
            System.out.println("Client restitue un vélo au site " + siteArrivee.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
