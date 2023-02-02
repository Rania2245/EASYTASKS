package tn.itdevspace.easytask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import tn.itdevspace.easytask.domain.enumeration.TypeEntite;

/**
 * A Estimation.
 */
@Entity
@Table(name = "estimation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Estimation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", length = 36)
    private UUID id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "valeur_jour")
    private Double valeurJour;

    @Column(name = "valeur_heure")
    private Double valeurHeure;

    @Column(name = "prise_en_charge")
    private Boolean priseEnCharge;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeEntite type;

    @ManyToOne
    @JsonIgnoreProperties(value = { "estimations", "livrable" }, allowSetters = true)
    private Activite activite;

    @ManyToOne
    @JsonIgnoreProperties(value = { "livrables", "estimations", "client" }, allowSetters = true)
    private Projet projet;

    @ManyToOne
    @JsonIgnoreProperties(value = { "activites", "estimations", "projet" }, allowSetters = true)
    private Livrable livrable;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Estimation id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Estimation date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getValeurJour() {
        return this.valeurJour;
    }

    public Estimation valeurJour(Double valeurJour) {
        this.setValeurJour(valeurJour);
        return this;
    }

    public void setValeurJour(Double valeurJour) {
        this.valeurJour = valeurJour;
    }

    public Double getValeurHeure() {
        return this.valeurHeure;
    }

    public Estimation valeurHeure(Double valeurHeure) {
        this.setValeurHeure(valeurHeure);
        return this;
    }

    public void setValeurHeure(Double valeurHeure) {
        this.valeurHeure = valeurHeure;
    }

    public Boolean getPriseEnCharge() {
        return this.priseEnCharge;
    }

    public Estimation priseEnCharge(Boolean priseEnCharge) {
        this.setPriseEnCharge(priseEnCharge);
        return this;
    }

    public void setPriseEnCharge(Boolean priseEnCharge) {
        this.priseEnCharge = priseEnCharge;
    }

    public TypeEntite getType() {
        return this.type;
    }

    public Estimation type(TypeEntite type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeEntite type) {
        this.type = type;
    }

    public Activite getActivite() {
        return this.activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Estimation activite(Activite activite) {
        this.setActivite(activite);
        return this;
    }

    public Projet getProjet() {
        return this.projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Estimation projet(Projet projet) {
        this.setProjet(projet);
        return this;
    }

    public Livrable getLivrable() {
        return this.livrable;
    }

    public void setLivrable(Livrable livrable) {
        this.livrable = livrable;
    }

    public Estimation livrable(Livrable livrable) {
        this.setLivrable(livrable);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estimation)) {
            return false;
        }
        return id != null && id.equals(((Estimation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Estimation{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", valeurJour=" + getValeurJour() +
            ", valeurHeure=" + getValeurHeure() +
            ", priseEnCharge='" + getPriseEnCharge() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
