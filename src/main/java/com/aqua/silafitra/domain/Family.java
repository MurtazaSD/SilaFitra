package com.aqua.silafitra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Family.
 */
@Entity
@Table(name = "family")
public class Family implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "hof_ejamaat_id", nullable = false, unique = true)
    private Long hofEjamaatId;

    @Column(name = "hof_full_name")
    private String hofFullName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "total_gents")
    private Integer totalGents;

    @Column(name = "total_ladies")
    private Integer totalLadies;

    @Column(name = "total_kids")
    private Integer totalKids;

    @Column(name = "total_pregnants")
    private Integer totalPregnants;

    @Column(name = "total_decesead")
    private Integer totalDecesead;

    @Column(name = "zakatul_fitr")
    private Float zakatulFitr;

    @Column(name = "najwatusukr")
    private Float najwatusukr;

    @Column(name = "khumus")
    private Float khumus;

    @Column(name = "silatul_imam")
    private Float silatulImam;

    @Column(name = "nazrul_maqam")
    private Float nazrulMaqam;

    @Column(name = "last_update_tstamp")
    private LocalDate lastUpdateTstamp;

    @Column(name = "submitted_by")
    private String submittedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHofEjamaatId() {
        return hofEjamaatId;
    }

    public Family hofEjamaatId(Long hofEjamaatId) {
        this.hofEjamaatId = hofEjamaatId;
        return this;
    }

    public void setHofEjamaatId(Long hofEjamaatId) {
        this.hofEjamaatId = hofEjamaatId;
    }

    public String getHofFullName() {
        return hofFullName;
    }

    public Family hofFullName(String hofFullName) {
        this.hofFullName = hofFullName;
        return this;
    }

    public void setHofFullName(String hofFullName) {
        this.hofFullName = hofFullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Family emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Family phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getTotalGents() {
        return totalGents;
    }

    public Family totalGents(Integer totalGents) {
        this.totalGents = totalGents;
        return this;
    }

    public void setTotalGents(Integer totalGents) {
        this.totalGents = totalGents;
    }

    public Integer getTotalLadies() {
        return totalLadies;
    }

    public Family totalLadies(Integer totalLadies) {
        this.totalLadies = totalLadies;
        return this;
    }

    public void setTotalLadies(Integer totalLadies) {
        this.totalLadies = totalLadies;
    }

    public Integer getTotalKids() {
        return totalKids;
    }

    public Family totalKids(Integer totalKids) {
        this.totalKids = totalKids;
        return this;
    }

    public void setTotalKids(Integer totalKids) {
        this.totalKids = totalKids;
    }

    public Integer getTotalPregnants() {
        return totalPregnants;
    }

    public Family totalPregnants(Integer totalPregnants) {
        this.totalPregnants = totalPregnants;
        return this;
    }

    public void setTotalPregnants(Integer totalPregnants) {
        this.totalPregnants = totalPregnants;
    }

    public Integer getTotalDecesead() {
        return totalDecesead;
    }

    public Family totalDecesead(Integer totalDecesead) {
        this.totalDecesead = totalDecesead;
        return this;
    }

    public void setTotalDecesead(Integer totalDecesead) {
        this.totalDecesead = totalDecesead;
    }

    public Float getZakatulFitr() {
        return zakatulFitr;
    }

    public Family zakatulFitr(Float zakatulFitr) {
        this.zakatulFitr = zakatulFitr;
        return this;
    }

    public void setZakatulFitr(Float zakatulFitr) {
        this.zakatulFitr = zakatulFitr;
    }

    public Float getNajwatusukr() {
        return najwatusukr;
    }

    public Family najwatusukr(Float najwatusukr) {
        this.najwatusukr = najwatusukr;
        return this;
    }

    public void setNajwatusukr(Float najwatusukr) {
        this.najwatusukr = najwatusukr;
    }

    public Float getKhumus() {
        return khumus;
    }

    public Family khumus(Float khumus) {
        this.khumus = khumus;
        return this;
    }

    public void setKhumus(Float khumus) {
        this.khumus = khumus;
    }

    public Float getSilatulImam() {
        return silatulImam;
    }

    public Family silatulImam(Float silatulImam) {
        this.silatulImam = silatulImam;
        return this;
    }

    public void setSilatulImam(Float silatulImam) {
        this.silatulImam = silatulImam;
    }

    public Float getNazrulMaqam() {
        return nazrulMaqam;
    }

    public Family nazrulMaqam(Float nazrulMaqam) {
        this.nazrulMaqam = nazrulMaqam;
        return this;
    }

    public void setNazrulMaqam(Float nazrulMaqam) {
        this.nazrulMaqam = nazrulMaqam;
    }

    public LocalDate getLastUpdateTstamp() {
        return lastUpdateTstamp;
    }

    public Family lastUpdateTstamp(LocalDate lastUpdateTstamp) {
        this.lastUpdateTstamp = lastUpdateTstamp;
        return this;
    }

    public void setLastUpdateTstamp(LocalDate lastUpdateTstamp) {
        this.lastUpdateTstamp = lastUpdateTstamp;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public Family submittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
        return this;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Family)) {
            return false;
        }
        return id != null && id.equals(((Family) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Family{" +
            "id=" + getId() +
            ", hofEjamaatId=" + getHofEjamaatId() +
            ", hofFullName='" + getHofFullName() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", totalGents=" + getTotalGents() +
            ", totalLadies=" + getTotalLadies() +
            ", totalKids=" + getTotalKids() +
            ", totalPregnants=" + getTotalPregnants() +
            ", totalDecesead=" + getTotalDecesead() +
            ", zakatulFitr=" + getZakatulFitr() +
            ", najwatusukr=" + getNajwatusukr() +
            ", khumus=" + getKhumus() +
            ", silatulImam=" + getSilatulImam() +
            ", nazrulMaqam=" + getNazrulMaqam() +
            ", lastUpdateTstamp='" + getLastUpdateTstamp() + "'" +
            ", submittedBy='" + getSubmittedBy() + "'" +
            "}";
    }
}
