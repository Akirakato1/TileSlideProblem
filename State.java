import java.util.Arrays;

public class State{
    int[] inputs;
    
    public State(int... in){
        this.inputs=in;
    }
    
    public State(State s,int... in){
        this.inputs=mergeArray(in, s.getInputs());
    }
    
    public int getEmptySpot(){
        for(int i=0;i<this.inputs.length;i++){
            if(this.inputs[i]==0){
                return i;
            }
        }
        return -1;
    }
    
    private int[] copyArray(int[] a){
        int[] output=new int[a.length];
        for(int i=0;i<a.length;i++){
            output[i]=a[i];
        }
        return output;
    }
    
    private static int[] mergeArray(int[] a, int[] b){
        int[]c = new int[a.length+b.length];
      int count = 0;
      
      for(int i = 0; i < a.length; i++) { 
         c[i] = a[i];
         count++;
      } 
      for(int j = 0; j < b.length;j++) { 
         c[count++] = b[j];
      } 
        return c;
    }
    
    public int[] getInputs(){
        return copyArray(this.inputs);
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof State)){
            return false;
        }else if(this.inputs.length!=((State)o).inputs.length){
            return false;
        }else{
            for(int i=0;i<this.inputs.length;i++){
                if(this.inputs[i]!=((State)o).inputs[i]){
                    return false;
                }
            }
            return true;
        }
    }
    
    @Override
    public String toString(){
        return Arrays.toString(this.inputs);
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.inputs);
    }
    
}