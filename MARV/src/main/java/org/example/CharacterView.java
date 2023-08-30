package org.example;

public class CharacterView {
    public void displayCharacter(CharacterModel character) {
        System.out.println("Nombre del personaje: " + character.getName());
        System.out.println("Descripción del personaje: " + character.getDescription());
        System.out.println("URL de la imagen del personaje: " + character.getThumbnailURL());
        System.out.println("Comics en los que participa: ");
        for (String comicTitle : character.getComicsTitle()) {
            System.out.println("- " + comicTitle);
        }
    }

    public void displayError(int responseCode) {
        System.out.println("Error al obtener los datos del personaje. Código de respuesta: " + responseCode);
    }
}
