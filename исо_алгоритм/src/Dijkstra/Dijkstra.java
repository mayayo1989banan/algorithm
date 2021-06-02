package Dijkstra;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class Pair<L,R> implements Comparable<Pair> {
    private L l;
    private R r;
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }

    public Pair() {
    }

    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }

    @Override
    public int compareTo(Pair o) {
        return 0;
    }
}

public class Dijkstra {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int N;
        ArrayList<String> vertices = new ArrayList<String>();
        int M;
        Scanner scanner = new Scanner(new File("file.txt"));
        N = scanner.nextInt();
        for(int i=0; i<N; i++)
        {
            vertices.add(scanner.next());
        }
        M =scanner.nextInt();
        ArrayList<Pair<String,String>> ribs = new ArrayList<Pair<String,String>>();
        ArrayList<Pair<String,String>> ribs1 = new ArrayList<Pair<String,String>>();


        ArrayList<Integer> weight = new ArrayList<Integer>();
        ArrayList<Integer> weight1 = new ArrayList<Integer>();

        /*int weight[];
        weight = new int[M];*/
        for(int i=0; i<M; i++)
        {
            String S1 = scanner.next();
            String S2 = scanner.next();
            Pair p0 = new Pair(S1,S2);
            ribs.add(p0); ribs1.add(p0);
            int k =scanner.nextInt();
            Integer k1 = new Integer(k);
            weight.add(k1); weight1.add(k1);
        }
        scanner.close();



        System.out.print("Vertices of the graph: ");
        for(int i=0; i<N; i++)
        {
            System.out.print(vertices.get(i)+" ");
        }
        System.out.println("\nRibs of the graph: ");
        for(int i=1; i<=M; i++) {
            System.out.println("Rib "+i+": from "+ribs.get(i-1).getL()+" to "+ribs.get(i-1).getR()+", weight "+weight.get(i-1));
        }
        int dist[];
        dist = new int[N];

        dist[0]=0;
        int inf = 100000000;
        for(int i=1; i<N; i++)
        {
            dist[i]=inf;
        }
        PriorityQueue<Pair<Integer,String>> q = new PriorityQueue<>();
        Pair s = new Pair(0,"s");
        q.add(s);

        while(q.peek() != null) {
            Pair v;
            v = q.poll();
            String v0 = (String)v.getR();
            for (int k = 0; k < ribs.size(); k++){
                if (v0.equals(ribs.get(k).getL())){

                if(dist[vertices.indexOf(v0)]+weight.get(k) < dist[vertices.indexOf(ribs.get(k).getR())]){

                    Integer p0 = new Integer(dist[vertices.indexOf(ribs.get(k).getR())]);

                    Pair p = new Pair(p0,ribs.get(k).getR());
                    q.remove(p);
                    dist[vertices.indexOf(ribs.get(k).getR())]=dist[vertices.indexOf(v0)]+weight.get(k);
                    Integer p1 = new Integer(dist[vertices.indexOf(ribs.get(k).getR())]);
                    p.setL(p1);
                    q.add(p);
                    ribs1.remove(k);
                    weight1.remove(k);

                }
                else continue;

                }

               else continue;
            }
            ribs = ribs1;
            weight = weight1;
            System.out.println(ribs.size());
        }
        System.out.println("Vertices of the graph with distances from s : ");
        for(int i=0; i<N; i++)
        {
            System.out.println(vertices.get(i)+" - distance "+dist[i]);
        }
        in.close();
    }
}
