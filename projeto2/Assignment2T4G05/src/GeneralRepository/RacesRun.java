package GeneralRepository;

import communication.Proxy.ServerProxy;
import communication.ServerChannel;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import settings.NodeSettsProxy;

/**
 * This class implements the main of the races.
 * @author Daniela Simões
 */
public class RacesRun {
    private static int SERVER_PORT;
    
    public static void main(String[] args) throws SocketException, SocketTimeoutException {
        NodeSettsProxy proxy = new NodeSettsProxy(); 
        SERVER_PORT = proxy.SERVER_PORTS().get("Races");
        
        // canais de comunicação
        ServerChannel schan, schani;
        
        // thread agente prestador do serviço
        ServerProxy cliProxy;                               

        /* estabelecimento do servico */
        
        // criação do canal de escuta e sua associação
        schan = new ServerChannel(SERVER_PORT);    
        schan.start();
        
        RacesServer server = new RacesServer();
        System.out.println("Races service has started!\nServer is listening.");

        /* processamento de pedidos */
        
        while (true) {
            
            try {
                // entrada em processo de escuta
                schani = schan.accept();
                // lançamento do agente prestador do serviço
                cliProxy = new ServerProxy(schan, schani, server, "Races");
                cliProxy.start();
            } catch (SocketTimeoutException ex) {
                Logger.getLogger(LogRun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
