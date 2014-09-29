package br.com.pinngo.model;

import java.util.ArrayList;

import br.com.pinngo.enumerators.TipoIconPesquisa;

public class Movie {
    private String title;
    private TipoIconPesquisa icon;
    private int year;
    private double rating;
    private ArrayList<String> genre;

    public Movie() {
    }

    public Movie(String name, TipoIconPesquisa icon, int year, double rating, ArrayList<String> genre) {
        this.title = name;
        this.icon = icon;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public TipoIconPesquisa getIcon() {
        return icon;
    }

    public void setIcon(TipoIconPesquisa icon) {
        this.icon = icon;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }
}
