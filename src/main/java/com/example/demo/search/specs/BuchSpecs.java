package com.example.demo.search.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Buch;
import com.example.demo.model.Buch_;
import com.example.demo.search.criteria.BuchCriteria;

public class BuchSpecs implements Specification<Buch> {
    private static final long serialVersionUID = -3428065339619275877L;

    private BuchCriteria criteria;

    public BuchSpecs(BuchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Buch> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<Predicate>();

        // by titel:
        if (criteria.getTitel() != null) {
            Predicate titelPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.titel)),
                    "%" + criteria.getTitel().toUpperCase() + "%");
            predicates.add(titelPredicate);
        }

        // by autor:
        if (criteria.getAutor() != null) {
            Predicate autorPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.autor)),
                    "%" + criteria.getAutor().toUpperCase() + "%");
            predicates.add(autorPredicate);
        }

        // by datum:
        if (criteria.getDatum() != null) {
            Predicate datumPredicate = criteriaBuilder.equal(root.get(Buch_.datum), criteria.getDatum());
            predicates.add(datumPredicate);
        }

        // by isbn13:
        if (criteria.getIsbn13() != 0) {
            Predicate isbn13Predicate = criteriaBuilder.equal(root.get(Buch_.isbn13), criteria.getIsbn13());
            predicates.add(isbn13Predicate);
        }

        // by isbn10:
        if (criteria.getIsbn10() != 0) {
            Predicate isbn10Predicate = criteriaBuilder.equal(root.get(Buch_.isbn10), criteria.getIsbn10());
            predicates.add(isbn10Predicate);
        }

        // by medienart:
        if (criteria.getMedienart() != null) {
            Predicate medienartPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.medienart)),
                    "%" + criteria.getMedienart().toUpperCase() + "%");
            predicates.add(medienartPredicate);
        }

        // by verlag:
        if (criteria.getVerlag() != null) {
            Predicate verlagPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.verlag)),
                    "%" + criteria.getVerlag().toUpperCase() + "%");
            predicates.add(verlagPredicate);
        }

        // by erscheinungsjahr:
        if (criteria.getErscheinungsjahr() != 0) {
            Predicate erscheinungsjahrPredicate = criteriaBuilder.equal(root.get(Buch_.erscheinungsjahr),
                    criteria.getErscheinungsjahr());
            predicates.add(erscheinungsjahrPredicate);
        }

        // by ausgabe:
        if (criteria.getAusgabe() != null) {
            Predicate ausgabePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.ausgabe)),
                    "%" + criteria.getAusgabe().toUpperCase() + "%");
            predicates.add(ausgabePredicate);
        }

        // by uebersicht:
        if (criteria.getUebersicht() != null) {
            Predicate uebersichtPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.uebersicht)),
                    "%" + criteria.getUebersicht().toUpperCase() + "%");
            predicates.add(uebersichtPredicate);
        }

        // by kategorie:
        if (criteria.getKategorie() != null) {
            Predicate kategoriePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.kategorie)),
                    "%" + criteria.getKategorie().toUpperCase() + "%");
            predicates.add(kategoriePredicate);
        }

        // by zustand:
        if (criteria.getZustand() != null) {
            Predicate zustandPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.zustand)),
                    "%" + criteria.getZustand().toUpperCase() + "%");
            predicates.add(zustandPredicate);
        }

        // by anmerkungen:
        if (criteria.getAnmerkungen() != null) {
            Predicate anmerkungenPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.anmerkungen)),
                    "%" + criteria.getAnmerkungen().toUpperCase() + "%");
            predicates.add(anmerkungenPredicate);
        }

        // by anmerkungen:
        if (criteria.getSprache() != null) {
            Predicate sprachePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.sprache)),
                    "%" + criteria.getSprache().toUpperCase() + "%");
            predicates.add(sprachePredicate);
        }

        // by signatur:
        if (criteria.getSignatur() != null) {
            Predicate signaturPredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Buch_.signatur)),
                    "%" + criteria.getSignatur().toUpperCase() + "%");
            predicates.add(signaturPredicate);
        }

        criteriaQuery.distinct(true);
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
