public class ComputerPlayer extends Player{

    public ComputerPlayer(Home home, String color){
        super(home, color);
    }


    /**
     * this part is for single play that find the best place
     * for putting its bead
     * @return the string that is x,y of the place that computer wants to
     * put its bead on it
     */
    public String put(){
        int[][] arr = new int[8][8];


        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){

                for(Bead bead: beads){
                    for(String position: bead.getPositions().keySet()){
                        if( (i == Integer.parseInt("" + position.charAt(0))) && (j == Integer.parseInt(("" + position.charAt(2)))) ) {
                            arr[i][j] += bead.getPositions().get(position);
                        }
                    }
                }

            }
        }


        int max = arr[0][0];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(arr[i][j] > max){
                    max = arr[i][j];
                }
            }
        }


        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(arr[i][j] == max){
                    return (i+ "," + j);
                }
            }
        }

        return null;
    }

}