package com.jck.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC {

    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {

        try {
            messages.add( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );
            messages.add( "Driver chargé!" );
        } catch ( ClassNotFoundException e ) {
            messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                    + e.getMessage() );
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://localhost:3306/bdd_sdzee";
        String utilisateur = "java";
        String motDePasse = "SdZ_eE";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            messages.add( "Connexion à la base de données..." );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages.add( "Connexion réussie !" );

            statement = connexion.createStatement();
            messages.add( "Objet requête crée!" );

            /*
             * Exécution d'une requête d'écriture avec renvoi de l'id
             * auto-généré
             */
            int statut = statement
                    .executeUpdate(
                            "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('jmarc15@mail.fr', MD5('lavieestbelle78'), 'jean-marc', NOW());",
                            Statement.RETURN_GENERATED_KEYS );

            /* Formatage pour affichage dans la JSP finale. */
            messages.add( "Résultat de la requête d'insertion : " + statut + "." );

            /* Récupération de l'id auto-généré par la requête d'insertion. */
            resultat = statement.getGeneratedKeys();
            /*
             * Parcours du ResultSet et formatage pour affichage de la valeur
             * qu'il contient dans la JSP finale.
             */
            while ( resultat.next() ) {
                messages.add( "ID retourné lors de la requête d'insertion :" + resultat.getInt( 1 ) );
            }

            resultat = statement.executeQuery( "SELECT id, email, mot_de_passe, nom FROM Utilisateur;" );
            messages.add( "Requête \"SELECT id, email, mot_de_pass, nom FROM Utilisateur;\" effectué" );

            while ( resultat.next() ) {
                int idUtilisateur = resultat.getInt( "id" );
                String emailUtilisateur = resultat.getString( "email" );
                String motDePasseUtilisateur = resultat.getNString( "mot_de_passe" );
                String nomUtilisateur = resultat.getNString( "nom" );

                messages.add( "Données retournées par la requete: id = " + idUtilisateur + ", email = "
                        + emailUtilisateur +
                        " , mot de passe = " + motDePasseUtilisateur + ", nom =" + nomUtilisateur + "." );

            }
        } catch ( SQLException e ) {
            messages.add( "Erreur lors de la connexion: <br/>" + e.getMessage() );

        } finally {
            messages.add( "Fermeture de l'objet ResultSest." );
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                    // TODO: handle exception
                }
            }
            messages.add( "Fermeture de l'objet Statement" );

            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                    // TODO: handle exception
                }
            }

            messages.add( "Fermeture de l'objet connexion" );

            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                    // TODO: handle exception
                }
            }
        }

        return messages;
    }
}
