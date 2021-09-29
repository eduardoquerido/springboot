package br.gov.sp.fatec.springbootapp.controller;

public class RegistrationDto {

    public String email;

    public String password;
    
    public String name;

    public String cellphone;

    public String audioHash;

    public String webGLHash;

    public String canvasHash;

    public String userAgent;

    public String fonts;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAudioHash() {
        return audioHash;
    }

    public void setAudioHash(String audioHash) {
        this.audioHash = audioHash;
    }

    public String getWebGLHash() {
        return webGLHash;
    }

    public void setWebGLHash(String webGLHash) {
        this.webGLHash = webGLHash;
    }

    public String getCanvasHash() {
        return canvasHash;
    }

    public void setCanvasHash(String canvasHash) {
        this.canvasHash = canvasHash;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getFonts() {
        return fonts;
    }

    public void setFonts(String fonts) {
        this.fonts = fonts;
    }
    
}
