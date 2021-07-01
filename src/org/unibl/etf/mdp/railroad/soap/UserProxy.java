package org.unibl.etf.mdp.railroad.soap;

public class UserProxy implements org.unibl.etf.mdp.railroad.soap.User {
  private String _endpoint = null;
  private org.unibl.etf.mdp.railroad.soap.User user = null;
  
  public UserProxy() {
    _initUserProxy();
  }
  
  public UserProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserProxy();
  }
  
  private void _initUserProxy() {
    try {
      user = (new org.unibl.etf.mdp.railroad.soap.UserServiceLocator()).getUser();
      if (user != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)user)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)user)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (user != null)
      ((javax.xml.rpc.Stub)user)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.unibl.etf.mdp.railroad.soap.User getUser() {
    if (user == null)
      _initUserProxy();
    return user;
  }
  
  public boolean deactivate(java.lang.String username) throws java.rmi.RemoteException{
    if (user == null)
      _initUserProxy();
    return user.deactivate(username);
  }
  
  public org.unibl.etf.mdp.railroad.model.User[] getUsers() throws java.rmi.RemoteException{
    if (user == null)
      _initUserProxy();
    return user.getUsers();
  }
  
  public boolean createUser(org.unibl.etf.mdp.railroad.model.User user0) throws java.rmi.RemoteException{
    if (user == null)
      _initUserProxy();
    return user.createUser(user0);
  }
  
  public boolean usernameExists(java.lang.String username) throws java.rmi.RemoteException{
    if (user == null)
      _initUserProxy();
    return user.usernameExists(username);
  }
  
  
}