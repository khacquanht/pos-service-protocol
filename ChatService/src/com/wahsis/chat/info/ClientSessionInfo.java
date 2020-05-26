/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wahsis.chat.info;

import org.eclipse.jetty.websocket.api.Session;

/**
 *
 * @author HaiNT
 */
public class ClientSessionInfo {
    private int _userType = 0;
    private String _userId = "";
    private String _companyId = "";
    private String _branchId = "";
    private String _displayName = "";
    private boolean _allowReplyToUser = true;   //test
    private Session _session = null;

    public ClientSessionInfo(Session session, int userType, String userId, String companyId, String displayName) {
        _session = session;
        _userType = userType;
        _userId = userId;
        _companyId = companyId;
        _displayName = displayName;
    }

    public int getUserType() {
        return _userType;
    }

    public void setUserType(int _userType) {
        this._userType = _userType;
    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String _userId) {
        this._userId = _userId;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String _companyId) {
        this._companyId = _companyId;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public void setDisplayName(String _displayName) {
        this._displayName = _displayName;
    }

    public Session getSession() {
        return _session;
    }

    public void setSession(Session _session) {
        this._session = _session;
    }

    public String getBranchId() {
        return _branchId;
    }

    public void setBranchId(String _branchId) {
        this._branchId = _branchId;
    }

    public boolean isAllowReplyToUser() {
        return _allowReplyToUser;
    }

    public void setAllowReplyToUser(boolean _allowReplyToUser) {
        this._allowReplyToUser = _allowReplyToUser;
    }
    
}
