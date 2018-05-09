package shared;

import GeneralRepository.RacesStub;
import communication.Proxy.APC;
import communication.ServerChannel;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import settings.NodeSettsStub;

/**
 * This class implements the main of stable.
 * @author Daniela Simões, 76771
 */
public class StableRun {
    private static int SERVER_PORT;
    
    public static void main(String[] args) throws SocketException {
        NodeSettsStub proxy = new NodeSettsStub(); 
        SERVER_PORT = proxy.SERVER_PORTS().get("Stable");
        
        // canais de comunicação
        ServerChannel schan, schani;
        
        // thread agente prestador do serviço
        APC apc;                               

        /* estabelecimento do servico */
        
        // criação do canal de escuta e sua associação
        schan = new ServerChannel(SERVER_PORT);    
        schan.start();
        
        RacesStub races = new RacesStub();
        
        StableInterface stableServer = new StableInterface(races);
        System.out.println("Stable service has started!\nServer is listening.");

        /* processamento de pedidos */
        
        while (true) {
            
            try {
                // entrada em processo de escuta
                schani = schan.accept();
                // lançamento do agente prestador do serviço
                apc = new APC(schan, schani, stableServer, "Stable");
                apc.start();
            } catch (SocketTimeoutException ex) {
                Logger.getLogger(BettingCentreRun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
