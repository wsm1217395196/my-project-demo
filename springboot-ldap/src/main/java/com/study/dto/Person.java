package com.study.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"posixAccount", "top", "inetOrgPerson", "shadowAccount"}, base = "ou=People")
public class Person {

    @Id
    @JsonIgnore
    private Name dn;

    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "gidNumber")
    private Integer gidNumber;

    @Attribute(name = "uidNumber")
    private Integer uidNumber;

    @Attribute(name = "sn")
    private String sn;

    @Attribute(name = "homeDirectory")
    private String homeDirectory;

    @Attribute(name = "userPassword")
    private String userPassword;

    public Person() {
    }

    public Person(String cn) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "People")
                .add("uid", cn)
                .build();
        this.dn = dn;
    }
}
