package com.example.demo.entity;


import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FactureTest {

    @Test
    public void getTotal_factureVideSansArticleRetourneZero(){
        //Given
        Facture facture = new Facture();
        facture.setLigneFactures(new HashSet<>());

        //When
        Double result = facture.getTotal();

        //Then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void getTotal_factureAvecUnArticleRetournePrixUnitaireDeLArticle(){
        //Given
        Facture facture = new Facture();

        Article article = new Article();
        article.setPrix(100);

        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setArticle(article);
        ligneFacture.setQuantite(1);

        Set<LigneFacture> lignes = new HashSet<LigneFacture>();
        lignes.add(ligneFacture);

        facture.setLigneFactures(lignes);

        //When
        Double result = facture.getTotal();

        //Then
        Assertions.assertThat(result).isEqualTo(article.getPrix());
    }

    @Test
    public void getTotal_factureAvecDeuxFoisLeMemeArticleRetournePrixUnitaireFoisDeux(){
        //Given
        Facture facture = new Facture();

        Article article = new Article();
        article.setPrix(100);

        LigneFacture ligneFacture = new LigneFacture();
        ligneFacture.setArticle(article);
        ligneFacture.setQuantite(2);

        Set<LigneFacture> lignes = new HashSet<LigneFacture>();
        lignes.add(ligneFacture);

        facture.setLigneFactures(lignes);

        //When
        Double result = facture.getTotal();

        //Then
        Assertions.assertThat(result).isEqualTo(article.getPrix()*2);
    }
}
