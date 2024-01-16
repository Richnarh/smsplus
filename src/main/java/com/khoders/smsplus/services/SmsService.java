/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.smsplus.services;

import Zenoph.SMSLib.Enums.MSGTYPE;
import Zenoph.SMSLib.ZenophSMS;
import com.khoders.smsplus.entities.sms.MessageTemplate;
import com.khoders.smsplus.entities.CustomerRegistration;
import com.khoders.smsplus.entities.sms.GroupContact;
import com.khoders.smsplus.entities.sms.SMSGrup;
import com.khoders.smsplus.entities.sms.SenderId;
import com.khoders.smsplus.entities.sms.Sms;
import com.khoders.smsplus.entities.sms.SmsAccess;
import com.khoders.smsplus.enums.SMSType;
import com.khoders.smsplus.listener.AppSession;
import com.khoders.resource.jpa.CrudApi;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

/**
 *
 * @author richa
 */
@Stateless
public class SmsService
{
    @Inject private AppSession appSession;
    @Inject private CrudApi crudApi;
    
    public List<CustomerRegistration> getContactList()
    {
        try
        {
            String qryString = "SELECT e FROM CustomerRegistration e WHERE e.userAccount=?1 ORDER BY e.customerName ASC";
            TypedQuery<CustomerRegistration> typedQuery = crudApi.getEm().createQuery(qryString, CustomerRegistration.class);
                               typedQuery.setParameter(1, appSession.getCurrentUser());
                         return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<GroupContact> getGroupContactList()
    {
        try
        {
            String qryString = "SELECT e FROM GroupContact e WHERE e.userAccount=?1";
            TypedQuery<GroupContact> typedQuery = crudApi.getEm().createQuery(qryString, GroupContact.class);
                                typedQuery.setParameter(1, appSession.getCurrentUser());
                             return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<SMSGrup> getGroupList()
    {
        try
        {
            String qryString = "SELECT e FROM SMSGrup e WHERE e.userAccount=?1 ";
            TypedQuery<SMSGrup> typedQuery = crudApi.getEm().createQuery(qryString, SMSGrup.class);
                                typedQuery.setParameter(1, appSession.getCurrentUser());
                             return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<GroupContact> getContactGroupList(SMSGrup smsGrup)
    {
        try
        {
            String qryString = "SELECT e FROM GroupContact e WHERE e.userAccount=?1 AND e.smsGrup=?2";
            TypedQuery<GroupContact> typedQuery = crudApi.getEm().createQuery(qryString, GroupContact.class);
                                typedQuery.setParameter(1, appSession.getCurrentUser());
                                typedQuery.setParameter(2, smsGrup);
                             return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public List<GroupContact> getContactGroupList()
    {
        try
        {
            String qryString = "SELECT e FROM GroupContact e WHERE e.userAccount=?1";
            TypedQuery<GroupContact> typedQuery = crudApi.getEm().createQuery(qryString, GroupContact.class);
                                typedQuery.setParameter(1, appSession.getCurrentUser());
                             return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
  
    public List<Sms> smsList()
    {
        try
        {
            String qryString = "SELECT e FROM Sms e WHERE e.userAccount=?1 ORDER BY e.smsTime DESC";
            TypedQuery<Sms> typedQuery = crudApi.getEm().createQuery(qryString, Sms.class);
                            typedQuery.setParameter(1, appSession.getCurrentUser());
                            return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public List<Sms> loadSmslogList(SMSType sMSType)
    {
        try
        {
            String qryString = "SELECT e FROM Sms e WHERE e.userAccount=?1 AND e.sMSType=?2 ORDER BY e.smsTime DESC";
            TypedQuery<Sms> typedQuery = crudApi.getEm().createQuery(qryString, Sms.class);
                            typedQuery.setParameter(1, appSession.getCurrentUser());
                            typedQuery.setParameter(2, sMSType);
                            return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<SenderId> getSenderIdList()
    {
        try
        {
            String qryString = "SELECT e FROM SenderId e WHERE e.userAccount=?1";
            TypedQuery<SenderId> typedQuery = crudApi.getEm().createQuery(qryString, SenderId.class);
            typedQuery.setParameter(1, appSession.getCurrentUser());
            typedQuery.getResultList();

            return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    public boolean isInternetAccessVailable()
    {
        try
        {
            URL url = new URL("http://www.google.com");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            Object object = httpURLConnection.getContent();
            System.out.println("SUCCESSFUL INTERNET CONNECTION");
            System.out.println(object);
            return true;

        } catch (UnknownHostException e)
        {
            System.out.println("CONNECTION FAILED");
            e.printStackTrace();
            return false;
        } catch (IOException e)
        {
            System.out.println("CONNECTION FAILED");
            e.printStackTrace();
        }
        return false;
    }
    
    public List<MessageTemplate> getMessageTemplateList()
    {
        try
        {
            String qryString = "SELECT e FROM MessageTemplate e WHERE e.userAccount=?1";
            TypedQuery<MessageTemplate> typedQuery = crudApi.getEm().createQuery(qryString, MessageTemplate.class);
            typedQuery.setParameter(1, appSession.getCurrentUser());
                             return typedQuery.getResultList();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public Sms getCustomer(CustomerRegistration customerRegistration)
    {
        try
        {
            String qryString = "SELECT e FROM Sms e WHERE e.customerRegistration=?1";
            TypedQuery<Sms> typedQuery = crudApi.getEm().createQuery(qryString, Sms.class)
                    .setParameter(1, customerRegistration);
            
                 return typedQuery.getResultStream().findFirst().orElse(null);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<SmsAccess> smsAccessList(){
        return crudApi.getEm().createQuery("SELECT e FROM SmsAccess e", SmsAccess.class).getResultList();
    }
    
    public ZenophSMS extractParams()
    {
        ZenophSMS zsms = new ZenophSMS();
        try
        {
            SmsAccess smsAccess = crudApi.getEm().createQuery("SELECT e FROM SmsAccess e WHERE e.userAccount=:userAccount", SmsAccess.class)
                    .setParameter("userAccount", appSession.getCurrentUser())
                    .getSingleResult();
            if(smsAccess != null){
                
            System.out.println("Username --- "+smsAccess.getUsername());
            System.out.println("Password --- "+smsAccess.getPassword());
            
            zsms.setUser(smsAccess.getUsername());
            zsms.setPassword(smsAccess.getPassword());
            zsms.authenticate();
            zsms.setMessageType(MSGTYPE.TEXT);
          }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return zsms;
    }
}
