package br.gov.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootapp.controller.View;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    @JsonView({View.ProfileAllView.class, View.ProfileView.class})
    private Long id;

    @Column(name = "profile_uuid")
    @JsonView({View.ProfileAllView.class, View.ProfileView.class})
    private String uuid;

    @Column(name = "profile_audio_hash")
    @JsonView(View.ProfileView.class)
    private String audioHash;

    @Column(name = "profile_webgl_hash")
    @JsonView(View.ProfileView.class)
    private String webGLHash;

    @Column(name = "profile_canvas_hash")
    @JsonView(View.ProfileView.class)
    private String canvasHash;

    @Column(name = "profile_user_agent")
    @JsonView(View.ProfileView.class)
    private String userAgent;

    @Column(name = "profile_fonts")
    @JsonView(View.ProfileView.class)
    private String fonts;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_registration",
        joinColumns = { @JoinColumn(name = "profile_id")},
        inverseJoinColumns = { @JoinColumn(name = "registration_id") }
    )
    @JsonView({View.ProfileAllView.class, View.ProfileView.class})
    private Set<Registration> registrations;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }
}