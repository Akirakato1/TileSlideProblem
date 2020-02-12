import java.util.Arrays;

public class DpSolution{
    
    //array M that stores the number of moves to that the state [i][j][k][l][m][n] 
    //intialised to Integer.MAX_VALUE
    public static int[][][][][][] M=new int[6][6][6][6][6][6];
    
    public static void main(String[] args){
    //***testing***
    
    //board looks like [0][1]
    //                 [2][3]
    //                 [4][5]   
        int[] input={0,1,2,3,4,5};
    //board looks like [5][3]
    //                 [2][0]
    //                 [1][4]  
        int[] output={5,3,2,0,1,4};
        
        System.out.println("input: "+Arrays.toString(input)+"\noutput: "+Arrays.toString(output)+"\nresult: "+find(input,output));
    }
    
    
    public static int find(int[] input, int[] output){
        
        //initialise content to be Integer.MAX_VALUE for each element
        initM();
        
        //board position of input will be 0 move away
        M[input[0]][input[1]][input[2]][input[3]][input[4]][input[5]]=0;
        
        //find which index of input array holds value 0 (empty) 
        //convenience function
        int emptySpot=findEmptySpot(input);
        
        //start finding the number of moves by going up,down,left,right
        //direction 0=up, 1=right, 2=down, 3=left
        dp(input,0,emptySpot);
        dp(input,1,emptySpot);
        dp(input,2,emptySpot);
        dp(input,3,emptySpot);
        
        //finish calculating and the number of move to any of the possible board states
        //are now stored in M
        if(accessM(output)!=Integer.MAX_VALUE){
            return accessM(output);
        }
        
        //if it's still Integer.MAX_VALUE, it's not able to reach the board state
        return -1;
    }
    
    public static void dp(int[] currentState, int direction, int emptySpot){
        
        //any of these combination of direction and emptyspot are invalid
        //for example trying to go up from index 0
        if((emptySpot%2==0&&direction==3)||(emptySpot%2==1&&direction==1)||(emptySpot<2&&direction==0)||(emptySpot>3&&direction==2)){
            return;
        }
        
        //the next state after the move in given direction
        int[] nextState = swap(currentState, direction, emptySpot);
        int emptyNextSpot=findEmptySpot(nextState);
        
        //if reach a board state that has been reached in fewer move, 
        //no point in recursing over it
        if(accessM(nextState)<=accessM(currentState)+1){
            return;
        }
        
        //else set the nextState move to current+1
        M[nextState[0]][nextState[1]][nextState[2]][nextState[3]][nextState[4]][nextState[5]]=accessM(currentState)+1;
        
        //recurse over the 4 direction just like in find function. 
        dp(nextState,0,emptyNextSpot);
        dp(nextState,1,emptyNextSpot);
        dp(nextState,2,emptyNextSpot);
        dp(nextState,3,emptyNextSpot);
    }
    
    public static int[] swap(int[] position, int direction, int emptySpot){
        int[] output=new int[6];
        for(int i=0;i<6;i++){
            output[i]=position[i];
        }
        if(direction==0){
            output[emptySpot]=output[emptySpot-2];
            output[emptySpot-2]=0;
        }else if(direction==1){
            output[emptySpot]=output[emptySpot+1];
            output[emptySpot+1]=0;
        }else if(direction==2){
            output[emptySpot]=output[emptySpot+2];
            output[emptySpot+2]=0;
        }else{
            output[emptySpot]=output[emptySpot-1];
            output[emptySpot-1]=0;
        }
        return output;
    }
    
    public static int accessM(int[] position){
        return M[position[0]][position[1]][position[2]][position[3]][position[4]][position[5]];
    }
    
    public static int findEmptySpot(int[] position){
        for(int i=0;i<6;i++){
            if(position[i]==0){
                return i;
            }
        }
        return -1;
    }
    
    public static void initM(){
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                for(int k=0;k<6;k++){
                    for(int l=0;l<6;l++){
                        for(int m=0;m<6;m++){
                            for(int n=0;n<6;n++){
                                M[i][j][k][l][m][n]=Integer.MAX_VALUE;
                            }
                        }
                    }
                }
            }
        }
        
    }
     
}
