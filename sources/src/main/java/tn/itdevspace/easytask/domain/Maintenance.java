package tn.itdevspace.easytask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import tn.itdevspace.easytask.domain.enumeration.Etat;

/**
 * A Maintenance.
 */
@Entity
@Table(name = "maintenance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", length = 36)
    private UUID id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "produit")
    private String produit;

    @Column(name = "solution")
    private String solution;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @NotNull
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "duree")
    private Double duree;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "maintenances", "chargeJournalieres" }, allowSetters = true)
    private Ressource ressource;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Maintenance id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Maintenance description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduit() {
        return this.produit;
    }

    public Maintenance produit(String produit) {
        this.setProduit(produit);
        return this;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public String getSolution() {
        return this.solution;
    }

    public Maintenance solution(String solution) {
        this.setSolution(solution);
        return this;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public Maintenance etat(Etat etat) {
        this.setEtat(etat);
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public LocalDate getDateDebut() {
        return this.dateDebut;
    }

    public Maintenance dateDebut(LocalDate dateDebut) {
        this.setDateDebut(dateDebut);
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return this.dateFin;
    }

    public Maintenance dateFin(LocalDate dateFin) {
        this.setDateFin(dateFin);
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Double getDuree() {
        return this.duree;
    }

    public Maintenance duree(Double duree) {
        this.setDuree(duree);
        return this;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public Ressource getRessource() {
        return this.ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public Maintenance ressource(Ressource ressource) {
        this.setRessource(ressource);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Maintenance)) {
            return false;
        }
        return id != null && id.equals(((Maintenance) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Maintenance{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", produit='" + getProduit() + "'" +
            ", solution='" + getSolution() + "'" +
            ", etat='" + getEtat() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", duree=" + getDuree() +
            "}";
    }
}
