package tn.itdevspace.easytask.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.itdevspace.easytask.domain.enumeration.Etat;
import tn.itdevspace.easytask.domain.enumeration.Type;

/**
 * A Projet.
 */
@Entity
@Table(name = "projet")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "ref_projet", length = 20, nullable = false, unique = true)
    private String refProjet;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "datedebut", nullable = false)
    private LocalDate datedebut;

    @Column(name = "datefin")
    private LocalDate datefin;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @OneToMany(mappedBy = "projet")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "activites", "estimations", "projet" }, allowSetters = true)
    private Set<Livrable> livrables = new HashSet<>();

    @OneToMany(mappedBy = "projet")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "activite", "projet", "livrable" }, allowSetters = true)
    private Set<Estimation> estimations = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "projets" }, allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Projet id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefProjet() {
        return this.refProjet;
    }

    public Projet refProjet(String refProjet) {
        this.setRefProjet(refProjet);
        return this;
    }

    public void setRefProjet(String refProjet) {
        this.refProjet = refProjet;
    }

    public Type getType() {
        return this.type;
    }

    public Projet type(Type type) {
        this.setType(type);
        return this;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public Projet description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatedebut() {
        return this.datedebut;
    }

    public Projet datedebut(LocalDate datedebut) {
        this.setDatedebut(datedebut);
        return this;
    }

    public void setDatedebut(LocalDate datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDate getDatefin() {
        return this.datefin;
    }

    public Projet datefin(LocalDate datefin) {
        this.setDatefin(datefin);
        return this;
    }

    public void setDatefin(LocalDate datefin) {
        this.datefin = datefin;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public Projet etat(Etat etat) {
        this.setEtat(etat);
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Set<Livrable> getLivrables() {
        return this.livrables;
    }

    public void setLivrables(Set<Livrable> livrables) {
        if (this.livrables != null) {
            this.livrables.forEach(i -> i.setProjet(null));
        }
        if (livrables != null) {
            livrables.forEach(i -> i.setProjet(this));
        }
        this.livrables = livrables;
    }

    public Projet livrables(Set<Livrable> livrables) {
        this.setLivrables(livrables);
        return this;
    }

    public Projet addLivrable(Livrable livrable) {
        this.livrables.add(livrable);
        livrable.setProjet(this);
        return this;
    }

    public Projet removeLivrable(Livrable livrable) {
        this.livrables.remove(livrable);
        livrable.setProjet(null);
        return this;
    }

    public Set<Estimation> getEstimations() {
        return this.estimations;
    }

    public void setEstimations(Set<Estimation> estimations) {
        if (this.estimations != null) {
            this.estimations.forEach(i -> i.setProjet(null));
        }
        if (estimations != null) {
            estimations.forEach(i -> i.setProjet(this));
        }
        this.estimations = estimations;
    }

    public Projet estimations(Set<Estimation> estimations) {
        this.setEstimations(estimations);
        return this;
    }

    public Projet addEstimation(Estimation estimation) {
        this.estimations.add(estimation);
        estimation.setProjet(this);
        return this;
    }

    public Projet removeEstimation(Estimation estimation) {
        this.estimations.remove(estimation);
        estimation.setProjet(null);
        return this;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Projet client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projet)) {
            return false;
        }
        return id != null && id.equals(((Projet) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projet{" +
            "id=" + getId() +
            ", refProjet='" + getRefProjet() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", datedebut='" + getDatedebut() + "'" +
            ", datefin='" + getDatefin() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
