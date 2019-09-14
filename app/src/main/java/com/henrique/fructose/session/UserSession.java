package com.henrique.fructose.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserSession implements Serializable {


    private static final String KEY_ID          = "id";
    private static final String KEY_NAME        = "name";
    private static final String KEY_EMAIL       = "email";
    private static final String KEY_SENHA       = "password";
    private static final String KEY_PHOTO_URI   = "photo_uri";
    private static final String KEY_CIDADE      = "cidade";
    private static final String KEY_CIDADE_ID   = "cidade_id";
    private static final String KEY_CPF         = "cpf";


    /*private static final String KEY_SITES = "sites";
    private static final String KEY_ENABLE_GUIDE = "guide_enable";*/


    private String name;
    private String password;
    private String email;
    private String userId;
    private String fotoUri;
    private String cpf;
    private String cidade;
    private String cidadeID;



    public UserSession() {
         name       = "";
         email      = "";
         password   = "";
         userId     = "";
         fotoUri    = "";
         cpf        = "";
         cidade     = "";
         cidadeID   = "";
    }


    Map<String, String> getValues() {
        Map<String, String> values = new HashMap<>();

        values.put(KEY_ID,          getUserId());
        values.put(KEY_NAME,        getName());
        values.put(KEY_EMAIL,       getEmail());
        values.put(KEY_SENHA,       getSenha());
        values.put(KEY_PHOTO_URI,   getFotoUri());
        values.put(KEY_CPF,         getCpf());
        values.put(KEY_CIDADE_ID,   getCidadeID());
        values.put(KEY_CIDADE,      getCidade());

        return values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void updateData(Map<String, String> values) {
        setName(        values.get(KEY_NAME));
        setEmail(       values.get(KEY_EMAIL));
        setUserId(      values.get(KEY_ID));
        setPassword(    values.get(KEY_SENHA));
        setFotoUri(     values.get(KEY_PHOTO_URI));
        setCidade(      values.get(KEY_CIDADE));
        setCidadeID(      values.get(KEY_CIDADE_ID));
    }

    public void selfUpdate(){
        updateData(getValues());
    }

    public void setTelefone(String phoneNumber) {}

    public String getSenha() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidadeID() {
        return cidadeID;
    }

    public void setCidadeID(String cidadeID) {
        this.cidadeID = cidadeID;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}


