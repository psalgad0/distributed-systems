/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.log;

import structures.enumerates.BrokerState;

/**
 *
 * @author Daniela
 */
public interface IBroker {
    void setBrokerState(BrokerState state);
}
