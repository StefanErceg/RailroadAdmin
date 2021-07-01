/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.unibl.etf.mdp.railroad.soap;

public interface User extends java.rmi.Remote {
    public boolean deactivate(java.lang.String username) throws java.rmi.RemoteException;
    public org.unibl.etf.mdp.railroad.model.User[] getUsers() throws java.rmi.RemoteException;
    public boolean createUser(org.unibl.etf.mdp.railroad.model.User user) throws java.rmi.RemoteException;
    public boolean usernameExists(java.lang.String username) throws java.rmi.RemoteException;
}
