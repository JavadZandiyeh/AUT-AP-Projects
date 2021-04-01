package Network;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    //identity details of user
    private String username;
    private String password;
    private boolean rememberOfPass;


    //setting details of user
    private long timeOfPlayingGame;
    private int numberOfWin_ComputerGame;
    private int numberOfLoss_ComputerGame;
    private int numberOfWin_ServerGame;
    private int numberOfLoss_ServerGame;
    private int healthOfTanks;
    private int powerOfBalls;
    private int healthOfWalls;
    private int speedOfBalls;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.rememberOfPass = false;
        timeOfPlayingGame = 0L;
        numberOfWin_ComputerGame = 0;
        numberOfLoss_ComputerGame = 0;
        numberOfWin_ServerGame = 0;
        numberOfLoss_ServerGame = 0;
        healthOfTanks = 1000;
        powerOfBalls = 1000;
        healthOfWalls = 1000;
        speedOfBalls = 1000;
    }
    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberOfPass() {
        return rememberOfPass;
    }

    public long getTimeOfPlayingGame() {
        return timeOfPlayingGame;
    }

    public int getNumberOfLoss_ComputerGame() {
        return numberOfLoss_ComputerGame;
    }

    public int getNumberOfLoss_ServerGame() {
        return numberOfLoss_ServerGame;
    }

    public int getNumberOfWin_ComputerGame() {
        return numberOfWin_ComputerGame;
    }

    public int getNumberOfWin_ServerGame() {
        return numberOfWin_ServerGame;
    }

    public int getHealthOfTanks() {
        return healthOfTanks;
    }

    public int getHealthOfWalls() {
        return healthOfWalls;
    }

    public int getPowerOfBalls() {
        return powerOfBalls;
    }

    public int getSpeedOfBalls() {
        return speedOfBalls;
    }

    //setters
    public void addTimeOfPlayingGame(long time) {
        this.timeOfPlayingGame += time;
    }

    public void setRememberOfPass(boolean rememberOfPass) {
        this.rememberOfPass = rememberOfPass;
    }

    public void addNumberOfLoss_ComputerGame(int number) {
        this.numberOfLoss_ComputerGame += number;
    }

    public void addNumberOfLoss_ServerGame(int number) {
        this.numberOfLoss_ServerGame += number;
    }

    public void addNumberOfWin_ComputerGame(int number) {
        this.numberOfWin_ComputerGame += number;
    }

    public void addNumberOfWin_ServerGame(int number) {
        this.numberOfWin_ServerGame += number;
    }

    public void setHealthOfWalls(int healthOfWalls) {
        this.healthOfWalls = healthOfWalls;
    }

    public void setHealthOfTanks(int healthOfTanks) {
        this.healthOfTanks = healthOfTanks;
    }

    public void setPowerOfBalls(int powerOfBalls) {
        this.powerOfBalls = powerOfBalls;
    }

    public void setSpeedOfBalls(int speedOfBalls) {
        this.speedOfBalls = speedOfBalls;
    }
}
