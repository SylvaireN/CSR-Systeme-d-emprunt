import java.util.Random;

class SystemeEmprunt {
    static final int NB_SITES = 5;
    static final int NB_CLIENTS = 20;

    private Site[] sites = new Site[NB_SITES];
    private Client[] clients = new Client[NB_CLIENTS];
    private Camion camion = null;

    SystemeEmprunt() {
        for (int i = 0; i < NB_SITES; i++) {
            sites[i] = new Site(i);
        }

        Random r = new Random();
        for (int i = 0; i < NB_CLIENTS; i++) {
            int siteDep = r.nextInt(NB_SITES);
            int siteArr = r.nextInt(NB_SITES);
            while (siteDep == siteArr) {
                siteArr = r.nextInt(NB_SITES);
            }
            clients[i] = new Client(sites[siteDep], sites[siteArr]);
        }

        camion = new Camion(sites);

        camion.start();
        for (Client client : clients) {
            client.start();
        }

        for (Client client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        camion.stopRunning();
        try {
            camion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulation terminée.");
    }

    public static void main(String[] args) {
        new SystemeEmprunt();
    }
}
