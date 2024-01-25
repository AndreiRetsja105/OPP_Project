/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classprojectoop;

/**
 *
 * @author arets
 */
class NewsFeedEntry {
     private String username;
    private String newsFeed;


  public NewsFeedEntry(String username, String newsFeed) {
        this.username = username;
        this.newsFeed = newsFeed;
    }

    public String getUsername() {
        return username;
    }

    public String getNewsFeed() {
        return newsFeed;
    }
}