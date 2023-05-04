package vn.ansv.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ansv.management.dto.User.UserLdapDTO;
import vn.ansv.management.entity.ResponseObject;

@RestController
@RequestMapping(path = "/ldap")
public class LdapController {
    DirContext connection;

    /* create connection during object creation */
    public void newConnection() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://172.24.104.6:389");
        env.put(Context.SECURITY_PRINCIPAL, "levanthanh@ansv.vn");
        env.put(Context.SECURITY_CREDENTIALS, "Thanh0204");
        try {
            connection = new InitialDirContext(env);
            System.out.println("------------------------------ Ket noi LDAP thanh cong! " + connection);
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getAllGroups() throws NamingException {
        String searchFilter = "(&(objectClass=group)(objectCategory=CN=Group,CN=Schema,CN=Configuration,DC=ansv,DC=vn))";
        String[] reqAtt = { "cn" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("CN=Users,DC=ansv,DC=vn", searchFilter, controls);

        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            System.out.println(attr.get("cn"));
            System.out.println("--------------------------------------------------------");
        }
    }

    public List<UserLdapDTO> getAllUsers() throws NamingException {
        String searchFilter = "(&(objectClass=organizationalPerson)(objectClass=person)(objectClass=top)(objectClass=user))";
        String[] reqAtt = { "cn", "sn", "userPrincipalName" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("CN=Users,DC=ansv,DC=vn", searchFilter, controls);

        SearchResult result = null;
        List<UserLdapDTO> listUserLdap = new ArrayList<UserLdapDTO>();
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            // String name = attr.get("cn").get(0).toString();
            // deleteUserFromGroup(name,"Administrators");
            // System.out.println(attr.get("cn"));
            // System.out.println(attr.get("sn"));
            // System.out.println(attr.get("userPrincipalName"));
            // System.out.println("------------------------------------------------------");

            UserLdapDTO data = new UserLdapDTO();
            data.setFullname((attr.get("cn") + "").replace("cn: ", ""));
            data.setUsername((attr.get("userPrincipalName") + "").replace("userPrincipalName: ", ""));
            listUserLdap.add(data);
        }
        return listUserLdap;
    }

    public List<UserLdapDTO> getAllUsersByGroup(String group) throws NamingException {
        String searchFilter = "(&(objectClass=organizationalPerson)(objectClass=person)(objectClass=user)"
                + "(memberOf=CN=" + group + ",CN=Users,DC=ansv,DC=vn))";
        String[] reqAtt = { "cn", "description", "userPrincipalName" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("CN=Users,DC=ansv,DC=vn", searchFilter, controls);

        SearchResult result = null;
        List<UserLdapDTO> listUserLdap = new ArrayList<UserLdapDTO>();
        // System.out.println("========================= Group: " + group + "
        // =========================");
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();

            // System.out.println("| " + attr.get("cn"));
            // System.out.println("| " + attr.get("description"));
            // System.out.println("| " + attr.get("userPrincipalName"));
            // System.out.println("|");

            UserLdapDTO data = new UserLdapDTO();
            data.setFullname((attr.get("cn") + "").replace("cn: ", ""));
            data.setUsername((attr.get("userPrincipalName") + "").replace("userPrincipalName: ", ""));
            data.setDescription((attr.get("description") + "").replace("description: ", ""));
            data.setUsed(0);
            listUserLdap.add(data);
        }
        return listUserLdap;
    }

    public void addUser() {
        Attributes attributes = new BasicAttributes();
        Attribute attribute = new BasicAttribute("objectClass");
        attribute.add("inetOrgPerson");

        attributes.put(attribute);
        // user details
        attributes.put("sn", "Ricky");
        try {
            connection.createSubcontext("cn=Tommy,ou=users,ou=system", attributes);
            System.out.println("success");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addUserToGroup(String username, String groupName) {
        ModificationItem[] mods = new ModificationItem[1];
        Attribute attribute = new BasicAttribute("uniqueMember", "cn=" + username + ",ou=users,ou=system");
        mods[0] = new ModificationItem(DirContext.ADD_ATTRIBUTE, attribute);
        try {
            connection.modifyAttributes("cn=" + groupName + ",ou=groups,ou=system", mods);
            System.out.println("success");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void deleteUser() {
        try {
            connection.destroySubcontext("cn=Tommy,ou=users,ou=system");
            System.out.println("success");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteUserFromGroup(String username, String groupName) {
        ModificationItem[] mods = new ModificationItem[1];
        Attribute attribute = new BasicAttribute("uniqueMember", "cn=" + username + ",ou=users,ou=system");
        mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attribute);
        try {
            connection.modifyAttributes("cn=" + groupName + ",ou=groups,ou=system", mods);
            System.out.println("success");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void searchUsers() throws NamingException {
        // String searchFilter = "(uid=1)"; // for one user
        // String searchFilter = "(&(uid=1)(cn=Smith))"; // and condition
        String searchFilter = "(|(uid=1)(uid=2)(cn=Smith))"; // or condition
        String[] reqAtt = { "cn", "sn", "uid" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("ou=users,ou=system", searchFilter, controls);

        SearchResult result = null;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            // String name = attr.get("cn").get(0).toString();
            // deleteUserFromGroup(name,"Administrators");
            System.out.println(attr.get("cn"));
            System.out.println(attr.get("sn"));
            System.out.println(attr.get("uid"));
        }

    }

    /* use this to authenticate any existing user */
    public static boolean authUser(String username, String password) {
        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
            env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=users,ou=system"); // check the DN correctly
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext con = new InitialDirContext(env);
            System.out.println("success");
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
            return false;
        }
    }

    /* use this to update user password */
    public void updateUserPassword(String username, String password) {
        try {
            String dnBase = ",ou=users,ou=system";
            ModificationItem[] mods = new ModificationItem[1];
            // if you want, then you can delete the old password and after that you can
            // replace with new password
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("userPassword", password));
            connection.modifyAttributes("cn=" + username + dnBase, mods);// try to form DN dynamically
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
        }
    }

    public void updateUserDetails(String username, String employeeNumber) {
        try {
            String dnBase = ",ou=users,ou=system";
            Attribute attribute = new BasicAttribute("employeeNumber", employeeNumber);
            ModificationItem[] mods = new ModificationItem[1];
            mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute);
            connection.modifyAttributes("cn=" + username + dnBase, mods);// try to form DN dynamically
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
        }
    }

    @GetMapping("/user/{group}")
    public ResponseEntity<ResponseObject> getAllUser(@PathVariable String group) throws NamingException {
        newConnection();
        // addUser();
        // getAllGroups();
        // getAllUsers();
        List<UserLdapDTO> data = getAllUsersByGroup(group);
        getAllUsersByGroup("pm");
        // for (UserLdapDTO userLdapDTO : data) {
        // System.out.println(userLdapDTO.getFullname());
        // System.out.println(userLdapDTO.getUsername());
        // System.out.println("-------------------------------------------");
        // }
        // deleteUser();
        // searchUsers();

        // System.out.println(authUser("test", "1574"));
        // updateUserPassword("test", "123");
        // updateUserDetails("Tommy", "123");

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Danh sách user trên LDAP", data));
    }
}
